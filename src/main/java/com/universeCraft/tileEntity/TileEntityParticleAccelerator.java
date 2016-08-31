package com.universeCraft.tileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.generation.ParAccelGenerationHandler;
import com.universeCraft.handler.RefinedStarHandler;
import com.universeCraft.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class TileEntityParticleAccelerator extends TileEntity{

	public int i = 0;
	public int limit = 0;
	private int diamond = 0;
	private int conductor = 0;
	private int condensed = 0;
	private int singularium = 0;
	private int toroid = 0;
	private int water = 0;
	private int glass = 0;
	public String status = "";
	public int antimatter = 0;
	private boolean isAccelerating = false;
	private int smallParticles = 5;
	private int largeParticles = 100;
	public int energyStored = 0;
	public int energyCapacity = 3000000;
	public int module = 0;
	private boolean build = false;
	private int energyLoss;
	private ItemStack itemstackToSpawn;

	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		this.status = tagCompound.getString("Status");
		this.isAccelerating = tagCompound.getBoolean("Accelerating");
		this.i = tagCompound.getInteger("Increment");
		this.limit = tagCompound.getInteger("Limit");
		this.diamond = tagCompound.getInteger("Diamond");
		this.conductor = tagCompound.getInteger("Conductor");
		this.condensed = tagCompound.getInteger("Condensed");
		this.singularium = tagCompound.getInteger("Singularium");
		this.antimatter = tagCompound.getInteger("Antimatter");
		this.water = tagCompound.getInteger("Water");
		this.glass = tagCompound.getInteger("Glass");
		this.energyStored = tagCompound.getInteger("Energy");
		this.module = tagCompound.getInteger("Module");
		this.build = tagCompound.getBoolean("Build");
		this.energyLoss = tagCompound.getInteger("EnergyLoss");
	}

	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setString("Status", this.status);
		tagCompound.setBoolean("Accelerating", this.isAccelerating);
		tagCompound.setInteger("Increment", this.i);
		tagCompound.setInteger("Limit", this.limit);
		tagCompound.setInteger("Diamond", this.diamond);
		tagCompound.setInteger("Conductor", this.conductor);
		tagCompound.setInteger("Condensed", this.condensed);
		tagCompound.setInteger("Singularium", this.singularium);
		tagCompound.setInteger("Antimatter", this.antimatter);
		tagCompound.setInteger("Water", this.water);
		tagCompound.setInteger("Glass", this.glass);
		tagCompound.setInteger("Energy", this.energyStored);
		tagCompound.setInteger("Module", this.module);
		tagCompound.setBoolean("Build", this.build);
		tagCompound.setInteger("EnergyLoss", this.energyLoss);

	}

	@Override
	public void updateEntity(){
		Random rand = new Random();
		if(this.build){
			if(i<120){
				i += 1;
			}
			if(i == 40 || i == 80 || i == 120){
				int k = i/40;
				this.formMultiBlock(worldObj, xCoord, yCoord, zCoord, k);
				if(i == 120){
					i = 0;
					this.build = false;
				}
			}
		}
		if(this.isAccelerating && this.energyStored > 0){
			if(i<limit){
				i++;
				if(i%20 == 0){
					worldObj.playSound(xCoord, yCoord, zCoord, "random.explode", 1F, 1F, true);
				}
				if(this.energyStored < (this.energyLoss/this.limit)){
					this.energyStored = 0;
					this.isAccelerating = false;
				}
				else{
					this.energyStored -= (this.energyLoss/this.limit);
				}
			}
			else{
				if(this.limit == 10000){
					this.antimatter += this.smallParticles + (this.largeParticles-this.smallParticles)*rand.nextDouble();
				}
				this.isAccelerating = false;
				this.i = 0; 
				this.energyLoss = 0; 
				this.limit = 0;
				if(itemstackToSpawn != null){
					this.output(itemstackToSpawn);
				}
				worldObj.playSound(xCoord, yCoord, zCoord, "random.explode", 2F, 1F, true);
			}
		}
		if(worldObj.getTileEntity(xCoord-7, yCoord+1, zCoord) != null){
			if(worldObj.getTileEntity(xCoord-7, yCoord+1, zCoord) instanceof TileEntityChest){
				TileEntityChest chest = (TileEntityChest) worldObj.getTileEntity(xCoord-7, yCoord+1, zCoord);
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
							this.isAccelerating = true;
							this.markDirty();
							worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
						}
					}
				}
			}
		}
	}

	public boolean findInput(ItemStack slot) {
		if(!this.isAccelerating){
			if(slot.isItemEqual(new ItemStack(Blocks.stone))){
				if(this.module != 1 || this.energyStored >= 10000){
					this.energyLoss = 10000;
					this.limit = 200;
					this.itemstackToSpawn = null;
					this.markDirty();
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					return true;
				}
				else{
					return false;
				}
			}
			else if(slot.isItemEqual(new ItemStack(ModItems.StarBase))){
				if(this.module != 1 || this.energyStored >= 120000){
					this.limit = 1200;
					this.energyLoss = 120000;
					this.itemstackToSpawn = new ItemStack(ModItems.StarBase);
					RefinedStarHandler handler = new RefinedStarHandler();
					handler.getStats(itemstackToSpawn, new Random());
					this.markDirty();
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					return true;
				}
				else{
					return false;
				}
			}
			else if(slot.isItemEqual(new ItemStack(ModItems.Particles, 1, 19))){
				if(this.module != 1 || this.energyStored >= 600000){
					this.limit = 1200;
					this.energyLoss = 600000;
					this.itemstackToSpawn = new ItemStack(ModItems.Particles, 1, 8);
					this.markDirty();
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					return true;
				}
				else{
					return false;
				}
			}
			else if(slot.isItemEqual(new ItemStack(ModItems.Particles, 1, 26))){
				if(this.module != 1 || this.energyStored >= 1200000){
					this.limit = 2400;
					this.energyLoss = 1200000;
					this.itemstackToSpawn = new ItemStack(ModItems.Particles, 1, 28);
					this.markDirty();
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					return true;
				}
				else{
					return false;
				}
			}
			else if(slot.isItemEqual(new ItemStack(ModItems.Particles, 1, 42))){
				if(this.module != 1 || this.energyStored >= 3000000){
					this.limit = 6000;
					this.energyLoss = 3000000;
					this.itemstackToSpawn = new ItemStack(ModItems.Particles, 1, 45);
					this.markDirty();
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
					return true;
				}
				else{
					return false;
				}
			}
			else{
				for(int increment = 9; increment<20; increment++){
					if(slot.isItemEqual(new ItemStack(ModItems.Particles, 1, increment))){
						if(this.module != 1 || this.energyStored >= 25000){
							this.limit = 200;
							this.energyLoss = 25000;
							if(slot.getTagCompound() != null && slot.getTagCompound().hasKey("Atoms") && slot.getTagCompound().hasKey("SingleUnit")){
								int amount = (int) (slot.getTagCompound().getDouble("Atoms")/slot.getTagCompound().getDouble("SingleUnit"));
								if(increment == 9) this.itemstackToSpawn = new ItemStack(Items.diamond, amount, 0);
								else if(increment == 10) this.itemstackToSpawn = new ItemStack(Items.gold_ingot, amount, 0);
								else if(increment == 11) this.itemstackToSpawn = new ItemStack(Items.emerald, amount, 0);
								else if(increment == 12) this.itemstackToSpawn = new ItemStack(Items.iron_ingot, amount, 0);
								else if(increment == 13) this.itemstackToSpawn = new ItemStack(Items.dye, amount, 4);
								else if(increment == 14) this.itemstackToSpawn = new ItemStack(Items.redstone, amount, 0);
								else if(increment == 15) this.itemstackToSpawn = new ItemStack(Items.quartz, amount, 0);
								else if(increment == 16) this.itemstackToSpawn = new ItemStack(ModItems.Particles, amount, 0);
								else if(increment == 17) this.itemstackToSpawn = new ItemStack(ModItems.Particles, amount, 1);
								else if(increment == 18) this.itemstackToSpawn = new ItemStack(Items.coal, amount, 0);
								else if(increment == 19) this.itemstackToSpawn = new ItemStack(ModItems.Particles, amount, 0);
								increment = 20;
								this.markDirty();
								worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public void output(ItemStack itemstack) {
		if(worldObj.getTileEntity(xCoord+7, yCoord+1, zCoord) != null){
			if(worldObj.getTileEntity(xCoord+7, yCoord+1, zCoord) instanceof TileEntityChest){
				TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(xCoord+7, yCoord+1, zCoord);
				boolean spawn = true;
				for(int i = 0; i<27; i++){
					ItemStack slot = te.getStackInSlot(i);
					if(itemstack != null){
						if(slot == null || slot.isItemEqual(itemstack)){
							if(te.getStackInSlot(i) != null){
								itemstack.stackSize = te.getStackInSlot(i).stackSize+itemstack.stackSize;
								if(itemstack.stackSize <= 64){
									te.setInventorySlotContents(i, itemstack);
									itemstack = null;
									spawn = false;
								}
								i = 27;
							}
							else{
								te.setInventorySlotContents(i, itemstack);
								i = 27;
								spawn = false;
							}
						}
						if(spawn){
							this.spawnInWorld(itemstack);
						}
					}
				}
			}
		}
	}

	public void spawnInWorld(ItemStack itemstack) {
		Entity entity = new EntityItem(worldObj);
		if(entity != null && itemstack != null){
			entity = new EntityItem(worldObj, xCoord, yCoord+2, zCoord, itemstack);
			if(!worldObj.isRemote){
				worldObj.spawnEntityInWorld(entity);
			}
		}
	}

	public void updateStatus(World world, int x, int y, int z, EntityPlayer player){
		this.status = "constructed";
	}

	public void consumeItems(EntityPlayer player, NBTTagCompound tagCompound){
		ItemStack itemstack = player.inventory.getCurrentItem();
		if(tagCompound != null || itemstack != null){
			if(itemstack.isItemEqual(new ItemStack(Items.diamond)) || itemstack.isItemEqual(new ItemStack(Blocks.diamond_block))){
				if(itemstack.isItemEqual(new ItemStack(Items.diamond))){
					if(this.diamond == 64){
						player.addChatComponentMessage(new ChatComponentText("You have Enough of This Material"));
					}
					else if(tagCompound.getInteger("Diamond")<64){
						this.diamond++;
						tagCompound.setInteger("Diamond", this.diamond);
						player.getCurrentEquippedItem().stackSize--;
					}
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Current Diamond Count: " + this.diamond + "/64"));
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(Blocks.diamond_block))){
					if(this.diamond > 55){
						if(worldObj.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Too Many Diamonds To Insert; Decrease Into Single Diamonds"));
						}
					}
					else if(tagCompound.getInteger("Diamond")<56){
						this.diamond = this.diamond+9;
						tagCompound.setInteger("Diamond", this.diamond);
						player.getCurrentEquippedItem().stackSize--;
					}
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Current Diamond Count: " + this.diamond + "/64"));
					}
				}
			}
			if(itemstack.isItemEqual(new ItemStack(ModBlocks.metadataBlocks, 1, 5))){
				if(this.conductor == 128){
					player.addChatComponentMessage(new ChatComponentText("You have Enough of This Material"));
				}
				else if(this.conductor<128){
					this.conductor++;
					tagCompound.setInteger("Conductor", this.conductor);
					player.getCurrentEquippedItem().stackSize--;
				}
				if(worldObj.isRemote){
					player.addChatComponentMessage(new ChatComponentText("Current Conductor Magnets Count: " + this.conductor + "/128"));
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 0)) || itemstack.isItemEqual(new ItemStack(ModBlocks.metadataBlocks, 1, 0))){
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 0))){
					if(this.condensed == 108){
						if(worldObj.isRemote){
							player.addChatComponentMessage(new ChatComponentText("There Are Already Enough Condensed Ingots"));
						}
					}
					else if(tagCompound.getInteger("Condensed")<108){
						this.condensed++;
						tagCompound.setInteger("Condensed", this.condensed);
						player.getCurrentEquippedItem().stackSize--;
					}
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Current Condensed Ingot Count: " + this.condensed + "/108"));
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(ModBlocks.metadataBlocks, 1, 0))){
					if(this.condensed > 99){
						if(worldObj.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Too Many Condensed Ingots To Insert; Decrease Into Single Condensed Ingots"));
						}
					}
					else if(tagCompound.getInteger("Condensed")<100){
						this.condensed = this.condensed+9;
						tagCompound.setInteger("Condensed", this.condensed);
						player.getCurrentEquippedItem().stackSize--;
					}
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Current Condensed Ingot Count: " + this.condensed + "/108"));
					}
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 1)) || itemstack.isItemEqual(new ItemStack(ModBlocks.metadataBlocks, 1, 1))){
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 1))){
					if(this.singularium == 72){
						if(worldObj.isRemote){
							player.addChatComponentMessage(new ChatComponentText("There Are Already Enough Singularium Ingots"));
						}
					}
					else if(tagCompound.getInteger("Singularium")<72){
						this.singularium++;
						tagCompound.setInteger("Singularium", this.singularium);
						player.getCurrentEquippedItem().stackSize--;
					}
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Current Singularium Ingot Count: " + this.singularium + "/72"));
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(ModBlocks.metadataBlocks, 1, 1))){
					if(this.singularium > 63){
						if(worldObj.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Too Many Singularium Ingots To Insert; Decrease Into Single singularium Ingots"));
						}
					}
					else if(tagCompound.getInteger("Singularium")<63){
						this.singularium = this.singularium+9;
						tagCompound.setInteger("singularium", this.singularium);
						player.getCurrentEquippedItem().stackSize--;
					}
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Current Singularium Ingot Count: " + this.singularium + "/72"));
					}
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(ModBlocks.metadataBlocks, 1, 4))){
				if(tagCompound.getInteger("Toroid") == 256){
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("There Are Already Enough Toroid Magnets"));
					}
				}
				else if(tagCompound.getInteger("Toroid")<256){
					this.toroid++;
					tagCompound.setInteger("Toroid", this.toroid);
					player.getCurrentEquippedItem().stackSize--;
				}
				if(worldObj.isRemote){
					player.addChatComponentMessage(new ChatComponentText("Current Toroid Magnet Count: " + this.toroid + "/256"));
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(Items.water_bucket))){
				if(this.water == 16){
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("There Is Already Enough Water"));
					}
				}
				else if(this.water<16){
					this.water++;
					tagCompound.setInteger("Water", this.water);
					player.inventory.addItemStackToInventory(new ItemStack(Items.bucket));
					player.getCurrentEquippedItem().stackSize--;
				}
				if(worldObj.isRemote){
					player.addChatComponentMessage(new ChatComponentText("Current Water Count: " + this.water + "/16"));
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(Blocks.glass))){
				if(tagCompound.getInteger("Glass") == 32){
					if(worldObj.isRemote){
						player.addChatComponentMessage(new ChatComponentText("There Are Already Enough Glass"));
					}
				}
				else if(tagCompound.getInteger("Glass")<32){
					this.glass++;
					tagCompound.setInteger("Glass", this.glass);
					player.getCurrentEquippedItem().stackSize--;
				}
				if(worldObj.isRemote){
					player.addChatComponentMessage(new ChatComponentText("Current Water Count: " + this.glass + "/32"));
				}
			}
		}
		if(this.water == 16 && this.glass == 32 && this.diamond == 64 && this.singularium == 72 && this.condensed == 108 && this.toroid == 256 && this.conductor == 128){
			player.addChatComponentMessage(new ChatComponentText("All Material Requirements Are Met"));
			this.status = "built";
		}
	}

	public void displayData(EntityPlayer player) {
		if(worldObj.isRemote){
			player.addChatComponentMessage(new ChatComponentText("Water: " + water + "/16"));
			player.addChatComponentMessage(new ChatComponentText("Glass: " + glass + "/32"));
			player.addChatComponentMessage(new ChatComponentText("Diamond: " + diamond + "/64"));
			player.addChatComponentMessage(new ChatComponentText("Singularium: " + singularium + "/72"));
			player.addChatComponentMessage(new ChatComponentText("Condensed: " + condensed + "/108"));
			player.addChatComponentMessage(new ChatComponentText("Conductor: " + conductor + "/128"));
			player.addChatComponentMessage(new ChatComponentText("Toroid: " + toroid + "/256"));
		}
	}

	public void buildLayer() {
		this.build = true;
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public void formMultiBlock(World world, int x, int y, int z, int layer){
		ParAccelGenerationHandler generator = new ParAccelGenerationHandler();
		if(layer == 1) generator.layer1(world, x-18, y, z-18);
		else if(layer == 2) generator.layer2(world, x-18, y-1, z-18);
		else if(layer == 3){
			generator.layer3(world, x-18, y-2, z-18);
			this.status = "multiblock";
		}
	}

	public void showMatter(World world, int x, int y, int z){
		double antimatterBlocks = this.antimatter/10;
		loop:
			if(this.antimatter > 1){
				for(int a = 18; a > -19; a--){
					for(int b = 1; b > -2; b--){
						for(int c = 19; c > -19; c--){
							if(world.getBlock(x+a, y+b, z+c) != Blocks.air){
								world.setBlock(x+a, y+b, z+c, Blocks.flowing_lava, 0, 2);
								antimatterBlocks--;
							}
							if(antimatterBlocks < 1){
								break loop;
							}
						}
					}
				}
			}
	}

	public void creativeMode(){
		this.water = 16;
		this.glass = 32;
		this.diamond = 64;
		this.singularium = 72;
		this.condensed = 108;
		this.conductor = 128;
		this.toroid = 256;
	}

	public void displayDataProcessing(EntityPlayer player) {
		if(worldObj.isRemote){
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Energy Stored: " + this.energyStored + "/" + this.energyCapacity));
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Antimatter: " + this.antimatter + " Atoms"));
			if(this.limit > 0){
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Burn time: " + ((this.limit-this.i)/20) + "s"));
				float percentage = (float)this.i/this.limit;
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Complete: " + percentage*100 + "%"));
			}
			else{
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Burn time: " + "Not running"));
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