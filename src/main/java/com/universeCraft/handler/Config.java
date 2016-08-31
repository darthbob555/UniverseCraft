package com.universeCraft.handler;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config{
	
	public static boolean spawnMeteor = true;
	public static boolean spawnCrystals = true;
	public static int meteorRarity = 5000;
	public static int crystalRarity = 250;
	public static int protectionPosY = -5;
	
	public static void loadConfig(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		config.addCustomCategoryComment("GENERATION", "This is where you can change the generation of certain objects. The value inputed means roughly 1 in X chance for the object to spawn every chunk");
		spawnMeteor = config.get("GENERATION", "SpawnMeteors", true).getBoolean(true);
		meteorRarity = config.get("GENERATION", "MeteorSpawnRate", 5000).getInt();
		spawnCrystals  = config.get("GENERATION", "SpawnCrystals", true).getBoolean(true);
		crystalRarity = config.get("GENERATION", "CrystalSpawnRate", 250).getInt();
		config.addCustomCategoryComment("GENERAL", "General config options");
		protectionPosY = config.get("GENERAL", "PosYSafetyValue", -5).getInt();
		config.save();
	}
}