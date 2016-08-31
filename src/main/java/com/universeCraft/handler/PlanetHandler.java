package com.universeCraft.handler;

import java.util.Random;

import com.universeCraft.tileEntity.TileEntityPlanetaryAssembler;
import com.universeCraft.tileEntity.TileEntityStellarEnricher;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class PlanetHandler {

	int diameter;
	int x = -diameter;
	int y = -diameter;
	int z = -diameter;
	int x2 = -diameter;
	int y2 = -diameter;
	int z2 = -diameter;
	int xSquare;
	int zSquare;
	int radius;

	public void createPlanet(World world, int xCoord, int yCoord, int zCoord, int d, Block blockToPlace){
		Random rand = new Random();
		this.diameter = d/2;
		if(x >= this.diameter){
			x = -this.diameter;
			if(y >= this.diameter){
				y = -this.diameter;
				if(z >= this.diameter){
					x = -this.diameter;
					y = -this.diameter;
					z = -this.diameter;
				}
				else{
					z++;
				}
			}
			else{
				y++;
			}
		}
		else{
			x++;
		}
		if((x == x2) && (y == y2) && (z == z2)){
			TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) world.getTileEntity(xCoord+50, yCoord-50, zCoord);
			if(te != null){
				te.isRunning = false;
				te.canRun = true;
			}
		}
		else{
			world.setBlock(xCoord + x, yCoord + y, zCoord + z, blockToPlace, 0, 2);
		}
	}

	public void createRings(World world, int xCoord, int yCoord, int zCoord, int d, Block blockToPlace){
		int maximum = d;
		//+1 to allow for an even diameter
		//For every 8 diameter, add 1 thickness to the ring
		for(int thickness = 1; thickness < maximum/8+1; thickness++){
			for(int row = -maximum+thickness; row < maximum; row++){
				for(int col = -maximum+thickness; col < maximum; col++){
					if(row == -maximum+thickness || row == maximum - thickness){
						world.setBlock(xCoord+row, yCoord, zCoord+col, blockToPlace);
					}
					else if (col == -maximum+thickness || col == maximum - thickness){
						world.setBlock(xCoord+row, yCoord, zCoord+col, blockToPlace);
					}
				}
			}
		}
		TileEntityPlanetaryAssembler te = (TileEntityPlanetaryAssembler) world.getTileEntity(xCoord+50, yCoord-50, zCoord);
		if(te != null){
			te.canRun = false;
			te.rings = false;
		}
	}

	public void sprinkle(World world, int x, int y, int z, int diameter2) {
		Random rand = new Random();
		this.diameter = diameter2/2;
		for(int i = 10*(diameter2/8); i>0; i--){
			world.setBlock(x+rand.nextInt(diameter2/2), y+rand.nextInt(diameter2/2), z+rand.nextInt(diameter2/2), Blocks.bedrock);
		}
	}
}
