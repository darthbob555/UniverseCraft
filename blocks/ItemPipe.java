package com.universeCraft.blocks;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemPipe extends ItemBlock{
	
	public ItemPipe(Block block) {
		super(block);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Transfers antimatter atoms]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Right click with a quantum wrench to see the current antimatter.");
			dataList.add(EnumChatFormatting.AQUA + "This pipe will balance its antimatter with other pipes and transfers");
			dataList.add(EnumChatFormatting.AQUA + "as much as a block can request from it.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}