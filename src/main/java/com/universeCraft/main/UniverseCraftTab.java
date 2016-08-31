package com.universeCraft.main;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.items.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class UniverseCraftTab extends CreativeTabs
{
	public UniverseCraftTab(int par1, String par2Str)
	{
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(ModBlocks.particleAccelerator);
	}
}