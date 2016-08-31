package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class ItemCondensedAxe extends ItemAxe 
{
	public ItemCondensedAxe(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "CondensedAxe");
		setTextureName(UniverseCraft.MODID + ":" + "CondensedAxe");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}
