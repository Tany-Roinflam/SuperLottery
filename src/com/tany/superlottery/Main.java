package com.tany.superlottery;

import org.bukkit.plugin.java.*;
import java.util.*;
import net.milkbowl.vault.economy.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import java.io.*;

import com.tany.superlottery.commands.*;
import org.bukkit.command.*;
import com.tany.superlottery.events.Event;

import org.bukkit.event.*;
import com.tany.superlottery.task.*;

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
    public boolean vault;
    
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
    
    public Main() {
        this.vault = false;
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public boolean onEconomy() {
  	    RegisteredServiceProvider<Economy> money = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
  	    if ((money != null) && ((Main.economy = (Economy)money.getProvider()) == null)) {
  	    	vault = true;
  	    }
  	    return vault = false;
  	}
    
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§e[§6superlottery§e]§a插件已启用");
        if (!new File(this.getDataFolder(), "config.yml").exists()) {
            this.saveDefaultConfig();
        }
        if (!new File(this.getDataFolder(), "data.yml").exists()) {
            this.saveResource("data.yml", false);
        }
        if (!new File(this.getDataFolder(), "message.yml").exists()) {
            this.saveResource("message.yml", false);
        }
        if (this.getConfig().getBoolean("StartStopBetTime")) {
            new StopBetTask().runTaskTimer((Plugin)this, (long)((this.getConfig().getInt("StopBetTime") + this.getConfig().getInt("BetTime")) * 20), (long)(this.getConfig().getInt("DrawTime") * 20));
        }
        if (this.getConfig().getBoolean("Countdownone")) {
            new Countdownone().runTaskTimer((Plugin)this, (long)((this.getConfig().getInt("DrawTime") - this.getConfig().getInt("CountdownoneTime")) * 20), (long)(this.getConfig().getInt("DrawTime") * 20));
        }
        if (this.getConfig().getBoolean("Countdowntwo")) {
            new Countdowntwo().runTaskTimer((Plugin)this, (long)((this.getConfig().getInt("DrawTime") - this.getConfig().getInt("CountdowntwoTime")) * 20), (long)(this.getConfig().getInt("DrawTime") * 20));
        }
        if (this.getConfig().getBoolean("Countdownthree")) {
            new Countdownthree().runTaskTimer((Plugin)this, (long)((this.getConfig().getInt("DrawTime") - this.getConfig().getInt("CountdownthreeTime")) * 20), (long)(this.getConfig().getInt("DrawTime") * 20));
        }
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getConsoleSender().sendMessage("§e[§6superlottery§e]§c未检测到§6Vault§c插件，游戏币下注功能未启用");
        }
        else {
            Bukkit.getConsoleSender().sendMessage("§e[§6superlottery§e]§6检测到§eVault§6插件，已启用");
            this.vault = this.onEconomy();
        }
        Main.plugin = (Plugin)this;
        this.getCommand("sl").setExecutor((CommandExecutor)new Commands());
        this.getCommand("superlottery").setExecutor((CommandExecutor)new Commands());
        this.getServer().getPluginManager().registerEvents((Listener)new Event(), (Plugin)this);
        new BetTask().runTaskTimer((Plugin)this, (long)(this.getConfig().getInt("BetTime") * 20), (long)(this.getConfig().getInt("DrawTime") * 20));
        new DrawTask().runTaskTimer((Plugin)this, (long)(this.getConfig().getInt("DrawTime") * 20), (long)(this.getConfig().getInt("DrawTime") * 20));
    }
    
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§e[§6superlottery§e]§e插件已卸载");
    }
}
