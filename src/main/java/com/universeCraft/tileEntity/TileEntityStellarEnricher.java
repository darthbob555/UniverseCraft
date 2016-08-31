package com.universeCraft.tileEntity;

import java.util.Random;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.generation.StarGenerationHandler;
import com.universeCraft.handler.PlanetHandler;
import com.universeCraft.handler.RefinedStarHandler;
import com.universeCraft.items.ItemStarBase;
import com.universeCraft.items.ModItems;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class TileEntityStellarEnricher extends TileEntity {

	public int energyStored = 0;
	public int energyCapacity = 50000000;
	public int energyRequired = 0;
	public int tier = 0;
	public boolean star = false;
	StarGenerationHandler starGen = new StarGenerationHandler();
	RefinedStarHandler bigStar = new RefinedStarHandler();
	ItemStarBase smallStar = new ItemStarBase();

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.energyStored = tagCompound.getInteger("Stored");
		this.energyRequired = tagCompound.getInteger("Required");
		this.star = tagCompound.getBoolean("Star");
		this.tier = tagCompound.getInteger("Tier");
	}

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Stored", this.energyStored);
		tagCompound.setInteger("Required", this.energyRequired);
		tagCompound.setBoolean("Star", this.star);
		tagCompound.setInteger("Tier", this.tier);
	}

	public void updateEntity(){
		if(this.energyRequired <= this.energyStored){
			if(this.star && worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) > 0){
				starGen.removeStarOutline(worldObj, xCoord-6, yCoord+1, zCoord-6);
				starGen.generateExternal(worldObj, xCoord-6, yCoord+1, zCoord-6);
				starGen.generateInternal(worldObj, xCoord-2, yCoord+4, zCoord-2, tier);
				this.star = false;
				this.energyStored -= this.energyRequired;
			}
		}
		if(worldObj.getBlockPowerInput(xCoord, yCoord, zCoord) == 0){
			if(worldObj.getBlock(xCoord, yCoord+1, zCoord) == ModBlocks.metadataBlocks2){
				int[] blocks = starGen.getInternal(worldObj, xCoord-2, yCoord+5, zCoord-2);
				this.tier = starGen.checkTier(blocks);
				ItemStack star = new ItemStack(ModItems.StarBase);
				star.setTagCompound(new NBTTagCompound());
				star.getTagCompound().setInteger("STARTYPE", tier);
				if(star.getTagCompound().getInteger("STARTYPE") >= 4){
					bigStar.getRadius(star, new Random());
					bigStar.getMass(star, new Random());
				}
				else if(star.getTagCompound().getInteger("STARTYPE") < 4){
					smallStar.getRadius(star, new Random());
					smallStar.getMass(star, new Random());
				}
				EntityItem entity = new EntityItem(worldObj, xCoord+1, yCoord+0.5, zCoord, star);
				if(!worldObj.isRemote){
					worldObj.spawnEntityInWorld(entity);
				}
				starGen.removeStar(worldObj, xCoord-6, yCoord+1, zCoord-6);
				this.tier = 0;
			}
		}
	}

	public void displayStar(){
		this.star = true;
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		starGen.starOutline(worldObj, xCoord-6, yCoord+1, zCoord-6);
	}

	public int getValues(ItemStack itemstack) {
		int size = itemstack.getTagCompound().getInteger("STARTYPE");
		this.energyRequired = size*size*100000;
		this.tier = size;
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return this.energyRequired;
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
