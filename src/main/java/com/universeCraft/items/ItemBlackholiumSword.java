package com.universeCraft.items;

import com.universeCraft.main.UniverseCraft;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemBlackholiumSword extends ItemSword {

	public ItemBlackholiumSword(ToolMaterial material, String name){
		super(material);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "BlackholiumSword");
		setTextureName(UniverseCraft.MODID + ":" + "BlackholiumSword");
		setCreativeTab(UniverseCraft.universeCraft);
	}
}
