package com.universeCraft.blocks;

import com.universeCraft.blocks.generators.BlockCarbonGenerator;
import com.universeCraft.blocks.generators.BlockStellarGenerator;
import com.universeCraft.blocks.generators.BlockVertexGenerator;
import com.universeCraft.blocks.generators.ItemCarbonGenerator;
import com.universeCraft.blocks.generators.ItemStellarGenerator;
import com.universeCraft.blocks.generators.ItemVertexGenerator;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {

	public static Block metadataBlocks;
	public static Block metadataBlocks2;
	public static Block particleAccelerator;
	public static Block partAccelBlock;
	public static Block atomicPulveriser;
	public static Block carbonGenerator;
	public static Block vertexGenerator;
	public static Block ionStorage;
	public static Block pipe;
	public static Block stellarGenerator;
	public static Block crystal;
	public static Block refinedDarkMatterTNT;
	public static Block blackholiumTNT;
	public static Block energyNode;
	
	public static void loadBlocks(){
		metadataBlocks = new BlockMetadataBlocks();
		particleAccelerator = new BlockParticleAccelerator();
		partAccelBlock = new BlockPartAccelBlock();
		atomicPulveriser = new BlockAtomicPulveriser();
		carbonGenerator = new BlockCarbonGenerator();
		vertexGenerator = new BlockVertexGenerator();
		stellarGenerator = new BlockStellarGenerator();
		ionStorage = new BlockIonStorage();
		pipe = new BlockPipe();
		crystal = new BlockCrystal();
		refinedDarkMatterTNT = new BlockRefinedDarkMatterTNT();
		blackholiumTNT = new BlockBlackholiumTNT();
		metadataBlocks2 = new BlockMetadataBlocks2();
		energyNode = new BlockEnergyNode();

		GameRegistry.registerBlock(metadataBlocks, ItemMetadataBlocks.class, "MetadataBlocks");
		GameRegistry.registerBlock(metadataBlocks2, ItemMetadataBlocks2.class, "MetadataBlocks2");
		GameRegistry.registerBlock(particleAccelerator, ItemParticleAccelerator.class, "ParticleAccelerator");
		GameRegistry.registerBlock(partAccelBlock, "PartAccelBlock");
		GameRegistry.registerBlock(atomicPulveriser, ItemAtomicPulveriser.class, "AtomicPulveriser");
		GameRegistry.registerBlock(carbonGenerator, ItemCarbonGenerator.class, "CarbonGenerator");
		GameRegistry.registerBlock(vertexGenerator, ItemVertexGenerator.class, "SolarGenerator");
		GameRegistry.registerBlock(stellarGenerator, ItemStellarGenerator.class, "stellarGenerator");
		GameRegistry.registerBlock(ionStorage, ItemIonStorage.class, "IonStorage");
		GameRegistry.registerBlock(pipe, ItemPipe.class, "pipe");
		GameRegistry.registerBlock(crystal, ItemCrystal.class, "Crystal");
		GameRegistry.registerBlock(blackholiumTNT, ItemBlackholiumTNT.class, "BlackholiumTNT");
		GameRegistry.registerBlock(refinedDarkMatterTNT, ItemRefinedDarkMatterTNT.class, "RefinedDarkMatterTNT");
		GameRegistry.registerBlock(energyNode, ItemEnergyNode.class, "EnergyNode");
	}
}
