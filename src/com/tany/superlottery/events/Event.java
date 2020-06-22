package com.tany.superlottery.events;

import org.bukkit.plugin.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.conversations.*;
import com.tany.superlottery.conversation.*;
import com.tany.superlottery.*;
import java.io.*;
import org.bukkit.*;
import org.bukkit.ChatColor;

import com.tany.superlottery.gui.*;
import org.bukkit.inventory.*;
import java.util.*;
import org.bukkit.event.*;

public class Event implements Listener
{
    Plugin config;
    File file;
    
    public Event() {
        config = Bukkit.getPluginManager().getPlugin("SuperLottery");
        file = new File(config.getDataFolder(), "data.yml");
    }
    
    @EventHandler
    public void InventoryClick(InventoryClickEvent a) {
        Inventory inventory = a.getClickedInventory();
        if (inventory == null) {
            return;
        }
        Player p = (Player)a.getWhoClicked();
        if (!(a.getWhoClicked() instanceof Player)) {
            return;
        }
        if (a.getClickedInventory().getTitle().startsWith("§e下§a注§c界§b面") && (a.getSlot() <= 8 || a.getSlot() >= 36 || a.getSlot() == 9 || a.getSlot() == 18 || a.getSlot() == 27 || a.getSlot() == 17 || a.getSlot() == 26 || a.getSlot() == 35)) {
            a.setCancelled(true);
            if (a.getSlot() == 45) {
                Main.lottery.put(p.getName(), 1);
                p.sendMessage("§a成功下注范围§6：§f单");
            }
            if (a.getSlot() == 46) {
                Main.lottery.put(p.getName(), 2);
                p.sendMessage("§a成功下注范围§6：§e双");
            }
            if (a.getSlot() == 47) {
                Main.lottery.put(p.getName(), 3);
                p.sendMessage("§a成功下注范围§6：§b小");
            }
            if (a.getSlot() == 48) {
                Main.lottery.put(p.getName(), 4);
                p.sendMessage("§a成功下注范围§6：§3大");
            }
            if (a.getSlot() == 49) {
                for (int i = 0; i <= 6; ++i) {
                    if (a.getClickedInventory().getItem(10 + i) != null) {
                        p.sendMessage("§c请先清空你的下注栏");
                        return;
                    }
                    if (a.getClickedInventory().getItem(19 + i) != null) {
                        p.sendMessage("§c请先清空你的下注栏");
                        return;
                    }
                    if (a.getClickedInventory().getItem(28 + i) != null) {
                        p.sendMessage("§c请先清空你的下注栏");
                        return;
                    }
                    if (i == 6) {
                        p.closeInventory();
                        p.sendMessage("§a提示：你输入的数字决定了你下注的号码");
                        Conversation conversation = new Conversation((Plugin)Main.getInstance(), (Conversable)p, (Prompt)new NumberPrompt());
                        p.beginConversation(conversation);
                        return;
                    }
                }
            }
            if (a.getSlot() == 51) {
                if (!Main.lottery.containsKey(p.getName())) {
                    p.sendMessage("§c请先选择你下注的号码范围！");
                    return;
                }
                if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
                    p.sendMessage("§e无法搜索插件前置：§6Vault");
                    return;
                }
                if(!Other.config.getBoolean("MoneyBet")) {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NoMoneyBet")));
                	return;
                }
                for (int i = 0; i <= 6; ++i) {
                    if (a.getClickedInventory().getItem(10 + i) != null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NotItem")));
                        return;
                    }
                    if (a.getClickedInventory().getItem(19 + i) != null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NotItem")));
                        return;
                    }
                    if (a.getClickedInventory().getItem(28 + i) != null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NotItem")));
                        return;
                    }
                }
                int money = (int)Main.economy.getBalance((OfflinePlayer)p);
                p.sendMessage("§e你的游戏币为§6" + money);
                if (money <= 0) {
                    p.sendMessage("§e你没有游戏币下不了注");
                    return;
                }
                Conversation moneyconversation = new Conversation((Plugin)Main.getInstance(), (Conversable)p, (Prompt)new MoneyPrompt());
                p.beginConversation(moneyconversation);
                p.sendMessage("§6请输入一个小于或等于你§e游戏币§6的数字");
                p.closeInventory();
            }
            if (a.getSlot() == 52) {
                if (!Main.lottery.containsKey(p.getName())) {
                    p.sendMessage("§c请先选择你下注的号码范围！");
                    return;
                }
                if(!Other.config.getBoolean("XpBet")) {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NoXpBet")));
                	return;
                }
                for (int i = 0; i <= 6; ++i) {
                    if (a.getClickedInventory().getItem(10 + i) != null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NotItem")));
                        return;
                    }
                    if (a.getClickedInventory().getItem(19 + i) != null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NotItem")));
                        return;
                    }
                    if (a.getClickedInventory().getItem(28 + i) != null) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NotItem")));
                        return;
                    }
                }
                p.sendMessage("§d你的经验有§5" + p.getTotalExperience() + "§d点");
                if (p.getTotalExperience() == 0) {
                    p.sendMessage("§b你没有经验下不了注");
                    return;
                }
                p.sendMessage("§a请输入一个小于或等于你经验的数字");
                Conversation xpconversation = new Conversation((Plugin)Main.getInstance(), (Conversable)p, (Prompt)new LevelPrompt());
                p.beginConversation(xpconversation);
                p.closeInventory();
            }
            if (a.getSlot() == 53) {
                if (!Main.lottery.containsKey(p.getName())) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NotBetMessage")));
                    return;
                }
                if(!Other.config.getBoolean("ItemBet")) {
                	p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("NoItemBet")));
                	return;
                }
                if (Other.data.getStringList("Item").size() == 0) {
                    p.sendMessage("§c当前没有任何物品可下注");
                    return;
                }
                int grids = 0;
                int values = 0;
                int s = 0;
                int nolore = 0;
                List<String> iteminfo = (List<String>)Other.data.getStringList("Item");
                while (grids <= 6) {
                    String checksitem = null;
                    String checkssitem = null;
                    String checksssitem = null;
                    ItemStack checks = a.getInventory().getItem(10 + grids);
                    ItemStack checkss = a.getInventory().getItem(19 + grids);
                    ItemStack checksss = a.getInventory().getItem(28 + grids);
                    if (checks != null) {
                        checksitem = Way.GetItemData(checks);
                    }
                    if (checkss != null) {
                        checkssitem = Way.GetItemData(checkss);
                    }
                    if (checksss != null) {
                        checksssitem = Way.GetItemData(checksss);
                    }
                    if (checks != null) {
                        int number = 0;
                        for (String itemdata : iteminfo) {
                            if (itemdata.equals(checksitem)) {
                                number = 0;
                                break;
                            }
                            if (++number == iteminfo.size()) {
                                ++values;
                                number = 0;
                                break;
                            }
                        }
                        if (checks.getItemMeta().hasLore() && checks.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', Other.config.getString("SetLore")))) {
                            ++nolore;
                        }
                    }
                    else {
                        ++s;
                    }
                    if (checkss != null) {
                        int number = 0;
                        for (String itemdata : iteminfo) {
                            if (itemdata.equals(checkssitem)) {
                                number = 0;
                                break;
                            }
                            if (++number == iteminfo.size()) {
                                ++values;
                                number = 0;
                                break;
                            }
                        }
                        if (checkss.getItemMeta().hasLore() && checks.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', Other.config.getString("SetLore")))) {
                            ++nolore;
                        }
                    }
                    else {
                        ++s;
                    }
                    if (checksss != null) {
                        int number = 0;
                        for (String itemdata : iteminfo) {
                            if (itemdata.equals(checksssitem)) {
                                number = 0;
                                break;
                            }
                            if (++number == iteminfo.size()) {
                                ++values;
                                number = 0;
                                break;
                            }
                        }
                        if (checksss.getItemMeta().hasLore() && checks.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', Other.config.getString("SetLore")))) {
                            ++nolore;
                        }
                    }
                    else {
                        ++s;
                    }
                    ++grids;
                }
                if (s == 21) {
                    p.sendMessage("§c你下注的时候不放物品的吗？");
                    s = 0;
                    return;
                }
                if (values > 0) {
                    p.sendMessage("§c下注的内容有" + values + "格为不可下注物品");
                    values = 0;
                    return;
                }
                if (nolore > 0) {
                    p.sendMessage("§c下注的内容有" + nolore + "格为之前下注过的注物品");
                    nolore = 0;
                    return;
                }
                List<String> shui = (List<String>)Other.data.getStringList("Player." + p.getName());
                shui.clear();
                Other.data.set("Player." + p.getName(), shui);
                try {
                    Other.data.save(file);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                for (int from = 10; from <= 34; ++from) {
                    if (from != 17 && from != 18 && from != 26 && from != 27) {
                        ItemStack check = a.getInventory().getItem(from);
                        if (check != null) {
                            String itemID = Way.GetItemData(check);
                            List<String> configItems = (List<String>)Other.data.getStringList("Item");
                            for (String listItem : configItems) {
                                if (!listItem.equals(itemID)) {
                                    continue;
                                }
                                List<String> list = (List<String>)Other.data.getStringList("Player." + p.getName());
                                int configItemAmount = 0;
                                for (String itemString : new ArrayList<String>(list)) {
                                    if (itemString.startsWith(itemID)) {
                                        configItemAmount = Integer.parseInt(itemString.split(":")[1]);
                                        list.remove(itemString);
                                        break;
                                    }
                                }
                                configItemAmount += check.getAmount();
                                String addString = itemID + ":" + configItemAmount;
                                list.add(addString);
                                Other.data.set("Player." + p.getName(), list);
                            }
                        }
                    }
                }
                try {
                    Other.data.save(file);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
                Main.info.put(p.getName(), true);
                p.closeInventory();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("SuccessBetMessage")));
            }
        }
        if (a.getClickedInventory().getTitle().startsWith("§a可下注列表§b：§2第§6")) {
            a.setCancelled(true);
            String title = a.getClickedInventory().getTitle();
            int type = Integer.parseInt(title.replace("§a可下注列表§b：§2第§6", "").replace("§2页", ""));
            if (a.getCurrentItem() == null || a.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (a.getClickedInventory().getItem(53).getItemMeta().getDisplayName().equals("§a下一页") && a.getRawSlot() == 53) {
                Gui.list(p, ++type);
                return;
            }
            if (a.getClickedInventory().getItem(45).getItemMeta().getDisplayName().equals("§a上一页") && a.getRawSlot() == 45) {
                Gui.list(p, --type);
                return;
            }
            if (p.isOp()) {
                if (a.getRawSlot() > 44) {
                    return;
                }
                List<String> data = (List<String>)Other.data.getStringList("Item");
                data.remove(data.get(a.getRawSlot() + (type - 1) * 45));
                Other.data.set("Item", data);
                try {
                    Other.data.save(file);
                }
                catch (IOException e3) {
                    e3.printStackTrace();
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getOpenInventory().getTitle().startsWith("§a可下注列表§b：§2第§6")) {
                        String titles = a.getClickedInventory().getTitle();
                        int types = Integer.parseInt(titles.replace("§a可下注列表§b：§2第§6", "").replace("§2页", ""));
                        player.closeInventory();
                        Gui.list(player, types);
                    }
                }
                p.sendMessage("§a成功移除此物品！");
            }
        }
    }
}
