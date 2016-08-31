package com.universeCraft.blocks;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.tileEntity.TileEntityAtomicPulveriser;
import com.universeCraft.tileEntity.TileEntityParticleAccelerator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockAtomicPulveriser extends BlockContainer {

	public BlockAtomicPulveriser() {
		super(Material.iron);
		setBlockName(UniverseCraft.MODID + "_" + "pulveriser");
		setCreativeTab(UniverseCraft.universeCraft);
		setResistance(5F);
		setHardness(5F);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public int getRenderType(){
		return -1;
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public boolean isNormalCube(){
		return false;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack itemstack = player.getCurrentEquippedItem();
		TileEntity entity = world.getTileEntity(x, y, z);
		if(itemstack != null && entity != null){
			if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 7))){
				if(entity instanceof TileEntityAtomicPulveriser){
					TileEntityAtomicPulveriser te = (TileEntityAtomicPulveriser) entity;
					te.displayerData(player);
				}
			}
		}
		return true;
	}

	public TileEntity createNewTileEntity(World world, int par2)
	{
		return new TileEntityAtomicPulveriser();
	}
}
