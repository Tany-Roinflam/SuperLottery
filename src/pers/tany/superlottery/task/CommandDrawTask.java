package pers.tany.superlottery.task;

import org.bukkit.scheduler.*;

import pers.tany.superlottery.*;

import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.io.*;
import java.util.*;
import org.bukkit.inventory.meta.*;

public class CommandDrawTask extends BukkitRunnable
{
    Plugin config;
    File file;
    
    public CommandDrawTask() {
        config = Bukkit.getPluginManager().getPlugin("SuperLottery");
        file = new File(config.getDataFolder(), "data.yml");
    }
    
    public void run() {
        Random randomnumber = new Random();
        int random = 0;
        int d = 0;
        List<String> one = Other.data.getStringList("One");
        List<String> two = Other.data.getStringList("Two");
        List<String> small = Other.data.getStringList("Small");
        List<String> big = Other.data.getStringList("Big");
        List<String> number = Other.data.getStringList("Number");
        List<String> supernumber = Other.data.getStringList("SuperNumber");
        if (Other.config.getBoolean("BetNumber") && Main.cb != 0 && Main.cb / Other.config.getInt("BetTakeAll") == 0) {
            random = 100;
        }
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (p.getOpenInventory().getTitle().equals("§e下注界面")) {
                p.closeInventory();
            }
        }
        if (Main.setrandom == 0) {
            if (Other.config.getBoolean("BlackBox")) {
                random = randomnumber.nextInt(100);
                ++random;
            }
            else {
                random = randomnumber.nextInt(99);
                ++random;
            }
        }
        else {
            random = Main.setrandom;
        }
        if (Other.config.getBoolean("BlackBox") && Main.setrandom == 0) {
            Random BlackBoxrandomnumber = new Random();
            int BlackBoxrandom = BlackBoxrandomnumber.nextInt(Other.config.getInt("Probability")) + 1;
            int abc = Main.info.size();
            int max = 0;
            if (Other.config.getBoolean("BetNumber")) {
                if (d != 0) {
                    if (d / Other.config.getInt("AddProbabilityNumber") == 0) {
                        max = Other.config.getInt("DefaultProbability") + (abc * Other.config.getInt("PeopleProbability") + Other.config.getInt("AddProbability"));
                    }
                    else {
                        max = Other.config.getInt("DefaultProbability") + abc * Other.config.getInt("PeopleProbability");
                    }
                }
                else {
                    max = Other.config.getInt("DefaultProbability") + abc * Other.config.getInt("PeopleProbability");
                }
            }
            else {
                max = Other.config.getInt("DefaultProbability") + abc * Other.config.getInt("PeopleProbability");
            }
            if (max > Other.config.getInt("MaxProbability")) {
                max = Other.config.getInt("MaxProbability");
            }
            if (BlackBoxrandom <= max) {
                random = 100;
            }
        }
        ++d;
        ++Main.cb;
        for (String name : Main.lottery.keySet()) {
            List<String> bet = Other.data.getStringList("Bet");
            for (String betlist : new ArrayList<String>(bet)) {
                if (betlist.startsWith(name)) {
                    bet.remove(betlist);
                    Other.data.set("Bet", bet);
                    break;
                }
            }
            switch (Main.lottery.get(name)) {
                case 1: {
                    if (random % 2 == 0 || random == 100 || random == 99) {
                        continue;
                    }
                    if (Main.info.containsKey(name)) {
                        one.add(name);
                        Other.data.set("One", one);
                        String bets = name + ":1";
                        bet.add(bets);
                        Other.data.set("Bet", bet);
                        Main.data.put(name, true);
                        continue;
                    }
                    Bukkit.getPlayer(name).sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("ChooseWayNotDraw")));
                    continue;
                }
                case 2: {
                    if (random % 2 != 0 || random == 100) {
                        continue;
                    }
                    if (Main.info.containsKey(name)) {
                        two.add(name);
                        Other.data.set("Two", two);
                        String bets = name + ":2";
                        bet.add(bets);
                        Other.data.set("Bet", bet);
                        Main.data.put(name, true);
                        continue;
                    }
                    Bukkit.getPlayer(name).sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("ChooseWayNotDraw")));
                    continue;
                }
                case 3: {
                    if (random < 1 || random > 33) {
                        continue;
                    }
                    if (Main.info.containsKey(name)) {
                        small.add(name);
                        Other.data.set("Small", small);
                        String bets = name + ":3";
                        bet.add(bets);
                        Other.data.set("Bet", bet);
                        Main.data.put(name, true);
                        continue;
                    }
                    Bukkit.getPlayer(name).sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("ChooseWayNotDraw")));
                    continue;
                }
                case 4: {
                    if (random < 67 || random > 99) {
                        continue;
                    }
                    if (Main.info.containsKey(name)) {
                        big.add(name);
                        Other.data.set("Big", big);
                        String bets = name + ":4";
                        bet.add(bets);
                        Other.data.set("Bet", bet);
                        Main.data.put(name, true);
                        continue;
                    }
                    Bukkit.getPlayer(name).sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("ChooseWayNotDraw")));
                    continue;
                }
                case 5: {
                    int numberss = Main.number.get(name);
                    if (numberss < 10) {
                        if (random != numberss && random != numberss + 10 && random != numberss + 20 && random != numberss + 30 && random != numberss + 40 && random != numberss + 50 && random != numberss + 60 && random != numberss + 70 && random != numberss + 80 && random != numberss + 90) {
                            continue;
                        }
                        if (Main.info.containsKey(name)) {
                            number.add(name);
                            Other.data.set("Number", number);
                            String bets2 = name + ":5";
                            bet.add(bets2);
                            Other.data.set("Bet", bet);
                            Main.data.put(name, true);
                            continue;
                        }
                        Bukkit.getPlayer(name).sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("ChooseWayNotDraw")));
                        continue;
                    }
                    else if (numberss == 10 || numberss == 20 || numberss == 30 || numberss == 40 || numberss == 50 || numberss == 60 || numberss == 70 || numberss == 80 || numberss == 90) {
                        if (random != numberss && random != numberss + 1 && random != numberss + 2 && random != numberss + 3 && random != numberss + 4 && random != numberss + 5 && random != numberss + 6 && random != numberss + 7 && random != numberss + 8 && random != numberss + 9) {
                            continue;
                        }
                        if (Main.info.containsKey(name)) {
                            number.add(name);
                            Other.data.set("Number", number);
                            String bets2 = name + ":5";
                            bet.add(bets2);
                            Other.data.set("Bet", bet);
                            Main.data.put(name, true);
                            continue;
                        }
                        Bukkit.getPlayer(name).sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("ChooseWayNotDraw")));
                        continue;
                    }
                    else {
                        if (random != numberss) {
                            continue;
                        }
                        if (Main.info.containsKey(name)) {
                            supernumber.add(name);
                            Other.data.set("SuperNumber", supernumber);
                            String bets2 = name + ":6";
                            bet.add(bets2);
                            Other.data.set("Bet", bet);
                            Main.data.put(name, true);
                            continue;
                        }
                        Bukkit.getPlayer(name).sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("ChooseWayNotDraw")));
                        continue;
                    }
                }
                default: {
                    continue;
                }
            }
        }
        String randomnumbermessage = null;
        if (random == 100) {
            randomnumbermessage = "§6§l开奖号码为§e§l：§4§l" + random + "\n§c§l全§f§l盘§4§l通吃§d§l！！！！！！\n§c§l全§f§l盘§4§l通吃§d§l！！！！！！\n§c§l全§f§l盘§4§l通吃§d§l！！！！！！";
        }
        else {
            randomnumbermessage = "§e§l开奖号码§a§l：§6§l" + random;
        }
        String message = "§6[]-------[开奖结果]-------[]";
        String onemessage = "§a下注§f单§a中奖名单§5:§c无人中奖";
        String twomessage = "§a下注§f双§e中奖名单§5:§c无人中奖";
        String smallmessage = "§a下注§f小§b中奖名单§5:§c无人中奖";
        String bigmessage = "§a下注§f大§3中奖名单§5:§c无人中奖";
        String numbermessage = "§a下注§d自§2选§e数§9字§3中奖名单§5:§c无人中奖";
        String supernumbermessage = "§a下注§d§l自§2§l选§e§l数§9§l字§3中奖名单§5:§c无人中奖";
        if (one.size() != 0) {
            onemessage = "§a下注§f单§a中奖名单§5:§6";
            for (String onename : one) {
                onemessage = onemessage + onename + " ";
            }
        }
        if (two.size() != 0) {
            twomessage = "§a下注§f双§e中奖名单§5:§6";
            for (String twoname : two) {
                twomessage = twomessage + twoname + " ";
            }
        }
        if (small.size() != 0) {
            smallmessage = "§a下注§f小§b中奖名单§5:§6";
            for (String smallname : small) {
                smallmessage = smallmessage + smallname + " ";
            }
        }
        if (big.size() != 0) {
            bigmessage = "§a下注§f大§3中奖名单§5:§6";
            for (String bigname : big) {
                bigmessage = bigmessage + bigname + " ";
            }
        }
        if (number.size() != 0) {
            numbermessage = "§a下注§d自§2选§e数§9字§3中奖名单§5:§d";
            for (String numbername : number) {
                numbermessage = numbermessage + numbername + " ";
            }
        }
        if (supernumber.size() != 0) {
            supernumbermessage = "§a下注§d§l自§2§l选§e§l数§9§l字§3中奖名单§5:§b";
            for (String supernumbername : supernumber) {
                supernumbermessage = supernumbermessage + supernumbername + " ";
            }
        }
//        Bukkit.broadcastMessage(message);
//        Bukkit.broadcastMessage(randomnumbermessage);
//        Bukkit.broadcastMessage("§a距离下轮开始下注时间来到还有§e" + Other.config.getInt("BetTime") + "§a秒 ");
//        Bukkit.broadcastMessage("§b参与本次下注人数§d：§3§l" + Main.info.size());
//        Bukkit.broadcastMessage("§d本次中奖人数：§3" + Main.data.size());
//        Bukkit.broadcastMessage("§e[]-------[§4中§b奖§d人§a员§e]-------[]");
//        Bukkit.broadcastMessage(onemessage);
//        Bukkit.broadcastMessage(twomessage);
//        Bukkit.broadcastMessage(smallmessage);
//        Bukkit.broadcastMessage(bigmessage);
//        Bukkit.broadcastMessage(numbermessage);
//        Bukkit.broadcastMessage(supernumbermessage);
//        Bukkit.broadcastMessage(message);
        for(String messages:Other.message.getStringList("BetsMessage")) {
        	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', messages.replace("[bet]", Main.info.size()+"")
        			.replace("[draw]", Main.data.size()+"").replace("[time]", Other.config.getInt("BetTime")+"")
        			.replace("[message]", message).replace("[randomnumbermessage]", randomnumbermessage).replace("[onemessage]", onemessage).replace("[twomessage]", twomessage)
        			.replace("[smallmessage]", smallmessage).replace("[bigmessage]", bigmessage).replace("[numbermessage]", numbermessage).replace("[supernumbermessage]", supernumbermessage)));
        }
        for (String player : new ArrayList<String>(Main.info.keySet())) {
            if (!Main.data.containsKey(player)) {
                List<String> item = Other.data.getStringList("Player." + player);
                List<String> ExpMoneylist = Other.data.getStringList("ExpMoney");
                item.clear();
                Other.data.set("Player." + player, item);
                for (String ExpMoney : new ArrayList<String>(ExpMoneylist)) {
                    if (ExpMoney.startsWith(player)) {
                        ExpMoneylist.remove(ExpMoney);
                        Other.data.set("ExpMoney", ExpMoneylist);
                        break;
                    }
                }
                Main.info.remove(player);
                if (random != 100) {
                    continue;
                }
                Bukkit.getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("NotAnyDraw")));
            }
        }
        int way = 0;
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            List<String> ExpMoneylist = Other.data.getStringList("ExpMoney");
            List<String> Bet = Other.data.getStringList("Bet");
            if (ExpMoneylist.size() != 0) {
                for (String ExpMoneys : ExpMoneylist) {
                    if (ExpMoneys.startsWith(player.getName())) {
                        ++way;
                        break;
                    }
                }
            }
            if (way != 0 && ExpMoneylist.size() != 0) {
                for (String ExpMoney2 : new ArrayList<String>(ExpMoneylist)) {
                    if (ExpMoney2.startsWith(player.getName() + ":")) {
                        int type = Integer.parseInt(ExpMoney2.split(":")[1]);
                        int numbers = Integer.parseInt(ExpMoney2.split(":")[2]);
                        if (type == 1) {
                            for (String bet2 : new ArrayList<String>(Bet)) {
                                if (bet2.startsWith(player.getName() + ":")) {
                                    int multiple = 0;
                                    switch (Integer.parseInt(bet2.split(":")[1])) {
                                        case 1: {
                                            multiple = Other.config.getInt("SadOdds");
                                            break;
                                        }
                                        case 2: {
                                            multiple = Other.config.getInt("SadOdds");
                                            break;
                                        }
                                        case 3: {
                                            multiple = Other.config.getInt("SizeOdds");
                                            break;
                                        }
                                        case 4: {
                                            multiple = Other.config.getInt("SizeOdds");
                                            break;
                                        }
                                        case 5: {
                                            multiple = Other.config.getInt("NumberOdds");
                                            break;
                                        }
                                        case 6: {
                                            multiple = Other.config.getInt("SuperOdds");
                                            break;
                                        }
                                    }
                                    player.giveExp(numbers * multiple);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("DrawXp").replace("[xp]", numbers * multiple+"")));
                                    Bet.remove(bet2);
                                    Bet.add(player.getName() + ":0");
                                    ExpMoneylist.remove(ExpMoney2);
                                    Other.data.set("Bet", Bet);
                                    Other.data.set("ExpMoney", ExpMoneylist);
                                }
                            }
                        }
                        if (type != 2) {
                            continue;
                        }
                        for (String bet2 : new ArrayList<String>(Bet)) {
                            if (bet2.startsWith(player.getName() + ":")) {
                                int multiple = 0;
                                switch (Integer.parseInt(bet2.split(":")[1])) {
                                    case 1: {
                                        multiple = Other.config.getInt("SadOdds");
                                        break;
                                    }
                                    case 2: {
                                        multiple = Other.config.getInt("SadOdds");
                                        break;
                                    }
                                    case 3: {
                                        multiple = Other.config.getInt("SizeOdds");
                                        break;
                                    }
                                    case 4: {
                                        multiple = Other.config.getInt("SizeOdds");
                                        break;
                                    }
                                    case 5: {
                                        multiple = Other.config.getInt("NumberOdds");
                                        break;
                                    }
                                    case 6: {
                                        multiple = Other.config.getInt("SuperOdds");
                                        break;
                                    }
                                }
                                Main.economy.depositPlayer(player, (double)(numbers * multiple));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("DrawMoney").replace("[money]", numbers * multiple+"")));
                                Bet.remove(bet2);
                                Bet.add(player.getName() + ":0");
                                ExpMoneylist.remove(ExpMoney2);
                                Other.data.set("Bet", Bet);
                                Other.data.set("ExpMoney", ExpMoneylist);
                            }
                        }
                    }
                }
            }
            if (way == 0 && Main.data.containsKey(player.getName())) {
                List<String> itemlist = Other.data.getStringList("Player." + player.getName());
                for (String item2 : new ArrayList<String>(itemlist)) {
                    int i = 0;
                    int a = 0;
                    while (i <= 35) {
                        if (player.getInventory().getItem(i) == null) {
                            ++a;
                        }
                        if (i == 35) {
                            Main.itemsizes.put(player.getName(), a);
                        }
                        ++i;
                    }
                    if (Main.itemsizes.get(player.getName()) == 0) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("NotEmptyBackup")));
                        break;
                    }
                    int allamount = Main.itemsizes.get(player.getName()) * 64;
                    int itemamount = Integer.parseInt(item2.split(":")[1]);
                    if (itemamount > allamount) {
                        itemamount = allamount;
                    }
                    int betinfo = 0;
                    List<String> bet3 = Other.data.getStringList("Bet");
                    for (String betdata : bet3) {
                        if (betdata.startsWith(player.getName())) {
                            betinfo = Integer.parseInt(betdata.split(":")[1]);
                        }
                    }
                    int allitemamount = 0;
                    int multiple = 1;
                    switch (betinfo) {
                        case 1: {
                            multiple = Other.config.getInt("SadOdds");
                            allitemamount = itemamount * multiple;
                            break;
                        }
                        case 2: {
                            multiple = Other.config.getInt("SadOdds");
                            allitemamount = itemamount * multiple;
                            break;
                        }
                        case 3: {
                            multiple = Other.config.getInt("SizeOdds");
                            allitemamount = itemamount * multiple;
                            break;
                        }
                        case 4: {
                            multiple = Other.config.getInt("SizeOdds");
                            allitemamount = itemamount * multiple;
                            break;
                        }
                        case 5: {
                            multiple = Other.config.getInt("NumberOdds");
                            allitemamount = itemamount * multiple;
                            break;
                        }
                        case 6: {
                            multiple = Other.config.getInt("SuperOdds");
                            allitemamount = itemamount * multiple;
                            break;
                        }
                    }
                    String id = item2.split(":")[0];
                    ItemStack iteminfo = CommonlyWay.GetItemStack(id);
                    iteminfo.setAmount(allitemamount);
                    if (Other.config.getBoolean("Lore")) {
                        ArrayList<String> lore = new ArrayList<String>();
                        ItemMeta items = iteminfo.getItemMeta();
                        lore.add(ChatColor.translateAlternateColorCodes('&', Other.config.getString("SetLore")));
                        items.setLore(lore);
                        iteminfo.setItemMeta(items);
                        lore.clear();
                    }
                    player.getInventory().addItem(new ItemStack[] { iteminfo });
                    int err;
                    if(allitemamount - Main.itemsizes.get(player.getName())==0) 
                    	err = 0;
                    else
                    	err = (allitemamount - Main.itemsizes.get(player.getName()) * 64) / multiple;
                    if (err > 0) {
                        String newitem = id + ":" + err;
                        itemlist.add(newitem);
                        itemlist.remove(item2);
                        Other.data.set("Player." + player.getName(), itemlist);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("NoDrawOneMessage")));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("NoDrawTwoMessage")));
                    }
                    else {
                        itemlist.remove(item2);
                        Other.data.set("Player." + player.getName(), itemlist);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Other.message.getString("Dealer")+Other.message.getString("TaskDrawMessage")));
                    }
                    List<String> newitemlist = Other.data.getStringList("Player." + player.getName());
                    if (newitemlist.size() != 0) {
                        continue;
                    }
                    List<String> newbet = Other.data.getStringList("Bet");
                    for (String news : new ArrayList<String>(newbet)) {
                        if (news.startsWith(player.getName())) {
                            newbet.add(player.getName() + ":0");
                            newbet.remove(news);
                            Other.data.set("Bet", newbet);
                        }
                    }
                }
            }
            way = 0;
        }
        one.clear();
        two.clear();
        small.clear();
        big.clear();
        number.clear();
        supernumber.clear();
        Other.data.set("One", one);
        Other.data.set("Two", two);
        Other.data.set("Small", small);
        Other.data.set("Big", big);
        Other.data.set("Number", number);
        Other.data.set("SuperNumber", supernumber);
        try {
            Other.data.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Main.number.clear();
        Main.lottery.clear();
        Main.info.clear();
        Main.itemsizes.clear();
        Main.setrandom = 0;
        Main.data.clear();
        Main.count = true;
        cancel();
    }
}
