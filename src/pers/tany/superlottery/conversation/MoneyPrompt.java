package pers.tany.superlottery.conversation;

import org.bukkit.conversations.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.io.*;
import org.bukkit.plugin.*;

import pers.tany.superlottery.*;

import java.util.*;

public class MoneyPrompt extends NumericPrompt
{
    Plugin config = Bukkit.getPluginManager().getPlugin("SuperLottery");
    File file = new File(config.getDataFolder(), "data.yml");
    protected Prompt acceptValidatedInput(ConversationContext context, Number input) {
        context.setSessionData("name", input.intValue());
        Player player = (Player)context.getForWhom();
        int money = (int)Main.economy.getBalance(player);
        if(input.intValue()<=0) {
        	return this;
        }
        if (money < input.intValue()) {
            return (Prompt)this;
        }
        if(input.intValue() > Other.config.getInt("MaxMoney")) {
        	return (Prompt)this;
        }
        Main.info.put(player.getName(), true);
        Main.economy.withdrawPlayer(player, (double)input.intValue());
        List<String> ExpMoney = (List<String>)Other.data.getStringList("ExpMoney");
        ExpMoney.add(String.valueOf(player.getName()) + ":2:" + input.intValue());
        Other.data.set("ExpMoney", ExpMoney);
        try {
            Other.data.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getPromptText(ConversationContext context) {
        return "§e请输入你要下注的§6游戏币，不能超过"+Other.config.getInt("MaxMoney");
    }
}
