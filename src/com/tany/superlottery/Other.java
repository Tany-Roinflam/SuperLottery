package com.tany.superlottery;

import org.bukkit.plugin.*;
import java.io.*;
import org.bukkit.*;
import org.bukkit.configuration.file.*;

public class Other
{
    static Plugin configs;
    static File file;
    static File file1;
    static File file2;
    public static FileConfiguration config;
    public static FileConfiguration data;
    public static FileConfiguration message;
    
    static {
        Other.configs = Bukkit.getPluginManager().getPlugin("SuperLottery");
        Other.file = new File(Other.configs.getDataFolder(), "config.yml");
        Other.file1 = new File(Other.configs.getDataFolder(), "data.yml");
        Other.file2 = new File(Other.configs.getDataFolder(), "message.yml");
        Other.config = (FileConfiguration)YamlConfiguration.loadConfiguration(Other.file);
        Other.data = (FileConfiguration)YamlConfiguration.loadConfiguration(Other.file1);
        Other.message = (FileConfiguration)YamlConfiguration.loadConfiguration(Other.file2);
    }
}
