package com.universeCraft.handler;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class VisualHandler {

	private int starsize;
	private Block block;
	
	public void begin(ItemStack itemstack){
		if(itemstack.getTagCompound() != null){
			if(itemstack.getTagCompound().hasKey("STARTYPE")){
				this.starsize = itemstack.getTagCompound().getInteger("STARTYPE");
			}
		}
		this.size(itemstack, this.starsize);
		this.material(itemstack, this.starsize);
	}
	
	public void size(ItemStack itemstack, int starsize){
		if(itemstack.getTagCompound() != null){
			if(itemstack.getTagCompound().hasKey("STARTYPE")){
				int radius = (starsize*7)+1;
			}
		}
	}
	
	public void material(ItemStack itemstack, int starsize){
		if(itemstack.getTagCompound() != null){
			if(itemstack.getTagCompound().hasKey("STARTYPE")){
				if(starsize == 0){
					
				}
			}
		}
	}
}
