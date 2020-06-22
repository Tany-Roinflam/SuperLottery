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
        if (a.getClickedInventory().getTitle().startsWith("��e�¡�aע��c���b��") && (a.getSlot() <= 8 || a.getSlot() >= 36 || a.getSlot() == 9 || a.getSlot() == 18 || a.getSlot() == 27 || a.getSlot() == 17 || a.getSlot() == 26 || a.getSlot() == 35)) {
            a.setCancelled(true);
            if (a.getSlot() == 45) {
                Main.lottery.put(p.getName(), 1);
                p.sendMessage("��a�ɹ���ע��Χ��6����f��");
            }
            if (a.getSlot() == 46) {
                Main.lottery.put(p.getName(), 2);
                p.sendMessage("��a�ɹ���ע��Χ��6����e˫");
            }
            if (a.getSlot() == 47) {
                Main.lottery.put(p.getName(), 3);
                p.sendMessage("��a�ɹ���ע��Χ��6����bС");
            }
            if (a.getSlot() == 48) {
                Main.lottery.put(p.getName(), 4);
                p.sendMessage("��a�ɹ���ע��Χ��6����3��");
            }
            if (a.getSlot() == 49) {
                for (int i = 0; i <= 6; ++i) {
                    if (a.getClickedInventory().getItem(10 + i) != null) {
                        p.sendMessage("��c������������ע��");
                        return;
                    }
                    if (a.getClickedInventory().getItem(19 + i) != null) {
                        p.sendMessage("��c������������ע��");
                        return;
                    }
                    if (a.getClickedInventory().getItem(28 + i) != null) {
                        p.sendMessage("��c������������ע��");
                        return;
                    }
                    if (i == 6) {
                        p.closeInventory();
                        p.sendMessage("��a��ʾ������������־���������ע�ĺ���");
                        Conversation conversation = new Conversation((Plugin)Main.getInstance(), (Conversable)p, (Prompt)new NumberPrompt());
                        p.beginConversation(conversation);
                        return;
                    }
                }
            }
            if (a.getSlot() == 51) {
                if (!Main.lottery.containsKey(p.getName())) {
                    p.sendMessage("��c����ѡ������ע�ĺ��뷶Χ��");
                    return;
                }
                if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
                    p.sendMessage("��e�޷��������ǰ�ã���6Vault");
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
                p.sendMessage("��e�����Ϸ��Ϊ��6" + money);
                if (money <= 0) {
                    p.sendMessage("��e��û����Ϸ���²���ע");
                    return;
                }
                Conversation moneyconversation = new Conversation((Plugin)Main.getInstance(), (Conversable)p, (Prompt)new MoneyPrompt());
                p.beginConversation(moneyconversation);
                p.sendMessage("��6������һ��С�ڻ�������e��Ϸ�ҡ�6������");
                p.closeInventory();
            }
            if (a.getSlot() == 52) {
                if (!Main.lottery.containsKey(p.getName())) {
                    p.sendMessage("��c����ѡ������ע�ĺ��뷶Χ��");
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
                p.sendMessage("��d��ľ����С�5" + p.getTotalExperience() + "��d��");
                if (p.getTotalExperience() == 0) {
                    p.sendMessage("��b��û�о����²���ע");
                    return;
                }
                p.sendMessage("��a������һ��С�ڻ�����㾭�������");
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
                    p.sendMessage("��c��ǰû���κ���Ʒ����ע");
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
                    p.sendMessage("��c����ע��ʱ�򲻷���Ʒ����");
                    s = 0;
                    return;
                }
                if (values > 0) {
                    p.sendMessage("��c��ע��������" + values + "��Ϊ������ע��Ʒ");
                    values = 0;
                    return;
                }
                if (nolore > 0) {
                    p.sendMessage("��c��ע��������" + nolore + "��Ϊ֮ǰ��ע����ע��Ʒ");
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
        if (a.getClickedInventory().getTitle().startsWith("��a����ע�б��b����2�ڡ�6")) {
            a.setCancelled(true);
            String title = a.getClickedInventory().getTitle();
            int type = Integer.parseInt(title.replace("��a����ע�б��b����2�ڡ�6", "").replace("��2ҳ", ""));
            if (a.getCurrentItem() == null || a.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (a.getClickedInventory().getItem(53).getItemMeta().getDisplayName().equals("��a��һҳ") && a.getRawSlot() == 53) {
                Gui.list(p, ++type);
                return;
            }
            if (a.getClickedInventory().getItem(45).getItemMeta().getDisplayName().equals("��a��һҳ") && a.getRawSlot() == 45) {
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
                    if (player.getOpenInventory().getTitle().startsWith("��a����ע�б��b����2�ڡ�6")) {
                        String titles = a.getClickedInventory().getTitle();
                        int types = Integer.parseInt(titles.replace("��a����ע�б��b����2�ڡ�6", "").replace("��2ҳ", ""));
                        player.closeInventory();
                        Gui.list(player, types);
                    }
                }
                p.sendMessage("��a�ɹ��Ƴ�����Ʒ��");
            }
        }
    }
}
