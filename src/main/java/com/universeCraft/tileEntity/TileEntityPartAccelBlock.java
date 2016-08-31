package com.universeCraft.tileEntity;

import java.util.Random;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityPartAccelBlock extends TileEntity {

	private int i = 0;
	private int j = 0;
	private int k = 0;
	private int l = 0;
	private int i2 = 0;
	private int blocksCollated = 0;
	public int sizeOfExplosion = 0;
	int r = 0;
	int x = -r;
	int y = -r;
	int z = -r;

	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		this.i = tagCompound.getInteger("Increment");
		this.sizeOfExplosion = tagCompound.getInteger("Explosion");
		this.blocksCollated = tagCompound.getInteger("Collated");
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Increment", this.i);
		tagCompound.setInteger("Explosion", this.sizeOfExplosion);
		tagCompound.setInteger("Collated", this.blocksCollated);
	}

	public int parAccelCheck(World world, int x, int y, int z, int switchValue, Random rand){
		for(int a = 18; a > -19; a--){
			for(int b = 1; b > -3; b--){
				for(int c = 19; c > -19; c--){
					if(switchValue == 0){
						if(world.getBlock(x+a, y+b, z+c) == ModBlocks.metadataBlocks && world.getBlockMetadata(x+a, y+b, z+c) == 8){
							blocksCollated++;
						}
					}
					if(switchValue == 1){
						if(world.getBlock(x+a, y+b, z+c) == ModBlocks.partAccelBlock){
							if(rand.nextInt(4) == 0){
								world.setBlockToAir(x+a, y+b, z+c);
							}
							else{
								world.setBlock(x+a, y+b, z+c, ModBlocks.metadataBlocks, 4, 2);
							}
						}
						else if(world.getBlock(x+a, y+b, z+c) == ModBlocks.metadataBlocks){
							world.setBlockMetadataWithNotify(x+a, y+b, z+c, 7, 2);
						}
					}
				}
			}
		}
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return blocksCollated;
	}

	public void updateEntity(){
		Random rand = new Random();
		if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1){
			this.sizeOfExplosion = this.parAccelCheck(worldObj, xCoord, yCoord, zCoord, 0, rand);
			if(this.sizeOfExplosion > 0){
				if(i<600){
					i++;
					if(i==60){
						EntityLightningBolt lightning0 = new EntityLightningBolt(worldObj, xCoord+10, yCoord, zCoord);
						worldObj.addWeatherEffect(lightning0);
						worldObj.addWeatherEffect(lightning0);
						worldObj.addWeatherEffect(lightning0);
						this.createSphere(20, 20, 0, 0);
					}
					else if(i==120){
						EntityLightningBolt lightning1 = new EntityLightningBolt(worldObj, xCoord-20, yCoord, zCoord);
						worldObj.addWeatherEffect(lightning1);
						worldObj.addWeatherEffect(lightning1);
						worldObj.addWeatherEffect(lightning1);
						this.createSphere(20, -20, 0, 0);
					}
					else if(i==180){
						EntityLightningBolt lightning2 = new EntityLightningBolt(worldObj, xCoord, yCoord, zCoord+20);
						worldObj.addWeatherEffect(lightning2);
						worldObj.addWeatherEffect(lightning2);
						worldObj.addWeatherEffect(lightning2);
						this.createSphere(20, 0, 0, 20);
					}
					else if(i==240){
						EntityLightningBolt lightning3 = new EntityLightningBolt(worldObj, xCoord+20, yCoord, zCoord-20);
						worldObj.addWeatherEffect(lightning3);
						worldObj.addWeatherEffect(lightning3);
						worldObj.addWeatherEffect(lightning3);
						this.createSphere(20, 0, 0, -20);
					}
					else if(i==300){
						EntityLightningBolt lightning4 = new EntityLightningBolt(worldObj, xCoord-20, yCoord, zCoord-20);
						worldObj.addWeatherEffect(lightning4);
						worldObj.addWeatherEffect(lightning4);
						worldObj.addWeatherEffect(lightning4);
						this.createSphere(20, -20, 0, -20);
					}
					else if(i==360){
						EntityLightningBolt lightning5 = new EntityLightningBolt(worldObj, xCoord+20, yCoord, zCoord-20);
						worldObj.addWeatherEffect(lightning5);
						worldObj.addWeatherEffect(lightning5);
						worldObj.addWeatherEffect(lightning5);
						this.createSphere(20, 20, 0, -20);
					}
					else if(i==420){
						EntityLightningBolt lightning6 = new EntityLightningBolt(worldObj, xCoord+20, yCoord, zCoord+20);
						worldObj.addWeatherEffect(lightning6);
						worldObj.addWeatherEffect(lightning6);
						worldObj.addWeatherEffect(lightning6);
						this.createSphere(20, 20, 0, 20);
					}
					else if(i==480){
						EntityLightningBolt lightning7 = new EntityLightningBolt(worldObj, xCoord-20, yCoord, zCoord+20);
						worldObj.addWeatherEffect(lightning7);
						worldObj.addWeatherEffect(lightning7);
						worldObj.addWeatherEffect(lightning7);
						this.createSphere(20, -20, 0, 20);
					}
				}
				else if(i==600 || i>600){
					for(int a = 0; a<20; a++){
						EntityLightningBolt lightning8 = new EntityLightningBolt(worldObj, xCoord+rand.nextInt(20)-rand.nextInt(30), yCoord, zCoord+rand.nextInt(20)-rand.nextInt(30));
						worldObj.addWeatherEffect(lightning8);
					}
					this.createSphere(30, 0, 0, 0);
				}
			}
			else{
				if(i2<200){
					i2++;
				}
				else{
					EntityLightningBolt lightning9 = new EntityLightningBolt(worldObj, xCoord+rand.nextInt(20)-rand.nextInt(30), yCoord, zCoord+rand.nextInt(20)-rand.nextInt(30));
					worldObj.addWeatherEffect(lightning9);
					this.createSphere(this.sizeOfExplosion, 0, 0, 0);
					this.parAccelCheck(worldObj, xCoord, yCoord, zCoord, 1, rand);
				}
			}
		}
		super.updateEntity();
	}

	public void createSphere(int radius, int xInc, int yInc, int zInc){
		for(this.r = 0; this.r < radius; this.r++){
			for(this.x = -this.r; this.x < this.r; this.x++){
				for(this.y = -this.r; this.y < this.r; this.y++){ 
					for(this.z = -this.r; this.z < this.r; this.z++){                                    
						double dist = MathHelper.sqrt_double((x*x + y*y + z*z)); //Calculates the distance
						if(dist > this.r){
							continue;
						}
						worldObj.setBlockToAir(xCoord+x+xInc, yCoord+y+yInc, zCoord+z+zInc);
					}
				}
			}
		}
	}
}
