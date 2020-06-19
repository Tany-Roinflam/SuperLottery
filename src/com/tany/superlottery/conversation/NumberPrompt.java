package com.tany.superlottery.conversation;

import org.bukkit.conversations.*;
import org.bukkit.entity.*;
import com.tany.superlottery.*;
import com.tany.superlottery.gui.*;

public class NumberPrompt extends NumericPrompt
{
    protected Prompt acceptValidatedInput(ConversationContext context, Number input) {
        context.setSessionData((Object)"name", (Object)input.intValue());
        Player player = (Player)context.getForWhom();
        if (input.intValue() >= 1 && input.intValue() <= 99) {
            Main.lottery.put(player.getName(), 5);
            Main.number.put(player.getName(), input.intValue());
            Gui.gui(player);
            return null;
        }
        return (Prompt)this;
    }
    
    public String getPromptText(ConversationContext context) {
        return "§a请输入一个1-99的数字";
    }
}
