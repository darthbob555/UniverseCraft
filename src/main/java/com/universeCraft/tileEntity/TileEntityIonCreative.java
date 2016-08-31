package com.universeCraft.tileEntity;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityIonCreative extends TileEntity {

	public int storedEnergy = 1;
	public int energyCapacity = 1;

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
	}

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
	}

	public void updateEntity(){
		if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == ModBlocks.ionStorage || worldObj.getBlock(xCoord, yCoord-1, zCoord) == ModBlocks.atomicPulveriser){
			TileEntity entity = worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
			this.entityValidation(entity);
		}
		super.updateEntity();
	}

	private void entityValidation(TileEntity entity) {
		if(entity != null){
			if(entity instanceof TileEntityAtomicPulveriser){
				TileEntityAtomicPulveriser te = (TileEntityAtomicPulveriser) entity;
				te.energyStored = te.energyCapacity;
			}
			else if(entity instanceof TileEntityIonHuge){
				TileEntityIonHuge te = (TileEntityIonHuge) entity;
				te.storedEnergy = te.energyCapacity;
			}
			else if(entity instanceof TileEntityIonLarge){
				TileEntityIonLarge te = (TileEntityIonLarge) entity;
				te.storedEnergy = te.energyCapacity;
			}
			else if(entity instanceof TileEntityIonMedium){
				TileEntityIonMedium te = (TileEntityIonMedium) entity;
				te.storedEnergy = te.energyCapacity;
			}
			else if(entity instanceof TileEntityIonSmall){
				TileEntityIonSmall te = (TileEntityIonSmall) entity;
				te.storedEnergy = te.energyCapacity;
			}
			else if(entity instanceof TileEntityStellarEnricher){
				TileEntityStellarEnricher te = (TileEntityStellarEnricher) entity;
				te.energyStored = te.energyCapacity;
			}
			else if(entity instanceof TileEntityPlanetaryAssembler){
				TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) entity;
				te.energyStored = te.energyCapacity;
			}
			else if(entity instanceof TileEntityDuplication){
				TileEntityDuplication te = (TileEntityDuplication) entity;
				te.energyStored = te.energyCapacity;
			}
		}
	}

	public void showStatus(EntityPlayer player) {
		if(worldObj.isRemote){
			String format = "\u00A7a";
			player.addChatComponentMessage(new ChatComponentText(format + "Energy: " + this.storedEnergy + "/100000000SE"));
			player.addChatComponentMessage(new ChatComponentText(format + "Energy: 100%"));
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
