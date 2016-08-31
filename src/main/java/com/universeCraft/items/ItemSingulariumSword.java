package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemSingulariumSword extends ItemSword 
{
	public ItemSingulariumSword(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "SingulariumSword");
		setTextureName(UniverseCraft.MODID + ":" + "SingulariumSword");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}
