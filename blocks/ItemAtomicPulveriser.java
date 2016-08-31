package com.universeCraft.blocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemAtomicPulveriser extends ItemBlock {

	public ItemAtomicPulveriser(Block block) {
		super(block);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[A machine to smash atoms on a larger scale]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "This block takes blocks of material and uses power to create");
			dataList.add(EnumChatFormatting.AQUA + "shards of material. These shards are much more useful and pure;");
			dataList.add(EnumChatFormatting.AQUA + "allowing much more refined crafting to take place.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
