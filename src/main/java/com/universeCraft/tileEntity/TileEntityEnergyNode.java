package com.universeCraft.tileEntity;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnergyNode extends TileEntity {

	public int storedEnergy = 0;
	public int energyCapacity = 2147483647;
	public int[] link = new int[3];

	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Stored", this.storedEnergy);
		tagCompound.setIntArray("Link", this.link);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.storedEnergy = tagCompound.getInteger("Stored");
		this.link = tagCompound.getIntArray("Link");
	}

	@Override
	public void updateEntity(){
		if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == ModBlocks.ionStorage || worldObj.getBlock(xCoord, yCoord-1, zCoord) == ModBlocks.atomicPulveriser){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
			this.entityValidation(entity, true);
		}
		else if(worldObj.getBlock(xCoord, yCoord+1, zCoord) == ModBlocks.ionStorage || worldObj.getBlock(xCoord, yCoord+1, zCoord) == ModBlocks.atomicPulveriser){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
			this.entityValidation(entity, true);
		}
		else if(worldObj.getBlock(xCoord, yCoord, zCoord+1) == ModBlocks.ionStorage || worldObj.getBlock(xCoord, yCoord, zCoord+1) == ModBlocks.atomicPulveriser){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord, zCoord+1);
			this.entityValidation(entity, true);
		}
		else if(worldObj.getBlock(xCoord, yCoord, zCoord-1) == ModBlocks.ionStorage || worldObj.getBlock(xCoord, yCoord, zCoord-1) == ModBlocks.atomicPulveriser){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord, zCoord-1);
			this.entityValidation(entity, true);
		}
		else if(worldObj.getBlock(xCoord+1, yCoord, zCoord) == ModBlocks.ionStorage || worldObj.getBlock(xCoord+1, yCoord, zCoord) == ModBlocks.atomicPulveriser){
			TileEntity entity = worldObj.getTileEntity(xCoord+1, yCoord, zCoord);
			this.entityValidation(entity, true);
		}
		else if(worldObj.getBlock(xCoord-1, yCoord, zCoord) == ModBlocks.ionStorage || worldObj.getBlock(xCoord-1, yCoord, zCoord) == ModBlocks.atomicPulveriser){
			TileEntity entity = worldObj.getTileEntity(xCoord-1, yCoord, zCoord);
			this.entityValidation(entity, true);
		}
		if(this.storedEnergy > 0){
			if(worldObj.getBlock(this.link[0], this.link[1], this.link[2]) == ModBlocks.ionStorage || worldObj.getBlock(this.link[0], this.link[1], this.link[2]) == ModBlocks.atomicPulveriser){
				TileEntity entity = worldObj.getTileEntity(this.link[0], this.link[1], this.link[2]);
				this.entityValidation(entity, false);
			}
		}
		super.updateEntity();
	}

	public void linkUp(int[] coords) {
		for(int i = 0; i<coords.length; i++){
			this.link[i] = coords[i];
		}
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	private void entityValidation(TileEntity entity, boolean input) {
		if(entity != null){
			if(input){
				if(entity instanceof TileEntityAtomicPulveriser){
					TileEntityAtomicPulveriser te = (TileEntityAtomicPulveriser) entity;
					if(te != null && te.energyStored > 0){
						int potential = te.energyStored;
						if((potential + this.storedEnergy > this.energyCapacity)){
							this.storedEnergy += potential;
							te.energyStored = 0;
						}
						else if(this.storedEnergy != this.energyCapacity){
							int fill = this.energyCapacity - this.storedEnergy;
							this.storedEnergy = this.energyCapacity;
							te.energyStored -= fill;
						}
					}
				}
				else if(entity instanceof TileEntityIonSmall){
					TileEntityIonSmall te = (TileEntityIonSmall) entity;
					if(te != null && te.storedEnergy > 0){
						int potential = te.storedEnergy;
						if((potential + this.storedEnergy > this.energyCapacity)){
							this.storedEnergy += potential;
							te.storedEnergy = 0;
						}
						else if(this.storedEnergy != this.energyCapacity){
							int fill = this.energyCapacity - this.storedEnergy;
							this.storedEnergy = this.energyCapacity;
							te.storedEnergy -= fill;
						}
					}
				}
				else if(entity instanceof TileEntityIonMedium){
					TileEntityIonMedium te = (TileEntityIonMedium) entity;
					if(te != null && te.storedEnergy > 0){
						int potential = te.storedEnergy;
						if((potential + this.storedEnergy > this.energyCapacity)){
							this.storedEnergy += potential;
							te.storedEnergy = 0;
						}
						else if(this.storedEnergy != this.energyCapacity){
							int fill = this.energyCapacity - this.storedEnergy;
							this.storedEnergy = this.energyCapacity;
							te.storedEnergy -= fill;
						}
					}
				}
				else if(entity instanceof TileEntityIonLarge){
					TileEntityIonLarge te = (TileEntityIonLarge) entity;
					if(te != null && te.storedEnergy > 0){
						int potential = te.storedEnergy;
						if((potential + this.storedEnergy > this.energyCapacity)){
							this.storedEnergy += potential;
							te.storedEnergy = 0;
						}
						else if(this.storedEnergy != this.energyCapacity){
							int fill = this.energyCapacity - this.storedEnergy;
							this.storedEnergy = this.energyCapacity;
							te.storedEnergy -= fill;
						}
					}
				}
				else if(entity instanceof TileEntityIonHuge){
					TileEntityIonHuge te = (TileEntityIonHuge) entity;
					if(te != null && te.storedEnergy > 0){
						int potential = te.storedEnergy;
						if((potential + this.storedEnergy > this.energyCapacity)){
							this.storedEnergy += potential;
							te.storedEnergy = 0;
						}
						else if(this.storedEnergy != this.energyCapacity){
							int fill = this.energyCapacity - this.storedEnergy;
							this.storedEnergy = this.energyCapacity;
							te.storedEnergy -= fill;
						}
					}
				}
				else if(entity instanceof TileEntityIonCreative){
					TileEntityIonCreative te = (TileEntityIonCreative) entity;
					this.storedEnergy = this.energyCapacity;
				}
				else if(entity instanceof TileEntityStellarEnricher){
					TileEntityStellarEnricher te = (TileEntityStellarEnricher) entity;
					if(te != null && te.energyStored > 0){
						int potential = te.energyStored;
						if((potential + this.storedEnergy > this.energyCapacity)){
							this.storedEnergy += potential;
							te.energyStored = 0;
						}
						else if(this.storedEnergy != this.energyCapacity){
							int fill = this.energyCapacity - this.storedEnergy;
							this.storedEnergy = this.energyCapacity;
							te.energyStored -= fill;
						}
					}
				}
				else if(entity instanceof TileEntityPlanetaryAssembler){
					TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) entity;
					if(te != null && te.energyStored > 0){
						int potential = te.energyStored;
						if((potential + this.storedEnergy > this.energyCapacity)){
							this.storedEnergy += potential;
							te.energyStored = 0;
						}
						else if(this.storedEnergy != this.energyCapacity){
							int fill = this.energyCapacity - this.storedEnergy;
							this.storedEnergy = this.energyCapacity;
							te.energyStored -= fill;
						}
					}
				}
				else if(entity instanceof TileEntityEnergyAcceptor){
					TileEntityEnergyAcceptor te = (TileEntityEnergyAcceptor) entity;
					if(te != null && te.storedEnergy > 0){
						int potential = te.storedEnergy;
						if((potential + this.storedEnergy > this.energyCapacity)){
							this.storedEnergy += potential;
							te.storedEnergy = 0;
						}
						else if(this.storedEnergy != this.energyCapacity){
							int fill = this.energyCapacity - this.storedEnergy;
							this.storedEnergy = this.energyCapacity;
							te.storedEnergy -= fill;
						}
					}
				}
			}
			else{
				if(entity instanceof TileEntityAtomicPulveriser){
					TileEntityAtomicPulveriser te = (TileEntityAtomicPulveriser) entity;
					if(te.energyStored < te.energyCapacity){
						for(int i = 0; i<200; i++){
							if(this.storedEnergy > 0 && te.energyStored < te.energyCapacity){
								this.storedEnergy--;
								te.energyStored++;
							}
						}
					}
				}
				else if(entity instanceof TileEntityIonSmall){
					TileEntityIonSmall te = (TileEntityIonSmall) entity;
					if(te.storedEnergy < te.energyCapacity){
						for(int i = 0; i<200; i++){
							if(this.storedEnergy > 0 && te.storedEnergy < te.energyCapacity){
								this.storedEnergy--;
								te.storedEnergy++;
							}
						}
					}
				}
				else if(entity instanceof TileEntityIonMedium){
					TileEntityIonMedium te = (TileEntityIonMedium) entity;
					if(te.storedEnergy < te.energyCapacity){
						for(int i = 0; i<200; i++){
							if(this.storedEnergy > 0 && te.storedEnergy < te.energyCapacity){
								this.storedEnergy--;
								te.storedEnergy++;
							}
						}
					}
				}
				else if(entity instanceof TileEntityIonLarge){
					TileEntityIonLarge te = (TileEntityIonLarge) entity;
					if(te.storedEnergy < te.energyCapacity){
						for(int i = 0; i<200; i++){
							if(this.storedEnergy > 0 && te.storedEnergy < te.energyCapacity){
								this.storedEnergy--;
								te.storedEnergy++;
							}
						}
					}
				}
				else if(entity instanceof TileEntityIonHuge){
					TileEntityIonHuge te = (TileEntityIonHuge) entity;
					if(te.storedEnergy < te.energyCapacity){
						for(int i = 0; i<200; i++){
							if(this.storedEnergy > 0 && te.storedEnergy < te.energyCapacity){
								this.storedEnergy--;
								te.storedEnergy++;
							}
						}
					}
				}
				else if(entity instanceof TileEntityStellarEnricher){
					TileEntityStellarEnricher te = (TileEntityStellarEnricher) entity;
					if(te.energyStored < te.energyCapacity){
						for(int i = 0; i<200; i++){
							if(this.storedEnergy > 0 && te.energyStored < te.energyCapacity){
								this.storedEnergy--;
								te.energyStored++;
							}
						}
					}
				}
				else if(entity instanceof TileEntityPlanetaryAssembler){
					TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) entity;
					if(te.energyStored < te.energyCapacity){
						for(int i = 0; i<200; i++){
							if(this.storedEnergy > 0 && te.energyStored < te.energyCapacity){
								this.storedEnergy--;
								te.energyStored++;
							}
						}
					}
				}
				else if(entity instanceof TileEntityEnergyAcceptor){
					TileEntityEnergyAcceptor te = (TileEntityEnergyAcceptor) entity;
					if(te.storedEnergy < te.energyCapacity){
						for(int i = 0; i<200; i++){
							if(this.storedEnergy > 0 && te.storedEnergy < te.energyCapacity){
								this.storedEnergy--;
								te.storedEnergy++;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet){
		readFromNBT(packet.func_148857_g());
	}

	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
}
