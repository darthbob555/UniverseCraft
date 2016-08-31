package com.universeCraft.tileEntity;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityWirelessController extends TileEntity{

	public int energyStored = 0;
	public int energyCapacity = 100000;
	public int[] module = new int[3];
	private int range = 5;
	private int speed = 1;
	public int x = -range;
	public int y = -range;
	public int z = -range;

	@Override
	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.x = tagCompound.getInteger("X");
		this.y = tagCompound.getInteger("Y");
		this.z = tagCompound.getInteger("Z");
		this.energyStored = tagCompound.getInteger("Energy");
		this.module = tagCompound.getIntArray("Module");
		this.speed = tagCompound.getInteger("Speed");
		this.range = tagCompound.getInteger("Range");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("X", this.x);
		tagCompound.setInteger("Y", this.y);
		tagCompound.setInteger("Z", this.z);
		tagCompound.setInteger("Speed", this.speed);
		tagCompound.setInteger("Range", this.range);
		tagCompound.setInteger("Energy", this.energyStored);
		tagCompound.setIntArray("Module", this.module);
	}

	@Override
	public void updateEntity(){
		if(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0){
			if(this.module[0] == 1) speed = 5;
			if(this.module[1] == 1) range = 15;
			if(this.module[2] == 1) speed = 20;
			for(int i = speed; i>0; i--){
				if(x >= range){
					x = -range;
					if(y >= range){
						y = -range;
						if(z >= range){
							x = -range;
							y = -range;
							z = -range;
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
				this.checkForEntity(worldObj, xCoord+x, yCoord+y, zCoord+z);
			}
		}
	}

	private void checkForEntity(World worldObj, int x, int y, int z) {
		TileEntity entity = worldObj.getTileEntity(x, y, z);
		if(entity != null){
			if(entity instanceof TileEntityCarbonGenerator){
				TileEntityCarbonGenerator te = (TileEntityCarbonGenerator) entity;
				if(te.powerStored > 0 && (te.powerStored + this.energyStored) <= this.energyCapacity){
					this.energyStored += te.powerStored;
					te.powerStored = 0;
				}
				else if(te.powerStored > 0){
					te.powerStored -= this.energyCapacity-this.energyStored;
					this.energyStored = this.energyCapacity;
				}
			}
			else if(entity instanceof TileEntityStellarGenerator){
				TileEntityStellarGenerator te = (TileEntityStellarGenerator) entity;
				if(te.powerStored > 0 && (te.powerStored + this.energyStored) <= this.energyCapacity){
					this.energyStored += te.powerStored;
					te.powerStored = 0;
				}
				else if(te.powerStored > 0){
					te.powerStored -= this.energyCapacity-this.energyStored;
					this.energyStored = this.energyCapacity;
				}
			}
			else if(entity instanceof TileEntityVertexGenerator){
				TileEntityVertexGenerator te = (TileEntityVertexGenerator) entity;
				if(te.powerStored > 0 && (te.powerStored + this.energyStored) <= this.energyCapacity){
					this.energyStored += te.powerStored;
					te.powerStored = 0;
				}
				else if(te.powerStored > 0){
					te.powerStored -= this.energyCapacity-this.energyStored;
					this.energyStored = this.energyCapacity;
				}
			}
			else if(entity instanceof TileEntityIonSmall){
				TileEntityIonSmall te = (TileEntityIonSmall) entity;
				if(te.storedEnergy < te.energyCapacity && (te.storedEnergy + this.energyStored) <= te.energyCapacity){
					te.storedEnergy += this.energyStored;
					this.energyStored = 0;
				}
				else{
					te.storedEnergy = te.energyCapacity;
					this.energyStored -= te.energyCapacity-te.storedEnergy;
				}
			}
			else if(entity instanceof TileEntityIonMedium){
				TileEntityIonMedium te = (TileEntityIonMedium) entity;
				if(te.storedEnergy < te.energyCapacity && (te.storedEnergy + this.energyStored) <= te.energyCapacity){
					te.storedEnergy += this.energyStored;
					this.energyStored = 0;
				}
				else{
					te.storedEnergy = te.energyCapacity;
					this.energyStored -= te.energyCapacity-te.storedEnergy;
				}
			}
			else if(entity instanceof TileEntityIonLarge){
				TileEntityIonLarge te = (TileEntityIonLarge) entity;
				if(te.storedEnergy < te.energyCapacity && (te.storedEnergy + this.energyStored) <= te.energyCapacity){
					te.storedEnergy += this.energyStored;
					this.energyStored = 0;
				}
				else{
					te.storedEnergy = te.energyCapacity;
					this.energyStored -= te.energyCapacity-te.storedEnergy;
				}
			}
			else if(entity instanceof TileEntityIonHuge){
				TileEntityIonHuge te = (TileEntityIonHuge) entity;
				if(te.storedEnergy < te.energyCapacity && (te.storedEnergy + this.energyStored) <= te.energyCapacity){
					te.storedEnergy += this.energyStored;
					this.energyStored = 0;
				}
				else{
					te.storedEnergy = te.energyCapacity;
					this.energyStored -= te.energyCapacity-te.storedEnergy;
				}
			}
			else if(entity instanceof TileEntityStellarEnricher){
				TileEntityStellarEnricher te = (TileEntityStellarEnricher) entity;
				if(te.energyStored < te.energyCapacity && (te.energyStored + this.energyStored) <= te.energyCapacity){
					te.energyStored += this.energyStored;
					this.energyStored = 0;
				}
				else if(te.energyStored > 0){
					te.energyStored -= this.energyCapacity-this.energyStored;
					this.energyStored = this.energyCapacity;
				}
			}
			else if(entity instanceof TileEntityPlanetaryAssembler){
				TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) entity;
				if(te.energyStored < te.energyCapacity && (te.energyStored + this.energyStored) <= te.energyCapacity){
					te.energyStored += this.energyStored;
					this.energyStored = 0;
				}
				else if(te.energyStored > 0){
					te.energyStored -= this.energyCapacity-this.energyStored;
					this.energyStored = this.energyCapacity;
				}
			}
			else if(entity instanceof TileEntityDuplication){
				TileEntityDuplication te = (TileEntityDuplication) entity;
				if(te.energyStored < te.energyCapacity && (te.energyStored + this.energyStored) <= te.energyCapacity){
					te.energyStored += this.energyStored;
					this.energyStored = 0;
				}
				else if(te.energyStored > 0){
					te.energyStored -= this.energyCapacity-this.energyStored;
					this.energyStored = this.energyCapacity;
				}
			}
		}
	}

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
