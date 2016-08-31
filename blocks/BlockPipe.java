package com.universeCraft.blocks;

import java.util.List;
import java.util.Random;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.render.RenderPipe;
import com.universeCraft.tileEntity.TileEntityCarbonGenerator;
import com.universeCraft.tileEntity.TileEntityVacuumPipe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPipe extends BlockContainer{

	private IIcon[] icons;

	public BlockPipe(){
		super(Material.iron);
		setHardness(1.0F);
		setStepSound(soundTypeMetal);
		setBlockName(UniverseCraft.MODID + "_" + "pipe");
		setBlockTextureName(UniverseCraft.MODID + ":" + "Pipe");
		setCreativeTab(UniverseCraft.universeCraft);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		TileEntity entity = world.getTileEntity(x, y, z);
		ItemStack itemstack = player.getCurrentEquippedItem();
		if(entity != null && itemstack != null){
			if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 7))){
				if(entity instanceof TileEntityVacuumPipe){
					TileEntityVacuumPipe pipe = (TileEntityVacuumPipe) entity;
					if(world.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Current stored antimatter: " + pipe.storedAntimatter + "/100"));
					}
				}
				return true;
			}
			return false;
		}
		else{
			return false;
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		setBlockBounds(0.20f, 0.20f, 0.20f, 0.77f, 0.77f, 0.77f);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return RenderPipe.renderId;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityVacuumPipe();
	}
}