package com.universeCraft.handler;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class RefinedStarHandler {

	private double lowRadii;
	private double highRadii;
	private double finalRadii;
	private double lowMass;
	private double highMass;
	private double finalMass;

	public void getStats(ItemStack itemstack, Random rand){
		itemstack.setTagCompound(new NBTTagCompound());
		this.getType(itemstack, rand);
		this.getRadius(itemstack, rand);
		this.getMass(itemstack, rand);
		this.setCostAmplifier(itemstack);
		this.getStorage(itemstack);
	}
	
	public void getType(ItemStack itemstack, Random rand){
		double d = rand.nextDouble()*100;
		if(d < 50){
			itemstack.stackTagCompound.setInteger("STARTYPE", 4);
		}
		else if(d < 80){
			itemstack.stackTagCompound.setInteger("STARTYPE", 5);
		}
		else{
			itemstack.stackTagCompound.setInteger("STARTYPE", 6);
		}
	}
	
	public void getRadius(ItemStack itemstack, Random rand){
		if(itemstack.getTagCompound().getInteger("STARTYPE") == 4){
			this.lowRadii = 1.4;
			this.highRadii = 1.8;
		}
		else if(itemstack.getTagCompound().getInteger("STARTYPE") == 5){
			this.lowRadii = 1.8;
			this.highRadii = 6.6;
		}
		else if(itemstack.getTagCompound().getInteger("STARTYPE") == 6){
			this.lowRadii = 6.6;
			this.highRadii = 15.0;
		}
		this.finalRadii = this.lowRadii+(this.highRadii-this.lowRadii)*rand.nextDouble();
		this.finalRadii = (double)Math.round(this.finalRadii*100d)/100d;
		itemstack.stackTagCompound.setDouble("STARRADII", this.finalRadii);
	}
	
	public void getMass(ItemStack itemstack, Random rand){
		if(itemstack.getTagCompound().getInteger("STARTYPE") == 4){
			this.highMass = 2.0;
			this.lowMass = 1.4;
		}
		else if(itemstack.getTagCompound().getInteger("STARTYPE") == 5){
			this.highMass = 16.0;
			this.lowMass = 2.0;
		}
		else if(itemstack.getTagCompound().getInteger("STARTYPE") == 6){
			this.highMass = 90.0;
			this.lowMass = 16.0;
		}
		this.finalMass = this.lowMass+(this.highMass-this.lowMass)*rand.nextDouble();
		this.finalMass = (double)Math.round(this.finalMass*100d)/100d;
		itemstack.stackTagCompound.setDouble("STARMASS", this.finalMass);
	}
	
	private void setCostAmplifier(ItemStack itemstack){
		int A = itemstack.getTagCompound().getInteger("STARMASS");
		int B = itemstack.getTagCompound().getInteger("STARTYPE");
		int C = itemstack.getTagCompound().getInteger("STARRADII");
		itemstack.stackTagCompound.setInteger("COST", (B+1)*(A+C)+1);
	}
	
	private void getStorage(ItemStack itemstack) {
		double size = itemstack.getTagCompound().getDouble("STARRADII");
		double mass = itemstack.getTagCompound().getDouble("STARMASS");
		double densityCal = size*mass*1000000;
		int total = (int) Math.round(densityCal);
		itemstack.stackTagCompound.setInteger("STORAGE", total);
		itemstack.stackTagCompound.setInteger("ENERGY", 0);
	}

	public void creativeMode(ItemStack itemstack) {
		itemstack.stackTagCompound.setInteger("STARTYPE", 6);
		itemstack.stackTagCompound.setDouble("STARRADII", 15);
		itemstack.stackTagCompound.setDouble("STARMASS", 90);
		itemstack.stackTagCompound.setInteger("COST", 841);
		itemstack.stackTagCompound.setInteger("STORAGE", 1350000000);
		itemstack.stackTagCompound.setInteger("ENERGY", 1350000000);
	}
}
