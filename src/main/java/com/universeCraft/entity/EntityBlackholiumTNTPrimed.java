package com.universeCraft.entity;

import com.universeCraft.tileEntity.TileEntityPlanetaryAssembler;

import ibxm.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityBlackholiumTNTPrimed extends EntityTNTPrimed{

	/** How long the fuse is */
	public int fuse;
	public int j = 0;
	public float f = 100.0F;
	int x = -128;
	int y = 0;
	int z = -128;

	public EntityBlackholiumTNTPrimed(World par1World){
		super(par1World);
		this.fuse = 0;
		this.preventEntitySpawning = true;
		this.entityCollisionReduction = 100;
		this.setSize(10.98F, 10.98F);
		this.yOffset = this.height / 2.0F;
	}

	public EntityBlackholiumTNTPrimed(World par1World, double par2, double par4, double par6, EntityLivingBase entityLivingBase){
		this(par1World);
		this.setPosition(par2, par4, par6);
		float f = (float)(Math.random() * Math.PI * 2.0D);
		this.motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
		this.motionY = 2.20000000298023224D;
		this.motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
		this.fuse = 85;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate(){
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround){
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= 0.5D;
		}

		if (this.fuse-- <= 0){
			if (!this.worldObj.isRemote){
				this.explode();
			}
		}
		else{
			EntityLightningBolt lightning0 = new EntityLightningBolt(worldObj, posX, posY, posZ);
			worldObj.addWeatherEffect(lightning0);
		}
	}

	private void explode(){	
		float f = 8.0F;
		for(int i = 0; i<600; i++){
			if(x >= 128){
				x = -128;
				if(y >= 150){
					y = 0;
					if(z >= 128){
						x = -128;
						y = 0;
						z = -128;
					}
					else{
						z++;
					}
				}
				else{
					y++;
				}
			}
			else{
				x++;
			}
			if((x == -128) && (y == 0) && (z == -128)){
				this.setDead();
			}
			worldObj.setBlockToAir(x+(int)this.posX, (int)this.posY-y, z+(int)this.posZ);
			System.out.print(x + " " + y + " " + z);
		}
	}
}