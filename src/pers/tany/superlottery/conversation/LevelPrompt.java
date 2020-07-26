package pers.tany.superlottery.conversation;

import org.bukkit.conversations.*;
import org.bukkit.entity.*;
import org.bukkit.*;

import java.io.*;
import org.bukkit.plugin.*;

import pers.tany.superlottery.*;

import java.util.*;

public class LevelPrompt extends NumericPrompt
{
    Plugin config = Bukkit.getPluginManager().getPlugin("SuperLottery");
    File file = new File(config.getDataFolder(), "data.yml");
    
    protected Prompt acceptValidatedInput(ConversationContext context, Number input) {
        context.setSessionData((Object)"name", (Object)input.intValue());
        Player player = (Player)context.getForWhom();
        if(input.intValue()<=0) {
        	return this;
        }
        if (player.getTotalExperience() < input.intValue()) {
            return (Prompt)this;
        }
        if(input.intValue() > Other.config.getInt("MaxXp")) {
        	return (Prompt)this;
        }
        Main.info.put(player.getName(), true);
        CommonlyWay.GiveExp(player, -input.intValue());
        List<String> ExpMoney = Other.data.getStringList("ExpMoney");
        ExpMoney.add(String.valueOf(player.getName()) + ":1:" + input.intValue());
        Other.data.set("ExpMoney", (Object)ExpMoney);
        try {
            Other.data.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getPromptText(ConversationContext context) {
        return "��a��������Ҫ��ע�ľ��飬���ܳ���"+Other.config.getInt("MaxXp");
    }
}
