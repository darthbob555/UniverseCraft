package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ItemSingulariumPickaxe extends ItemPickaxe 
{
	public ItemSingulariumPickaxe(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "SingulariumPickaxe");
		setTextureName(UniverseCraft.MODID + ":" + "SingulariumPickaxe");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}
