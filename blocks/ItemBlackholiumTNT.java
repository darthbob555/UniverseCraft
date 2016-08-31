package com.universeCraft.blocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlackholiumTNT extends ItemBlock{

	public ItemBlackholiumTNT(Block par1){
		super(par1);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[When blackholes go rouge]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "This TNT harnesses the power of blackholium. It doesn't like chunks,");
			dataList.add(EnumChatFormatting.AQUA + "so it likes to eat them. Have fun...just watch your step...");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
