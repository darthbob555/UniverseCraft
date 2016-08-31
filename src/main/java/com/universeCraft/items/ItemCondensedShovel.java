package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ItemCondensedShovel extends ItemSpade
{
	public ItemCondensedShovel(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "CondensedShovel");
		setTextureName(UniverseCraft.MODID + ":" + "CondensedShovel");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}

