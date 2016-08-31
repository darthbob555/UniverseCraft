package com.universeCraft.blocks.generators;

import java.util.List;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
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
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCarbonGenerator extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockCarbonGenerator() {
		super(Material.iron);
		setBlockName(UniverseCraft.MODID + "_" + "carbonGenerator");
		setCreativeTab(UniverseCraft.universeCraft);
		setResistance(5F);
		setHardness(2F);
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

		if(entity != null){
			if(entity instanceof TileEntityCarbonGenerator){
				TileEntityCarbonGenerator te = (TileEntityCarbonGenerator) entity;
				if(itemstack != null){
					if(itemstack.isItemEqual(new ItemStack(Items.coal))){
						te.generate(50);
						player.inventory.consumeInventoryItem(Items.coal);
					}
					else if(itemstack.isItemEqual(new ItemStack(Blocks.coal_block))){
						te.generate(450);
						player.inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.coal_block));
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 18))){
						te.generate(450);
						player.inventory.getCurrentItem().stackSize--;
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 7))){
						te.checkAmount(player);
					}
				}
				else{
					te.checkAmount(player);
				}
			}
		}
		return true;
	}

	public TileEntity createNewTileEntity(World world, int metadata)
	{
			return new TileEntityCarbonGenerator();
	}
}
