package com.universeCraft.blocks;

import java.util.Random;

import com.universeCraft.main.UniverseCraft;
import com.universeCraft.tileEntity.TileEntityPartAccelBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPartAccelBlock extends BlockContainer{	

	private IIcon[] icons;

	public BlockPartAccelBlock(){
		super(Material.iron);
		setBlockName(UniverseCraft.MODID + "_" + "ToroidComponent");
		setBlockTextureName(UniverseCraft.MODID + ":" + "ToroidComponent");
		setCreativeTab(UniverseCraft.universeCraft);
		setHardness(10F);
		setResistance(6000F);
		setStepSound(soundTypeMetal);
	}

	@Override
	public int quantityDropped(Random rand){
		return 0;
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest){
		if(player.capabilities.isCreativeMode){
			world.setBlockToAir(x, y, z);
		}
		else{
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You are attempting to break the multiblock! Shift right click"));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "with a quantum wrench on the controller to deform the"));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "multiblock. All antimatter will be lost in the process. Right click"));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "with a star to form again."));
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityPartAccelBlock();
	}
}