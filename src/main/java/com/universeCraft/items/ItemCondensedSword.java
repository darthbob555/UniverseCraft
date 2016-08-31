package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemCondensedSword extends ItemSword 
{
	public ItemCondensedSword(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "CondensedSword");
		setTextureName(UniverseCraft.MODID + ":" + "CondensedSword");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}
