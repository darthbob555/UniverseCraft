package com.universeCraft.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.universeCraft.main.UniverseCraft;
import com.universeCraft.tileEntity.TileEntityAtomicPulveriser;
import com.universeCraft.tileEntity.TileEntityEnergyAcceptor;
import com.universeCraft.tileEntity.TileEntityIonHuge;
import com.universeCraft.tileEntity.TileEntityIonLarge;
import com.universeCraft.tileEntity.TileEntityIonMedium;
import com.universeCraft.tileEntity.TileEntityIonSmall;
import com.universeCraft.tileEntity.TileEntityParticleAccelerator;
import com.universeCraft.tileEntity.TileEntityPlanetaryAssembler;
import com.universeCraft.tileEntity.TileEntityStellarEnricher;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MetadataItems extends Item {

	private int modeFluid = 0;
	private int modeSolid = 0;
	public IIcon[] icons = new IIcon[71];
	private IIcon[] icons2 = new IIcon[5];
	private String[] name = new String[7];

	public MetadataItems(String unlocalizedName) {
		super();
		this.setHasSubtypes(true);
		this.setUnlocalizedName(unlocalizedName);
		setCreativeTab(UniverseCraft.universeCraft);
		name[0] = "Dirt";
		name[1] = "Endstone";
		name[2] = "Netherrack";
		name[3] = "Obsidian";
		name[4] = "Stone";
		name[5] = "Diamond Block";
		name[6] = "Emerald Block";
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		if (meta > 70)
			meta = 0;
		return this.icons[meta];
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 71; i ++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		for (int i = 0; i < 71; i ++) {
			if(i == 37){
				this.icons2[0] = reg.registerIcon(UniverseCraft.MODID + ":" + "Water");
				this.icons2[1] = reg.registerIcon(UniverseCraft.MODID + ":" + "Lava");
			}
			else if(i == 38){
				this.icons2[2] = reg.registerIcon(UniverseCraft.MODID + ":" + "Cobblestone");
				this.icons2[3] = reg.registerIcon(UniverseCraft.MODID + ":" + "Stone");
				this.icons2[4] = reg.registerIcon(UniverseCraft.MODID + ":" + "Obsidian");
			}
			else{
				this.icons[i] = reg.registerIcon(UniverseCraft.MODID + ":Particle" + i);
			}
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10){
		Random rand = new Random();
		if(itemstack.getTagCompound() != null){
			if(itemstack.getTagCompound().hasKey("Resources")){
				if(itemstack.getTagCompound().getInteger("Resources") > 0){
					if(itemstack.getItemDamage() == 34){
						if(world.getBlock(x, y+1, z) == Blocks.air){
							world.setBlock(x, y+1, z, Blocks.torch);
							itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")-1);
							return true;
						}
						else{
							return false;
						}
					}
					else if(itemstack.getItemDamage() == 35){
						if(world.getBlock(x, y+1, z) == Blocks.air){
							world.setBlock(x, y+1, z, Blocks.glowstone);
							itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")-1);
							return true;
						}
						else{
							return false;
						}
					}
					else if(itemstack.getItemDamage() == 36){
						if (!world.isRemote) {
							player.addExperience(370+rand.nextInt(505));
							itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")-1);
						}
						return true;
					}
					else if(itemstack.getItemDamage() == 37){
						if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
							if(!world.isRemote){
								if(modeFluid == 0){
									modeFluid = 1;
								}
								else{
									modeFluid = 0;
								}
							}
							return true;
						}
						else if(world.getBlock(x, y+1, z) == Blocks.air){
							if(modeFluid == 0){
								world.setBlock(x, y+1, z, Blocks.water);
							}
							else{
								world.setBlock(x, y+1, z, Blocks.lava);
							}
							itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")-1);
							return true;
						}
						else{
							return false;
						}
					}
					else if(itemstack.getItemDamage() == 38){
						if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
							if(!world.isRemote){
								if(modeSolid == 0){
									modeSolid = 1;
								}
								else if(modeSolid == 1){
									modeSolid = 2;
								}
								else{
									modeSolid = 0;
								}
							}
							return true;
						}
						else if(world.getBlock(x, y+1, z) == Blocks.air){
							if(modeSolid == 0){
								world.setBlock(x, y+1, z, Blocks.cobblestone);
								itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")-1);
							}
							else if(modeSolid == 1){
								world.setBlock(x, y+1, z, Blocks.stone);
								itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")-2);
							}
							else{
								world.setBlock(x, y+1, z, Blocks.obsidian);
								itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")-5);
							}
							return true;
						}
						else{
							return false;
						}
					}
					else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 49))){
						if(itemstack.getTagCompound().hasKey("Time")){
							if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
								if(!world.isRemote){
									if(itemstack.getTagCompound().getInteger("Time") == 0){
										itemstack.getTagCompound().setInteger("Time", 1);
									}
									else if(itemstack.getTagCompound().getInteger("Time") == 1){
										itemstack.getTagCompound().setInteger("Time", 0);
									}
								}
							}
							else if(!world.isRemote){
								if(itemstack.getTagCompound().getInteger("Time") == 0){
									world.setWorldTime(0);
								}
								else if(itemstack.getTagCompound().getInteger("Time") == 1){
									world.setWorldTime(12000);
								}
								itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")-1);
							}
							return true;
						}
						else{
							return false;
						}
					}
					else{
						return false;
					}
				}
				else{
					player.addChatComponentMessage(new ChatComponentText("Out of resources."));
					return false;
				}
			}
			else if(itemstack.isItemEqual(new ItemStack(ModItems.Particles, 1, 48))){
				if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
					if(itemstack.getTagCompound().hasKey("Diameter")){
						int diameter = itemstack.getTagCompound().getInteger("Diameter");
						if(diameter != 128){
							diameter += 8;
						}
						else{
							diameter = 8;
						}
						itemstack.getTagCompound().setInteger("Diameter", diameter);
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Diameter set to: " + diameter));
						}
					}
					return true;
				}
				else if(Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
					if(itemstack.getTagCompound().hasKey("Material")){
						int material = itemstack.getTagCompound().getInteger("Material");
						if(material != 6){
							material++;
						}
						else{
							material = 0;
						}
						itemstack.getTagCompound().setInteger("Material", material);
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Material set to: " + name[material]));
						}
					}
					return true;
				}
				else if(Keyboard.isKeyDown(Keyboard.KEY_LMENU) || Keyboard.isKeyDown(Keyboard.KEY_RMENU)){
					if(itemstack.getTagCompound().hasKey("Rings")){
						boolean rings = itemstack.getTagCompound().getBoolean("Rings");
						if(rings){
							rings = false;
						}
						else{
							rings = true;
						}
						itemstack.getTagCompound().setBoolean("Rings", rings);
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Rings set to: " + rings));
						}
					}
					return true;
				}
				return false;
			}
			else{
				return false;
			}
		}
		else if(itemstack.getItemDamage() == 7){
			if(world.getTileEntity(x, y, z) != null){
				if(world.getTileEntity(x, y, z) instanceof TileEntityParticleAccelerator){
					TileEntityParticleAccelerator te = (TileEntityParticleAccelerator) world.getTileEntity(x, y, z);
					if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
						if(te.status == "multiblock"){
							te.updateStatus(world, x, y, z, player);
							te.antimatter = 0;
						}
					}
				}
			}
			return true;
		}
		if(itemstack.getItemDamage() == 69){
			if((Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))){
				TileEntity entity = world.getTileEntity(x, y, z);
				int[] coords = new int[3];
				If:
					if(entity != null){
						if(entity instanceof TileEntityAtomicPulveriser){
							TileEntityAtomicPulveriser te = (TileEntityAtomicPulveriser) entity;
							coords[0] = te.xCoord;
							coords[1] = te.yCoord;
							coords[2] = te.zCoord;
						}
						else if(entity instanceof TileEntityIonSmall){
							TileEntityIonSmall te = (TileEntityIonSmall) entity;
							coords[0] = te.xCoord;
							coords[1] = te.yCoord;
							coords[2] = te.zCoord;
						}
						else if(entity instanceof TileEntityIonMedium){
							TileEntityIonMedium te = (TileEntityIonMedium) entity;
							coords[0] = te.xCoord;
							coords[1] = te.yCoord;
							coords[2] = te.zCoord;
						}
						else if(entity instanceof TileEntityIonLarge){
							TileEntityIonLarge te = (TileEntityIonLarge) entity;
							coords[0] = te.xCoord;
							coords[1] = te.yCoord;
							coords[2] = te.zCoord;
						}
						else if(entity instanceof TileEntityIonHuge){
							TileEntityIonHuge te = (TileEntityIonHuge) entity;
							coords[0] = te.xCoord;
							coords[1] = te.yCoord;
							coords[2] = te.zCoord;
						}
						else if(entity instanceof TileEntityStellarEnricher){
							TileEntityStellarEnricher te = (TileEntityStellarEnricher) entity;
							coords[0] = te.xCoord;
							coords[1] = te.yCoord;
							coords[2] = te.zCoord;
						}
						else if(entity instanceof TileEntityPlanetaryAssembler){
							TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) entity;
							coords[0] = te.xCoord;
							coords[1] = te.yCoord;
							coords[2] = te.zCoord;
						}
						else if(entity instanceof TileEntityEnergyAcceptor){
							TileEntityEnergyAcceptor te = (TileEntityEnergyAcceptor) entity;
							coords[0] = te.xCoord;
							coords[1] = te.yCoord;
							coords[2] = te.zCoord;
						}
						else{
							break If;
						}
						itemstack.setTagCompound(new NBTTagCompound());
						itemstack.getTagCompound().setIntArray("Coords", coords);
						if(world.isRemote){
							player.addChatComponentMessage(new ChatComponentText("Coordinates added"));
						}
						return true;
					}
			}
			return false;
		}
		else{
			return false;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack itemstack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining){
		int i = itemstack.getItemDamage();
		if(i == 37){
			if(this.modeFluid == 0){
				return this.icons2[0];
			}
			else if(this.modeFluid == 1){
				return this.icons2[1];
			}
			else{
				return this.icons[i];
			}
		}
		else if(i == 38){
			if(this.modeSolid == 0){
				return this.icons2[2];
			}
			else if(this.modeSolid == 1){
				return this.icons2[3];
			}
			else if(this.modeSolid == 2){
				return this.icons2[4];
			}
			else{
				return this.icons[i];
			}
		}
		else{
			return icons[i];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		if(i == 37){
			if(this.modeFluid == 0){
				return this.icons2[0];
			}
			else if(this.modeFluid == 1){
				return this.icons2[1];
			}
			else{
				return this.icons[i];
			}
		}
		else if(i==38){
			if(this.modeSolid == 0){
				return this.icons2[2];
			}
			else if(this.modeSolid == 1){
				return this.icons2[3];
			}
			else if(this.modeSolid == 2){
				return this.icons2[4];
			}
			else{
				return this.icons[i];
			}
		}
		else{
			return icons[i];
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemstack){
		switch(itemstack.getItemDamage()){
		case 45:
			return true;
		}
		return false;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName() + "_" + stack.getItemDamage();
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
		if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Recharge")){
			if(!world.isRemote){
				itemstack.getTagCompound().setInteger("Resources", itemstack.getTagCompound().getInteger("Resources")+1);
			}
			itemstack.getTagCompound().removeTag("Recharge");
		}
		else if(itemstack.getItemDamage() == 34){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Resources", 512);
			}
		}
		else if(itemstack.getItemDamage() == 35){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Resources", 192);
			}
		}
		else if(itemstack.getItemDamage() == 36){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Resources", 5);
			}
		}
		else if(itemstack.getItemDamage() == 37){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Resources", 64);
			}
		}
		else if(itemstack.getItemDamage() == 38){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Resources", 512);
			}
		}
		else if(itemstack.getItemDamage() == 48){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Diameter", 16);
				itemstack.getTagCompound().setInteger("Material", 0);
				itemstack.getTagCompound().setBoolean("Rings", false);
			}
		}
		else if(itemstack.getItemDamage() == 49){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Time", 0);
				itemstack.getTagCompound().setInteger("Resources", 4);
			}
		}
		else if(itemstack.getItemDamage() == 60){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Energy", 0);
				itemstack.getTagCompound().setInteger("Capacity", 20000);
			}
		}
		else if(itemstack.getItemDamage() == 61){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Energy", 0);
				itemstack.getTagCompound().setInteger("Capacity", 125000);
			}
		}
		else if(itemstack.getItemDamage() == 62){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Energy", 0);
				itemstack.getTagCompound().setInteger("Capacity", 8250000);
			}
		}
		else if(itemstack.getItemDamage() == 63){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Energy", 0);
				itemstack.getTagCompound().setInteger("Capacity", 75000000);
			}
		}
		else if(itemstack.getItemDamage() == 70){
			if(itemstack.getTagCompound() == null){
				itemstack.setTagCompound(new NBTTagCompound());
				itemstack.getTagCompound().setInteger("Energy", 0);
				itemstack.getTagCompound().setInteger("Capacity", 1000000);
			}
		}
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List dataList, boolean bool){
		int meta = itemstack.getItemDamage();
		if(meta == 7){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A wrench to check the status of blocks]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "The wrench is used by right clicking on blocks to");
				dataList.add(EnumChatFormatting.AQUA + "see their status.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 8){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[The polar opposite of matter]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Negatively charged and the opposite of normal matter,");
				dataList.add(EnumChatFormatting.AQUA + "antimatter is dangerously reactive. However, now that");
				dataList.add(EnumChatFormatting.AQUA + "it is contained, this matter could be used in ways");
				dataList.add(EnumChatFormatting.AQUA + "which normal matter could not.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 9 || meta == 10 || meta == 11 || meta == 12 || meta == 13 || meta == 14 || meta == 15 || meta == 16 || meta == 17 || meta == 18 || meta == 19){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Pure atoms of the parent material]");
				if(itemstack.getTagCompound() != null){
					if(itemstack.getTagCompound().hasKey("GramUnit")){
						dataList.add("");
						dataList.add(EnumChatFormatting.AQUA + "Grams/Unit: " + itemstack.getTagCompound().getInteger("GramUnit"));
					}
					if(itemstack.getTagCompound().hasKey("SingleUnit") && itemstack.getTagCompound().hasKey("Unit")){
						dataList.add(EnumChatFormatting.GREEN + "Particles/Unit: " + itemstack.getTagCompound().getDouble("SingleUnit") + itemstack.getTagCompound().getString("Unit"));
					}
					if(itemstack.getTagCompound().hasKey("Unit") && itemstack.getTagCompound().hasKey("Atoms")){
						dataList.add(EnumChatFormatting.GREEN + "Particles Stored: " + itemstack.getTagCompound().getDouble("Atoms") + itemstack.getTagCompound().getString("Unit"));
					}
				}
				else{
					dataList.add(EnumChatFormatting.ITALIC +  "No Data Set");
				}
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 20){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A key component in dynamic components]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This mechanism is used to make movable machines.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 21){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A key component in motors]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This is used to make motors transfer electrical");
				dataList.add(EnumChatFormatting.AQUA + "current.");

			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 22){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A key component in electrical generators]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This is used to make generators transfer electrical");
				dataList.add(EnumChatFormatting.AQUA + "current.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 23){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A basic component for quantum machines]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Magnifies particles to allow micro alterations.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 24){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A compact crystal with all materials combined]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This crystal has many amterials inside of it, making");
				dataList.add(EnumChatFormatting.AQUA + "it very versatile.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 25){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Prevents particle accelerator running constantly]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This module will not allow the acceleration of new");
				dataList.add(EnumChatFormatting.AQUA + "blocks/items if the energy stored is not enough.");
				dataList.add(EnumChatFormatting.AQUA + "Right click to apply to a particle accelerator.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 26){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[The unknown matter of the universe]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Little is known about this type of matter, but once");
				dataList.add(EnumChatFormatting.AQUA + "again, experimenting with this substance may lead to");
				dataList.add(EnumChatFormatting.AQUA + "new discoveries, and new discoveries lead to power.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 28){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Refined unknown matter of the universe]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Little is known about this type of matter, but refining");
				dataList.add(EnumChatFormatting.AQUA + "dark matter has lead to an even more powerful substance.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(itemstack.getItemDamage() == 29){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Radiation core]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Radiates heat at exceptional rates. This will be needed");
				dataList.add(EnumChatFormatting.AQUA + "to sustain blackholes from collasping in on themselves");
				if(itemstack.getTagCompound() != null){
					if(itemstack.getTagCompound().hasKey("Power")){
						dataList.add("");
						dataList.add(EnumChatFormatting.GOLD + "Power: " + itemstack.getTagCompound().getInteger("Power"));
					}
				}
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 31){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A module to increase the energy output]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This module can only be applied to a stellar generator.");
				dataList.add(EnumChatFormatting.AQUA + "Once installed, this module will speed up the generator");
				dataList.add(EnumChatFormatting.AQUA + "by 5x. No power is gained in the long run as the burn time");
				dataList.add(EnumChatFormatting.AQUA + "is reduced by 5x. This module can only be installed if the");
				dataList.add(EnumChatFormatting.AQUA + "generator is not currently generating.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 32){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[An advanced component for quantum machines]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Allows for much larger alternations in the");
				dataList.add(EnumChatFormatting.AQUA + "quantum world");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 33){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A complex component for quantum machines]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This component is much more capable and more");
				dataList.add(EnumChatFormatting.AQUA + "powerful. It may also interact with particles");
				dataList.add(EnumChatFormatting.AQUA + "in the air...");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 34){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A controlled blackhole that emits light]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "The quasar will place torches on a right click.");
				dataList.add(EnumChatFormatting.AQUA + "Torches are placed one block above the clicked block");
				dataList.add(EnumChatFormatting.AQUA + "The quasar can be repaired by using dark matter.");
				dataList.add(EnumChatFormatting.AQUA + "One dark matter fills the entire buffer. Torches");
				dataList.add(EnumChatFormatting.AQUA + "are placed one block above the block clicked on.");
			}
			else{
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Resources")){
					dataList.add(EnumChatFormatting.GOLD + "Resources: " + itemstack.getTagCompound().getInteger("Resources"));
					dataList.add("");
				}
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 35){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A controlled blackhole that emits bright light]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "The blazar will place glowstone on a right click.");
				dataList.add(EnumChatFormatting.AQUA + "Glowstone is placed one block above the clicked block");
				dataList.add(EnumChatFormatting.AQUA + "The blazar can be repaired by using refined dark matter.");
				dataList.add(EnumChatFormatting.AQUA + "One refined dark matter fills the entire buffer. Glowstone");
				dataList.add(EnumChatFormatting.AQUA + "is placed one block above the block clicked on.");
			}
			else{
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Resources")){
					dataList.add(EnumChatFormatting.GOLD + "Resources: " + itemstack.getTagCompound().getInteger("Resources"));
					dataList.add("");
				}
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 36){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[An unstable star that radiates XP]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "The variable star XP will give the player 20-30 levels");
				dataList.add(EnumChatFormatting.AQUA + "worth of experience. This will have the standard");
				dataList.add(EnumChatFormatting.AQUA + "diminishing returns after experience level 0");
				dataList.add(EnumChatFormatting.AQUA + "1 refined dark matter fills the entire buffer.");
			}
			else{
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Resources")){
					dataList.add(EnumChatFormatting.GOLD + "Resources: " + itemstack.getTagCompound().getInteger("Resources"));
					dataList.add("");
				}
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 37){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[An unstable fluid star that radiates fluids]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "The variable star fluidic will place a liquid block");
				dataList.add(EnumChatFormatting.AQUA + "in the world. Shift right click to change fluid and");
				dataList.add(EnumChatFormatting.AQUA + "right click to place the fluid. 2 dark matter fills");
				dataList.add(EnumChatFormatting.AQUA + "the entire buffer. Fluid blocks are placed on block");
				dataList.add(EnumChatFormatting.AQUA + "above the block clicked on.");
			}
			else{
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Resources")){
					dataList.add(EnumChatFormatting.GOLD + "Resources: " + itemstack.getTagCompound().getInteger("Resources"));
					dataList.add("");
				}
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 38){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[An unstable star that radiates forms of stone]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "The variable star aquatically igneatic will place");
				dataList.add(EnumChatFormatting.AQUA + "either stone, cobblestone or obsidian blocks in world.");
				dataList.add(EnumChatFormatting.AQUA + "Cobblestone costs 1 resource, stone takes 2 resources and");
				dataList.add(EnumChatFormatting.AQUA + "obisidian takes 5 resources. Shift right click to change");
				dataList.add(EnumChatFormatting.AQUA + "mode 3 dark matter fills the entire buffer. Blocks are");
				dataList.add(EnumChatFormatting.AQUA + "one block above the block clicked on.");
			}
			else{
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Resources")){
					dataList.add(EnumChatFormatting.GOLD + "Resources: " + itemstack.getTagCompound().getInteger("Resources"));
					dataList.add("");
				}
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 39){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A module to increase the speed of machines]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This module can be applied to the wireless controller.");
				dataList.add(EnumChatFormatting.AQUA + "Once installed with a right click, the controller will");
				dataList.add(EnumChatFormatting.AQUA + "scan the area 5x faster than normal.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 40){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A module to increase the range of machines]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This module can be applied to the wireless controller.");
				dataList.add(EnumChatFormatting.AQUA + "Once installed with a right click, the controller will");
				dataList.add(EnumChatFormatting.AQUA + "scan the area in a 31 block diameter range.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 41){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A very, very dense fragment of a blackhole]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "A fragment of a once whole hyper-condensed blackhole. It");
				dataList.add(EnumChatFormatting.AQUA + "will be required in making the ingot form of this fragment");
				dataList.add(EnumChatFormatting.AQUA + "but it will require extra components to reform it.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 42){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A massive, dense point in space]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "A ridiciously heavy, yet infinesimally small point in space");
				dataList.add(EnumChatFormatting.AQUA + "that is inert...for now. As it is so dense it seems to be");
				dataList.add(EnumChatFormatting.AQUA + "hard to carry, and will require special armor not to kill you.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 43){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A massive, dense point in space]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "A ridiciously heavy, yet infinesimally small point in space");
				dataList.add(EnumChatFormatting.AQUA + "that contains masses of gravity. Right click a blank core on");
				dataList.add(EnumChatFormatting.AQUA + "a highly compressed diamond block to craft.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 45){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A massive, dense point in space full of energy]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "A ridiciously heavy, yet infinesimally small point in space");
				dataList.add(EnumChatFormatting.AQUA + "that has been energised with energy. It will kill anything that");
				dataList.add(EnumChatFormatting.AQUA + "possesses it but slowly enough to allow you handle it for a few seconds.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 46){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[An imbued yet unstable star]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Once crafted, it will contain ALL enchants possible including");
				dataList.add(EnumChatFormatting.AQUA + "MOD enchants.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 47){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A module to increase the speed of machines]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "This module can be applied to the wireless controller.");
				dataList.add(EnumChatFormatting.AQUA + "Once installed with a right click, the controller will");
				dataList.add(EnumChatFormatting.AQUA + "scan the area 20x faster than normal. Requires previous");
				dataList.add(EnumChatFormatting.AQUA + "speed module to work.");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 48){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A template of creating a planet]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "SHIFT + right click to change diameter - (16-128)");
				dataList.add(EnumChatFormatting.AQUA + "CONTROL + right click to change material - Dirt,");
				dataList.add(EnumChatFormatting.AQUA + "Endstone, Netherrack, Obsidian, Stone. Diamond, Emerald.");
				dataList.add(EnumChatFormatting.AQUA + "ALT + right click to add/remove rings - default false.");
				dataList.add(EnumChatFormatting.AQUA + "Right click on a planetary assembler to copy settings.");
				dataList.add("");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
				dataList.add("");
				if(itemstack.getTagCompound() != null){
					if(itemstack.getTagCompound().hasKey("Diameter")){
						dataList.add(EnumChatFormatting.GREEN + "Diameter: " + itemstack.getTagCompound().getInteger("Diameter"));
					}
					if(itemstack.getTagCompound().hasKey("Material")){
						dataList.add(EnumChatFormatting.GREEN + "Material: " + itemstack.getTagCompound().getInteger("Material"));
					}
					if(itemstack.getTagCompound().hasKey("Rings")){
						dataList.add(EnumChatFormatting.GREEN + "Rings: " + itemstack.getTagCompound().getBoolean("Rings"));
					}
				}
			}

		}
		else if(meta == 49){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A blackhole that distorts time]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Right click to change the time of day.");
				dataList.add(EnumChatFormatting.AQUA + "SHIFT + Right click will change the preset.");
				dataList.add(EnumChatFormatting.AQUA + "Requires 5 dark matter to refill.");
			}
			else{
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Resources") && itemstack.getTagCompound().hasKey("Time")){
					dataList.add(EnumChatFormatting.GOLD + "Resources: " + itemstack.getTagCompound().getInteger("Resources"));
					dataList.add(EnumChatFormatting.GOLD + "Time Preset: " + (itemstack.getTagCompound().getInteger("Time")*18) + ":00");
					dataList.add("");
				}
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
			}
		}
		else if(meta == 60){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A small battery to store SE]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Gives energy to armor and tools in the");
				dataList.add(EnumChatFormatting.AQUA + "player's inventory");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
					dataList.add(EnumChatFormatting.GOLD + "");
					dataList.add(EnumChatFormatting.RED + "ENERGY: " + itemstack.getTagCompound().getInteger("Energy") + "/20000");
				}
			}
		}
		else if(meta == 61){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A large battery to store SE]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Gives energy to armor and tools in the");
				dataList.add(EnumChatFormatting.AQUA + "player's inventory");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
					dataList.add(EnumChatFormatting.GOLD + "");
					dataList.add(EnumChatFormatting.RED + "ENERGY: " + itemstack.getTagCompound().getInteger("Energy") + "/125000");
				}
			}
		}
		else if(meta == 62){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A big battery to store SE]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Gives energy to armor and tools in the");
				dataList.add(EnumChatFormatting.AQUA + "player's inventory");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
					dataList.add(EnumChatFormatting.GOLD + "");
					dataList.add(EnumChatFormatting.RED + "ENERGY: " + itemstack.getTagCompound().getInteger("Energy") + "/8250000");
				}
			}
		}
		else if(meta == 63){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[A huge battery to store SE]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Gives energy to armor and tools in the");
				dataList.add(EnumChatFormatting.AQUA + "player's inventory");
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
					dataList.add(EnumChatFormatting.GOLD + "");
					dataList.add(EnumChatFormatting.RED + "ENERGY: " + itemstack.getTagCompound().getInteger("Energy") + "/75000000");
				}
			}
		}
		else if(meta == 69){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Links nodes to blocks]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "Stors the coords of a block for");
				dataList.add(EnumChatFormatting.AQUA + "a node to transfer energy to.");
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Coords")){
					dataList.add("");
					dataList.add(EnumChatFormatting.RED + "Coords: " + itemstack.getTagCompound().getIntArray("Coords")[0] + " " + itemstack.getTagCompound().getIntArray("Coords")[1] + " " + itemstack.getTagCompound().getIntArray("Coords")[2]);
				}
			}
			else{
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");

			}
		}
		else if(meta == 70){
			if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				dataList.add(EnumChatFormatting.DARK_AQUA + "[Protects the player]");
				dataList.add("");
				dataList.add(EnumChatFormatting.AQUA + "If the player falls out of the world, at a cost");
				dataList.add(EnumChatFormatting.AQUA + "of 250K SE, the player will be teleported upwards");
				dataList.add(EnumChatFormatting.AQUA + "to Y180 and given mandatory flight for 10 seconds.");
				dataList.add(EnumChatFormatting.AQUA + "If the player has no regenration potion active, and");
				dataList.add(EnumChatFormatting.AQUA + "is below half health, regeneration 3 will be given.");
			}
			else{
				if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
					dataList.add("");
					dataList.add(EnumChatFormatting.RED + "Energy: " + itemstack.getTagCompound().getInteger("Energy") + "/" + itemstack.getTagCompound().getInteger("Capacity") + "SE");
				}
				dataList.add(EnumChatFormatting.GOLD + "<Hold SHIFT For Details>");

			}
		}
	}
}
