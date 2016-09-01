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

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}