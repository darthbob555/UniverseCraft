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

public class ItemMetadataBlocks extends ItemBlock{
	
	public ItemMetadataBlocks(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		String name = "";
		switch(itemstack.getItemDamage()){
		case 0:
			name = "CondensedBlock";
			break;
		case 1:
			name = "SingulariumBlock";
			break;
		case 2:
			name = "CondensedOre";
			break;
		case 3:
			name = "SingulariumOre";
			break;
		case 4:
			name = "ToroidMagnet";
			break;
		case 5:
			name = "ToroidConductor";
			break;
		case 6:
			name = "FusedDiamonicBlock";
			break;
		case 7:
			name = "PlasmaticStone";
			break;
		case 8:
			name = "DarkMatter";
			break;
		case 9:
			name = "InterStellarExtractor";
			break;
		case 10:
			name = "PaperStack";
			break;
		case 11:
			name = "CompressedDiamond";
			break;
		case 12:
			name = "DenseDiamond";
			break;
		case 13:
			name = "BlackholiumBlock";
			break;
		case 14:
			name = "EnergisedBlackholiumBlock";
			break;
		case 15:
			name = "RefinedDarkMatter";
			break;
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public int getMetadata(int par1){
		return par1;
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(itemstack.getItemDamage() == 2){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A collection of condensed stone and minerals]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This block is better than iron and seems to");
				dataList.add(EnumChatFormatting.AQUA + "be durable. It will likely be needed for");
				dataList.add(EnumChatFormatting.AQUA + "reinforcing machines.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 3){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A collection of hypercondensed stone and minerals]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This block is better than diamond and is very");
				dataList.add(EnumChatFormatting.AQUA + "durable. It is going to be needed for high.");
				dataList.add(EnumChatFormatting.AQUA + "tier tools.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 4){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Part of the particle accelerator construction]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This block is used to make the particle");
				dataList.add(EnumChatFormatting.AQUA + "accelerator multiblock.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 5){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Part of the particle accelerator construction]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This block is used to make the particle");
				dataList.add(EnumChatFormatting.AQUA + "accelerator multiblock.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 6){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Fused diamond atoms]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This block is very dense from the diamond");
				dataList.add(EnumChatFormatting.AQUA + "properties. Its properties may make it useful");
				dataList.add(EnumChatFormatting.AQUA + "in containing matter");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 7){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Soldified plasma from stellar objects]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This block is very hot and will burn when in");
				dataList.add(EnumChatFormatting.AQUA + "one's inventory. It seems too unstable to drop");
				dataList.add(EnumChatFormatting.AQUA + "itself, rather atoms of fusion.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 9){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Extracts dark matter from the air]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This block uses star energy to extract dark matter");
				dataList.add(EnumChatFormatting.AQUA + "from the air. This block can extract between 1-3 dark");
				dataList.add(EnumChatFormatting.AQUA + " matter per operation.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 10){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A block of paper]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Right click with a radiant core to get blank cores.");
				dataList.add(EnumChatFormatting.AQUA + "The higher the power of the radiant core, the more");
				dataList.add(EnumChatFormatting.AQUA + "the chance of bonus matter and blank cores.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 11){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Condensed diamond blocks]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "9 diamond blocks into a single block.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 12){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Highly compressed diamond blocks]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "81 diamond blocks into a single block.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
	}
}