package com.tany.superlottery;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.comphenix.protocol.utility.StreamSerializer;

public class Way {
//		�����ж��Ƿ�ΪOP
		public static boolean OpUseCommand(CommandSender player) {
			if(player.isOp())
			return false;
			else
			return true;
		}
		
//		�ж�����Ƿ�ΪOP
		public static boolean OpUse(Player player) {
			if(player.isOp())
			return false;
			else
			return true;
		}
		
//		�ж�����Ƿ�ΪOP
		public static boolean ConsoleUse(CommandSender sender) {
			if(sender instanceof Player)
			return false;
			else
			return true;
		}
		
//		ItemStackתString
		public static String GetItemData(ItemStack item) {
			String a;
			int amount = item.getAmount();
			item.setAmount(1);
			try {
			    a = new StreamSerializer().serializeItemStack(item);
			} catch (Exception e) {
			    a = null;
			}
			item.setAmount(amount);
			return a;
		}
//		StringתItemStack
		public static ItemStack GetItemStack(String data) {
			try {
				return new StreamSerializer().deserializeItemStack(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
//		��ȡ��ұ����ո���
		public static int Backpack(Player player) {
			int a=0;
			int b=0;
			while(a<=35) {
				if(player.getInventory().getItem(a)==null) {
					b++;
				}
				a++;
			}
			return b;
		}
		
//		����/�۳���ҵľ���
		public static  void giveExp(Player player, int exp) {
			int level = player.getLevel();
			int toNextLevel = getUpgradeExp(level);
			int floatExp = (int) (player.getExp() * toNextLevel) + exp;
          	while(floatExp >= toNextLevel) {
        	  	floatExp -= toNextLevel;
        	  	toNextLevel = getUpgradeExp(++level);
          	}
          	while(floatExp < 0) {
          		floatExp += (toNextLevel = getUpgradeExp(--level));
                  	if(level < 0) 
                  		level = floatExp = 0;
          	}
          	player.setLevel(level);
          	player.setExp( (float) floatExp / toNextLevel);
          	player.setTotalExperience(getTotalExp(level) + floatExp);
	  	}
  
	  	public static int getUpgradeExp(int level) {
	  		return level < 16  ? level * 2 + 7 : level < 30 ? level * 5 - 38 : level * 9 - 158;
  		}
  
  		public static int getTotalExp(int level) {
  			return (int) (level < 17 ? level * (level + 6) : level < 31 ? level * (level * 2.5 - 40.5) + 360 : level * (level * 4.5 - 162.5) + 2220);
  		}
}
