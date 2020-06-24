package com.tany.superlottery.commands;

import org.bukkit.plugin.*;
import org.bukkit.command.*;
import com.tany.superlottery.gui.*;
import com.tany.superlottery.*;
import java.io.*;
import org.bukkit.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import com.tany.superlottery.task.*;
import java.util.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.configuration.file.*;

public class Commands implements CommandExecutor
{
    Plugin config;
    File file1;
    File file2;
    File file3;
    
    public Commands() {
        config = Bukkit.getPluginManager().getPlugin("SuperLottery");
        file1 = new File(config.getDataFolder(), "config.yml");
        file2 = new File(config.getDataFolder(), "data.yml");
        file3 = new File(config.getDataFolder(), "message.yml");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("gui")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("��a����̨����ʹ�ô�ָ��");
                    return true;
                }
                if (!sender.hasPermission("sl.gui")) {
                    sender.sendMessage("��c��û��Ȩ��ʹ�ô�ָ��");
                    return true;
                }
                if (Main.i == 1) {
                    if (!Main.info.containsKey(sender.getName())) {
                        Player p = (Player)sender;
                        Gui.gui(p);
                        return true;
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("StartMessage")));
                    return true;
                }
                else {
                    if (Main.i == 2) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("StopMessage")));
                        return true;
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("BetMessage")));
                    return true;
                }
            }
            else if (args[0].equalsIgnoreCase("rule")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("��a����̨����ʹ�ô�ָ��");
                    return true;
                }
                if (sender.hasPermission("sl.rule")) {
                    Player p = (Player)sender;
                    Gui.list(p, 1);
                    return true;
                }
                sender.sendMessage("��c��û��Ȩ��ʹ�ô�ָ��");
                return true;
            }
            else if (args[0].equalsIgnoreCase("add")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("��a����̨����ʹ�ô�ָ��");
                    return true;
                }
                if (!sender.isOp()) {
                    sender.sendMessage("��c��û��Ȩ��ʹ�ô�ָ��");
                    return true;
                }
                Player p = (Player)sender;
                List<String> list = Other.data.getStringList("Item");
                if (p.getInventory().getItemInHand() == null || p.getInventory().getItemInHand().getType() == Material.AIR) {
                    p.sendMessage("��4�޷���ӿ���");
                    return true;
                }
                if (p.getInventory().getItemInHand().getItemMeta().hasLore() && p.getInventory().getItemInHand().getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', Other.config.getString("SetLore")))) {
                    p.sendMessage("��4�޷���ӶĲ������Ʒ");
                    return true;
                }
                String item = Way.GetItemData(p.getInventory().getItemInHand());
                if (list.contains(item)) {
                    p.sendMessage("��4�����ظ����");
                    return true;
                }
                list.add(item);
                Other.data.set("Item", list);
                try {
                    Other.data.save(file2);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                p.sendMessage("��a��ӳɹ�");
                return true;
            }
            else if (args[0].equalsIgnoreCase("draw")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("��c����̨����ʹ�ô�ָ��");
                    return true;
                }
                if (!sender.hasPermission("sl.gui")) {
                    sender.sendMessage("��c��û��Ȩ��ʹ�ô�ָ��");
                    return true;
                }
                int ab = 0;
                List<String> ExpMoneylist = Other.data.getStringList("ExpMoney");
                List<String> Bet = Other.data.getStringList("Bet");
                if (ExpMoneylist.size() != 0) {
                    for (String ExpMoney : ExpMoneylist) {
                        if (ExpMoney.startsWith(sender.getName())) {
                            ++ab;
                            break;
                        }
                    }
                }
                if (ab != 0 && ExpMoneylist.size() != 0) {
                    for (String ExpMoney : new ArrayList<String>(ExpMoneylist)) {
                        if (ExpMoney.startsWith(sender.getName() + ":")) {
                            int type = Integer.parseInt(ExpMoney.split(":")[1]);
                            int numbers = Integer.parseInt(ExpMoney.split(":")[2]);
                            if (type == 1) {
                                for (String bet : Bet) {
                                    if (bet.startsWith(sender.getName() + ":")) {
                                        int betnumber = Integer.parseInt(bet.split(":")[1]);
                                        int multiple = 0;
                                        if (betnumber == 1 || betnumber == 2) {
                                            multiple = Other.config.getInt("SadOdds");
                                        }
                                        else if (betnumber == 3 || betnumber == 4) {
                                            multiple = Other.config.getInt("SizeOdds");
                                        }
                                        else if (betnumber == 5) {
                                            multiple = Other.config.getInt("NumberOdds");
                                        }
                                        else if (betnumber == 6) {
                                            multiple = Other.config.getInt("SuperOdds");
                                        }
                                        ((Player)sender).giveExpLevels(numbers * multiple);
                                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("DrawXp").replace("[xp]", numbers * multiple+"")));
                                        Bet.remove(bet);
                                        Bet.add(sender.getName() + ":0");
                                        ExpMoneylist.remove(ExpMoney);
                                        Other.data.set("Bet", Bet);
                                        Other.data.set("ExpMoney", ExpMoneylist);
                                        try {
                                            Other.data.save(file2);
                                        }
                                        catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (type != 2) {
                                continue;
                            }
                            for (String bet : new ArrayList<String>(Bet)) {
                                if (bet.startsWith(sender.getName() + ":")) {
                                    int betnumber = Integer.parseInt(bet.split(":")[1]);
                                    int multiple = 0;
                                    if (betnumber == 1 || betnumber == 2) {
                                        multiple = Other.config.getInt("SadOdds");
                                    }
                                    else if (betnumber == 3 || betnumber == 4) {
                                        multiple = Other.config.getInt("SizeOdds");
                                    }
                                    else if (betnumber == 5) {
                                        multiple = Other.config.getInt("NumberOdds");
                                    }
                                    else if (betnumber == 6) {
                                        multiple = Other.config.getInt("SuperOdds");
                                    }
                                    Player player = (Player)sender;
                                    Main.economy.depositPlayer(player, (double)(numbers * multiple));
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("DrawMoney").replace("[money]", numbers * multiple+"")));
                                    Bet.remove(bet);
                                    Bet.add(sender.getName() + ":0");
                                    ExpMoneylist.remove(ExpMoney);
                                    Other.data.set("Bet", Bet);
                                    Other.data.set("ExpMoney", ExpMoneylist);
                                    try {
                                        Other.data.save(file2);
                                    }
                                    catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
                if (ab == 0) {
                    List<String> betlist = Other.data.getStringList("Bet");
                    if (betlist.size() == 0) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer") + Other.message.getString("NotDrawMessage")));
                        return true;
                    }
                    int bets = 0;
                    for (String bet2 : betlist) {
                        if (bet2.startsWith(sender.getName())) {
                            break;
                        }
                        ++bets;
                        if (betlist.size() == bets) {
                        	sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer") + Other.message.getString("NotDrawMessage")));
                            return true;
                        }
                    }
                    List<String> itemlist = Other.data.getStringList("Player." + sender.getName());
                    for (String item2 : new ArrayList<String>(itemlist)) {
                        int i = 0;
                        int a = 0;
                        while (i <= 35) {
                            if (((HumanEntity)sender).getInventory().getItem(i) == null) {
                                ++a;
                            }
                            if (i == 35) {
                                Main.itemsizes.put(sender.getName(), a);
                            }
                            ++i;
                        }
                        if (Main.itemsizes.get(sender.getName()) == 0) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("NotEmptyBackup")));
                            break;
                        }
                        int allamount = Main.itemsizes.get(sender.getName()) * 64;
                        int itemamount = Integer.parseInt(item2.split(":")[1]);
                        if (itemamount > allamount) {
                            itemamount = allamount;
                        }
                        int betinfo = 0;
                        List<String> bet3 = Other.data.getStringList("Bet");
                        for (String betdata : bet3) {
                            if (betdata.startsWith(sender.getName())) {
                                betinfo = Integer.parseInt(betdata.split(":")[1]);
                            }
                        }
                        int allitemamount = 0;
                        int multiple = 0;
                        if (betinfo == 1 || betinfo == 2) {
                            multiple = Other.config.getInt("SadOdds");
                            allitemamount = itemamount * multiple;
                        }
                        else if (betinfo == 3 || betinfo == 4) {
                            multiple = Other.config.getInt("SizeOdds");
                            allitemamount = itemamount * multiple;
                        }
                        else if (betinfo == 5) {
                            multiple = Other.config.getInt("NumberOdds");
                            allitemamount = itemamount * multiple;
                        }
                        else if (betinfo == 6) {
                            multiple = Other.config.getInt("SuperOdds");
                            allitemamount = itemamount * multiple;
                        }
                        String id = item2.split(":")[0];
                        ItemStack iteminfo = Way.GetItemStack(id);
                        iteminfo.setAmount(allitemamount);
                        if (Other.config.getBoolean("Lore")) {
                            ArrayList<String> lore = new ArrayList<String>();
                            ItemMeta items = iteminfo.getItemMeta();
                            lore.add(ChatColor.translateAlternateColorCodes('&', Other.config.getString("SetLore")));
                            items.setLore(lore);
                            iteminfo.setItemMeta(items);
                            lore.clear();
                        }
                        ((HumanEntity)sender).getInventory().addItem(new ItemStack[] { iteminfo });
                        int err = (allitemamount - Main.itemsizes.get(sender.getName()) * 64) / multiple;
                        if (err > 0) {
                            String newitem = id + ":" + err;                            itemlist.add(newitem);
                            itemlist.remove(item2);
                            Other.data.set("Player." + sender.getName(), itemlist);
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("NoDrawOneMessage")));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("NoDrawTwoMessage")));
                        }
                        else {
                            itemlist.remove(item2);
                            Other.data.set("Player." + sender.getName(), itemlist);
                        }
                        List<String> newitemlist = Other.data.getStringList("Player." + sender.getName());
                        if (newitemlist.size() == 0) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("DrawMessage")));
                            List<String> newbet = Other.data.getStringList("Bet");
                            for (String news : new ArrayList<String>(newbet)) {
                                if (news.startsWith(sender.getName())) {
                                    newbet.add(sender.getName() + ":0");
                                    newbet.remove(news);
                                    Other.data.set("Bet", newbet);
                                }
                            }
                        }
                        try {
                            Other.data.save(file2);
                        }
                        catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                }
                return true;
            }
            else if (args[0].equalsIgnoreCase("start")) {
                if (!Other.config.getBoolean("BlackBox")) {
                    sender.sendMessage("��cδ�������书��");
                    return true;
                }
                if (!sender.isOp()) {
                    sender.sendMessage("��c��û��Ȩ��ʹ�ô�ָ��");
                    return true;
                }
                if (!Main.count) {
                    sender.sendMessage("��c��ǰ����������ִ��");
                    return true;
                }
                new CommandTask().runTaskTimer(Main.plugin, 40L, 1L);
                new CommandDrawTask().runTaskTimer(Main.plugin, 200L, 1L);
                sender.sendMessage("��a���ڿ�ʼ������ע�����Ϣ");
                Main.count = false;
                return true;
            }
            else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.isOp()) {
                    Other.config = (FileConfiguration)YamlConfiguration.loadConfiguration(file1);
                    Other.data = (FileConfiguration)YamlConfiguration.loadConfiguration(file2);
                    Other.message = (FileConfiguration)YamlConfiguration.loadConfiguration(file3);
                    sender.sendMessage("��a���سɹ�");
                    return true;
                }
                sender.sendMessage("��c��û��Ȩ��ʹ�ô�ָ��");
                return true;
            }
            else {
                if (Other.config.getBoolean("BlackBox") && args[0].equalsIgnoreCase("set")) {
                    sender.sendMessage("��c����������");
                    return true;
                }
                if (args[0].equalsIgnoreCase("set")) {
                    sender.sendMessage("��cδ�������书��");
                    return true;
                }
            }
        }
        if (args.length == 2) {
            if (!Other.config.getBoolean("BlackBox") || !args[0].equalsIgnoreCase("set")) {
                sender.sendMessage("��cδ�������书��");
                return true;
            }
            if (!sender.isOp()) {
                sender.sendMessage("��c��û��Ȩ��ʹ�ô�ָ��");
                return true;
            }
            int number;
            try {
                number = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e5) {
                sender.sendMessage("��c����������");
                return true;
            }
            if (number <= 0 || number >= 101) {
                sender.sendMessage("��c������һ����ΧΪ1-100������");
                return true;
            }
            if (number == 100) {
                Main.setrandom = number;
                sender.sendMessage("��6��������һ�ο���Ϊ��4��lͨ��");
                return true;
            }
            Main.setrandom = number;
            sender.sendMessage("��a��������һ�ο�������Ϊ��6" + number);
            return true;
        }
        else {
            if (!sender.isOp()) {
                sender.sendMessage("��a/sl gui  ��2��ע");
                sender.sendMessage("��a/sl rule  ��2�鿴����ע��Ʒ");
                sender.sendMessage("��a/sl draw  ��2��ȡʣ����עδ����ȡ����Ʒ");
                return true;
            }
            if (Other.config.getBoolean("BlackBox")) {
                sender.sendMessage("��e[]======��e[��6superlottery��e]��6�齱ϵͳ��e======[]");
                sender.sendMessage("��a/sl gui  ��2��ע");
                sender.sendMessage("��a/sl rule  ��2�鿴����ע��Ʒ");
                sender.sendMessage("��a/sl add  ��2���������Ʒ������ע����");
                sender.sendMessage("��a/sl draw  ��2��ȡʣ����עδ����ȡ����Ʒ");
                sender.sendMessage("��a/sl set ��6����  ��2������һ�ε������Ϊ '��6���֡�2' ");
                sender.sendMessage("��a/sl start  ��2ֱ�ӿ�ʼһ�ν�����ע���");
                sender.sendMessage("��a/sl reload  ��2���������ļ�");
                sender.sendMessage("��e[]==================================[]");
                return true;
            }
            sender.sendMessage("��e[]======��e[��6superlottery��e]��6�齱ϵͳ��e======[]");
            sender.sendMessage("��a/sl gui  ��2��ע");
            sender.sendMessage("��a/sl rule  ��2�鿴����ע��Ʒ");
            sender.sendMessage("��a/sl draw  ��2��ȡʣ����עδ����ȡ����Ʒ");
            sender.sendMessage("��a/sl add  ��2���������Ʒ������ע����");
            sender.sendMessage("��a/sl reload  ��2���������ļ�");
            sender.sendMessage("��e[]==================================[]");
            return true;
        }
    }
}
