package com.universeCraft.handler;

import java.util.Random;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.items.ModItems;
import com.universeCraft.proxies.CommonProxy;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingRecipeHandler {

	public void shapedRecipe(){
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 0),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 0)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 1),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 1)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks2, 1, 11),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 57)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks2, 1, 12),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 58)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks2, 1, 13),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 59)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 8),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 26)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 15),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 28)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 10),
				"XXX",
				"XXX",
				"XXX",
				'X', Items.paper
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 11), 
				"DDD",
				"DDD",
				"DDD",
				'D', Blocks.diamond_block
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 12), 
				"DDD",
				"DDD",
				"DDD",
				'D', new ItemStack(ModBlocks.metadataBlocks, 1, 11)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 13),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 42)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 14),
				"XXX",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 45)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 4),
				"XXX",
				"XYX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 57), 'Y', new ItemStack(ModItems.Particles, 1, 6)
				);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 5),
				" X ",
				"XYX",
				" X ",
				'X', new ItemStack(ModItems.Particles, 1, 59), 'Y', new ItemStack(ModBlocks.metadataBlocks, 1, 4)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumAxe),
				"XX ",
				"XY ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 45), 'Y', new ItemStack(ModBlocks.metadataBlocks, 1, 12)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumPickaxe),
				"XXX",
				" Y ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 45), 'Y', new ItemStack(ModBlocks.metadataBlocks, 1, 12)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumShovel),
				" X ",
				" Y ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 45), 'Y', new ItemStack(ModBlocks.metadataBlocks, 1, 12)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumSword),
				" X ",
				" X ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 45), 'Y', new ItemStack(ModBlocks.metadataBlocks, 1, 12)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumPaxel),
				"ABC",
				" X ",
				" X ",
				'X', new ItemStack(ModBlocks.metadataBlocks, 1, 12), 'A', ModItems.blackholiumPickaxe, 'B', ModItems.blackholiumAxe, 'C', ModItems.blackholiumShovel
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.condensedAxe),
				"XX ",
				"XY ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 0), 'Y', Blocks.obsidian
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.condensedPickaxe),
				"XXX",
				" Y ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 0), 'Y', Blocks.obsidian
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.condensedShovel),
				" X ",
				" Y ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 0), 'Y', Blocks.obsidian
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.condensedSword),
				" X ",
				" X ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 0), 'Y', Blocks.obsidian
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.singulariumAxe),
				"XX ",
				"XY ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 1), 'Y', Blocks.obsidian
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.singulariumPickaxe),
				"XXX",
				" Y ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 1), 'Y', Blocks.obsidian
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.singulariumShovel),
				" X ",
				" Y ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 1), 'Y', Blocks.obsidian
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.singulariumSword),
				" X ",
				" X ",
				" Y ",
				'X', new ItemStack(ModItems.Particles, 1, 1), 'Y', Blocks.obsidian
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.condensedPaxel),
				"ABC",
				" X ",
				" X ",
				'X', Blocks.obsidian, 'A', ModItems.condensedPickaxe, 'B', ModItems.condensedAxe, 'C', ModItems.condensedShovel
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.singulariumPaxel),
				"ABC",
				" X ",
				" X ",
				'X', Blocks.obsidian, 'A', ModItems.singulariumPickaxe, 'B', ModItems.singulariumAxe, 'C', ModItems.singulariumShovel
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.darkMatterHelmet), 
				"XXX",
				"X X",
				'X', new ItemStack(ModItems.Particles, 1, 26)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.darkMatterChestplate), 
				"X X",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 26)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.darkMatterLeggings), 
				"XXX",
				"X X",
				"X X",
				'X', new ItemStack(ModItems.Particles, 1, 26)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.darkMatterBoots), 
				"X X",
				"X X",
				'X', new ItemStack(ModItems.Particles, 1, 26)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumHelmet), 
				"XXX",
				"X X",
				'X', new ItemStack(ModItems.Particles, 1, 45)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumChestplate), 
				"X X",
				"XXX",
				"XXX",
				'X', new ItemStack(ModItems.Particles, 1, 45)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumLeggings), 
				"XXX",
				"X X",
				"X X",
				'X', new ItemStack(ModItems.Particles, 1, 45)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.blackholiumBoots), 
				"X X",
				"X X",
				'X', new ItemStack(ModItems.Particles, 1, 45)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.atomicPulveriser),
				"PCP",
				"PPP",
				"OOO",
				'O', Blocks.obsidian, 'P', new ItemStack(ModItems.Particles, 1, 57), 'C', ModItems.condensedPickaxe
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.metadataBlocks, 1, 9), 
				"RBR",
				"RBR",
				"DRD",
				'R', new ItemStack(ModItems.Particles, 1, 58), 'B', new ItemStack(ModItems.Particles, 1, 23), 'D', Items.diamond
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 7),
				"X  ",
				" S ",
				"  S",
				'S', new ItemStack(ModItems.Particles, 1, 57), 'X', ModItems.StarBase
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.StarBase),
				"ABA",
				"BXB",
				"ABA",
				'A', new ItemStack(ModItems.Particles, 1, 4), 'X', Items.nether_star, 'B', new ItemStack(ModItems.Particles, 1, 5)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ionStorage, 1, 0),
				"WPW",
				"PCP",
				"WPW",
				'C', new ItemStack(ModItems.Particles, 1, 6), 'P', new ItemStack(ModItems.Particles, 1, 57), 'W', Blocks.planks
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ionStorage, 1, 1),
				"LRL",
				"RSR",
				"LRL",
				'S', new ItemStack(ModBlocks.ionStorage, 1, 0), 'L', new ItemStack(ModItems.Particles, 1, 64), 'R', new ItemStack(ModItems.Particles, 1, 58)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ionStorage, 1, 3),
				"MRM",
				"ZSZ",
				"MRM",
				'R', new ItemStack(ModItems.Particles, 1, 58), 'S', new ItemStack(ModBlocks.ionStorage, 1, 1), 'Z', new ItemStack(ModItems.Particles, 1, 59), 'M', new ItemStack(ModItems.Particles, 1, 65)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ionStorage, 1, 4),
				"HDH",
				"CSC",
				"HDH",
				'D', new ItemStack(ModItems.Particles, 1, 8), 'S', new ItemStack(ModBlocks.ionStorage, 1, 3), 'C', new ItemStack(ModItems.Particles, 1, 33), 'H', new ItemStack(ModItems.Particles, 1, 66)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ionStorage, 1, 2), 
				"ZZZ",
				"PRP",
				"RRR",
				'R', new ItemStack(ModItems.Particles, 1, 28), 'Z', new ItemStack(ModBlocks.metadataBlocks2, 1, 13), 'P', new ItemStack(ModBlocks.metadataBlocks2, 1, 11)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.stellarGenerator),
				" V ",
				"DAD",
				"NIN",
				'D', new ItemStack(ModItems.Particles, 1, 28), 'V', ModBlocks.vertexGenerator, 'A', new ItemStack(ModItems.Particles, 1, 32), 'N', Items.nether_star, 'I', new ItemStack(ModBlocks.ionStorage, 1, 1)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.carbonGenerator),
				" F ",
				"RCR",
				"PRP",
				'C', Items.coal, 'F', Blocks.furnace, 'P', new ItemStack(ModItems.Particles, 1, 57), 'R', new ItemStack(ModItems.Particles, 1, 58)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.vertexGenerator),
				" Q ",
				"ECE",
				"MIM",
				'Q', new ItemStack(ModItems.Particles, 1, 20), 'M', new ItemStack(ModItems.Particles, 1, 65), 'C', ModBlocks.carbonGenerator, 'E', new ItemStack(ModItems.Particles, 1, 3), 'V', new ItemStack(ModItems.Particles, 1, 22), 'I', new ItemStack(ModBlocks.ionStorage, 1, 0)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blackholiumTNT), 
				"TDT",
				"DTD",
				"TDT",
				'D', new ItemStack(ModItems.Particles, 1, 42), 'T', Blocks.tnt
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.refinedDarkMatterTNT), 
				"TDT",
				"DTD",
				"TDT",
				'D', new ItemStack(ModItems.Particles, 1, 28), 'T', Blocks.tnt
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.particleAccelerator),
				"VZV",
				"TCT",
				"PZP",
				'C', new ItemStack(ModItems.Particles, 1, 23), 'T', new ItemStack(ModBlocks.metadataBlocks, 1, 4), 'P', new ItemStack(ModBlocks.metadataBlocks2, 1, 11), 'Z', new ItemStack(ModItems.Particles, 1, 59), 'V', ModBlocks.pipe 
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.pipe, 2, 0),
				"RPR",
				"LLL",
				"RPR",
				'R', new ItemStack(ModItems.Particles, 1, 58), 'P', new ItemStack(ModItems.Particles, 1, 57), 'L', new ItemStack(ModItems.Particles, 1, 64)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 6),
				"XXX",
				"AXB",
				"XXX",
				'A', new ItemStack(ModItems.Particles, 1, 2), 'B', new ItemStack(ModItems.Particles, 1, 3), 'X', Items.iron_ingot
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 20),
				"GGG",
				"LZL",
				"LZL",
				'L', new ItemStack(ModItems.Particles, 1, 64), 'Z', new ItemStack(ModItems.Particles, 1, 59), 'G', Blocks.glass
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 23),
				"LGP",
				"GHG",
				"PGL",
				'L', new ItemStack(ModItems.Particles, 1, 58), 'P', new ItemStack(ModItems.Particles, 1, 57), 'G', Blocks.glass, 'H', new ItemStack(ModItems.Particles, 1, 5)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 25),
				"ZCZ",
				"SAS",
				"ZBZ",
				'C', Blocks.redstone_torch, 'A', new ItemStack(ModItems.Particles, 1, 32), 'B', new ItemStack(ModItems.Particles, 1, 54), 'Z', new ItemStack(ModItems.Particles, 1, 59), 'S', new ItemStack(ModItems.Particles, 1, 66)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 30),
				"PPP",
				"PCP",
				"PPP",
				'C', new ItemStack(ModItems.Particles, 1, 8), 'P', Items.paper
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 31),
				"MRM",
				"RDR",
				"ABA",
				'D', new ItemStack(ModItems.Particles, 1, 26), 'R', Blocks.redstone_torch, 'B', new ItemStack(ModItems.Particles, 1, 54), 'A', new ItemStack(ModItems.Particles, 1, 8), 'M', new ItemStack(ModItems.Particles, 1, 65)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 32),
				"PDP",
				"BLB",
				"LZL",
				'B', new ItemStack(ModItems.Particles, 1, 23), 'P', new ItemStack(ModItems.Particles, 1, 56), 'D', new ItemStack(ModItems.Particles, 1, 26), 'L', new ItemStack(ModItems.Particles, 1, 64), 'Z', new ItemStack(ModItems.Particles, 1, 59)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 33),
				"CRC",
				"AHA",
				"HRH",
				'A', new ItemStack(ModItems.Particles, 1, 32), 'H', new ItemStack(ModItems.Particles, 1, 66), 'R', new ItemStack(ModItems.Particles, 1, 28), 'C', new ItemStack(ModItems.Particles, 1, 8)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 34),
				"DTD",
				"TCT",
				"DTD",
				'C', new ItemStack(ModItems.Particles, 1, 30), 'D', new ItemStack(ModItems.Particles, 1, 26), 'T', Blocks.torch
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 35),
				"DGD",
				"GCG",
				"DGD",
				'C', new ItemStack(ModItems.Particles, 1, 30), 'D', new ItemStack(ModItems.Particles, 1, 28), 'G', Blocks.glowstone
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 36),
				"DCD",
				"CRC",
				"DCD",
				'C', new ItemStack(ModItems.Particles, 1, 30), 'D', new ItemStack(ModItems.Particles, 1, 28), 'R', new ItemStack(ModItems.Particles, 1, 29)
				);

		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 37),
				"BCL",
				"CRC",
				"LCB",
				'C', new ItemStack(ModItems.Particles, 1, 30), 'B', Items.water_bucket, 'L', Items.lava_bucket, 'R', new ItemStack(ModItems.Particles, 1, 29)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 38),
				"BOL",
				"SRS",
				"LCB",
				'S', Blocks.stone, 'B', Items.water_bucket, 'L', Items.lava_bucket, 'R', new ItemStack(ModItems.Particles, 1, 29), 'O', Blocks.obsidian, 'C', Blocks.cobblestone
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ionStorage, 1, 5), 
				"LCL",
				"CIC",
				"LCL",
				'L', new ItemStack(ModItems.Particles, 1, 64), 'C', new ItemStack(ModItems.Particles, 1, 0), 'I', Items.iron_ingot
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 39), 
				"MRM",
				"RAR",
				"MBM",
				'M', new ItemStack(ModItems.Particles, 1, 56), 'B', Blocks.redstone_block, 'A', new ItemStack(ModItems.Particles, 1, 32), 'R', new ItemStack(ModItems.Particles, 1, 26)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 40), 
				"MZM",
				"ZAZ",
				"MBM",
				'Z', new ItemStack(ModItems.Particles, 1, 59), 'A', new ItemStack(ModItems.Particles, 1, 8), 'M', new ItemStack(ModItems.Particles, 1, 65), 'B', new ItemStack(ModItems.Particles, 1, 54)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 44), 
				"DDD",
				"DDD",
				"DDD",
				'D', new ItemStack(ModItems.Particles, 1, 41)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 42), 
				"DDD",
				"DSD",
				"DDD",
				'D', new ItemStack(ModItems.Particles, 1, 44), 'S', new ItemStack(ModItems.Particles, 1, 43)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ionStorage, 1, 6),
				"ARA",
				"DBD",
				"ARA",
				'B', new ItemStack(ModBlocks.ionStorage, 1, 4), 'D', new ItemStack(ModBlocks.metadataBlocks, 1, 12), 'A', new ItemStack(ModItems.Particles, 1, 8), 'R', new ItemStack(ModBlocks.metadataBlocks, 1, 14)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 48), 
				"III",
				"IDI",
				"III",
				'D', new ItemStack(ModItems.Particles, 1, 26), 'I', Items.paper
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 49), 
				"WDW",
				"DCD",
				"WDW",
				'D', new ItemStack(ModItems.Particles, 1, 28), 'C', new ItemStack(ModItems.Particles, 1, 33), 'W', Items.clock
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 47), 
				"SAS",
				"DBD",
				"SDS",
				'D', new ItemStack(ModItems.Particles, 1, 26), 'S', new ItemStack(ModItems.Particles, 1, 39), 'A', new ItemStack(ModItems.Particles, 1, 8), 'B', new ItemStack(ModItems.Particles, 1, 54)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 56), 
				"LLL",
				"C C",
				"C C",
				'C', new ItemStack(ModItems.Particles, 1, 64), 'L', new ItemStack(Items.dye, 1, 4)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 55), 
				"LLL",
				"C C",
				"C C",
				'C', new ItemStack(ModItems.Particles, 1, 64), 'L', Items.redstone
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 54), 
				"CIT",
				"III",
				"CIT",
				'C', new ItemStack(ModItems.Particles, 1, 56), 'T', new ItemStack(ModItems.Particles, 1, 55), 'I', new ItemStack(ModItems.Particles, 1, 64)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 50), 
				"SSS",
				"SNS",
				"SBS",
				'S', new ItemStack(ModItems.Particles, 1, 58), 'B', new ItemStack(ModItems.Particles, 1, 54), 'N', Items.nether_star
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 51), 
				"PPP",
				"LLL",
				"LBL",
				'P', new ItemStack(ModItems.Particles, 1, 20), 'B', new ItemStack(ModItems.Particles, 1, 54), 'L', new ItemStack(ModItems.Particles, 1, 64)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 52), 
				"PPP",
				"LLL",
				"LBL",
				'P', new ItemStack(ModItems.Particles, 1, 20), 'B', new ItemStack(ModItems.Particles, 1, 51), 'L', new ItemStack(ModItems.Particles, 1, 65)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 53), 
				"PPP",
				"LLL",
				"LBL",
				'P', new ItemStack(ModItems.Particles, 1, 20), 'B', new ItemStack(ModItems.Particles, 1, 52), 'L', new ItemStack(ModItems.Particles, 1, 66)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.ionStorage, 1, 8), 
				"HZH",
				"ZCZ",
				"HZH",
				'C', new ItemStack(ModItems.Particles, 1, 33), 'H', new ItemStack(ModItems.Particles, 1, 66), 'Z', new ItemStack(ModBlocks.metadataBlocks2, 1, 13) 
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 60), 
				" S ",
				"SIS",
				"SPS",
				'C', new ItemStack(ModItems.Particles, 1, 33), 'P', new ItemStack(ModItems.Particles, 1, 57), 'S', Items.iron_ingot, 'I', new ItemStack(ModBlocks.ionStorage, 1, 0)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 61), 
				" S ",
				"SIS",
				"SDS",
				'D', new ItemStack(ModItems.Particles, 1, 26), 'S', new ItemStack(ModItems.Particles, 1, 64), 'I', new ItemStack(ModBlocks.ionStorage, 1, 1)  
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 62), 
				" S ",
				"SIS",
				"SDS",
				'D', new ItemStack(ModItems.Particles, 1, 28), 'S', new ItemStack(ModItems.Particles, 1, 65), 'I', new ItemStack(ModBlocks.ionStorage, 1, 3) 
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 63), 
				" S ",
				"SIS",
				"SBS",
				'B', new ItemStack(ModItems.Particles, 1, 42), 'S', new ItemStack(ModItems.Particles, 1, 66), 'I', new ItemStack(ModBlocks.ionStorage, 1, 4) 
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 64), 
				" C ",
				"CIC",
				" C ",
				'C', new ItemStack(ModItems.Particles, 1, 68), 'I', Items.iron_ingot
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 65), 
				" C ",
				"CIC",
				" C ",
				'C', new ItemStack(ModItems.Particles, 1, 68), 'I', new ItemStack(ModItems.Particles, 1, 64)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 66), 
				" C ",
				"CIC",
				" C ",
				'C', new ItemStack(ModItems.Particles, 1, 68), 'I', new ItemStack(ModItems.Particles, 1, 65)
				);
		GameRegistry.addRecipe(new ItemStack(ModItems.Particles, 1, 69), 
				"D  ",
				" C ",
				"  C",
				'C', new ItemStack(ModItems.Particles, 1, 59), 'D', new ItemStack(ModItems.Particles, 1, 26)
				);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.energyNode), 
				"DPD",
				"HIP",
				"DPD",
				'P', new ItemStack(ModItems.Particles, 1, 57), 'D', new ItemStack(ModItems.Particles, 1, 26), 'H', new ItemStack(ModItems.Particles, 1, 66), 'I', new ItemStack(ModBlocks.ionStorage, 1, 1)
				);
	}

	public void shapelessRecipe(){
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 2), Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, new ItemStack(Items.dye, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 3), Items.iron_ingot, Items.iron_ingot, Items.iron_ingot, new ItemStack(Items.dye, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 34), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 34));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 35), new ItemStack(ModItems.Particles, 1, 28), new ItemStack(ModItems.Particles, 1, 35));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 36), new ItemStack(ModItems.Particles, 1, 28), new ItemStack(ModItems.Particles, 1, 36));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 37), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 37));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 38), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 38));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 49), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 49));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.Particles, 1, 68), new ItemStack(ModItems.Particles, 1, 18), new ItemStack(ModItems.Particles, 1, 18),new ItemStack(ModItems.Particles, 1, 18));

		ItemStack star = new ItemStack(ModItems.Particles, 1, 46);
		for(int i = 0; i<Enchantment.enchantmentsList.length; i++){
			if(Enchantment.enchantmentsList[i] != null){
				star.addEnchantment(Enchantment.enchantmentsList[i], 10);
			}
		}
		GameRegistry.addShapelessRecipe(star, new ItemStack(ModItems.Particles, 1, 45), new ItemStack(ModItems.Particles, 1, 45), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 26), new ItemStack(ModItems.Particles, 1, 28), new ItemStack(ModItems.Particles, 1, 28));
	}


	public void smeltingRecipe(){
		GameRegistry.addSmelting(new ItemStack(ModBlocks.metadataBlocks, 1, 2), new ItemStack(ModItems.Particles, 1, 0), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.metadataBlocks, 1, 3), new ItemStack(ModItems.Particles, 1, 1), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.metadataBlocks2, 1, 8), new ItemStack(ModItems.Particles, 1, 57), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.metadataBlocks2, 1, 9), new ItemStack(ModItems.Particles, 1, 58), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.metadataBlocks2, 1, 10), new ItemStack(ModItems.Particles, 1, 59), 1.0F);
	}
}