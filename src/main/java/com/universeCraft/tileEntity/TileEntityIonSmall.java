package com.universeCraft.tileEntity;

import com.universeCraft.blocks.ModBlocks;

import gnu.trove.list.linked.TIntLinkedList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityIonSmall extends TileEntity {

	public int storedEnergy = 0;
	public int energyCapacity = 200000;

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Stored", this.storedEnergy);
	}

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.storedEnergy = tagCompound.getInteger("Stored");
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
		}
	}

	public void transferEnergy(ItemStack itemstack){
		if(itemstack.getTagCompound() != null){
			if(itemstack.getTagCompound().hasKey("Energy")){
				for(int i = itemstack.getTagCompound().getInteger("Energy"); i>0; i--){
					if(this.storedEnergy == 200000){
						itemstack.getTagCompound().setInteger("Energy", i);
						i = 0;
					}
					else{
						this.storedEnergy++;
					}
				}
			}
		}
	}

	public void showStatus(EntityPlayer player) {
		if(worldObj.isRemote){
			float percentage = (float)(this.storedEnergy/(float)200000*100);
			String format;
			if(percentage <= 25){
				format = "\u00A7c";
			}
			else if(percentage <= 50){
				format = "\u00A76";
			}
			else if(percentage <= 75){
				format = "\u00A7b";
			}
			else{
				format = "\u00A7a";
			}
			player.addChatComponentMessage(new ChatComponentText(format + "Energy: " + this.storedEnergy + "/200000SE"));
			player.addChatComponentMessage(new ChatComponentText(format + "Energy: " + percentage + "%"));
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
