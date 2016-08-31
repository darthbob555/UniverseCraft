package com.universeCraft.blocks;

import org.lwjgl.input.Keyboard;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.tileEntity.TileEntityParticleAccelerator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockParticleAccelerator extends BlockContainer{

	public BlockParticleAccelerator() {
		super(Material.iron);
		setBlockName(UniverseCraft.MODID + "_" + "ParticleAccelerator");
		setBlockTextureName(UniverseCraft.MODID + ":" + "ParticleAccelerator");
		setCreativeTab(UniverseCraft.universeCraft);
		setHardness(3F);
		setResistance(10F);
		setStepSound(soundTypeMetal);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		NBTTagCompound tagCompound = new NBTTagCompound();
		TileEntity entity = world.getTileEntity(x, y, z);
		ItemStack itemstack = player.inventory.getCurrentItem();
		if(entity != null && entity instanceof TileEntityParticleAccelerator){
			TileEntityParticleAccelerator te = (TileEntityParticleAccelerator) entity;
			if(itemstack != null){
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 25))){
					if(te.status.equals("multiblock")){
						if(te.module != 1){
							te.module = 1;
							itemstack.stackSize--;
							player.addChatComponentMessage(new ChatComponentText("Safety module installed successfully"));
						}
						else{
							player.addChatComponentMessage(new ChatComponentText("Safety module failed to install; already installed previously"));
						}
					}
					else{
						player.addChatComponentMessage(new ChatComponentText("Safety module failed to install; particle accelerator is not a multiblock yet"));
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 7))){
					if(te.status.equals("built")){
						te.updateStatus(world, x, y, z, player);
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("The Particle Accelerator Has Been Constructed"));
							player.addChatComponentMessage(new ChatComponentText("Right Click With A Star To Form"));
						}
					}
					else if(te.status.equals("")){
						for(int radiusX = -18; radiusX<18; radiusX++){
							for(int radiusZ = -18; radiusZ<18; radiusZ++){
								world.spawnParticle("flame", x+radiusX+0.5, y+0.5, z+radiusZ+0.5, 0.0D, 0.0D, 0.0D);
							}
						}
						te.displayData(player);
					}
					else if(te.status.equals("constructed")){
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Right Click With A Star To Form"));
						}
						else{
							player.addChatComponentMessage(new ChatComponentText("The Particle Accelerator Has Not Been Constructed"));
						}
					}
					else{
						te.displayDataProcessing(player);
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(ModItems.StarBase))){
					if(te.status.equals("constructed")){
						if(itemstack.getTagCompound() != null){
							if(itemstack.getTagCompound().getDouble("STARMASS") > 1){
								te.buildLayer();
							}
							else{
								player.addChatComponentMessage(new ChatComponentText("This Star Does Not Have Enough Mass To Form The Structure"));
							}
						}
						else{
							if(world.isRemote){
								player.addChatComponentMessage(new ChatComponentText("This Star Does Not Have Enough Mass To Form The Structure"));
							}
						}
					}
					else if(!te.status.equals("multiblock")){
						player.addChatComponentMessage(new ChatComponentText("The Particle Accelerator Has Not Been Constructed"));
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(Blocks.bedrock)) && player.capabilities.isCreativeMode){
					te.creativeMode();
				}
				else{
					if(te.status.equals("")){
						te.consumeItems(player, tagCompound);
					}
				}
			}
		}
		return true;
	}

	public TileEntity createNewTileEntity(World world, int par2)
	{
		return new TileEntityParticleAccelerator();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		TileEntity entity = world.getTileEntity(x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
		if(entity != null){
			if(entity instanceof TileEntityParticleAccelerator){
				TileEntityParticleAccelerator te = (TileEntityParticleAccelerator) entity;
				if(te.status.equals("multiblock")){
					te.showMatter(world, x, y, z);
					world.setBlock(x, y, z, ModBlocks.partAccelBlock, 1, 2);
				}
			}
		}
	}
}