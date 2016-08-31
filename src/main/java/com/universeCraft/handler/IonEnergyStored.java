package com.universeCraft.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class IonEnergyStored {
	
	public void showStatus(EntityPlayer player, World world, int energyStored, int energyCapacity) {
		if(world.isRemote && energyStored != -2 && energyCapacity != -2){
			float percentage = (float)(energyStored/(float)energyCapacity*100);
			String format;
			if(percentage <= 25){
				format = "\u00A7c";
			}
			else if(percentage <= 50){
				format = "\u00A76";
			}
			else if(percentage <= 75){
				format = "\u00A7b";
			}
			else{
				format = "\u00A7a";
			}
			player.addChatComponentMessage(new ChatComponentText(format + "Energy: " + energyStored + "/" + energyCapacity + "SE"));
			player.addChatComponentMessage(new ChatComponentText(format + "Energy: " + percentage + "%"));
		}
	}
}
