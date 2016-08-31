package com.universeCraft.generation;

import java.util.Random;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.handler.Config;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class CustomWorldGenerator implements IWorldGenerator{

	private int type = 0;
	int i = 0;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		switch (world.provider.dimensionId){
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateEnd(World world, Random random, int x, int z){
		int posX = x + random.nextInt(16);
		int posZ = z + random.nextInt(16); 
		if(Config.spawnCrystals){
			if(random.nextInt(Math.round((Config.crystalRarity/2))) == 0){
				for(int i = 120; i>0; i--){
					if(world.getBlock(posX, i, posZ) == Blocks.end_stone){
						world.setBlock(posX, i+1, posZ, ModBlocks.crystal);
						i = 0;
					}
				}
			}
		}
	}

	private void generateSurface(World world, Random random, int x, int z){
		addOreSpawn(ModBlocks.metadataBlocks, 2, Blocks.stone, world, random, x, z, 16, 16, 5 + random.nextInt(5), 4, 20, 60);
		addOreSpawn(ModBlocks.metadataBlocks, 3, Blocks.stone, world, random, x, z, 16, 16, 3 + random.nextInt(3), 4, 20, 60);
		addOreSpawn(ModBlocks.metadataBlocks2, 8, Blocks.stone, world, random, x, z, 16, 16, 1 + random.nextInt(3), 4, 20, 30);
		addOreSpawn(ModBlocks.metadataBlocks2, 9, Blocks.stone, world, random, x, z, 16, 16, 4 + random.nextInt(2), 4, 40, 70);
		addOreSpawn(ModBlocks.metadataBlocks2, 10, Blocks.stone, world, random, x, z, 16, 16, 5 + random.nextInt(3), 4, 40, 90);

		
		int posX = x + random.nextInt(16);
		int posY = 50 + random.nextInt(50);
		int posZ = z + random.nextInt(16); 
		if(Config.spawnMeteor){
			if(random.nextInt(Config.meteorRarity) == 0){
				type = random.nextInt(3);
				if(type==0){
					new WorldGenSmallMeteor().generate(world, random, posX, posY, posZ);
				}
				else if(type==1){
					new WorldGenSmallMeteor().generate1(world, random, posX, posY, posZ);
				}
				else if(type==2){
					new WorldGenSmallMeteor().generate2(world, random, posX, posY, posZ);
				}
			}
		}

		BiomeGenBase b = world.getBiomeGenForCoords(x, z);
		if(Config.spawnCrystals){
			if(b.biomeName.equals("Desert")){
				if(random.nextInt(Config.crystalRarity) == 0){
					for(int i = 120; i>0; i--){
						if(world.getBlock(posX, i, posZ) == Blocks.sand){
							world.setBlock(posX, i+1, posZ, ModBlocks.crystal);
							i = 0;
						}
					}
				}
			}
		}
	}

	private void generateNether(World world, Random random, int x, int z){
	}

	public void addOreSpawn(Block block, int metadata, Block target, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY){
		assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

		int diffBtwnMinMaxY = maxY - minY;
		for (int x = 0; x < chancesToSpawn; x++){
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, metadata, maxVeinSize, target)).generate(world, random, posX, posY, posZ);
		}
	}
}