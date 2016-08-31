package com.universeCraft.blocks;

import com.universeCraft.entity.EntityBlackholiumTNTPrimed;
import com.universeCraft.main.UniverseCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockBlackholiumTNT extends BlockTNT{
	
	public IIcon[] icons1 = new IIcon[6];
	
	public BlockBlackholiumTNT(){
		super();
		this.setHardness(0.0F);
		this.setStepSound(soundTypeGrass);
		this.setBlockName(UniverseCraft.MODID + "_" + "BlackholiumTNT");
		this.setBlockTextureName(UniverseCraft.MODID + ":" + "BlackholiumTNT");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return this.icons1[side];
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for (int i = 0; i < 6; i++) {
			this.icons1[i] = reg.registerIcon(this.textureName + "_" + i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion){
		if (!par1World.isRemote){
			EntityBlackholiumTNTPrimed entitytntprimed = new EntityBlackholiumTNTPrimed(par1World, (double) ((float) par2 + 0.5F), (double) ((float) par3 + 0.5F), (double) ((float) par4 + 0.5F), par5Explosion.getExplosivePlacedBy());
			entitytntprimed.fuse = par1World.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
			par1World.spawnEntityInWorld(entitytntprimed);
		}
	}

	/**
	 * Called right before the block is destroyed by a player. Args: world, x, y, z, metaData
	 */
	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5){
		this.detonate(par1World, par2, par3, par4, par5, (EntityLiving) null);
	}

	public void detonate(World par1World, int par2, int par3, int par4, int par5, EntityLiving par6EntityLiving){
		if (!par1World.isRemote){
			if ((par5 & 1) == 1){
				EntityBlackholiumTNTPrimed entitytntprimed = new EntityBlackholiumTNTPrimed(par1World, (double) ((float) par2 + 0.5F), (double) ((float) par3 + 0.5F), (double) ((float) par4 + 0.5F), par6EntityLiving);
				par1World.spawnEntityInWorld(entitytntprimed);
				par1World.playSoundAtEntity(entitytntprimed, "random.fuse", 1.0F, 1.0F);
			}
		}
	}
}
