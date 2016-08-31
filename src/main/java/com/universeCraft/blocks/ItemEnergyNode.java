package com.universeCraft.blocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemEnergyNode extends ItemBlock{

	public ItemEnergyNode(Block par1){
		super(par1);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Transmits/receives energy]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Shift right click with a quantum wrench to change mode.");
			dataList.add(EnumChatFormatting.AQUA + "TRANSMIT MODE: Sends out energy to a receiver.");
			dataList.add(EnumChatFormatting.AQUA + "RECEIVE MODE: Retrieves energy from a transmitter.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
