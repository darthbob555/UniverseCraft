package com.universeCraft.proxies;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.generation.CustomWorldGenerator;
import com.universeCraft.handler.Config;
import com.universeCraft.handler.CraftingRecipeHandler;
import com.universeCraft.handler.ServerTickHandler;
import com.universeCraft.items.ModItems;
import com.universeCraft.tileEntity.TileEntityAtomicPulveriser;
import com.universeCraft.tileEntity.TileEntityCarbonGenerator;
import com.universeCraft.tileEntity.TileEntityCrystal;
import com.universeCraft.tileEntity.TileEntityDuplication;
import com.universeCraft.tileEntity.TileEntityEnergyAcceptor;
import com.universeCraft.tileEntity.TileEntityEnergyNode;
import com.universeCraft.tileEntity.TileEntityIonCreative;
import com.universeCraft.tileEntity.TileEntityIonHuge;
import com.universeCraft.tileEntity.TileEntityIonLarge;
import com.universeCraft.tileEntity.TileEntityIonMedium;
import com.universeCraft.tileEntity.TileEntityIonSmall;
import com.universeCraft.tileEntity.TileEntityPartAccelBlock;
import com.universeCraft.tileEntity.TileEntityParticleAccelerator;
import com.universeCraft.tileEntity.TileEntityPlanetaryAssembler;
import com.universeCraft.tileEntity.TileEntityStellarEnricher;
import com.universeCraft.tileEntity.TileEntityStellarGenerator;
import com.universeCraft.tileEntity.TileEntityVacuumPipe;
import com.universeCraft.tileEntity.TileEntityVertexGenerator;
import com.universeCraft.tileEntity.TileEntityWirelessController;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	CraftingRecipeHandler handler = new CraftingRecipeHandler();
	public static CustomWorldGenerator worldGen = new CustomWorldGenerator();
	
	public void preInit(FMLPreInitializationEvent e) {
		Config.loadConfig(e);
		
		ModBlocks.loadBlocks();
		ModItems.loadItems();
		
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
		
		GameRegistry.registerTileEntity(TileEntityParticleAccelerator.class, "tileEntityParticleAccelerator");
		GameRegistry.registerTileEntity(TileEntityPartAccelBlock.class, "tileEntityPartAccel");
		GameRegistry.registerTileEntity(TileEntityAtomicPulveriser.class, "tileEntityPlasmaticGenerator");
		GameRegistry.registerTileEntity(TileEntityCarbonGenerator.class, "tileEntityCoalGenerator");
		GameRegistry.registerTileEntity(TileEntityVertexGenerator.class, "tileEntityVertexGenerator");
		GameRegistry.registerTileEntity(TileEntityStellarGenerator.class, "tileEntityStellarGenerator");
		GameRegistry.registerTileEntity(TileEntityIonSmall.class, "tileEntityIonSmall");
		GameRegistry.registerTileEntity(TileEntityIonMedium.class, "tileEntityIonMedium");
		GameRegistry.registerTileEntity(TileEntityIonLarge.class, "tileEntityIonLarge");
		GameRegistry.registerTileEntity(TileEntityIonHuge.class, "tileEntityIonHuge");
		GameRegistry.registerTileEntity(TileEntityIonCreative.class, "tileEntityIonCreative");
		GameRegistry.registerTileEntity(TileEntityWirelessController.class, "tileEntityWirelessController");
		GameRegistry.registerTileEntity(TileEntityPlanetaryAssembler.class, "tileEntityQuantumTunnel");
		GameRegistry.registerTileEntity(TileEntityVacuumPipe.class, "tileEntityVacuumPipe");
		GameRegistry.registerTileEntity(TileEntityCrystal.class, "tileEntityCrystal");
		GameRegistry.registerTileEntity(TileEntityDuplication.class, "tileEntityDuplication");
		GameRegistry.registerTileEntity(TileEntityEnergyAcceptor.class, "tileEntityEnergyAcceptor");
		GameRegistry.registerTileEntity(TileEntityStellarEnricher.class, "tileEntityStellarEnricher");
		GameRegistry.registerTileEntity(TileEntityEnergyNode.class, "tileEntityEnergyNode");
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    	GameRegistry.registerWorldGenerator((IWorldGenerator) worldGen, 0);
		handler.shapedRecipe();
		handler.shapelessRecipe();
		handler.smeltingRecipe();
    }
}
