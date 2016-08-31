package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;

public class ItemBlackholiumAxe extends ItemAxe {

	public ItemBlackholiumAxe(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "BlackholiumAxe");
		setTextureName(UniverseCraft.MODID + ":" + "BlackholiumAxe");
		setCreativeTab(UniverseCraft.universeCraft);
		setHarvestLevel("axe", 100);
	}
}
