package com.universeCraft.tileEntity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityVertexGenerator extends TileEntity{

	private int capacity = 100000;
	public int powerStored = 0;
	private int i = 1;
	private int j = 0;
	private int generating = 0;
	private String colour = "";
	private String colour2 = "";
	private boolean canGenerate = true;
	public int module = 0;
	private int multiplyier = 1;

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.capacity = tagCompound.getInteger("Capacity");
		this.powerStored = tagCompound.getInteger("PowerStored");
		this.generating = tagCompound.getInteger("Generating");
		this.canGenerate = tagCompound.getBoolean("CanGenerate");
		this.i = tagCompound.getInteger("I");
		this.j = tagCompound.getInteger("J");
		this.module = tagCompound.getInteger("Moduel");
	}

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Capacity", this.capacity);
		tagCompound.setInteger("PowerStored", this.powerStored);
		tagCompound.setInteger("Generating", this.generating);
		tagCompound.setBoolean("CanGenerate", this.canGenerate);
		tagCompound.setInteger("I", this.i);
		tagCompound.setInteger("J", this.j);
		tagCompound.setInteger("Module", this.module);
	}

	public void updateEntity(){
		Random rand = new Random();
		if(this.module == 1) this.multiplyier = 2;
		if(this.module == 2) this.multiplyier = 4;
		if(this.module == 3) this.multiplyier = 8;
		if(this.canGenerate){
			if(worldObj.getBlock(xCoord, yCoord+1, zCoord) == Blocks.air){
				if(worldObj.getBlock(xCoord-1, yCoord, zCoord) != Blocks.air){
					i++;
				}
				if(worldObj.getBlock(xCoord+1, yCoord, zCoord) != Blocks.air){
					i++;
				}
				if(worldObj.getBlock(xCoord, yCoord, zCoord-1) != Blocks.air){
					i++;
				}
				if(worldObj.getBlock(xCoord, yCoord, zCoord+1) != Blocks.air){
					i++;
				}
				if(i > 1){
					this.powerStored += (25/i)*this.multiplyier;
					this.generating = (25/i)*this.multiplyier;
					j++;
					j *= i;
					i = 1;
				}
				else{
					this.powerStored += 25*this.multiplyier;
					this.generating = 25*this.multiplyier;
				}
				if(j==20 || j > 20){
					worldObj.spawnParticle("explode", xCoord+0.5, yCoord+0.5, zCoord+0.5, 0.0D, 0.0D, 0.0D);
					j = 0;
				}
			}
			else{
				this.generating = 0;
			}
		}
		else{
			this.generating = 0;
		}
		if(this.powerStored > 0){
			if(this.powerStored > 100000){
				this.powerStored = 100000;
			}
			else if(powerStored < 30000){
				this.colour = "\u00A7c";
			}
			else if(this.powerStored < 70000){
				this.colour = "\u00A76";
			}
			else{
				this.colour = "\u00A72";
			}
		}
		else if(this.powerStored > this.capacity){
			this.powerStored = this.capacity;
			this.canGenerate = false;
		}
		else if(this.powerStored < this.capacity){
			this.canGenerate = true;
		}
		if(this.generating > 0){
			if(this.generating < 10){
				this.colour2 = "\u00A74";
			}
			else if(this.generating < 20){
				this.colour2  = "\u00A76";
			}
			else{
				this.colour2 = "\u00A72";
			}
		}
		super.updateEntity();
	}

	public void checkAmount(EntityPlayer player) {
		if(worldObj.isRemote){
			player.addChatComponentMessage(new ChatComponentText(this.colour + "Energy: " + this.powerStored + "/" + this.capacity));
			player.addChatComponentMessage(new ChatComponentText(this.colour2 + "Generating: " + this.generating));
		}
	}

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
