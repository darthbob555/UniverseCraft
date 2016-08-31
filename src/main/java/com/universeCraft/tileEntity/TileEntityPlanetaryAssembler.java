package com.universeCraft.tileEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.universeCraft.handler.PlanetHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class TileEntityPlanetaryAssembler extends TileEntity {

	public int energyStored = 0;
	public int energyCapacity = 820200000;
	public boolean isRunning = false;
	public boolean canRun = false;
	public int energyRequired = 0;
	private Block blockToPlace = Blocks.air;
	public int diameter = 0;
	PlanetHandler handler = new PlanetHandler();
	public boolean rings = false;
	private int i = 0;

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.energyStored = tagCompound.getInteger("Energy");
		this.isRunning = tagCompound.getBoolean("Running");
		this.energyRequired = tagCompound.getInteger("Required");
		this.diameter = tagCompound.getInteger("Diameter");
		this.rings = tagCompound.getBoolean("Rings");
	}

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Energy", this.energyStored);
		tagCompound.setBoolean("Running", this.isRunning);
		tagCompound.setInteger("Required", this.energyRequired);
		tagCompound.setInteger("Diameter", this.diameter);
		tagCompound.setBoolean("Rings", this.rings);
	}

	public void updateEntity(){
		if(this.energyRequired > 0 && this.energyRequired <= this.energyStored){
			this.isRunning = true;
			this.energyStored -= this.energyRequired;
			this.energyRequired = 0;
		}
		if(this.isRunning && this.getBlockToPlace() != Blocks.air){
			for(int i = 0; i<10; i++){
				handler.createPlanet(worldObj, xCoord-50, yCoord+50, zCoord, this.diameter, this.getBlockToPlace());
			}
		}
		if(this.canRun && this.rings && this.getBlockToPlace() != Blocks.air){
			if(i<20){
				i++;
			}
			else{
				handler.createRings(worldObj, xCoord-50, yCoord+50, zCoord, this.diameter, blockToPlace);
			}
		}
		if(this.canRun && !this.rings){
			handler.sprinkle(worldObj, xCoord-50, yCoord+50, zCoord, this.diameter);
		}
		super.updateEntity();
	}

	public void establishBase(ItemStack itemstack, EntityPlayer player) {
		String materialChat = "";
		int diameter = itemstack.getTagCompound().getInteger("Diameter");
		int material = itemstack.getTagCompound().getInteger("Material");
		int intMaterial = 1;
		int intRings = 1;
		boolean rings = itemstack.getTagCompound().getBoolean("Rings");
		if(rings){
			intRings = 100;
		}
		if(material == 0){
			this.setBlockToPlace(Blocks.dirt);
			materialChat = "Dirt";
		}
		if(material == 1){
			intMaterial = 5;
			this.setBlockToPlace(Blocks.end_stone);
			materialChat = "Endstone";
		}
		else if(material == 2){
			intMaterial = 5; 
			this.setBlockToPlace(Blocks.netherrack);
			materialChat = "Netherrack";
		}
		else if(material == 3){ 
			intMaterial = 20;
			this.setBlockToPlace(Blocks.obsidian);
			materialChat = "Obsidian";
		}
		else if(material == 4){
			this.setBlockToPlace(Blocks.stone);
			materialChat = "Stone";
		}
		else if(material == 5){ 
			intMaterial = 500;
			this.setBlockToPlace(Blocks.diamond_block);
			materialChat = "Diamond Block";
		}
		else if(material == 6){ 
			intMaterial = 500;
			this.setBlockToPlace(Blocks.emerald_block);
			materialChat = "Emerald Block";
		}
		this.energyRequired = (diameter*diameter)*intMaterial*intRings;
		this.rings = rings;
		if(worldObj.isRemote){
			player.addChatComponentMessage(new ChatComponentText(""));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Planetary traits copied."));
			player.addChatComponentMessage(new ChatComponentText(""));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Planet settings:" + "\u00A7n"));
			player.addChatComponentMessage(new ChatComponentText(""));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Diameter: " + diameter));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Material: " + materialChat));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Rings: " + this.rings));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Base cost: 1,000,000 SE"));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Additional cost: " + this.energyRequired + " SE"));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Total cost: " + (this.energyRequired+1000000) + " SE"));
		}
		this.energyRequired += 1000000;
		this.diameter = diameter;
	}	
	
	public Block getBlockToPlace() {
		return blockToPlace;
	}

	public void setBlockToPlace(Block blockToPlace) {
		this.blockToPlace = blockToPlace;
	}

	public void status(EntityPlayer player) {
		if(energyRequired > 0){
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Energy Required: " + this.energyRequired));
			if(this.isRunning){
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "QTD has enough energy. Creating planet."));
			}
		}
		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.BLUE + "Energy: " + this.energyStored));
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
