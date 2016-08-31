package com.universeCraft.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.universeCraft.tileEntity.TileEntityIonLarge;
import com.universeCraft.tileEntity.TileEntityIonMedium;
import com.universeCraft.tileEntity.TileEntityIonSmall;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemIonStorage extends ItemBlock{
	
	public ItemIonStorage(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		String name = "";
		switch(itemstack.getItemDamage()){
		case 0:
			name = "small";
			break;
		case 1:
			name = "medium";
			break;
		case 2:
			name = "tunnel";
			break;
		case 3:
			name = "large";
			break;
		case 4:
			name = "huge";
			break;
		case 5:
			name = "wirelessController";
			break;
		case 6:
			name = "duplication";
			break;
		case 7:
			name = "energy";
			break;
		case 8:
			name = "enricher";
			break;
		case 9:
			name = "creative";
			break;
		}
		return getUnlocalizedName() + "." + name;
	}

	@Override
	public int getMetadata(int par1){
		return par1;
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			if(itemstack.getItemDamage() == 0){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Stores stellar energy]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "The basic of the basic energy storage and stores little SE.");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Output/Input: 200SE/tick");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Capacity: 200,000SE");
				if(itemstack.hasTagCompound()){
					if(itemstack.getTagCompound().hasKey("Stored")){
						dataList.add("");
						dataList.add(EnumChatFormatting.DARK_GREEN + "Stored Energy: " +itemstack.getTagCompound().getInteger("Stored") + "/200,000SE");
					}
				}
			}
			else if(itemstack.getItemDamage() == 1){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Stores stellar energy]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "An upgraded form of the basic to store more SE.");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Output/Input: 800SE/tick");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Capacity: 800,000SE");
				if(itemstack.hasTagCompound()){
					if(itemstack.getTagCompound().hasKey("Stored")){
						dataList.add("");
						dataList.add(EnumChatFormatting.DARK_GREEN + "Stored Energy: " +itemstack.getTagCompound().getInteger("Stored") + "/800,000SE");
					}
				}
			}
			else if(itemstack.getItemDamage() == 2){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Creates in-world planets]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Right click with a star to copy traits.");
				dataList.add(EnumChatFormatting.AQUA + "Add in energy and it will create a cuboid planet.");
				dataList.add(EnumChatFormatting.AQUA + "The material is random from a list of 4 materials.");
				dataList.add(EnumChatFormatting.AQUA + "Costs vary depending on traits of star.");
				dataList.add(EnumChatFormatting.RED + "WARNING: Replaces blocks and can cost 1B+SE!" + EnumChatFormatting.BOLD);
			}
			else if(itemstack.getItemDamage() == 3){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Stores stellar energy]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "An upgraded form of the advanced to store more SE.");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Output/Input: 2000SE/tick");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Capacity: 5,000,000SE");
				if(itemstack.hasTagCompound()){
					if(itemstack.getTagCompound().hasKey("Stored")){
						dataList.add("");
						dataList.add(EnumChatFormatting.DARK_GREEN + "Stored Energy: " +itemstack.getTagCompound().getInteger("Stored") + "/5,000,000SE");
					}
				}
			}
			else if(itemstack.getItemDamage() == 4){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Stores stellar energy]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "A much more larger energy cell from the last tier.");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Output/Input: 20000SE/tick");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Capacity: 100,000,000SE");
				if(itemstack.hasTagCompound()){
					if(itemstack.getTagCompound().hasKey("Stored")){
						dataList.add("");
						dataList.add(EnumChatFormatting.DARK_GREEN + "Stored Energy: " +itemstack.getTagCompound().getInteger("Stored") + "/100,000,000SE");
					}
				}
			}
			else if(itemstack.getItemDamage() == 5){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Wireless transmission of energy]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This machine will scan the nearby area (default: 11)");
				dataList.add(EnumChatFormatting.AQUA + "in a 3D plane. The machine has a small internal buffer");
				dataList.add(EnumChatFormatting.AQUA + "of 100000 but can extract/insert this amount to any block.");
				dataList.add(EnumChatFormatting.AQUA + "Only generators can be extracted from; energy cells will be");
				dataList.add(EnumChatFormatting.AQUA + "replenished of energy. Two machines will NOT interact together.");
				dataList.add(EnumChatFormatting.AQUA + "Upgrades are available for this machine.");
				dataList.add("");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Output/Input: 100000SE/tick");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Capacity: 100000SE");
				if(itemstack.hasTagCompound()){
					if(itemstack.getTagCompound().hasKey("Stored")){
						dataList.add("");
						dataList.add(EnumChatFormatting.DARK_GREEN + "Stored Energy: " +itemstack.getTagCompound().getInteger("Stored") + "/100000000SE");
					}
				}
			}
			else if(itemstack.getItemDamage() == 6){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Duplicates items]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Requires 5 energised blackholium ingots and 50 million SE");
				dataList.add(EnumChatFormatting.AQUA + "to duplicate one item. However, it will keep the NBT of the");
				dataList.add(EnumChatFormatting.AQUA + "item duplicated. Right click on the block after the blackholium");
				dataList.add(EnumChatFormatting.AQUA + "ingots have been inserted and 50 million SE is procurred.");
				if(itemstack.hasTagCompound()){
					if(itemstack.getTagCompound().hasKey("Stored")){
						dataList.add("");
						dataList.add(EnumChatFormatting.DARK_GREEN + "Stored Energy: " +itemstack.getTagCompound().getInteger("Stored") + "/100000000SE");
					}
				}
			}
			else if(itemstack.getItemDamage() == 8){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Enriches and upgrades stars]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Right click with a star and the supply power.");
				dataList.add(EnumChatFormatting.AQUA + "Apply a redstone signal to spawn in the star");
				dataList.add(EnumChatFormatting.AQUA + "and upgrade accordingly. Unpower the redstone");
				dataList.add(EnumChatFormatting.AQUA + "to make it into an item again.");
			}
			else if(itemstack.getItemDamage() == 9){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Stores stellar energy]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Stores infinite SE");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Output/Input: INFINITE SE/tick");
				dataList.add(EnumChatFormatting.DARK_GREEN + "Capacity: -1SE");
				if(itemstack.hasTagCompound()){
					if(itemstack.getTagCompound().hasKey("Stored")){
						dataList.add("");
						dataList.add(EnumChatFormatting.DARK_GREEN + "Stored Energy: " +itemstack.getTagCompound().getInteger("Stored") + "/100,000,000SE");
					}
				}
			}
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
		}
	}
}