package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemAxe;

public class ItemSingulariumAxe extends ItemAxe 
{
	public ItemSingulariumAxe(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "SingulariumAxe");
		setTextureName(UniverseCraft.MODID + ":" + "SingulariumAxe");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}
