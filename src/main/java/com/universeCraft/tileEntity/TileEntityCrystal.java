package com.universeCraft.tileEntity;

import java.util.Random;

import com.universeCraft.items.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrystal extends TileEntity {

	public boolean primed = false;
	public boolean explode = false;
	public int i = 0;
	EntityItem item;
	public int power = 0;
	public int limit = 5;

	public void readFromNBT(NBTTagCompound tagCompound){
		super.readFromNBT(tagCompound);
		this.primed = tagCompound.getBoolean("Primed");
		this.explode = tagCompound.getBoolean("Explode");
		this.i = tagCompound.getInteger("Increment");
		this.power = tagCompound.getInteger("Power");
		this.limit = tagCompound.getInteger("Limit");
	}

	public void writeToNBT(NBTTagCompound tagCompound){
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("Primed", this.primed);
		tagCompound.setBoolean("Explode", this.explode);
		tagCompound.setInteger("Increment", this.i);
		tagCompound.setInteger("Power", this.power);
		tagCompound.setInteger("Limit", this.limit);
	}

	public void updateEntity(){
		Random rand = new Random();
		if(this.primed){
			for(int i = 0; i<10; i++){
				worldObj.spawnParticle("reddust", xCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, yCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, zCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, 0.25D, 0.25D, 0.25D);
				worldObj.spawnParticle("reddust", xCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, yCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, zCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, 0.0D, 0.5D, 0.0D);
			}
		}
		else if(this.explode){
			if(this.i < 200){
				this.i++;
				for(int i = 0; i<10; i++){
					worldObj.spawnParticle("reddust", xCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, yCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, zCoord+rand.nextFloat()-(rand.nextFloat()*2)+1, 0.0D, 0.0D, 0.0D);
				}
			}
			else{
				item = new EntityItem(worldObj, xCoord, yCoord, zCoord, new ItemStack(ModItems.Particles, this.power, 41));
				if(!worldObj.isRemote){
					worldObj.spawnEntityInWorld(item);
				}
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				this.i = 0;
				this.explode = false;
				EntityTNTPrimed tnt = new EntityTNTPrimed(worldObj);
				worldObj.createExplosion(tnt, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (this.power/4), true);
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
