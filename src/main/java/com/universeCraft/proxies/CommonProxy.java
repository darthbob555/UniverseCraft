package com.universeCraft.proxies;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.handler.Config;
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

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		Config.loadConfig(e);
		ModBlocks.loadBlocks();
		ModItems.loadItems();
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }
}
