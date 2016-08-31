package com.universeCraft.blocks.generators;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemCarbonGenerator extends ItemBlock{

	public ItemCarbonGenerator(Block par1){
		super(par1);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Generates 50SE/tick using carbon]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Right click with a coal item or coal block to generate SE.");
			dataList.add(EnumChatFormatting.AQUA + "Right click with the quantum wrench to see the current stats.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
