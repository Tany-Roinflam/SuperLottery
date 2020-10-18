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
        Inventory gui = Bukkit.createInventory((InventoryHolder)null, 54, "��e�¡�aע��c���b��");
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
        
        framedata.setDisplayName("��e�ָ���");
        lore.add("��f���a�ڡ�bȦ��d�ڡ�c�¡�eע");
        lore.add("��cͬһ��ֻ��ѡ��һ����ע����");
        framedata.setLore(lore);
        frame.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("��e��ע����f��");
        lore.add("��e��ע��ֵ1-98��Χ�ڵĵ���");
        lore.add("��a�¡�bע��c���6�ʡ�e��l1��3��l �� ��f��l" + Other.config.getInt("SadOdds"));
        framedata.setLore(lore);
        single.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("��e��ע��˫");
        lore.add("��f��ע����1-98��Χ�ڵ�˫��");
        lore.add("��a�¡�bע��c���6�ʡ�e��l1��3��l �� ��e��l" + Other.config.getInt("SadOdds"));
        framedata.setLore(lore);
        twin.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("��e��ע����bС");
        lore.add("��3��ע����1-33");
        lore.add("��a�¡�bע��c���6�ʡ�e��l1��3��l �� ��b��l" + Other.config.getInt("SizeOdds"));
        framedata.setLore(lore);
        small.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("��e��ע����3��");
        lore.add("��b��ע����67-99");
        lore.add("��a�¡�bע��c���6�ʡ�e��l1��3��l �� ��3��l" + Other.config.getInt("SizeOdds"));
        framedata.setLore(lore);
        big.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("��e��ע����d�ԡ�cѡ��e����9��");
        lore.add("��d�ԡ�cѡ��e����9��");
        lore.add("��a���������һ��1-99������");
        lore.add("��6�н���ΧΪ��");
        lore.add("��a�������һλ��ʱ����ΧΪ��10+��ֵ��20+��ֵ��30+��ֵ....90+��ֵ");
        lore.add("��b�������λ��Ϊ0��˫λ��ʱ����ΧΪ����ֵ+1����ֵ+2����ֵ+3...��ֵ+9");
        lore.add("��9������������������ʱ��ֻ�������Ǹ����ֲŻ��н�����������Ϊ���");
        lore.add("\n��b����aΧ��c����4�֡�a�¡�bע��c���6�ʡ�e��l1��3��l �� ��6��l" + Other.config.getInt("NumberOdds"));
        lore.add("��9ָ��6����c����4�֡�a�¡�bע��c���6�ʡ�e��l1��3��l �� ��4��l" + Other.config.getInt("SuperOdds"));
        framedata.setLore(lore);
        number.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("��eȷ��a����d�¡�3ע ��e���ͣ���6��Ϸ��");
        lore.add("��e��ע����Ϊ��Ϸ��");
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            lore.add("��c��l�����Ͳ�����");
        }
        framedata.setLore(lore);
        money.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("��eȷ��a����d�¡�3ע ���ͣ���5����");
        lore.add("��d��ע����Ϊ����");
        framedata.setLore(lore);
        xp.setItemMeta(framedata);
        lore.clear();
        
        framedata.setDisplayName("��eȷ��a����d�¡�3ע");
        lore.add("��9ȷ��");
        lore.add("��4ע�⣡��6һ��ȷ�Ϻ��޷����ģ�");
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
                    p.sendMessage("��c������/sl draw��ȡ��ʣ�µ���Ʒ������ע");
                    return;
                }
                continue;
            }
        }
        p.openInventory(gui);
    }
    
    public static void list(Player player, Integer type) {
        ItemStack frame = BasicLibrary.stainedglass.get(4);
        Inventory gui = Bukkit.createInventory((InventoryHolder)null, 54, "��a����ע�б��b����2�ڡ�6" + type + "��2ҳ");
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
                lore.add("��e����ע����Ʒ");
                if (player.isOp()) {
                    lore.add("��c����Ƴ�����Ʒ");
                }
                framedata.setLore(lore);
                item.setItemMeta(framedata);
                lore.clear();
                gui.setItem(ia, item);
            }
            ItemMeta framedata = frame.getItemMeta();
            ArrayList<String> empty = new ArrayList<String>();
            
            framedata.setDisplayName("��c���5��");
            empty.add("��f���a���b�ԡ�dʾ��c�ġ�e�ǡ�4�ɡ�d�¡�2ע��6���5Ʒ");
            framedata.setLore(empty);
            frame.setItemMeta(framedata);
            empty.clear();
            
            framedata.setDisplayName("��a��һҳ");
            empty.add("��a����c����d�¡�fһ��eҳ");
            framedata.setLore(empty);
            xiaye.setItemMeta(framedata);
            empty.clear();
            
            framedata.setDisplayName("��a��һҳ");
            empty.add("��f����b����2�ϡ�3һ��1ҳ");
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
        player.sendMessage("��c����û�����ݣ���򲻿�");
    }
}
