package com.tany.superlottery.task;

import org.bukkit.scheduler.*;
import com.tany.superlottery.*;
import org.bukkit.*;
import org.bukkit.ChatColor;

public class CommandTask extends BukkitRunnable
{
    public void run() {
        Bukkit.broadcastMessage(String.valueOf(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("CommandTask"))));
        cancel();
    }
}
