package com.universeCraft.tileEntity;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityCarbonGenerator extends TileEntity{

	private boolean isGenerating = false;
	private int capacity = 100000;
	public int powerStored = 0;
	private int fuelLevel = 0;
	private String colour = "";

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.isGenerating = tagCompound.getBoolean("Active");
		this.capacity = tagCompound.getInteger("Capacity");
		this.powerStored = tagCompound.getInteger("PowerStored");
		this.fuelLevel = tagCompound.getInteger("Fuel");
	}

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("Active", this.isGenerating);
		tagCompound.setInteger("Capacity", this.capacity);
		tagCompound.setInteger("PowerStored", this.powerStored);
		tagCompound.setInteger("Fuel", this.fuelLevel);
	}

	public void updateEntity(){
		if(this.isGenerating){
			this.powerStored += 60;
			this.fuelLevel -= 1;
			if(this.fuelLevel == 0 || this.fuelLevel < 0){
				this.isGenerating = false;
			}
		}
		if(this.powerStored > 100000){
			this.powerStored = 100000;
		}
		if(powerStored < 30000){
			this.colour = "\u00A7c";
		}
		else if(this.powerStored < 70000){
			this.colour = "\u00A76";
		}
		else{
			this.colour = "\u00A72";
		}
		super.updateEntity();
	}
	
	public void generate(double d) {
		this.fuelLevel += d;
		this.isGenerating = true;
	}

	public void checkAmount(EntityPlayer player) {
		if(worldObj.isRemote){
			player.addChatComponentMessage(new ChatComponentText(this.colour + "Energy: " + this.powerStored + "/" + this.capacity));
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
