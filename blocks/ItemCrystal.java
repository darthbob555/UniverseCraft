package com.universeCraft.blocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemCrystal extends ItemBlock {

	public ItemCrystal(Block par1){
		super(par1);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[A solid glass formation]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Crystals can only be found in desert biomes and");
			dataList.add(EnumChatFormatting.AQUA + "will only spawn above ground. Also spawns in the END.");
			dataList.add(EnumChatFormatting.AQUA + "Right click with atoms to increase the power of the crystal.");
			dataList.add(EnumChatFormatting.AQUA + "Maximum of 5 upgrades. Refined dark matter finilises craft. ");
			dataList.add("");
			dataList.add(EnumChatFormatting.GREEN + "Antimatter - 2x");
			dataList.add(EnumChatFormatting.GREEN + "Diamond, Singularium, Emerald - 1.5x");
			dataList.add(EnumChatFormatting.GREEN + "Gold, Condensed - +5");
			dataList.add(EnumChatFormatting.GREEN + "Iron, Redstone - +3");
			dataList.add(EnumChatFormatting.GREEN + "Lapis, Quartz - +2");
			dataList.add(EnumChatFormatting.GREEN + "Coal - +1");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
