package com.tany.superlottery.conversation;

import org.bukkit.conversations.*;
import org.bukkit.entity.*;
import com.tany.superlottery.*;
import org.bukkit.*;
import java.io.*;
import org.bukkit.plugin.*;
import java.util.*;

public class MoneyPrompt extends NumericPrompt
{
    Plugin config = Bukkit.getPluginManager().getPlugin("SuperLottery");
    File file = new File(config.getDataFolder(), "data.yml");
    protected Prompt acceptValidatedInput(ConversationContext context, Number input) {
        context.setSessionData("name", input.intValue());
        Player player = (Player)context.getForWhom();
        int money = (int)Main.economy.getBalance(player);
        if (money < input.intValue()) {
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
        return "§e请输入你要下注的§6游戏币";
    }
}
