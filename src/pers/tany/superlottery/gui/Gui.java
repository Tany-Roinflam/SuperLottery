package pers.tany.superlottery.gui;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import pers.tany.superlottery.*;

import java.util.*;

public class Gui
{
    public static void gui(Player p) {
        Inventory gui = Bukkit.createInventory((InventoryHolder)null, 54, "§e下§a注§c界§b面");
        ItemStack frame = BasicLibrary.stainedglass.get(4);
        ItemStack single = BasicLibrary.stainedwool.get(0);
        ItemStack twin = BasicLibrary.stainedwool.get(1);
        ItemStack big = BasicLibrary.stainedwool.get(11);
        ItemStack small = BasicLibrary.stainedwool.get(3);
        ItemStack number = BasicLibrary.stainedwool.get(14);
        ItemStack money = BasicLibrary.stainedwool.get(4);
        ItemStack xp = BasicLibrary.stainedwool.get(10);
        ItemStack confirm = new ItemStack(Material.BEACON);
        
        ItemMeta framedata = frame.getItemMeta();
        List<String> lore = new ArrayList<String>();
        
        framedata.setDisplayName("§e分隔栏");
        lore.add("§f请§a在§b圈§d内§c下§e注");
        lore.add("§c同一轮只能选择一种下注类型");
        framedata.setLore(lore);
        frame.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("§e下注：§f单");
        lore.add("§e下注数值1-98范围内的单数");
        lore.add("§a下§b注§c赔§6率§e§l1§3§l ： §f§l" + Other.config.getInt("SadOdds"));
        framedata.setLore(lore);
        single.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("§e下注：双");
        lore.add("§f下注数字1-98范围内的双数");
        lore.add("§a下§b注§c赔§6率§e§l1§3§l ： §e§l" + Other.config.getInt("SadOdds"));
        framedata.setLore(lore);
        twin.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("§e下注：§b小");
        lore.add("§3下注数字1-33");
        lore.add("§a下§b注§c赔§6率§e§l1§3§l ： §b§l" + Other.config.getInt("SizeOdds"));
        framedata.setLore(lore);
        small.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("§e下注：§3大");
        lore.add("§b下注数字67-99");
        lore.add("§a下§b注§c赔§6率§e§l1§3§l ： §3§l" + Other.config.getInt("SizeOdds"));
        framedata.setLore(lore);
        big.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("§e下注：§d自§c选§e数§9字");
        lore.add("§d自§c选§e数§9字");
        lore.add("§a点击后输入一个1-99的数字");
        lore.add("§6中奖范围为：");
        lore.add("§a当填入个一位数时，范围为：10+数值、20+数值、30+数值....90+数值");
        lore.add("§b当填入个位数为0的双位数时，范围为：数值+1、数值+2、数值+3...数值+9");
        lore.add("§9当均不满足以上条件时，只有中了那个数字才会中奖，但奖励极为丰厚");
        lore.add("\n§b范§a围§c数§4字§a下§b注§c赔§6率§e§l1§3§l ： §6§l" + Other.config.getInt("NumberOdds"));
        lore.add("§9指§6定§c数§4字§a下§b注§c赔§6率§e§l1§3§l ： §4§l" + Other.config.getInt("SuperOdds"));
        framedata.setLore(lore);
        number.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("§e确§a定§d下§3注 §e类型：§6游戏币");
        lore.add("§e下注类型为游戏币");
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            lore.add("§c§l此类型不可用");
        }
        framedata.setLore(lore);
        money.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("§e确§a定§d下§3注 类型：§5经验");
        lore.add("§d下注类型为经验");
        framedata.setLore(lore);
        xp.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("§e确§a定§d下§3注");
        lore.add("§9确定");
        lore.add("§4注意！§6一旦确认后无法更改！");
        framedata.setLore(lore);
        confirm.setItemMeta(framedata);
        lore.clear();
        
        gui.setItem(0, frame);
        gui.setItem(1, frame);
        gui.setItem(2, frame);
        gui.setItem(3, frame);
        gui.setItem(4, frame);
        gui.setItem(5, frame);
        gui.setItem(6, frame);
        gui.setItem(7, frame);
        gui.setItem(8, frame);
        gui.setItem(9, frame);
        gui.setItem(17, frame);
        gui.setItem(18, frame);
        gui.setItem(26, frame);
        gui.setItem(27, frame);
        gui.setItem(35, frame);
        gui.setItem(36, frame);
        gui.setItem(37, frame);
        gui.setItem(38, frame);
        gui.setItem(39, frame);
        gui.setItem(40, frame);
        gui.setItem(41, frame);
        gui.setItem(42, frame);
        gui.setItem(43, frame);
        gui.setItem(44, frame);
        gui.setItem(45, single);
        gui.setItem(46, twin);
        gui.setItem(47, small);
        gui.setItem(48, big);
        gui.setItem(49, number);
        gui.setItem(50, frame);
        gui.setItem(51, money);
        gui.setItem(52, xp);
        gui.setItem(53, confirm);
        List<String> betlist = Other.data.getStringList("Bet");
        for (String bet : betlist) {
            if (bet.startsWith(p.getName())) {
                int betnumber = Integer.parseInt(bet.split(":")[1]);
                if (betnumber != 0) {
                    p.sendMessage("§c请输入/sl draw领取完剩下的物品才能下注");
                    return;
                }
                continue;
            }
        }
        p.openInventory(gui);
    }
    
    public static void list(Player player, Integer type) {
        ItemStack frame = BasicLibrary.stainedglass.get(4);
        Inventory gui = Bukkit.createInventory((InventoryHolder)null, 54, "§a可下注列表§b：§2第§6" + type + "§2页");
        ItemStack xiaye = BasicLibrary.stainedglass.get(14);
        ItemStack shangye = BasicLibrary.stainedglass.get(11);
        List<String> list = Other.data.getStringList("Item");
        if (list.size() >= (type - 1) * 45 + 1) {
            for (int i = (type - 1) * 45, ia = 0, size = list.size() - 1; i <= size && i <= 44 + (type - 1) * 45; ++i, ++ia) {
                ItemStack item = CommonlyWay.GetItemStack(list.get(i));
                ItemMeta framedata = item.getItemMeta();
                List<String> lore;
                if (framedata.hasLore()) {
                    lore = framedata.getLore();
                }
                else {
                    lore = new ArrayList<String>();
                }
                lore.add("§e可下注的物品");
                if (player.isOp()) {
                    lore.add("§c点击移除此物品");
                }
                framedata.setLore(lore);
                item.setItemMeta(framedata);
                lore.clear();
                gui.setItem(ia, item);
            }
            ItemMeta framedata = frame.getItemMeta();
            ArrayList<String> empty = new ArrayList<String>();
            
            framedata.setDisplayName("§c介§5绍");
            empty.add("§f这§a里§b显§d示§c的§e是§4可§d下§2注§6物§5品");
            framedata.setLore(empty);
            frame.setItemMeta(framedata);
            empty.clear();
            
            framedata.setDisplayName("§a下一页");
            empty.add("§a翻§c到§d下§f一§e页");
            framedata.setLore(empty);
            xiaye.setItemMeta(framedata);
            empty.clear();
            
            framedata.setDisplayName("§a上一页");
            empty.add("§f翻§b到§2上§3一§1页");
            framedata.setLore(empty);
            shangye.setItemMeta(framedata);
            empty.clear();
            
            if (type > 1) {
                gui.setItem(45, shangye);
            }
            else {
                gui.setItem(45, frame);
            }
            gui.setItem(46, frame);
            gui.setItem(47, frame);
            gui.setItem(48, frame);
            gui.setItem(49, frame);
            gui.setItem(50, frame);
            gui.setItem(51, frame);
            gui.setItem(52, frame);
            if (list.size() < 46 + (type - 1) * 45) {
                gui.setItem(53, frame);
            }
            else {
                gui.setItem(53, xiaye);
            }
            player.openInventory(gui);
            return;
        }
        if (type > 1) {
            player.closeInventory();
            list(player, --type);
            return;
        }
        player.sendMessage("§c里面没有内容，你打不开");
    }
}
