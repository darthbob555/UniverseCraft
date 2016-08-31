package com.universeCraft.tileEntity;

import java.util.Random;

import com.universeCraft.blocks.ModBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityStellarGenerator extends TileEntity{

	private int capacity = 10000000;
	public int powerStored = 0;
	private String colour = "";
	private String colour2 = "";
	public int Generate = 0;
	public int module = 0;

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.capacity = tagCompound.getInteger("Capacity");
		this.powerStored = tagCompound.getInteger("PowerStored");
		this.Generate = tagCompound.getInteger("Generate");
		this.module = tagCompound.getInteger("Module");
	}

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Capacity", this.capacity);
		tagCompound.setInteger("PowerStored", this.powerStored);
		tagCompound.setInteger("Generate", this.Generate);
		tagCompound.setInteger("Module", this.module);
	}

	public void generate(ItemStack itemstack, int i, EntityPlayer player) {
		if(this.module == 3){
			if(this.module == 1){
				this.Generate += i/5;
				itemstack.stackSize--;
			}
		}
		else if(this.module == 2){
			this.Generate += i;
			itemstack.stackSize--;
		}
		else if(this.Generate == 0){
			if(this.module == 1){
				this.Generate = i/5;
			}
			else{
				this.Generate = i;
			}
			
		}
		else{
			player.addChatComponentMessage(new ChatComponentText("Already generating."));
		}
	}

	public void starGenerate(ItemStack currentEquippedItem, EntityPlayer player) {
		if(currentEquippedItem.getTagCompound() != null && currentEquippedItem.getTagCompound().hasKey("STORAGE")){
			if(this.Generate == 0){
				if(this.module == 1){
					this.Generate = ((currentEquippedItem.getTagCompound().getInteger("STORAGE")/1000)/5);
				}
				else{
					this.Generate = (currentEquippedItem.getTagCompound().getInteger("STORAGE")/1000);
				}
				currentEquippedItem.stackSize--;
			}
			else{
				player.addChatComponentMessage(new ChatComponentText("Already generating."));
			}
		}
	}

	public void updateEntity(){
		if(this.Generate > 0){
			if(this.module == 1){
				this.powerStored += 5000;
			}
			else{
				this.powerStored += 1000;
			}
			this.Generate--;
		}
		if(this.powerStored > 0){
			if(this.powerStored > 10000000){
				this.powerStored = 10000000;
			}
			else if(powerStored < 3000000){
				this.colour = "\u00A7c";
			}
			else if(this.powerStored < 7000000){
				this.colour = "\u00A76";
			}
			else{
				this.colour = "\u00A72";
			}
		}
		else if(this.powerStored > this.capacity){
			this.powerStored = this.capacity;
		}
		super.updateEntity();
	}

	public void checkAmount(EntityPlayer player) {
		if(worldObj.isRemote){
			player.addChatComponentMessage(new ChatComponentText(this.colour + "Energy: " + this.powerStored + "/" + this.capacity));
			if(this.Generate > 0){
				if(this.module == 1){
					player.addChatComponentMessage(new ChatComponentText("Generating: 5000"));
				}
				else{
					player.addChatComponentMessage(new ChatComponentText("Generating: 1000"));
				}
				player.addChatComponentMessage(new ChatComponentText("Burntime: " + this.Generate/20 + "s"));
			}
			else{
				player.addChatComponentMessage(new ChatComponentText("Generating: 0"));
				player.addChatComponentMessage(new ChatComponentText("Not running"));
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
