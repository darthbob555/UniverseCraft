package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;

public class ItemBlackholiumShovel extends ItemSpade {

	public ItemBlackholiumShovel(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "BlackholiumShovel");
		setTextureName(UniverseCraft.MODID + ":" + "BlackholiumShovel");
		setCreativeTab(UniverseCraft.universeCraft);
		setHarvestLevel("shovel", 100);
	}
}
