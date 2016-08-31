package com.universeCraft.tileEntity;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVacuumPipe extends TileEntity{

	public int storedAntimatter = 0;

	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.storedAntimatter = tagCompound.getInteger("Antimatter");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Antimatter", this.storedAntimatter);
	}

	@Override
	public void updateEntity(){
		if(worldObj.getBlock(xCoord-1, yCoord, zCoord) == ModBlocks.particleAccelerator || worldObj.getBlock(xCoord-1, yCoord, zCoord) == ModBlocks.atomicPulveriser || worldObj.getBlock(xCoord-1, yCoord, zCoord) == ModBlocks.pipe){
			TileEntity entity = worldObj.getTileEntity(xCoord-1, yCoord, zCoord);
			this.entityValidation(entity);
		}
		if(worldObj.getBlock(xCoord+1, yCoord, zCoord) == ModBlocks.particleAccelerator || worldObj.getBlock(xCoord+1, yCoord, zCoord) == ModBlocks.atomicPulveriser || worldObj.getBlock(xCoord+1, yCoord, zCoord) == ModBlocks.pipe){
			TileEntity entity = worldObj.getTileEntity(xCoord+1, yCoord, zCoord);
			this.entityValidation(entity);
		}
		if(worldObj.getBlock(xCoord, yCoord, zCoord-1) == ModBlocks.particleAccelerator || worldObj.getBlock(xCoord, yCoord, zCoord-1) == ModBlocks.atomicPulveriser || worldObj.getBlock(xCoord, yCoord, zCoord-1) == ModBlocks.pipe){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord, zCoord-1);
			this.entityValidation(entity);
		}
		if(worldObj.getBlock(xCoord, yCoord, zCoord+1) == ModBlocks.particleAccelerator || worldObj.getBlock(xCoord, yCoord, zCoord+1) == ModBlocks.atomicPulveriser || worldObj.getBlock(xCoord, yCoord, zCoord+1) == ModBlocks.pipe){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord, zCoord+1);
			this.entityValidation(entity);
		}
		if(worldObj.getBlock(xCoord, yCoord+1, zCoord) == ModBlocks.particleAccelerator || worldObj.getBlock(xCoord, yCoord+1, zCoord) == ModBlocks.atomicPulveriser || worldObj.getBlock(xCoord, yCoord+1, zCoord) == ModBlocks.pipe){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
			this.entityValidation(entity);
		}
		if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == ModBlocks.particleAccelerator || worldObj.getBlock(xCoord, yCoord-1, zCoord) == ModBlocks.atomicPulveriser || worldObj.getBlock(xCoord, yCoord-1, zCoord) == ModBlocks.pipe){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
			this.entityValidation(entity);
		}
	}

	private void entityValidation(TileEntity entity) {
		if(entity != null){
			if(entity instanceof TileEntityParticleAccelerator){
				TileEntityParticleAccelerator particleAccel = (TileEntityParticleAccelerator) entity;	
				if(particleAccel.antimatter > 1 || particleAccel.antimatter == 1){
					for(int i = 100; i>0; i--){
						if(particleAccel.antimatter > 0 && this.storedAntimatter < 100){
							this.storedAntimatter++;
							particleAccel.antimatter--;
						}
					}
				}
			}
			else if(entity instanceof TileEntityVacuumPipe){
				TileEntityVacuumPipe pipe = (TileEntityVacuumPipe) entity;
				if(this.storedAntimatter != pipe.storedAntimatter){
					if(pipe.storedAntimatter > this.storedAntimatter){
						this.storedAntimatter++;
						pipe.storedAntimatter--;
					}
					else if(this.storedAntimatter > pipe.storedAntimatter){
						this.storedAntimatter--;
						pipe.storedAntimatter++;
					}
				}
			}
			else if(entity instanceof TileEntityAtomicPulveriser){
				TileEntityAtomicPulveriser te = (TileEntityAtomicPulveriser) entity;
				if(this.storedAntimatter > 0){
					for(int i = 50; i>0; i--){
						if(this.storedAntimatter > 0){
							this.storedAntimatter--;
							te.antimatter++;
						}
					}
				}
			}
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		this.readFromNBT(packet.func_148857_g());
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound var1 = new NBTTagCompound();
		this.writeToNBT(var1);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, var1);
	}
}
