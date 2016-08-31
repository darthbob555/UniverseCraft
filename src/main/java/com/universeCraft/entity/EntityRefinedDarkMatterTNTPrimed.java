package com.universeCraft.entity;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

public class EntityRefinedDarkMatterTNTPrimed extends EntityTNTPrimed
{
	/** How long the fuse is */
	public int fuse;

	public EntityRefinedDarkMatterTNTPrimed(World par1World)
	{
		super(par1World);
		this.fuse = 0;
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.yOffset = this.height / 2.0F;
	}

	public EntityRefinedDarkMatterTNTPrimed(World par1World, double par2, double par4, double par6, EntityLivingBase entityLivingBase)
	{
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

	public int j = 0;

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
			this.motionY *= 0.01D;
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
		Random rand = new Random();
		float f = 8.0F;
		this.motionY = 0;

		if(j != 200){
			if(rand.nextInt(4) == 0){
				EntityLightningBolt lightning0 = new EntityLightningBolt(worldObj, posX, posY, posZ);
				worldObj.addWeatherEffect(lightning0);
			}
			else{
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, f, true);
				this.worldObj.createExplosion(this, this.posX+rand.nextInt(50)-rand.nextInt(100), this.posY+rand.nextInt(50)-rand.nextInt(100), this.posZ+rand.nextInt(50)-rand.nextInt(100), f, true);
				this.worldObj.createExplosion(this, this.posX+rand.nextInt(50)-rand.nextInt(100), this.posY+rand.nextInt(50)-rand.nextInt(100), this.posZ+rand.nextInt(50)-rand.nextInt(100), f, true);
				this.worldObj.createExplosion(this, this.posX+rand.nextInt(50)-rand.nextInt(100), this.posY+rand.nextInt(50)-rand.nextInt(100), this.posZ+rand.nextInt(50)-rand.nextInt(100), f, true);
				this.worldObj.createExplosion(this, this.posX+rand.nextInt(50)-rand.nextInt(100), this.posY+rand.nextInt(50)-rand.nextInt(100), this.posZ+rand.nextInt(50)-rand.nextInt(100), f, true);
			}
			j++;
		}
		else{
			this.setDead();
		}
	}
}