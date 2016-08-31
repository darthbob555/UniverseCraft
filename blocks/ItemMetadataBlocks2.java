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

public class ItemMetadataBlocks2 extends ItemBlock{

	public ItemMetadataBlocks2(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		String name = "";
		switch(itemstack.getItemDamage()){
		case 0:
			name = "IntLimit";
			break;
		case 1:
			name = "ToroidComponent";
			break;
		case 2:
			name = "InterfaceO";
			break;
		case 3:
			name = "InterfaceI";
			break;
		case 4:
			name = "Core";
			break;
		case 5:
			name = "StellarCasingYellow";
			break;
		case 6:
			name = "StellarCasingLightRed";
			break;
		case 7:
			name = "StellarCasingDarkRed";
			break;
		case 8:
			name = "PalladiumOre";
			break;
		case 9:
			name = "RhodiumOre";
			break;
		case 10:
			name = "ZirconiumOre";
			break;
		case 11:
			name = "PalladiumBlock";
			break;
		case 12:
			name = "RhodiumBlock";
			break;
		case 13:
			name = "ZirconiumBlock";
			break;
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public int getMetadata(int par1){
		return par1;
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(itemstack.getItemDamage() == 0){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[The Integer Limit of EMC]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This block has the highest EMC possible.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
	}
}