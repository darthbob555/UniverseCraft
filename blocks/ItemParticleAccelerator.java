package com.universeCraft.blocks;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemParticleAccelerator extends ItemBlock {

	public ItemParticleAccelerator(Block par1){
		super(par1);
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Accelerates stone particles to create antimatter atoms]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Right click with a quantum wrench to see the current data.");
			dataList.add(EnumChatFormatting.AQUA + "Right click with materials (can be in block form) to fulfill");
			dataList.add(EnumChatFormatting.AQUA + "the block's needs. Once all materials are consumed, right click again");
			dataList.add(EnumChatFormatting.AQUA + "with the quantum wrench to build the structure.");
			dataList.add(EnumChatFormatting.AQUA + "Finally, right click with a star of solar mass >1 to form the structure.");
			dataList.add(EnumChatFormatting.AQUA + "Antimatter can be made by right clicking with stone on the multiblock.");
			dataList.add(EnumChatFormatting.AQUA + "This requires 10000 SE to do. Antimatter can be piped out with vacuum.");
			dataList.add(EnumChatFormatting.AQUA + "pipes to transfer to an atomic pulveriser.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}
