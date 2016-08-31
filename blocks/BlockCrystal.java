package com.universeCraft.blocks;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.render.RenderCrystal;
import com.universeCraft.tileEntity.TileEntityCrystal;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCrystal extends BlockContainer {

	protected BlockCrystal(){
		super(Material.glass);
		setBlockName(UniverseCraft.MODID + "_" + "crystal");
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

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack itemstack = player.getCurrentEquippedItem();
		TileEntity entity = world.getTileEntity(x, y, z);
		if(entity != null && itemstack != null){
			if(entity instanceof TileEntityCrystal){
				TileEntityCrystal te = (TileEntityCrystal) entity;
				if(te.limit != 0){
					if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 9))){
						te.power *=1.5; 
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 10))){
						te.power +=5;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 11))){
						te.power *=1.5;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 12))){
						te.power +=3;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 13))){ 
						te.power +=2;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 14))){ 
						te.power +=2;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 15))){
						te.power +=2;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 16))){
						te.power +=5;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 17))){
						te.power *=1.5;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 18))){
						te.power +=1;
						te.limit--;
						itemstack.stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 19))){
						te.power *=2;
						te.limit--;
						itemstack.stackSize--;
					}
				}
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 28))){
					if(!te.primed && !te.explode){
						itemstack.stackSize--;
						te.primed = true;
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(Blocks.tnt))){
					if(te.primed && !te.explode){
						itemstack.stackSize--;
						te.explode = true;
						te.primed = false;
					}
				}
				if(world.isRemote){
					player.addChatComponentMessage(new ChatComponentText("Current power output: " + te.power));
					player.addChatComponentMessage(new ChatComponentText("Upgrades left: " + te.limit));
				}
			}
		}
		return true;
	}

	public TileEntity createNewTileEntity(World world, int par2){
		return new TileEntityCrystal();
	}
}
