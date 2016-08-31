package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ItemSingulariumShovel extends ItemSpade
{
	public ItemSingulariumShovel(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "SingulariumShovel");
		setTextureName(UniverseCraft.MODID + ":" + "SingulariumShovel");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}

