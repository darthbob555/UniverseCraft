package com.universeCraft.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockMetadataBlocks extends Block{	
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockMetadataBlocks(){
		super(Material.rock);
		setBlockName(UniverseCraft.MODID + "_" + "MetaDataBlock");
		setCreativeTab(UniverseCraft.universeCraft);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public int damageDropped(int meta){
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		icons = new IIcon[16];
		for (int i = 0; i < icons.length; i++){
			icons[i] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "MetaDataBlock" + i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2){
		return icons[par2];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List){
		for (int var4 = 0; var4 < 16; ++var4){
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z){
		int metadata = world.getBlockMetadata(x, y, z);
		switch(metadata){
		case 6:
			return -1F;
		default:
			setResistance(5F);
			return 2.5F;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		Random rand = new Random();
		ItemStack itemstack = player.getCurrentEquippedItem();
		if(itemstack != null){
			if(world.getBlockMetadata(x, y, z) == 9){
				if(itemstack.isItemEqual(new ItemStack(ModItems.StarBase))){
					if(itemstack.getTagCompound() != null){
						if(itemstack.getTagCompound().hasKey("ENERGY")){
							if(itemstack.getTagCompound().getInteger("ENERGY") > 1000){
								itemstack.getTagCompound().setInteger("ENERGY", itemstack.getTagCompound().getInteger("ENERGY")-1000);
								EntityLightningBolt entity1 = new EntityLightningBolt(world, x+rand.nextInt(5)-+rand.nextInt(10), y, z+rand.nextInt(5)-+rand.nextInt(10));
								for(int i = 0; i<10; i++){
									world.spawnEntityInWorld(entity1);
								}
								EntityItem item = new EntityItem(world);
								item = new EntityItem(world, x, y+1, z, new ItemStack(ModItems.Particles, 1+rand.nextInt(2), 26));
								if(!world.isRemote){
									world.spawnEntityInWorld(item);
								}
							}
							else{
								if(world.isRemote){
									player.addChatComponentMessage(new ChatComponentText("Cannot extract dark matter - not enough energy stored in the star"));
								}
							}
						}
					}
				}
				else{
					if(world.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Cannot extract dark matter - insert a stellar catalyst"));
					}
				}
			}
			else if(world.getBlockMetadata(x, y, z) == 10){
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 29))){
					if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Power")){
						int cap = 10;
						int amount = itemstack.getTagCompound().getInteger("Power");
						EntityItem blankCore = new EntityItem(world, x, y, z, new ItemStack(ModItems.Particles, amount, 30));
						EntityItem darkMatter = new EntityItem(world, x, y, z, new ItemStack(ModItems.Particles, 1, 26));
						EntityItem RdarkMatter = new EntityItem(world, x, y, z, new ItemStack(ModItems.Particles, 1, 28));
						if(!world.isRemote){
							for(int i = amount; i>0; i--){
								if(rand.nextInt(amount*2) > 3){
									cap--;
									if(cap > 0){
										world.spawnEntityInWorld(darkMatter);
									}
								}
								else if(rand.nextInt(amount) > 10){
									cap--;
									if(cap > 0){
										world.spawnEntityInWorld(RdarkMatter);
									}
								}
							}
							world.spawnEntityInWorld(blankCore);
						}
						itemstack.stackSize--;
					}
					return true;
				}
			}
			else if(world.getBlockMetadata(x, y, z) == 12){
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 30))){
					if(!world.isRemote){
						EntityItem entity = new EntityItem(world, x, y+1, z, new ItemStack(ModItems.Particles, 1, 43));
			        	world.spawnEntityInWorld(entity);
					}
					world.setBlockToAir(x, y, z);
					itemstack.stackSize--;
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		Random rand = new Random();
		if(metadata == 7){
			drops.add(new ItemStack(ModItems.Particles, 1+rand.nextInt(4), 4));
			drops.add(new ItemStack(ModItems.Particles, 1+rand.nextInt(4), 5));
		}
		else{
			drops.add(new ItemStack(ModBlocks.metadataBlocks, 1, metadata));
		}
		return drops;
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity par5Entity){
		if(world.getBlockMetadata(x, y, z) == 7) par5Entity.setFire(5);
	}
}