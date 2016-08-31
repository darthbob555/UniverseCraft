package com.universeCraft.blocks.generators;

import java.util.List;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.tileEntity.TileEntityAtomicPulveriser;
import com.universeCraft.tileEntity.TileEntityCarbonGenerator;
import com.universeCraft.tileEntity.TileEntityVertexGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockVertexGenerator extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockVertexGenerator() {
		super(Material.iron);
		setBlockName(UniverseCraft.MODID + "_" + "vertexGenerator");
		setCreativeTab(UniverseCraft.universeCraft);
		setResistance(5F);
		setHardness(2F);
		setHarvestLevel("pickaxe", 2);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if(entity != null){
			if(entity instanceof TileEntityVertexGenerator){
				TileEntityVertexGenerator te = (TileEntityVertexGenerator) entity;
				if(player.getCurrentEquippedItem() != null){
					if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(ModItems.Particles, 1, 7))){
						te.checkAmount(player);
					}
					else if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(ModItems.Particles, 1, 51))){
						if(te.module == 0){
							te.module++;
							player.getCurrentEquippedItem().stackSize--;
							if(world.isRemote){
								player.addChatComponentMessage(new ChatComponentText("Module successfully installed!"));
							}
						}
						else if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Module failed to install; module already installed!"));
						}
					}
					else if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(ModItems.Particles, 1, 52))){
						if(te.module == 1){
							te.module++;
							player.getCurrentEquippedItem().stackSize--;
							if(world.isRemote){
								player.addChatComponentMessage(new ChatComponentText("Module successfully installed!"));
							}
						}
						else if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Module failed to install; either the previous level of module is nt installed or the module already installed!"));
						}
					}
					else if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(ModItems.Particles, 1, 53))){
						if(te.module == 2){
							te.module++;
							player.getCurrentEquippedItem().stackSize--;
							if(world.isRemote){
								player.addChatComponentMessage(new ChatComponentText("Module successfully installed!"));
							}
						}
						else if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Module failed to install; either the previous level of module is nt installed or the module already installed!"));
						}
					}
				}
			}
		}
		return true;
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

	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityVertexGenerator();
	}
}
