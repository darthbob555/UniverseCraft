package com.universeCraft.items;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.ibm.icu.impl.ICUService.Key;
import com.universeCraft.handler.RefinedStarHandler;
import com.universeCraft.main.UniverseCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemStarBase extends Item{

	public IIcon[] icons;
	//Colour
	private String setColour;
	private String setColourRadii;
	private String setColourMass;
	private String setColourStatus;
	private String setColourStorage;
	//Font format
	private String setFormatBold;
	//Data
	private double setDataStabiliseProgress;
	private int setDataType;
	private int setDataCost;
	private String setDataStatus;
	private double setDataSolarRadii;
	private double setDataMass;
	private int setDataStorage;
	private int setCurrentStorage;
	//NBT Data Tag Variables
	private double lowMass;
	private double highMass;
	private double finalMass;
	private double lowRadii;
	private double highRadii;
	private double finalRadii;
	private double progress;
	private int required;
	private int amount;
	private int energy;

	public ItemStarBase(){
		setUnlocalizedName(UniverseCraft.MODID + "_" + "StarBase");
		setTextureName(UniverseCraft.MODID + ":" + "StarBase");
		setCreativeTab(UniverseCraft.universeCraft);
		this.setMaxStackSize(1);
		this.setColour = "\u00A79";
		this.setColourMass = "\u00A72";
		this.setColourRadii = "\u00A7e";
		this.setColourStatus = "\u00A76";
		this.setColourStorage = "\u00A74";
		this.setFormatBold = "\u00A7l";
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_){
		Random rand = new Random();
		if(itemstack.stackTagCompound == null){
			itemstack.stackTagCompound = new NBTTagCompound();
			itemstack.stackTagCompound.setString("STATUS", "FORMED");
			itemstack.stackTagCompound.setBoolean("FORMED", true);
			if(player.capabilities.isCreativeMode){
				RefinedStarHandler handler = new RefinedStarHandler();
				handler.creativeMode(itemstack);
			}
			else{
				this.getType(itemstack, rand);
				this.getRadius(itemstack, rand);
				this.getMass(itemstack, rand);
				this.setCostAmplifier(itemstack);
				this.getStorage(itemstack);
			}
		}
		else{
			if(world.getBlock(x, y, z) == Blocks.diamond_block){
				if(itemstack.getTagCompound().getInteger("STORAGE") == itemstack.getTagCompound().getInteger("ENERGY")){
					ItemStack core = new ItemStack(ModItems.Particles, 1, 29);
					core.stackTagCompound = new NBTTagCompound();
					core.stackTagCompound.setInteger("Power", itemstack.getTagCompound().getInteger("COST"));
					player.inventory.addItemStackToInventory(core);
					itemstack.getTagCompound().setInteger("ENERGY", 0);
					world.setBlockToAir(x, y, z);
					Entity entity = new EntityLightningBolt(world, x, y, z);
					world.spawnEntityInWorld(entity);
				}
				else{
					if(world.isRemote){
						player.addChatComponentMessage(new ChatComponentText("This star needs to be fully charged"));
					}
				}
			}
		}
		return true;
	}

	private void getStorage(ItemStack itemstack) {
		double size = itemstack.getTagCompound().getDouble("STARRADII");
		double mass = itemstack.getTagCompound().getDouble("STARMASS");
		double densityCal = size*mass*1000000;
		int total = (int) Math.round(densityCal);
		itemstack.stackTagCompound.setInteger("STORAGE", total);
		itemstack.stackTagCompound.setInteger("ENERGY", 0);
	}

	private void getType(ItemStack itemstack, Random rand) {
		double d = rand.nextDouble()*100;
		if(d < 40){ //0-39
			itemstack.stackTagCompound.setInteger("STARTYPE", 0);
		}
		else if(d < 70){//40-69
			itemstack.stackTagCompound.setInteger("STARTYPE", 1);
		}
		else if(d < 90){ //70-89
			itemstack.stackTagCompound.setInteger("STARTYPE", 2);
		}
		else{ //90-99
			itemstack.stackTagCompound.setInteger("STARTYPE", 3);
		}
	}

	public void getRadius(ItemStack itemstack, Random rand){
		if(itemstack.getTagCompound().getInteger("STARTYPE") == 0){
			this.lowRadii = 0.08;
			this.highRadii = 0.7;
		}
		else if(itemstack.getTagCompound().getInteger("STARTYPE") == 1){
			this.lowRadii = 0.7;
			this.highRadii = 0.96;
		}
		else if(itemstack.getTagCompound().getInteger("STARTYPE") == 2){
			this.lowRadii = 0.96;
			this.highRadii = 1.15;
		}
		else if(itemstack.getTagCompound().getInteger("STARTYPE") == 3){
			this.lowRadii = 1.15;
			this.highRadii = 1.4;
		}
		this.finalRadii = this.lowRadii+(this.highRadii-this.lowRadii)*rand.nextDouble();
		this.finalRadii = (double)Math.round(this.finalRadii*100d)/100d;
		itemstack.stackTagCompound.setDouble("STARRADII", this.finalRadii);
	}

	public void getMass(ItemStack itemstack, Random rand){
		if(itemstack.getTagCompound() != null){
			if(itemstack.getTagCompound().hasKey("STARTYPE")){
				if(itemstack.getTagCompound().getInteger("STARTYPE") == 0){
					this.highMass = 0.45;
					this.lowMass = 0.09;
				}
				else if(itemstack.getTagCompound().getInteger("STARTYPE") == 1){
					this.highMass = 0.8;
					this.lowMass = 0.45;
				}
				else if(itemstack.getTagCompound().getInteger("STARTYPE") == 2){
					this.highMass = 1.04;
					this.lowMass = 0.8;
				}
				else if(itemstack.getTagCompound().getInteger("STARTYPE") == 3){
					this.highMass = 1.4;
					this.lowMass = 1.04;
				}
				this.finalMass = this.lowMass+(this.highMass-this.lowMass)*rand.nextDouble();
				this.finalMass = (double)Math.round(this.finalMass*100d)/100d;
				itemstack.stackTagCompound.setDouble("STARMASS", this.finalMass);
			}
		}
	}

	public void setCostAmplifier(ItemStack itemstack){
		int A = itemstack.getTagCompound().getInteger("STARMASS");
		int B = itemstack.getTagCompound().getInteger("STARTYPE");
		int C = itemstack.getTagCompound().getInteger("STARRADII");
		itemstack.getTagCompound().setInteger("COST", (B+1)*(A+C)+1); //max = 8100
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack){
		String name = "";
		if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("STARTYPE")){
			switch(itemstack.getTagCompound().getInteger("STARTYPE")){
			case 0:
				name = "M-TypeStar";
				break;
			case 1:
				name = "K-TypeStar";
				break;
			case 2:
				name = "G-TypeStar";
				break;
			case 3:
				name = "F-TypeStar";
				break;
			case 4:
				name = "A-TypeStar";
				break;
			case 5:
				name = "B-TypeStar";
				break;
			case 6:
				name = "O-TypeStar";
				break;
			default:
				name = "This star has incorrect NBT";
			}
		}
		else{
			name = "InterstellarClouds";
		}
		return name;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		icons = new IIcon[9];
		icons[0] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "TypeM");
		icons[1] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "TypeK");
		icons[2] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "TypeG");
		icons[3] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "TypeF");
		icons[4] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "TypeA");
		icons[5] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "TypeB");
		icons[6] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "TypeO");
		icons[7] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "InterStellarClouds");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack itemstack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		if(itemstack.stackTagCompound != null){
			if(itemstack.getTagCompound().hasKey("STARTYPE")){
				if(itemstack.stackTagCompound.getInteger("STARTYPE") == 0){
					return icons[0];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 1){
					return icons[1];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 2){
					return icons[2];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 3){
					return icons[3];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 4){
					return icons[4];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 5){
					return icons[5];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 6){
					return icons[6];
				}
				else{
					return icons[7];
				}
			}
			else{
				return icons[7];
			}
		}
		else{
			return icons[7];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack itemstack) {
		if(itemstack.stackTagCompound != null){
			if(itemstack.getTagCompound().hasKey("STARTYPE")){
				if(itemstack.stackTagCompound.getInteger("STARTYPE") == 0){
					return icons[0];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 1){
					return icons[1];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 2){
					return icons[2];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 3){
					return icons[3];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 4){
					return icons[4];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 5){
					return icons[5];
				}
				else if(itemstack.stackTagCompound.getInteger("STARTYPE") == 6){
					return icons[6];
				}
				else{
					return icons[7];
				}
			}
			else{
				return icons[7];
			}
		}
		else{
			return icons[7];
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		if(itemstack.getTagCompound() != null){
			if(itemstack.getTagCompound().hasKey("STATUS")){
				this.setDataStatus = itemstack.getTagCompound().getString("STATUS");
				dataList.add(ToolTipStatus());
			}
			if(itemstack.getTagCompound().hasKey("STARTYPE")){
				this.setDataType = itemstack.getTagCompound().getInteger("STARTYPE");
				dataList.add(ToolTipSize());
			}
			if(itemstack.getTagCompound().hasKey("STARMASS")){
				this.setDataMass = itemstack.stackTagCompound.getDouble("STARMASS");
				dataList.add(ToolTipMass());
			}
			if(itemstack.getTagCompound().hasKey("STARRADII")){
				this.setDataSolarRadii = itemstack.stackTagCompound.getDouble("STARRADII");
				dataList.add(ToolTipSolarRadii());
			}
			if(itemstack.getTagCompound().hasKey("COST")){
				this.setDataCost = itemstack.stackTagCompound.getInteger("COST");
				dataList.add(ToolTipCost());
			}
			if(itemstack.getTagCompound().hasKey("STORAGE")){
				this.setDataStorage = itemstack.stackTagCompound.getInteger("STORAGE");
				this.setCurrentStorage = itemstack.stackTagCompound.getInteger("ENERGY");
				dataList.add("");
				dataList.add(ToolTipCurrentStorage() + ToolTipStorage());
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add("");
				dataList.add(EnumChatFormatting.GOLD + "Right click while this star is full");
				dataList.add(EnumChatFormatting.GOLD + "on a diamond block to create a radiant");
				dataList.add(EnumChatFormatting.GOLD + "core. The higher the amplifier, the higher");
				dataList.add(EnumChatFormatting.GOLD + "the power of the radiant core.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else{
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A collection of stellar gases]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "It seems that only a right click could transform this group of gases");
				dataList.add(EnumChatFormatting.AQUA + "into something more powerful. It also seems to be random and limited,");
				dataList.add(EnumChatFormatting.AQUA + "perhaps a molecular level atom smashing may imbue this item further.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
	}

	private String ToolTipCost(){
		return this.setColour + "Cost Amplifier: " + this.setDataCost;
	}

	private String ToolTipStatus(){
		return this.setColourStatus + this.setFormatBold + "STATUS: " + this.setDataStatus;
	}

	private String ToolTipSolarRadii(){
		return this.setColourRadii + "Solar Radii: " + this.setDataSolarRadii + "R☉";
	}

	private String ToolTipSize(){
		return this.setColour + "Star Size: " + this.setDataType;
	}

	private String ToolTipMass(){
		return this.setColourMass + "Solar Mass: " + this.setDataMass + "M☉";
	}

	private String ToolTipStorage(){
		return this.setColourStorage + this.setDataStorage + "SE";
	}

	private String ToolTipCurrentStorage() {
		return "Energy: " + this.setColourStorage + this.setCurrentStorage + "/";
	}
}
