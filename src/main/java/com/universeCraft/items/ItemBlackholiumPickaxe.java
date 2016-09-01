package com.universeCraft.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.universeCraft.handler.IonEnergyStored;
import com.universeCraft.main.UniverseCraft;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBlackholiumPickaxe extends ItemPickaxe {

	IonEnergyStored energyStored = new IonEnergyStored();

	public ItemBlackholiumPickaxe(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "BlackholiumPickaxe");
		setTextureName(UniverseCraft.MODID + ":" + "BlackholiumPickaxe");
		setCreativeTab(UniverseCraft.universeCraft);
		setHarvestLevel("pickaxe", 100);
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
		if(itemstack.getTagCompound() == null){
			itemstack.setTagCompound(new NBTTagCompound());
			itemstack.getTagCompound().setInteger("Energy", 0);
			itemstack.getTagCompound().setInteger("Capacity", 5000000);
		}
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta){
		if(itemstack.getTagCompound().getInteger("Energy") >= 1000){
			return 100000000000000F;
		}
		else{
			return 0F;
		}
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player){
		if(itemstack.getTagCompound().getInteger("Energy") >= 1000){
			itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Energy")-1000);
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Protects the player]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "A very powerful pickaxe that has immense mining speed.");
			dataList.add(EnumChatFormatting.AQUA + "Costs 1000 SE per block mined.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
				dataList.add(EnumChatFormatting.GOLD + "");
				String format = energyStored.showStatusCapacitor(itemstack.getTagCompound().getInteger("Energy"), itemstack.getTagCompound().getInteger("Capacity"));
				float percentageF = (float)itemstack.getTagCompound().getInteger("Energy")/(float)itemstack.getTagCompound().getInteger("Capacity")*100;
				int percentage = (int)percentageF;
				dataList.add(EnumChatFormatting.BLUE + "ENERGY: " + itemstack.getTagCompound().getInteger("Energy") + "/" + itemstack.getTagCompound().getInteger("Capacity") + EnumChatFormatting.RESET + " (" + format + percentage + "% Full"+ EnumChatFormatting.RESET + ")");
			}
		}
	}
}
