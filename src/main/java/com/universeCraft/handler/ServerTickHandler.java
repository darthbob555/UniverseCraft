package com.universeCraft.handler;

import java.util.Random;

import net.minecraft.event.ClickEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ServerTickHandler{

	private boolean shouldDeactivate = true;

	@SubscribeEvent
	public void fireBlocks(PlayerTickEvent event){
		if(event.player.inventory.hasItemStack(new ItemStack(ModBlocks.metadataBlocks, 1, 7)) && !event.player.capabilities.isCreativeMode){
			event.player.setFire(1);
		}
		if(event.player.inventory.hasItemStack(new ItemStack(ModBlocks.metadataBlocks2, 1, 4)) && !event.player.capabilities.isCreativeMode){
			event.player.setFire(1);
		}
	}

	@SubscribeEvent
	public void darkMatterArmor(PlayerTickEvent event){
		if(event.player.inventory.armorItemInSlot(3) != null && event.player.inventory.armorItemInSlot(2) != null && event.player.inventory.armorItemInSlot(1) != null && event.player.inventory.armorItemInSlot(0) != null){
			ItemStack helmet = event.player.inventory.armorItemInSlot(3);
			ItemStack chestplate = event.player.inventory.armorItemInSlot(2);
			ItemStack leggings = event.player.inventory.armorItemInSlot(1);
			ItemStack boots = event.player.inventory.armorItemInSlot(0);
			if(helmet.getItem() == ModItems.darkMatterHelmet){
				if(helmet.getTagCompound().getString("Energy") != null && helmet.getTagCompound().getInteger("Energy") > 0){
					event.player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 1, 1));
					helmet.getTagCompound().setInteger("Energy", helmet.getTagCompound().getInteger("Energy")-20);
				}
			}
			if(leggings.getItem() == ModItems.darkMatterLeggings){
				if(leggings.getTagCompound().getString("Energy") != null && leggings.getTagCompound().getInteger("Energy") > 0){
					event.player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1, 200));
					leggings.getTagCompound().setInteger("Energy", leggings.getTagCompound().getInteger("Energy")-20);
				}
			}
			if(boots.getItem() == ModItems.darkMatterBoots){
				if(boots.getTagCompound().getString("Energy") != null && boots.getTagCompound().getInteger("Energy") > 0){
					if(event.player.motionY > 0){
						event.player.motionY += 0.03;
					}
					event.player.addPotionEffect(new PotionEffect(Potion.resistance.id, 1, 10));
					boots.getTagCompound().setInteger("Energy", boots.getTagCompound().getInteger("Energy")-20);
				}
			}
			if(helmet.getItem() == ModItems.darkMatterHelmet && chestplate.getItem() == ModItems.darkMatterChestplate && leggings.getItem() == ModItems.darkMatterLeggings && boots.getItem() == ModItems.darkMatterBoots){
				ItemStack Star = event.player.getCurrentEquippedItem();
				Random rand = new Random();
				int limit = 0;
				if((boots.getTagCompound().getString("Energy") != null && boots.getTagCompound().getInteger("Energy") > 0) && (leggings.getTagCompound().getString("Energy") != null && leggings.getTagCompound().getInteger("Energy") > 0) && (chestplate.getTagCompound().getString("Energy") != null && chestplate.getTagCompound().getInteger("Energy") > 0) && (helmet.getTagCompound().getString("Energy") != null && helmet.getTagCompound().getInteger("Energy") > 0)){
					if(Star != null && Star.getItem() == ModItems.Particles){
						if(Star.getTagCompound() != null && Star.getTagCompound().hasKey("Resources")){
							if(Star.isItemEqual(new ItemStack(ModItems.Particles, 1, 34))) limit = 512;
							if(Star.isItemEqual(new ItemStack(ModItems.Particles, 1, 37))) limit = 64;
							if(Star.isItemEqual(new ItemStack(ModItems.Particles, 1, 38))) limit = 512;
							if(Star.isItemEqual(new ItemStack(ModItems.Particles, 1, 49))) limit = 4;
							if(rand.nextInt(400) == 0 && Star.getTagCompound().getInteger("Resources") < limit){
								Star.getTagCompound().setBoolean("Recharge", true);
							}
						}
					}
					chestplate.getTagCompound().setInteger("Energy", chestplate.getTagCompound().getInteger("Energy")-20);
				}
				else{
					Star.getTagCompound().setBoolean("Recharge", true);
				}
			}
		}
	}

	@SubscribeEvent
	public void blackholiumIngots(PlayerTickEvent event){
		if(event.player.inventory.hasItemStack(new ItemStack(ModItems.Particles, 1, 42)) && event.player.getHealth() > 0 && !event.player.capabilities.isCreativeMode){
			if(event.player.getCurrentArmor(2) != null){
				if(!event.player.inventory.armorItemInSlot(2).isItemEqual(new ItemStack(ModItems.darkMatterChestplate))){
					event.player.setHealth(event.player.getHealth()-0.1F);
				}
				else{
					event.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1, 5));
				}
			}
			else{
				event.player.setHealth(event.player.getHealth()-0.1F);
			}
		}
		else if(event.player.inventory.hasItemStack(new ItemStack(ModItems.Particles, 1, 45)) && event.player.getHealth() > 0 && !event.player.capabilities.isCreativeMode){
			if(event.player.getCurrentArmor(2) != null){
				if(event.player.getCurrentArmor(2).isItemEqual(new ItemStack(ModItems.blackholiumChestplate))){
					if(event.player.inventory.armorItemInSlot(2).isItemEqual(new ItemStack(ModItems.darkMatterChestplate))){
						if(event.player.getActivePotionEffect(Potion.regeneration) != null){
							event.player.setHealth(0);
							event.player.addChatComponentMessage(new ChatComponentText("Regeneration reacted vigourously with the energies in the blackholium ingot..."));
						}
						else{
							event.player.setHealth(event.player.getHealth()-0.1F);
							event.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1, 10));
						}
					}
					else{
						event.player.setHealth(event.player.getHealth()-0.2F);
					}
				}
			}
			else{
				event.player.setHealth(event.player.getHealth()-0.2F);
			}
		}
		else if(event.player.getHealth() == 0 && (event.player.inventory.hasItemStack(new ItemStack(ModItems.Particles, 1, 42)) || event.player.inventory.hasItemStack(new ItemStack(ModItems.Particles, 1, 45)))){
			event.player.addChatComponentMessage(new ChatComponentText(event.player.getDisplayName() + " was spaghettified by a collasping blackhole. Maybe coating yourself in dark matter can help..."));
		}
	}

	@SubscribeEvent
	public void blackholiumArmor(PlayerTickEvent event){
		ItemStack helmet = event.player.inventory.armorItemInSlot(3);
		ItemStack chestplate = event.player.inventory.armorItemInSlot(2);
		ItemStack leggings = event.player.inventory.armorItemInSlot(1);
		ItemStack boots = event.player.inventory.armorItemInSlot(0);
		if(helmet != null && helmet.isItemEqual(new ItemStack(ModItems.blackholiumHelmet))){
			if(helmet.getTagCompound().hasKey("Energy") && helmet.getTagCompound().getInteger("Energy") > 99){
				event.player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 20, 1));
				event.player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20, 20));
				helmet.getTagCompound().setInteger("Energy", helmet.getTagCompound().getInteger("Energy")-100);
				shouldDeactivate = true;
			}
		}
		else if(shouldDeactivate) this.deactivate(event);
		if(chestplate != null && chestplate.isItemEqual(new ItemStack(ModItems.blackholiumChestplate))){
			if(chestplate.getTagCompound().hasKey("Energy") && chestplate.getTagCompound().getInteger("Energy") > 99){
				event.player.capabilities.allowFlying = true;
				event.player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20, 20));
				event.player.addPotionEffect(new PotionEffect(Potion.resistance.id,20, 20));
				chestplate.getTagCompound().setInteger("Energy", chestplate.getTagCompound().getInteger("Energy")-100);
				shouldDeactivate = true;
			}
			else{
				event.player.capabilities.allowFlying = false;
			}
		}
		else if(shouldDeactivate) this.deactivate(event);
		if(leggings != null && leggings.isItemEqual(new ItemStack(ModItems.blackholiumLeggings))){
			if(leggings.getTagCompound().hasKey("Energy") && leggings.getTagCompound().getInteger("Energy") > 99){
				event.player.capabilities.setFlySpeed(0.5F);
				event.player.capabilities.setPlayerWalkSpeed(1F);
				leggings.getTagCompound().setInteger("Energy", leggings.getTagCompound().getInteger("Energy")-100);
				shouldDeactivate = true;
			}
			else{
				event.player.capabilities.setFlySpeed(0.05F);
				event.player.capabilities.setPlayerWalkSpeed(0.1F);
			}
		}
		else if(shouldDeactivate) this.deactivate(event);
		if(boots != null && boots.isItemEqual(new ItemStack(ModItems.blackholiumBoots))){
			if(boots.getTagCompound().hasKey("Energy") && boots.getTagCompound().getInteger("Energy") > 99){
				event.player.stepHeight = 1;
				event.player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 20));
				boots.getTagCompound().setInteger("Energy", boots.getTagCompound().getInteger("Energy")-100);
				shouldDeactivate = true;
			}
			else{
				event.player.stepHeight = 0.1F;
			}
		}
		else if(shouldDeactivate) this.deactivate(event);
	}

	private void deactivate(PlayerTickEvent event) {
		event.player.capabilities.allowFlying = false;
		event.player.stepHeight = 0.1F;
		event.player.capabilities.setFlySpeed(0.05F);
		event.player.capabilities.setPlayerWalkSpeed(0.1F);
		event.player.clearActivePotions();
		shouldDeactivate = false;
	}

	@SubscribeEvent
	public void chargeItems(PlayerTickEvent event){
		ItemStack helmet = event.player.inventory.armorItemInSlot(3);
		ItemStack chestplate = event.player.inventory.armorItemInSlot(2);
		ItemStack leggings = event.player.inventory.armorItemInSlot(1);
		ItemStack boots = event.player.inventory.armorItemInSlot(0);
		for(int i = 0; i<27; i++){
			ItemStack slot = event.player.inventory.getStackInSlot(i);
			if(slot != null && (slot.isItemEqual(new ItemStack(ModItems.Particles, 1, 60)) || slot.isItemEqual(new ItemStack(ModItems.Particles, 1, 61)) || slot.isItemEqual(new ItemStack(ModItems.Particles, 1, 62)) || slot.isItemEqual(new ItemStack(ModItems.Particles, 1, 63)))){
				if(slot.getTagCompound() != null && slot.getTagCompound().hasKey("Energy")){
					if(slot.getTagCompound().getInteger("Energy") > 1000){
						for(int j = 0; j<27; j++){
							ItemStack slot2 = event.player.inventory.getStackInSlot(j);
							if(slot2 != null && slot2.isItemEqual(new ItemStack(ModItems.Particles, 1, 70))){
								if(slot2.getTagCompound() != null && slot2.getTagCompound().hasKey("Energy")){
									if(slot2.getTagCompound().getInteger("Energy") < slot2.getTagCompound().getInteger("Capacity")){
										if(slot2.getTagCompound().getInteger("Energy") + 1000 < slot2.getTagCompound().getInteger("Capacity")){
											slot2.getTagCompound().setInteger("Energy", slot2.getTagCompound().getInteger("Energy")+1000);
											slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
										}
										else{
											int diff = slot2.getTagCompound().getInteger("Capacity")-slot2.getTagCompound().getInteger("Energy");
											slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
											slot2.getTagCompound().setInteger("Energy", slot2.getTagCompound().getInteger("Capacity"));
										}
									}
								}
							}
						}
						if(boots != null && boots.isItemEqual(new ItemStack(ModItems.darkMatterBoots))){
							if(boots.getTagCompound() != null && boots.getTagCompound().hasKey("Energy")){
								if(boots.getTagCompound().getInteger("Energy")+1000 < 50000){
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
									boots.getTagCompound().setInteger("Energy", boots.getTagCompound().getInteger("Energy")+1000);
								}
								else{
									int diff = 50000-boots.getTagCompound().getInteger("Energy");
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
									boots.getTagCompound().setInteger("Energy", 50000);
								}
							}
						}
						else if(boots != null && boots.isItemEqual(new ItemStack(ModItems.blackholiumBoots))){
							if(boots.getTagCompound() != null && boots.getTagCompound().hasKey("Energy")){
								if(boots.getTagCompound().getInteger("Energy")+1000 < 5000000){
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
									boots.getTagCompound().setInteger("Energy", boots.getTagCompound().getInteger("Energy")+1000);
								}
								else{
									int diff = 5000000-boots.getTagCompound().getInteger("Energy");
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
									boots.getTagCompound().setInteger("Energy", 5000000);
								}
							}
						}
						if(leggings != null && leggings.isItemEqual(new ItemStack(ModItems.darkMatterLeggings))){
							if(leggings.getTagCompound() != null && leggings.getTagCompound().hasKey("Energy")){
								if(leggings.getTagCompound().getInteger("Energy")+1000 < 50000){
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
									leggings.getTagCompound().setInteger("Energy", leggings.getTagCompound().getInteger("Energy")+1000);
								}
								else{
									int diff = 50000-leggings.getTagCompound().getInteger("Energy");
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
									leggings.getTagCompound().setInteger("Energy", 50000);
								}
							}
						}
						else if(leggings != null && leggings.isItemEqual(new ItemStack(ModItems.blackholiumLeggings))){
							if(leggings.getTagCompound() != null && leggings.getTagCompound().hasKey("Energy")){
								if(leggings.getTagCompound().getInteger("Energy")+1000 < 5000000){
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
									leggings.getTagCompound().setInteger("Energy", leggings.getTagCompound().getInteger("Energy")+1000);
								}
								else{
									int diff = 5000000-leggings.getTagCompound().getInteger("Energy");
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
									leggings.getTagCompound().setInteger("Energy", 5000000);
								}
							}
						}
						if(chestplate != null && chestplate.isItemEqual(new ItemStack(ModItems.darkMatterChestplate))){
							if(chestplate.getTagCompound() != null && chestplate.getTagCompound().hasKey("Energy")){
								if(chestplate.getTagCompound().getInteger("Energy")+1000 < 50000){
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
									chestplate.getTagCompound().setInteger("Energy", chestplate.getTagCompound().getInteger("Energy")+1000);
								}
								else{
									int diff = 50000-chestplate.getTagCompound().getInteger("Energy");
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
									chestplate.getTagCompound().setInteger("Energy", 50000);
								}
							}
						}
						else if(chestplate != null && chestplate.isItemEqual(new ItemStack(ModItems.blackholiumChestplate))){
							if(chestplate.getTagCompound() != null && chestplate.getTagCompound().hasKey("Energy")){
								if(chestplate.getTagCompound().getInteger("Energy")+1000 < 5000000){
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
									chestplate.getTagCompound().setInteger("Energy", chestplate.getTagCompound().getInteger("Energy")+1000);
								}
								else{
									int diff = 5000000-chestplate.getTagCompound().getInteger("Energy");
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
									chestplate.getTagCompound().setInteger("Energy", 5000000);
								}
							}
						}
						if(helmet != null && helmet.isItemEqual(new ItemStack(ModItems.darkMatterHelmet))){
							if(helmet.getTagCompound() != null && helmet.getTagCompound().hasKey("Energy")){
								if(helmet.getTagCompound().getInteger("Energy")+1000 < 50000){
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
									helmet.getTagCompound().setInteger("Energy", helmet.getTagCompound().getInteger("Energy")+1000);
								}
								else{
									int diff = 50000-helmet.getTagCompound().getInteger("Energy");
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
									helmet.getTagCompound().setInteger("Energy", 50000);
								}
							}
						}
						else if(helmet != null && helmet.isItemEqual(new ItemStack(ModItems.blackholiumHelmet))){
							if(helmet.getTagCompound() != null && helmet.getTagCompound().hasKey("Energy")){
								if(helmet.getTagCompound().getInteger("Energy")+1000 < 5000000){
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-1000);
									helmet.getTagCompound().setInteger("Energy", helmet.getTagCompound().getInteger("Energy")+1000);
								}
								else{
									int diff = 5000000-boots.getTagCompound().getInteger("Energy");
									slot.getTagCompound().setInteger("Energy", slot.getTagCompound().getInteger("Energy")-diff);
									helmet.getTagCompound().setInteger("Energy", 5000000);
								}
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void healthStar(PlayerTickEvent event){
		int ticksLeft = 0;
		ItemStack itemstack = new ItemStack(ModItems.Particles, 1, 70);
		if(event.player.inventory.hasItemStack(itemstack)){
			if(itemstack.getTagCompound() != null && itemstack.getTagCompound().hasKey("Energy")){
				if(event.player.getPlayerCoordinates().posY < Config.protectionPosY){
					if(itemstack.getTagCompound().getInteger("Energy") >= 250000){
						event.player.setPosition(event.player.getPlayerCoordinates().posX, 256, event.player.getPlayerCoordinates().posZ);
						ticksLeft = 400;
						event.player.addChatComponentMessage(new ChatComponentText("Health Star: Saved Player - Teleported 180 blocks upwards and forced flight for 10 seconds"));
					}
				}
				if(event.player.getHealth() <= 5){
					if(event.player.getActivePotionEffect(Potion.regeneration) != null){
						if(itemstack.getTagCompound().getInteger("Energy") >= 50000){
							event.player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 3, 10));
							event.player.addChatComponentMessage(new ChatComponentText("Health Star: Saved Player - Given regeneration boost for 10 seconds"));
						}
					}
				}
			}
		}
		if(ticksLeft > 0){
			ticksLeft--;
			event.player.isAirBorne = true;
			event.player.capabilities.allowFlying = true;
			shouldDeactivate = true;
		}
		else if(shouldDeactivate){
			event.player.capabilities.allowFlying = false;
			shouldDeactivate = false;
		}
	}

	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(PlayerTickEvent event){
		if(!UniverseCraft.versionChecked && event.player.worldObj.isRemote && UniverseCraft.versionChecker.isLatestVersion()){
			ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.curse.com/mc-mods/minecraft/241579-universecraft");
			ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
			ChatComponentText versionWarningChatComponent = new ChatComponentText("There is a newer version of UniverseCraft available!  Click here to see it.");
			versionWarningChatComponent.setChatStyle(clickableChatStyle);
			event.player.addChatMessage(versionWarningChatComponent);
			UniverseCraft.versionChecked = true;
		}
	}
}