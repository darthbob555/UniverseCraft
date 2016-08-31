package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ItemCondensedPickaxe extends ItemPickaxe 
{
	public ItemCondensedPickaxe(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "CondensedPickaxe");
		setTextureName(UniverseCraft.MODID + ":" + "CondensedPickaxe");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}
