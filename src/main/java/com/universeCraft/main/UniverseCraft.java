package com.universeCraft.main;

import net.minecraft.creativetab.CreativeTabs;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.generation.CustomWorldGenerator;
import com.universeCraft.handler.Config;
import com.universeCraft.handler.CraftingRecipeHandler;
import com.universeCraft.handler.ServerTickHandler;
import com.universeCraft.handler.UpdateCheckerHandler;
import com.universeCraft.items.ModItems;
import com.universeCraft.proxies.ClientProxy;
import com.universeCraft.proxies.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = UniverseCraft.MODID, version = UniverseCraft.VERSION) 
public class UniverseCraft{
	
	public static final String MODID = "universeCraft";
	public static final String VERSION = "1.3.17.0";
	
	public static UpdateCheckerHandler versionChecker;
	public static boolean versionChecked = false;

	public static CreativeTabs universeCraft = new UniverseCraftTab(CreativeTabs.getNextID(), "UniverseCraft");
	
	//Proxies
	@SidedProxy(clientSide = "com.universeCraft.proxies.ClientProxy", serverSide = "com.universeCraft.proxies.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event){
		proxy.preInit(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event){
		proxy.init(event);
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}	
}