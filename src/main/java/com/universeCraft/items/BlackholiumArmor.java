package com.universeCraft.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class BlackholiumArmor extends ItemArmor{	
	
	public BlackholiumArmor(ArmorMaterial material, int armorType, String name){
		super(material, 0, armorType);
		setUnlocalizedName(UniverseCraft.MODID + "_" + name);
		setTextureName(UniverseCraft.MODID + ":" + name);
		setMaxDamage(5000000);
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
		if(itemstack.getTagCompound() == null){
			itemstack.setTagCompound(new NBTTagCompound());
			itemstack.getTagCompound().setInteger("Energy", 0);
		}
		if(itemstack.getItemDamage() > 0){
			itemstack.setItemDamage(0);
		}
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (stack.getItem() == ModItems.blackholiumBoots || stack.getItem() == ModItems.blackholiumChestplate || stack.getItem() == ModItems.blackholiumHelmet) {
			return UniverseCraft.MODID + ":models/armor/blackholium1.png";
		}
		else if(stack.getItem() == ModItems.blackholiumLeggings){
			return UniverseCraft.MODID + ":models/armor/blackholium2.png";
		}
		else{
			System.out.println("Invalid Item");
			return null;
		}
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Armor made out of dark matter]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Blackholium seems to be the most powerful substance ever;");
			dataList.add(EnumChatFormatting.AQUA + "and this armor seems to imbue the use of blackholium further.");
			dataList.add(EnumChatFormatting.AQUA + "This armor is highly protective and gives augments on top of");
			dataList.add(EnumChatFormatting.AQUA + "the properties that energised blackholium naturally yields.");
			dataList.add(EnumChatFormatting.GREEN + "Helmet will give water breathing and immunity to lava.");
			dataList.add(EnumChatFormatting.GREEN + "Chestplate will give regen 20 and resistance 20.");
			dataList.add(EnumChatFormatting.GREEN + "Leggings will give speed 20 and haste 20.");
			dataList.add(EnumChatFormatting.GREEN + "Boots will give jump 20.");
			dataList.add(EnumChatFormatting.AQUA + "Requires 100 SE/Tick to maintain effects");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
				dataList.add(EnumChatFormatting.GOLD + "");
				dataList.add(EnumChatFormatting.RED + "ENERGY: " + itemstack.getTagCompound().getInteger("Energy") + "/5000000");
			}
		}
	}
}