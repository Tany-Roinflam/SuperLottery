package pers.tany.superlottery;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Other {
    public static File file=new File(Main.plugin.getDataFolder(),"config.yml");
    public static File file1=new File(Main.plugin.getDataFolder(),"data.yml");
    public static File file2=new File(Main.plugin.getDataFolder(),"message.yml");
	public static FileConfiguration config=YamlConfiguration.loadConfiguration(file);
	public static FileConfiguration data=YamlConfiguration.loadConfiguration(file1);
	public static FileConfiguration message=YamlConfiguration.loadConfiguration(file2);
}
