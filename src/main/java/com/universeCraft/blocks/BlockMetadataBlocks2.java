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
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockMetadataBlocks2 extends Block{	

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockMetadataBlocks2(){
		super(Material.rock);
		setBlockName(UniverseCraft.MODID + "_" + "MetaDataBlock2");
		setCreativeTab(UniverseCraft.universeCraft);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 2);
		setResistance(5F);
		setHardness(2F);
	}

	@Override
	public int damageDropped(int meta){
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		icons = new IIcon[14];
		for (int i = 0; i < icons.length; i++){
			icons[i] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "MetaDataBlock2" + i);
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
		for (int var4 = 0; var4 < 14; ++var4){
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity par5Entity){
		if(world.getBlockMetadata(x, y, z) == 4) par5Entity.setFire(5);
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest){
		if(world.getBlockMetadata(x, y, z) == 1){
			if(player.capabilities.isCreativeMode){
				world.setBlockToAir(x, y, z);
			}
			else{
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You are attempting to break the multiblock! Shift right click"));
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "with a quantum wrench on the controller to deform the"));
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "multiblock. All antimatter will be lost in the process. Right click"));
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "with a star to form again."));
			}
		}
		else{
			world.setBlockToAir(x, y, z);
		}
		return true;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		Random rand = new Random();
		if(metadata == 1){}
		else{
			drops.add(new ItemStack(ModBlocks.metadataBlocks2, 1, metadata));
		}
		return drops;
	}
}