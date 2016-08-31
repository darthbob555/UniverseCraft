package com.universeCraft.blocks.generators;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.tileEntity.TileEntityStellarGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockStellarGenerator extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockStellarGenerator() {
		super(Material.iron);
		setBlockName(UniverseCraft.MODID + "_" + "stellarGenerator");
		setCreativeTab(UniverseCraft.universeCraft);
		setResistance(10F);
		setHardness(7F);
		setHarvestLevel("pickaxe", 2);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if(entity != null){
			if(entity instanceof TileEntityStellarGenerator){
				TileEntityStellarGenerator te = (TileEntityStellarGenerator) entity;
				if(player.getCurrentEquippedItem() != null){
					if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(ModItems.Particles, 1, 7))){
						te.checkAmount(player);
					}
					else if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(Items.nether_star))){
						te.generate(player.getCurrentEquippedItem(), 2000, player);
					}
					else if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(ModItems.StarBase))){
						te.starGenerate(player.getCurrentEquippedItem(), player);
					}
					else if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(ModItems.Particles, 1, 31))){
						if(te.module == 0){
							if(te.Generate == 0){
								te.module = 1;
								player.getCurrentEquippedItem().stackSize--;
								if(world.isRemote){
									player.addChatComponentMessage(new ChatComponentText("Module succesfully installed!"));
								}
							}
							else{
								if(world.isRemote){
									player.addChatComponentMessage(new ChatComponentText("Module failed to install; generator is currently generating!"));
								}
							}
						}
						else{
							if(world.isRemote){
								player.addChatComponentMessage(new ChatComponentText("Module failed to install; module already installed!"));
							}
						}
					}
					else if(player.getCurrentEquippedItem().isItemEqual(new ItemStack(ModItems.Particles, 1, 50))){
						if(te.module != 2 || te.module != 3){
							te.module +=2;
							player.getCurrentEquippedItem().stackSize--;
						}
						else{
							if(world.isRemote){
								player.addChatComponentMessage(new ChatComponentText("Module failed to install; module already installed!"));
							}
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
		return new TileEntityStellarGenerator();
	}
}
