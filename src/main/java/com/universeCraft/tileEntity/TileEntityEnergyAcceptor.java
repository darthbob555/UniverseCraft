package com.universeCraft.tileEntity;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnergyAcceptor extends TileEntity {

	public int storedEnergy = 0;
	public int energyCapacity = 100000;

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Stored", this.storedEnergy);
	}

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.storedEnergy = tagCompound.getInteger("Stored");
	}

	public void updateEntity(){
		if(worldObj.getBlock(xCoord, yCoord+1, zCoord) == ModBlocks.ionStorage){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
			this.entityValidation(entity);
		}
		if(this.storedEnergy > 0){
			if(worldObj.getTileEntity(xCoord, yCoord, zCoord+7) != null && worldObj.getTileEntity(xCoord, yCoord, zCoord+7) instanceof TileEntityParticleAccelerator){
				TileEntityParticleAccelerator te = (TileEntityParticleAccelerator) worldObj.getTileEntity(xCoord, yCoord, zCoord+7);
				if(te.energyStored < te.energyCapacity){
					if((te.energyStored + this.storedEnergy) <= te.energyCapacity){
						te.energyStored += this.storedEnergy;
						this.storedEnergy = 0;
					}
					else{
						for(int i = this.storedEnergy; i>0; i--){
							if(te.energyStored < te.energyCapacity && this.storedEnergy > 0){
								this.storedEnergy = i;
								te.energyStored++;
							}
						}
					}
				}
			}
			else if(worldObj.getTileEntity(xCoord, yCoord, zCoord-7) != null && worldObj.getTileEntity(xCoord, yCoord, zCoord-7) instanceof TileEntityParticleAccelerator){
				TileEntityParticleAccelerator te = (TileEntityParticleAccelerator) worldObj.getTileEntity(xCoord, yCoord, zCoord-7);
				if(te.energyStored < te.energyCapacity){
					if((te.energyStored + this.storedEnergy) <= te.energyCapacity){
						te.energyStored += this.storedEnergy;
						this.storedEnergy = 0;
					}
					else{
						for(int i = this.storedEnergy; i>0; i--){
							if(te.energyStored < te.energyCapacity && this.storedEnergy > 0){
								this.storedEnergy = i;
								te.energyStored++;
							}
						}
					}
				}
			}
		}
		super.updateEntity();
	}

	private void entityValidation(TileEntity entity) {
		if(entity != null){
			if(entity instanceof TileEntityIonSmall){
				TileEntityIonSmall te = (TileEntityIonSmall) entity;
				if(te.storedEnergy > 0){
					for(int i = 0; i<200; i++){
						if(this.storedEnergy < this.energyCapacity && te.storedEnergy > 0){
							this.storedEnergy++;
							te.storedEnergy--;
						}
					}
				}
			}
			else if(entity instanceof TileEntityIonMedium){
				TileEntityIonMedium te = (TileEntityIonMedium) entity;
				if(te.storedEnergy > 0){
					for(int i = 0; i<800; i++){
						if(this.storedEnergy < this.energyCapacity && te.storedEnergy > 0){
							this.storedEnergy++;
							te.storedEnergy--;
						}
					}
				}
			}
			else if(entity instanceof TileEntityIonLarge){
				TileEntityIonLarge te = (TileEntityIonLarge) entity;
				if(te.storedEnergy > 0){
					for(int i = 0; i<2000; i++){
						if(this.storedEnergy < this.energyCapacity && te.storedEnergy > 0){
							this.storedEnergy++;
							te.storedEnergy--;
						}
					}
				}
			}
			else if(entity instanceof TileEntityIonHuge){
				TileEntityIonHuge te = (TileEntityIonHuge) entity;
				if(te.storedEnergy > 0){
					for(int i = 0; i<10000; i++){
						if(this.storedEnergy < this.energyCapacity && te.storedEnergy > 0){
							this.storedEnergy++;
							te.storedEnergy--;
						}
					}
				}
			}
			else if(entity instanceof TileEntityIonCreative){
				this.storedEnergy = this.energyCapacity;
			}
		}
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
}
