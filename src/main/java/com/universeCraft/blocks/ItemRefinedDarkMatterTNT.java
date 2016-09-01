package com.universeCraft.blocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemRefinedDarkMatterTNT extends ItemBlock{

	public ItemRefinedDarkMatterTNT(Block par1){
		super(par1);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Matter that hates over matter]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Refined dark matter may be inert to light but it does");
			dataList.add(EnumChatFormatting.AQUA + "react vigorously with other matter.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
