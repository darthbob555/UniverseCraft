package com.universeCraft.blocks.generators;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemStellarGenerator extends ItemBlock {

	public ItemStellarGenerator(Block par1){
		super(par1);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Generates 1000/tick]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "A nether star will produce 2 million SE.");
			dataList.add(EnumChatFormatting.AQUA + "A star will produce around that of its maximum");
			dataList.add(EnumChatFormatting.AQUA + "capacity. This will generate at a rate of 1000/tick");
			dataList.add(EnumChatFormatting.AQUA + "unless an increased output upgrade is installed.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
