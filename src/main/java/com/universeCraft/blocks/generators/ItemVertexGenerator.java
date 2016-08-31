package com.universeCraft.blocks.generators;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemVertexGenerator extends ItemBlock {

	public ItemVertexGenerator(Block par1){
		super(par1);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Generates upto 25SE/tick]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Generates SE using no resources but is dependant on factors.");
			dataList.add(EnumChatFormatting.AQUA + "If there are few air blocks next to the generator, then the");
			dataList.add(EnumChatFormatting.AQUA + "SE generated is reduced due to a lack of ventillation overall.");
			dataList.add(EnumChatFormatting.AQUA + "If there is a block directly above the generator, then the");
			dataList.add(EnumChatFormatting.AQUA + "output will be 0. Smoke will be displayed when the ventillation");
			dataList.add(EnumChatFormatting.AQUA + "is reduced by non air blocks next to the generator.");
			dataList.add(EnumChatFormatting.AQUA + "Right click with a quantum wrench to check its stats.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
