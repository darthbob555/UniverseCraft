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

public class DarkMatterArmor extends ItemArmor{	

	public DarkMatterArmor(ArmorMaterial material, int armorType, String name){
		super(material, 0, armorType);
		setUnlocalizedName(UniverseCraft.MODID + "_" + name);
		setTextureName(UniverseCraft.MODID + ":" + name);
		setMaxDamage(50000);
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
		if(stack.getItem() == ModItems.darkMatterBoots || stack.getItem() == ModItems.darkMatterChestplate || stack.getItem() == ModItems.darkMatterHelmet) {
			return UniverseCraft.MODID + ":models/armor/darkMatter1.png";
		}
		else if(stack.getItem() == ModItems.darkMatterLeggings){
			return UniverseCraft.MODID + ":models/armor/darkMatter2.png";
		}
		else{
			return null;
		}
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			dataList.add(EnumChatFormatting.DARK_AQUA + "[Armor made out of dark matter]");
			dataList.add("");
			dataList.add(EnumChatFormatting.AQUA + "Dark matter doesn't seem to react much with anything;");
			dataList.add(EnumChatFormatting.AQUA + "and so doesn't protect from much. However, though it is");
			dataList.add(EnumChatFormatting.AQUA + "not durable either, it seems to have little mass thanks to");
			dataList.add(EnumChatFormatting.AQUA + "its low density, and so is less affected by gravity.");
			dataList.add(EnumChatFormatting.AQUA + "Helmet will give invisibility, chestplate negates effects from");
			dataList.add(EnumChatFormatting.AQUA + "a blackhole, leggings will give night vision and boots will give");
			dataList.add(EnumChatFormatting.AQUA + "less vertical gravity. A full set of armor will slowly recharge");
			dataList.add(EnumChatFormatting.AQUA + "variable stars. The rate is 0.0025% (1 in 400) chance per tick");
			dataList.add(EnumChatFormatting.AQUA + "to recharge once. Requires 20 SE/Tick to maintain effects.");
		}
		else{
			dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
				dataList.add(EnumChatFormatting.GOLD + "");
				dataList.add(EnumChatFormatting.RED + "ENERGY: " + itemstack.getTagCompound().getInteger("Energy") + "/50000");
			}
		}
	}
}