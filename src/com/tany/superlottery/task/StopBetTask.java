package com.tany.superlottery.task;

import org.bukkit.scheduler.*;
import com.tany.superlottery.*;
import org.bukkit.*;

public class StopBetTask extends BukkitRunnable
{
    public void run() {
        Main.i = 2;
        Bukkit.getServer().broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer"))) + "§a已经停止下注");
        Bukkit.getServer().broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")))) + "§a距离开奖还有§6" + (Other.config.getInt("DrawTime") - Other.config.getInt("BetTime") - Other.config.getInt("StopBetTime")) + "§a秒");
    }
}
