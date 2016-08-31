package com.universeCraft.blocks;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.universeCraft.generation.StarGenerationHandler;
import com.universeCraft.handler.IonEnergyStored;
import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.tileEntity.TileEntityDuplication;
import com.universeCraft.tileEntity.TileEntityEnergyAcceptor;
import com.universeCraft.tileEntity.TileEntityIonCreative;
import com.universeCraft.tileEntity.TileEntityIonHuge;
import com.universeCraft.tileEntity.TileEntityIonLarge;
import com.universeCraft.tileEntity.TileEntityIonMedium;
import com.universeCraft.tileEntity.TileEntityIonSmall;
import com.universeCraft.tileEntity.TileEntityPlanetaryAssembler;
import com.universeCraft.tileEntity.TileEntityStellarEnricher;
import com.universeCraft.tileEntity.TileEntityWirelessController;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import scala.swing.event.Key;
import tv.twitch.chat.Chat;

public class BlockIonStorage extends BlockContainer{	

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlockIonStorage(){
		super(Material.rock);
		setBlockName(UniverseCraft.MODID + "_" + "Ion");
		setCreativeTab(UniverseCraft.universeCraft);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 2);
		setResistance(1000F);
		setHardness(5.5F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity entity = world.getTileEntity(x, y, z);
		ItemStack itemstack = player.getCurrentEquippedItem();
		if(entity != null && itemstack != null){
			if(itemstack.isItemEqual(new ItemStack(ModItems.StarBase))){
				if(itemstack.getTagCompound() != null){
					if(itemstack.getTagCompound().getInteger("ENERGY") != itemstack.getTagCompound().getInteger("STORAGE")){
						int requested = itemstack.getTagCompound().getInteger("STORAGE") - itemstack.getTagCompound().getInteger("ENERGY");
						if(entity instanceof TileEntityIonSmall){
							TileEntityIonSmall te = (TileEntityIonSmall) entity;
							if(requested < te.storedEnergy || requested == te.storedEnergy){
								itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("STORAGE"));
								te.storedEnergy -= requested;
							}
							else{
								itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("ENERGY") + te.storedEnergy);
								te.storedEnergy = 0;
							}
						}
						else if(entity instanceof TileEntityIonMedium){
							TileEntityIonMedium te = (TileEntityIonMedium) entity;
							if(requested < te.storedEnergy || requested == te.storedEnergy){
								itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("STORAGE"));
								te.storedEnergy -= requested;
							}
							else{
								itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("ENERGY") + te.storedEnergy);
								te.storedEnergy = 0;
							}
						}
						else if(entity instanceof TileEntityIonLarge){
							TileEntityIonLarge te = (TileEntityIonLarge) entity;
							if(requested < te.storedEnergy || requested == te.storedEnergy){
								itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("STORAGE"));
								te.storedEnergy -= requested;
							}
							else{
								itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("ENERGY") + te.storedEnergy);
								te.storedEnergy = 0;
							}
						}
						else if(entity instanceof TileEntityIonHuge){
							TileEntityIonHuge te = (TileEntityIonHuge) entity;
							if(requested < te.storedEnergy || requested == te.storedEnergy){
								itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("STORAGE"));
								te.storedEnergy -= requested;
							}
							else{
								itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("ENERGY") + te.storedEnergy);
								te.storedEnergy = 0;
							}
						}
						else if(entity instanceof TileEntityIonCreative) itemstack.getTagCompound().setInteger("ENERGY" , itemstack.getTagCompound().getInteger("STORAGE"));
					}
					if(entity instanceof TileEntityStellarEnricher){
						TileEntityStellarEnricher te = (TileEntityStellarEnricher) entity;
						int required = te.getValues(itemstack);
						te.displayStar();
						itemstack.stackSize--;
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Star consumed; requires: " + required + " SE"));
						}
					}
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 60)) || itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1,61)) || itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 62)) || itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 63))){
				if(entity instanceof TileEntityIonSmall){
					TileEntityIonSmall te = (TileEntityIonSmall) entity;
					if(itemstack.getTagCompound().getInteger("Energy")+1000 < itemstack.getTagCompound().getInteger("Capacity")){
						itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Energy")+1000);
						te.storedEnergy -= 1000;
					}
					else if(itemstack.getTagCompound().getInteger("Energy") < itemstack.getTagCompound().getInteger("Capacity")){
						int dif = itemstack.getTagCompound().getInteger("Capacity") - itemstack.getTagCompound().getInteger("Energy");
						itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Capacity"));
						te.storedEnergy -= dif;
					}
				}
				else if(entity instanceof TileEntityIonMedium){
					TileEntityIonMedium te = (TileEntityIonMedium) entity;
					if(itemstack.getTagCompound().getInteger("Energy")+1000 < itemstack.getTagCompound().getInteger("Capacity")){
						itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Energy")+1000);
						te.storedEnergy -= 1000;
					}
					else if(itemstack.getTagCompound().getInteger("Energy") < itemstack.getTagCompound().getInteger("Capacity")){
						int dif = itemstack.getTagCompound().getInteger("Capacity") - itemstack.getTagCompound().getInteger("Energy");
						itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Capacity"));
						te.storedEnergy -= dif;
					}
				}
				else if(entity instanceof TileEntityIonLarge){
					TileEntityIonLarge te = (TileEntityIonLarge) entity;
					if(itemstack.getTagCompound().getInteger("Energy")+1000 < itemstack.getTagCompound().getInteger("Capacity")){
						itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Energy")+1000);
						te.storedEnergy -= 1000;
					}
					else if(itemstack.getTagCompound().getInteger("Energy") < itemstack.getTagCompound().getInteger("Capacity")){
						int dif = itemstack.getTagCompound().getInteger("Capacity") - itemstack.getTagCompound().getInteger("Energy");
						itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Capacity"));
						te.storedEnergy -= dif;
					}
				}
				else if(entity instanceof TileEntityIonHuge){
					TileEntityIonHuge te = (TileEntityIonHuge) entity;
					if(itemstack.getTagCompound().getInteger("Energy")+1000 < itemstack.getTagCompound().getInteger("Capacity")){
						itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Energy")+1000);
						te.storedEnergy -= 1000;
					}
					else if(itemstack.getTagCompound().getInteger("Energy") < itemstack.getTagCompound().getInteger("Capacity")){
						int dif = itemstack.getTagCompound().getInteger("Capacity") - itemstack.getTagCompound().getInteger("Energy");
						itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Capacity"));
						te.storedEnergy -= dif;
					}
				}
				else if(entity instanceof TileEntityIonCreative){
					itemstack.getTagCompound().setInteger("Energy", itemstack.getTagCompound().getInteger("Capacity"));
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 48))){
				if(entity instanceof TileEntityPlanetaryAssembler){
					TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) entity;
					if(!te.isRunning){
						te.establishBase(itemstack, player);
					}
					else if(world.isRemote){
						player.addChatComponentMessage(new ChatComponentText("Already running!"));
					}
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 7))){
				if(entity instanceof TileEntityPlanetaryAssembler){
					TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) entity;
					if(world.isRemote){
						te.status(player);
					}
				}
				else if(entity instanceof TileEntityWirelessController){
					TileEntityWirelessController te = (TileEntityWirelessController) entity;
					world.spawnParticle("flame", x+te.x+0, y+te.y, z+te.z+0, 0.0D, 0.0D, 0.0D);
					world.spawnParticle("flame", x+te.x+0, y+te.y, z+te.z+0.5, 0.0D, 0.0D, 0.0D);
					world.spawnParticle("flame", x+te.x+0.5, y+te.y, z+te.z, 0.0D, 0.0D, 0.0D);
					world.spawnParticle("flame", x+te.x+0.5, y+te.y, z+te.z+0.5, 0.0D, 0.0D, 0.0D);
					if(world.isRemote){
						player.addChatComponentMessage(new ChatComponentText(te.x+x + " " + te.y+y + " " + te.z+z));
					}
				}
				else if(entity instanceof TileEntityDuplication){
					TileEntityDuplication te = (TileEntityDuplication) entity;
					if(te.blackholium == 5){
						if(te.energyStored == te.energyCapacity){
							if(world.isRemote){
								player.addChatComponentMessage(new ChatComponentText("Ready to duplicate!"));
							}
						}
					}
					if(world.isRemote){
						player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Requires " + (5-te.blackholium) + " energised blackholium ingots."));
						player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Energy: " + te.energyStored + "/" + te.energyCapacity + "SE"));
					}
				}
				else if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) | Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
					ItemStack energyCell = new ItemStack(ModBlocks.ionStorage, 1, 0);
					if(entity instanceof TileEntityIonSmall){
						TileEntityIonSmall te = (TileEntityIonSmall) entity;
						energyCell = new ItemStack(ModBlocks.ionStorage, 1, 0);
						energyCell.setTagCompound(new NBTTagCompound());
						energyCell.getTagCompound().setInteger("Stored", te.storedEnergy);
					}
					else if(entity instanceof TileEntityIonMedium){
						energyCell = new ItemStack(ModBlocks.ionStorage, 1, 1);
						TileEntityIonMedium te = (TileEntityIonMedium) entity;
						energyCell.setTagCompound(new NBTTagCompound());
						energyCell.getTagCompound().setInteger("Stored", te.storedEnergy);
					}
					else if(entity instanceof TileEntityIonLarge){
						energyCell = new ItemStack(ModBlocks.ionStorage, 1, 3);
						TileEntityIonLarge te = (TileEntityIonLarge) entity;
						energyCell.setTagCompound(new NBTTagCompound());
						energyCell.getTagCompound().setInteger("Stored", te.storedEnergy);
					}
					else if(entity instanceof TileEntityIonHuge){
						energyCell = new ItemStack(ModBlocks.ionStorage, 1, 4);
						TileEntityIonHuge te = (TileEntityIonHuge) entity;
						energyCell.setTagCompound(new NBTTagCompound());
						energyCell.getTagCompound().setInteger("Stored", te.storedEnergy);
					}
					else if(entity instanceof TileEntityIonCreative){
						energyCell = new ItemStack(ModBlocks.ionStorage, 1, 9);
						TileEntityIonCreative te = (TileEntityIonCreative) entity;
						energyCell.setTagCompound(new NBTTagCompound());
						energyCell.getTagCompound().setInteger("Stored", te.storedEnergy);
					}
					EntityItem cell = new EntityItem(world, x, y, z, energyCell);
					if(!world.isRemote){
						world.spawnEntityInWorld(cell);
					}
					world.setBlockToAir(x, y, z);
				}
				else{
					IonEnergyStored status = new IonEnergyStored();
					int energyCapacity = -2;
					int energyStored = -2;
					if(entity instanceof TileEntityIonSmall){
						TileEntityIonSmall te = (TileEntityIonSmall) entity;
						energyCapacity = te.energyCapacity;
						energyStored = te.storedEnergy;
					}
					else if(entity instanceof TileEntityIonMedium){
						TileEntityIonMedium te = (TileEntityIonMedium) entity;
						energyCapacity = te.energyCapacity;
						energyStored = te.storedEnergy;
					}
					else if(entity instanceof TileEntityIonLarge){
						TileEntityIonLarge te = (TileEntityIonLarge) entity;
						energyCapacity = te.energyCapacity;
						energyStored = te.storedEnergy;
					}
					else if(entity instanceof TileEntityIonHuge){
						TileEntityIonHuge te = (TileEntityIonHuge) entity;
						energyCapacity = te.energyCapacity;
						energyStored = te.storedEnergy;
					}
					else if(entity instanceof TileEntityIonCreative){
						TileEntityIonCreative te = (TileEntityIonCreative) entity;
						energyCapacity = te.energyCapacity;
						energyStored = te.storedEnergy;
					}
					else if(entity instanceof TileEntityWirelessController){
						TileEntityWirelessController te = (TileEntityWirelessController) entity;
						energyStored = te.energyStored;
						energyCapacity = te.energyCapacity;
					}
					else if(entity instanceof TileEntityDuplication){
						TileEntityDuplication te = (TileEntityDuplication) entity;
						energyStored = te.energyStored;
						energyCapacity = te.energyCapacity;
						if(world.isRemote) player.addChatComponentMessage(new ChatComponentText("Blackholium Ingots: " + te.blackholium + "/5"));
					}
					else if(entity instanceof TileEntityPlanetaryAssembler){
						TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) entity;
						energyStored = te.energyStored;
						energyCapacity = te.energyCapacity;
					}
					else if(entity instanceof TileEntityStellarEnricher){
						TileEntityStellarEnricher te = (TileEntityStellarEnricher) entity;
						energyStored = te.energyStored;
						energyCapacity = te.energyCapacity;
					}
					status.showStatus(player, world, energyStored, energyCapacity);
				}
			}
			else if(entity instanceof TileEntityWirelessController){
				TileEntityWirelessController te = (TileEntityWirelessController) entity;
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 39))){
					if(te.module[0] == 0){
						te.module[0] = 1;
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Speed module successfully installed!"));
						}
						player.getCurrentEquippedItem().stackSize--;
					}
					else{
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Speed module failed to install; already installed!"));
						}
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 47))){
					if(te.module[2] == 0 && te.module[0] == 1){
						te.module[2] = 1;
						player.getCurrentEquippedItem().stackSize--;
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Advanced speed module successfully installed!"));
						}
					}
					else{
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Advanced speed module failed to install; either already installed or previous upgrade is not installed!"));
						}
					}
				}
				else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 40))){
					if(te.module[1] == 0){
						te.module[1]++;
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Range module successfully installed!"));
						}
						player.getCurrentEquippedItem().stackSize--;
					}
					else{
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Range module failed to install; already installed!"));
						}
					}
				}
			}
			else if(entity instanceof TileEntityDuplication){
				TileEntityDuplication te = (TileEntityDuplication) entity;
				if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 45))){
					if(te.blackholium == 5){
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Already has enough energised blackholium ingots!"));
						}
					}
					else{
						te.blackholium++;
						itemstack.stackSize--;
					}
				}
				else{
					ItemStack equipped = itemstack.copy();
					te.start(equipped);
				}
			}
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		icons = new IIcon[10];
		for (int i = 0; i < icons.length; i++){
			icons[i] = par1IconRegister.registerIcon(UniverseCraft.MODID + ":" + "Ion" + i);
		}
	}

	@Override
	public int damageDropped(int meta){
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2){
		return icons[par2];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List){
		for (int var4 = 0; var4 < 10; ++var4){
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack itemstack) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if(entity instanceof TileEntityIonSmall){
			TileEntityIonSmall te = (TileEntityIonSmall) entity;
			if(itemstack != null && itemstack.getTagCompound() != null){
				if(itemstack.getTagCompound().hasKey("Stored")){
					te.storedEnergy = itemstack.getTagCompound().getInteger("Stored");
				}
			}
		}
		else if(entity instanceof TileEntityIonMedium){
			TileEntityIonMedium te = (TileEntityIonMedium) entity;
			if(itemstack != null && itemstack.getTagCompound() != null){
				if(itemstack.getTagCompound().hasKey("Stored")){
					te.storedEnergy = itemstack.getTagCompound().getInteger("Stored");
				}
			}
		}
		else if(entity instanceof TileEntityIonLarge){
			TileEntityIonLarge te = (TileEntityIonLarge) entity;
			if(itemstack != null && itemstack.getTagCompound() != null){
				if(itemstack.getTagCompound().hasKey("Stored")){
					te.storedEnergy = itemstack.getTagCompound().getInteger("Stored");
				}
			}
		}
		else if(entity instanceof TileEntityIonHuge){
			TileEntityIonHuge te = (TileEntityIonHuge) entity;
			if(itemstack != null && itemstack.getTagCompound() != null){
				if(itemstack.getTagCompound().hasKey("Stored")){
					te.storedEnergy = itemstack.getTagCompound().getInteger("Stored");
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		switch(metadata){
		case 0:
			return new TileEntityIonSmall();
		case 1:
			return new TileEntityIonMedium();
		case 2:
			return new TileEntityPlanetaryAssembler();
		case 3:
			return new TileEntityIonLarge();
		case 4:
			return new TileEntityIonHuge();
		case 5:
			return new TileEntityWirelessController();
		case 6:
			return new TileEntityDuplication();
		case 7:
			return new TileEntityEnergyAcceptor();
		case 8:
			return new TileEntityStellarEnricher();
		case 9:
			return new TileEntityIonCreative();
		}
		return null;
	}
}