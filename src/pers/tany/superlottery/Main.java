package pers.tany.superlottery;

import org.bukkit.plugin.java.*;
import java.util.*;
import net.milkbowl.vault.economy.*;
import pers.tany.superlottery.commands.*;
import pers.tany.superlottery.events.Event;
import pers.tany.superlottery.task.*;

import org.bukkit.*;
import org.bukkit.plugin.*;
import java.io.*;

import org.bukkit.command.*;
import org.bukkit.event.*;

public class Main extends JavaPlugin
{
    private static Main instance;
    public static Plugin plugin;
    public static int i;
    public static int cb;
    public static int setrandom;
    public static boolean count;
    public static HashMap<String, Integer> lottery;
    public static HashMap<String, Integer> number;
    public static HashMap<String, Integer> item;
    public static HashMap<String, Boolean> data;
    public static HashMap<String, Boolean> info;
    public static HashMap<String, Integer> itemsizes;
    public static Economy economy;
    
    static {
        Main.i = 0;
        Main.cb = 0;
        Main.setrandom = 0;
        Main.count = true;
        Main.lottery = new HashMap<String, Integer>();
        Main.number = new HashMap<String, Integer>();
        Main.item = new HashMap<String, Integer>();
        Main.data = new HashMap<String, Boolean>();
        Main.info = new HashMap<String, Boolean>();
        Main.itemsizes = new HashMap<String, Integer>();
        Main.economy = null;
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public void Vault() {
  	    RegisteredServiceProvider<Economy> money = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
  	    Main.economy = money.getProvider();
  	}
    
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§e[§6superlottery§e]§a插件已启用");
        Main.plugin = this;
        if (!new File(getDataFolder(), "config.yml").exists()) {
        	saveResource("config.yml", false);
        }
        if (!new File(getDataFolder(), "data.yml").exists()) {
            saveResource("data.yml", false);
        }
        if (!new File(getDataFolder(), "message.yml").exists()) {
            saveResource("message.yml", false);
        }
        if (Other.config.getBoolean("StartStopBetTime")) {
            new StopBetTask().runTaskTimer(this, (long)((Other.config.getInt("StopBetTime") + Other.config.getInt("BetTime")) * 20), (long)(Other.config.getInt("DrawTime") * 20));
        }
        if (Other.config.getBoolean("Countdownone")) {
            new Countdownone().runTaskTimer(this, (long)((Other.config.getInt("DrawTime") - Other.config.getInt("CountdownoneTime")) * 20), (long)(Other.config.getInt("DrawTime") * 20));
        }
        if (Other.config.getBoolean("Countdowntwo")) {
            new Countdowntwo().runTaskTimer(this, (long)((Other.config.getInt("DrawTime") - Other.config.getInt("CountdowntwoTime")) * 20), (long)(Other.config.getInt("DrawTime") * 20));
        }
        if (Other.config.getBoolean("Countdownthree")) {
            new Countdownthree().runTaskTimer(this, (long)((Other.config.getInt("DrawTime") - Other.config.getInt("CountdownthreeTime")) * 20), (long)(Other.config.getInt("DrawTime") * 20));
        }
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getConsoleSender().sendMessage("§e[§6superlottery§e]§c未检测到§6Vault§c插件，游戏币下注功能未启用");
        } else {
            Bukkit.getConsoleSender().sendMessage("§e[§6superlottery§e]§6检测到§eVault§6插件，已启用");
            Vault();
        }
        new BasicLibrary();
        getCommand("sl").setExecutor((CommandExecutor)new Commands());
        getServer().getPluginManager().registerEvents((Listener)new Event(), this);
        new BetTask().runTaskTimer(this, (long)(Other.config.getInt("BetTime") * 20), (long)(Other.config.getInt("DrawTime") * 20));
        new DrawTask().runTaskTimer(this, (long)(Other.config.getInt("DrawTime") * 20), (long)(Other.config.getInt("DrawTime") * 20));
    }
    
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§e[§6superlottery§e]§e插件已卸载");
    }
}
