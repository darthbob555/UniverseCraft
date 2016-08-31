package com.universeCraft.proxies;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.generation.CustomWorldGenerator;
import com.universeCraft.handler.UpdateCheckerHandler;
import com.universeCraft.items.ModItems;
import com.universeCraft.main.UniverseCraft;
import com.universeCraft.render.GenericBlockItemRenderer;
import com.universeCraft.render.RenderCarbonGenerator;
import com.universeCraft.render.RenderCrystal;
import com.universeCraft.render.RenderEnergyNode;
import com.universeCraft.render.RenderPipe;
import com.universeCraft.render.RenderPulveriser;
import com.universeCraft.render.RenderStarBase;
import com.universeCraft.render.RenderStellarGenerator;
import com.universeCraft.render.RenderVertexGenerator;
import com.universeCraft.tileEntity.TileEntityAtomicPulveriser;
import com.universeCraft.tileEntity.TileEntityCarbonGenerator;
import com.universeCraft.tileEntity.TileEntityCrystal;
import com.universeCraft.tileEntity.TileEntityEnergyNode;
import com.universeCraft.tileEntity.TileEntityStellarGenerator;
import com.universeCraft.tileEntity.TileEntityVacuumPipe;
import com.universeCraft.tileEntity.TileEntityVertexGenerator;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAtomicPulveriser.class, new RenderPulveriser());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCarbonGenerator.class, new RenderCarbonGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVertexGenerator.class, new RenderVertexGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStellarGenerator.class, new RenderStellarGenerator());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new RenderCrystal());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVacuumPipe.class, new RenderPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyNode.class, new RenderEnergyNode());

		RenderingRegistry.registerBlockHandler(new RenderPipe());
		RenderingRegistry.registerBlockHandler(new RenderEnergyNode());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.pipe), new GenericBlockItemRenderer(new TileEntityVacuumPipe(), new RenderPipe()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.atomicPulveriser), new GenericBlockItemRenderer(new TileEntityAtomicPulveriser(), new RenderPulveriser()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.carbonGenerator), new GenericBlockItemRenderer(new TileEntityCarbonGenerator(), new RenderCarbonGenerator()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.vertexGenerator), new GenericBlockItemRenderer(new TileEntityVertexGenerator(), new RenderVertexGenerator()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.stellarGenerator), new GenericBlockItemRenderer(new TileEntityStellarGenerator(), new RenderStellarGenerator()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.crystal), new GenericBlockItemRenderer(new TileEntityCrystal(), new RenderCrystal()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.energyNode), new GenericBlockItemRenderer(new TileEntityEnergyNode(), new RenderEnergyNode()));
		MinecraftForgeClient.registerItemRenderer(ModItems.StarBase, new RenderStarBase());
    }

	@Override
    public void init(FMLInitializationEvent e) {
		super.init(e);
    }

	@Override
    public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
    	UniverseCraft.versionChecker = new UpdateCheckerHandler();
		Thread versionCheckThread = new Thread(UniverseCraft.versionChecker, "Version Checker");
		versionCheckThread.start();
    }
}
