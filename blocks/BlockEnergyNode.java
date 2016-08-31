package com.universeCraft.blocks;

import org.lwjgl.input.Keyboard;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.render.RenderEnergyNode;
import com.universeCraft.render.RenderPipe;
import com.universeCraft.tileEntity.TileEntityEnergyNode;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEnergyNode extends BlockContainer {

	public BlockEnergyNode() {
		super(Material.iron);
		setBlockName(UniverseCraft.MODID + "_" + "energyNode");
		setBlockTextureName(UniverseCraft.MODID + ":" + "energyNodeConnection");
		setCreativeTab(UniverseCraft.universeCraft);
		setResistance(5F);
		setHardness(5F);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack itemstack = player.getCurrentEquippedItem();
		TileEntity entity = world.getTileEntity(x, y, z);
		if(itemstack != null && entity != null){
			if(entity instanceof TileEntityEnergyNode){
				TileEntityEnergyNode te = (TileEntityEnergyNode) entity;
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 7))){
					if(world.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Energy: " + te.storedEnergy));
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 69))){
					if((Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))){
						if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Coords")){
							int[] link = itemstack.getTagCompound().getIntArray("Coords");
							te.linkUp(link);
							if(world.isRemote){
								player.addChatComponentMessage(new ChatComponentText("Connection secured at location: X " + te.link[0] + " Y " + te.link[1] + " Z " + te.link[2]));
							}
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		setBlockBounds(0.20f, 0.20f, 0.20f, 0.77f, 0.77f, 0.77f);
	}
	
	@Override
	public int getRenderType()
	{
		return RenderEnergyNode.renderId;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
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
	public TileEntity createNewTileEntity(World world, int par2){
		return new TileEntityEnergyNode();
	}
}
