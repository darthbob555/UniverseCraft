package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemBlackholiumPickaxe extends ItemPickaxe {

	public ItemBlackholiumPickaxe(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "BlackholiumPickaxe");
		setTextureName(UniverseCraft.MODID + ":" + "BlackholiumPickaxe");
		setCreativeTab(UniverseCraft.universeCraft);
		setHarvestLevel("pickaxe", 100);
	}
}
