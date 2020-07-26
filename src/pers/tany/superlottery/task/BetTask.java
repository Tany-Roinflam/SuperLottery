package pers.tany.superlottery.task;

import org.bukkit.scheduler.*;

import pers.tany.superlottery.*;

import org.bukkit.*;
import org.bukkit.ChatColor;

public class BetTask extends BukkitRunnable
{
    public void run() {
        Main.i = 1;
        if (Other.config.getBoolean("StartStopBetTime")) {
            Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("BetTaskOneMessage"))));
            Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("BetTaskTwoMessage"))));
            Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("BetTaskThreeMessage").replace("[time]", (Other.config.getInt("DrawTime") - Other.config.getInt("BetTime"))+""))));
            Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("BetTaskFourMessage").replace("[time]", Other.config.getInt("StopBetTime")+""))));
        }
        else {
            Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("BetTaskOneMessage"))));
            Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("BetTaskTwoMessage"))));
            Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("BetTaskThreeMessage").replace("[time]", (Other.config.getInt("DrawTime") - Other.config.getInt("BetTime"))+""))));
        }
    }
}
