package com.universeCraft.handler;

import java.math.BigDecimal;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ParticleHandler {
	
	private double particles = 0;
	private double singleUnit = 0;
	private BigDecimal inpure = new BigDecimal("0.0");
	private String unit = "0";
	private BigDecimal multiply = new BigDecimal("0.0");

	public ItemStack setParticles(ItemStack itemstack, BigDecimal partPerGram, int gramUnit){
		Random rand = new Random();
		if(partPerGram.toString().length() <= 21){
			unit = "x10^20";
			this.inpure = partPerGram.divide(new BigDecimal("100000000000000000000"));
		}
		else{
			unit = "x10^23";
			this.inpure = partPerGram.divide(new BigDecimal("100000000000000000000000"));
		}
		this.singleUnit = this.inpure.doubleValue();
		this.singleUnit = Math.round(this.singleUnit*1000d)/1000d;
		this.multiply = new BigDecimal(1+rand.nextInt(3));
		this.inpure = this.inpure.multiply(this.multiply);
		this.particles = this.inpure.floatValue();
		this.particles = Math.round((this.particles)*1000d)/1000d;
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setDouble("Atoms", this.particles);
		itemstack.getTagCompound().setInteger("GramUnit", gramUnit);
		itemstack.getTagCompound().setDouble("SingleUnit", this.singleUnit);
		itemstack.getTagCompound().setString("Unit", unit);
		return itemstack;
	}
}
