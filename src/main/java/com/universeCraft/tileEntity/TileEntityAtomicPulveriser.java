package com.universeCraft.tileEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.handler.ParticleHandler;
import com.universeCraft.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;

public class TileEntityAtomicPulveriser extends TileEntity {

	private int i = 0;
	private int a = 0;
	private String block = "";
	public boolean isRunning = false;
	public int energyStored = 0;
	private int energyRequired = 0;
	public int energyCapacity = 100000;
	public int antimatter = 0;
	ParticleHandler handler = new ParticleHandler();

	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		this.i = tagCompound.getInteger("Increment");
		this.block = tagCompound.getString("Block");
		this.isRunning = tagCompound.getBoolean("Active");
		this.energyStored = tagCompound.getInteger("EnergyS");
		this.energyRequired = tagCompound.getInteger("EnergyR");
		this.energyCapacity = tagCompound.getInteger("EnergyC");
		this.antimatter = tagCompound.getInteger("Antimatter");
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Increment", this.i);
		tagCompound.setString("Block", this.block);
		tagCompound.setBoolean("Active", this.isRunning);
		tagCompound.setInteger("EnergyS", this.energyStored);
		tagCompound.setInteger("EnergyR", this.energyRequired);
		tagCompound.setInteger("EnergyC", this.energyCapacity);
		tagCompound.setInteger("Antimatter", this.antimatter);
	}

	public void updateEntity(){
		Random rand =  new Random();
		if(this.isRunning && this.block != ""){
			if(this.i == 0){
				ItemStack itemstackToPlace = null;
				int gramUnit = 0;
				BigDecimal partPerGram = new BigDecimal("0.0");
				if(this.block.equals("diamond")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 9);
					gramUnit = 4;
					partPerGram = new BigDecimal("200566194837635303913405");
				}
				else if(this.block.equals("gold")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 10);
					gramUnit = 12400;
					partPerGram = new BigDecimal("37904974619289340101522843");
				}
				else if(this.block.equals("emerald")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 11);
					gramUnit = 4;
					partPerGram = new BigDecimal("4481488372093023255814");
				}
				else if(this.block.equals("iron")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 12);
					gramUnit = 3130;
					partPerGram = new BigDecimal("33658678571428571428571");
				}
				else if(this.block.equals("lapis")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 13);
					gramUnit = 250;
					partPerGram = new BigDecimal("97794666926499723927377");
				}
				else if(this.block.equals("redstone")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 14);
					gramUnit = 250;
					partPerGram = new BigDecimal("48897333463249861963689");
				}
				else if(this.block.equals("quartz")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 15);
					gramUnit = 250;
					partPerGram = new BigDecimal("2509166666666666666666667");
				}
				else if(this.block.equals("condensed")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 16);
					gramUnit = 6260;
					partPerGram = new BigDecimal("1805095753538717735220649");
				}
				else if(this.block.equals("singularium")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 17);
					gramUnit = 12520;
					partPerGram = new BigDecimal("16245861781848459616985845");
				}
				else if(this.block.equals("coal")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 18);
					gramUnit = 1000;
					partPerGram = new BigDecimal("10949090909090909090909091");
				}
				else if(this.block.equals("antimatter")){
					itemstackToPlace = new ItemStack(ModItems.Particles, 1, 19);
					gramUnit = 1;
					partPerGram = new BigDecimal("602200000000000000000000");
				}
				itemstackToPlace = handler.setParticles(itemstackToPlace, partPerGram, gramUnit);
				if(worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null){
					if(worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityChest){
						boolean spawn = true;
						TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
						for(int i = 0; i<27; i++){
							ItemStack slot = te.getStackInSlot(i);
							if(itemstackToPlace != null){
								if(slot == null || slot.isItemEqual(itemstackToPlace)){
									if(te.getStackInSlot(i) != null){
										itemstackToPlace.stackSize = te.getStackInSlot(i).stackSize+itemstackToPlace.stackSize;
										if(itemstackToPlace.stackSize <= 64){
											te.setInventorySlotContents(i, itemstackToPlace);
											spawn = false;
										}
										else{
											this.spawnInWorld(itemstackToPlace);
										}
										i = 27;
									}
									else{
										te.setInventorySlotContents(i, itemstackToPlace);
										i = 27;
										spawn = false;
									}
								}
							}
						}
						if(spawn){
							this.spawnInWorld(itemstackToPlace);
						}
					}
					else{
						this.spawnInWorld(itemstackToPlace);
					}
				}
				else{
					this.spawnInWorld(itemstackToPlace);
				}
				this.isRunning = false;
				this.block = "";
			}
			else{
				if(this.energyStored == 0 || this.energyStored < 0){
					this.energyRequired = 0;
					this.isRunning = false;
					this.energyStored = 0;
					this.block = "";
					this.i = 0;
				}
				else{
					i--;
					this.energyRequired-=50;
					this.energyStored-=50;
				}
			}
			worldObj.spawnParticle("reddust", xCoord+0.5, yCoord+0.5, zCoord+0.5, 0.0D+rand.nextFloat(), 0.0D+rand.nextFloat(), 0.0D+rand.nextFloat());
		}
		else if(!this.isRunning && worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0){
			if(worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null){
				if(worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityChest){
					TileEntityChest chest = (TileEntityChest) worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
					for(int i = 0; i<27; i++){
						ItemStack slot = chest.getStackInSlot(i);
						if(slot != null){
							if(this.findInput(slot)){
								if(slot.stackSize == 1){
									chest.setInventorySlotContents(i, null);
								}
								else{
									slot.stackSize--;
									chest.setInventorySlotContents(i, slot);
								}
								i = 27;
							}
						}
					}
				}
			}
		}
		if(this.antimatter > 999 && this.energyStored > 51200){
			this.isRunning = true;
			this.antimatter -= 1000;
			this.block = "antimatter";
			this.i = 1200;
		}
	}

	private void spawnInWorld(ItemStack itemstackToPlace) {
		Entity entity = new EntityItem(worldObj);
		entity = new EntityItem(worldObj, xCoord, yCoord+2, zCoord, itemstackToPlace);
		if(!worldObj.isRemote){
			worldObj.spawnEntityInWorld(entity);
		}
	}

	private boolean findInput(ItemStack itemstack){
		if(itemstack.isItemEqual(new ItemStack(Blocks.gold_ore))){
			this.addMaterial("gold", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(Blocks.diamond_ore))){
			this.addMaterial("diamond", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(Blocks.emerald_ore))){
			this.addMaterial("emerald", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(Blocks.iron_ore))){
			this.addMaterial("iron", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(Blocks.redstone_ore))){
			this.addMaterial("redstone", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(Blocks.lapis_ore))){
			this.addMaterial("lapis", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(Blocks.quartz_ore))){
			this.addMaterial("quartz", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(ModBlocks.metadataBlocks, 1, 2))){
			this.addMaterial("condensed", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(ModBlocks.metadataBlocks, 1, 3))){
			this.addMaterial("singularium", itemstack);
			return true;
		}
		else if(itemstack.isItemEqual(new ItemStack(Blocks.coal_ore))){
			this.addMaterial("coal", itemstack);
			return true;
		}
		else{
			return false;
		}
	}

	public void addMaterial(String material, ItemStack itemstack) {
		this.block = material;
		if(this.block.equals("diamond"))this.i = 200;
		else if(this.block.equals("gold"))this.i = 50;
		else if(this.block.equals("emerald"))this.i = 200;
		else if(this.block.equals("iron"))this.i = 20;
		else if(this.block.equals("lapis"))this.i = 40;
		else if(this.block.equals("redstone"))this.i = 30;
		else if(this.block.equals("quartz"))this.i = 30;
		else if(this.block.equals("condensed"))this.i = 130;
		else if(this.block.equals("singularium"))this.i = 180;
		else if(this.block.equals("coal"))this.i = 20;
		this.isRunning = true;
		this.markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public void displayerData(EntityPlayer player) {
		if(worldObj.isRemote){
			player.addChatComponentMessage(new ChatComponentText("Active: " + this.isRunning));
			player.addChatComponentMessage(new ChatComponentText("Current block: " + this.block));
			player.addChatComponentMessage(new ChatComponentText("Energy stored: " + this.energyStored));
			player.addChatComponentMessage(new ChatComponentText("Burn time: " + (float)this.i/20 + "s"));
			player.addChatComponentMessage(new ChatComponentText("Antimatter atoms: " + this.antimatter));
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