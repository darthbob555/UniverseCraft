package com.universeCraft.tileEntity;

import java.util.Random;

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

public class TileEntityDuplication extends TileEntity {

	public int blackholium = 0;
	public int energyStored = 0;
	public int energyCapacity = 50000000;
	public boolean isRunning = false;
	private int i = 0;
	private ItemStack equipped;

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.blackholium = tagCompound.getInteger("Blackholium");
		this.energyStored = tagCompound.getInteger("Energy");
		this.isRunning = tagCompound.getBoolean("Running");
		this.i = tagCompound.getInteger("Increment");
	}

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("Blackholium", this.blackholium);
		tagCompound.setInteger("Energy", this.energyStored);
		tagCompound.setBoolean("Running", this.isRunning);
		tagCompound.setInteger("Increment", this.i);
	}

	public void updateEntity(){
		Random rand = new Random();
		if(this.isRunning){
			if(i < 300){
				if(i%10 == 0){
					EntityTNTPrimed tnt = new EntityTNTPrimed(worldObj);
					EntityLightningBolt lightning = new EntityLightningBolt(worldObj, xCoord+rand.nextInt(10)-rand.nextInt(20), yCoord+rand.nextInt(10)-rand.nextInt(20), zCoord+rand.nextInt(10)-rand.nextInt(20));
					for(int i = 0; i<5; i++){
						worldObj.spawnEntityInWorld(lightning);
					}
					worldObj.createExplosion(tnt, (double)this.xCoord+rand.nextInt(10)-rand.nextInt(20), (double)this.yCoord+rand.nextInt(10)-rand.nextInt(20), (double)this.zCoord+rand.nextInt(10)-rand.nextInt(20), 8.0F, true);
				}
				i++;
			}
			else{
				i = 0;
				this.isRunning = false;
				EntityItem entity = new EntityItem(worldObj, xCoord, yCoord, zCoord, equipped);
				if(!worldObj.isRemote){
					worldObj.spawnEntityInWorld(entity);
				}
				this.energyStored = 0;
				this.blackholium = 0;
			}
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

	public void start(ItemStack equipped) {
		this.isRunning = true;
		this.equipped = equipped;
	}
}
