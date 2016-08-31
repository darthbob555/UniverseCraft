package com.universeCraft.render;

import org.lwjgl.opengl.GL11;

import com.universeCraft.blocks.ModBlocks;
import com.universeCraft.models.ModelCrystal;
import com.universeCraft.models.ModelEnergyNode;
import com.universeCraft.models.ModelPipe;
import com.universeCraft.models.ModelStarBase;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class RenderEnergyNode extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{
	
	public static int renderId = RenderingRegistry.getNextAvailableRenderId();
	private ModelEnergyNode model;
	public ResourceLocation texture1 = new ResourceLocation("UniverseCraft:textures/blocks/EnergyNode.png");

	public RenderEnergyNode(){
		model = new ModelEnergyNode();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float scale){
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5, y+0.3, z+0.5);
		GL11.glRotated(180, 0, 0, 1);
		this.bindTexture(texture1);
		this.model.render((Entity)null, 0, -0.1f, 0, 0, 0, 0.0625f);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer){
		if(modelID == renderId){
			renderer.setRenderBounds(0.15f, 0f, 0.15f, 0.85f, 1f, 0.85f);
			renderInvBlock(renderer, block, metadata);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer){
		if (modelID == renderId){
			//top
			if(world.getBlock(x, y+1, z) == ModBlocks.particleAccelerator || world.getBlock(x, y+1, z) == ModBlocks.atomicPulveriser || world.getBlock(x, y+1, z) == ModBlocks.ionStorage || world.getBlock(x, y+1, z) == ModBlocks.vertexGenerator || world.getBlock(x, y+1, z) == ModBlocks.carbonGenerator || world.getBlock(x, y+1, z) == ModBlocks.stellarGenerator){
				renderer.setRenderBounds(0.33f, 0.66f, 0.33f, 0.66f, 1.15f, 0.66f);
				renderer.renderStandardBlock(block, x, y, z);
			}
			//bottom
			if(world.getBlock(x, y-1, z) == ModBlocks.particleAccelerator || world.getBlock(x, y-1, z) == ModBlocks.atomicPulveriser || world.getBlock(x, y-1, z) == ModBlocks.ionStorage || world.getBlock(x, y-1, z) == ModBlocks.vertexGenerator || world.getBlock(x, y-1, z) == ModBlocks.carbonGenerator || world.getBlock(x, y-1, z) == ModBlocks.stellarGenerator)
			{
				renderer.setRenderBounds(0.33f, 0f, 0.33f, 0.66f, 0.33f, 0.66f);
				renderer.renderStandardBlock(block, x, y, z);
			}
			//left
			if(world.getBlock(x+1, y, z) == ModBlocks.particleAccelerator || world.getBlock(x+1, y, z) == ModBlocks.atomicPulveriser || world.getBlock(x+1, y, z) == ModBlocks.ionStorage || world.getBlock(x+1, y, z) == ModBlocks.vertexGenerator || world.getBlock(x+1, y, z) == ModBlocks.carbonGenerator || world.getBlock(x+1, y, z) == ModBlocks.stellarGenerator){
				renderer.setRenderBounds(0.33f, 0.33f, 0.33f, 1.15f, 0.66f, 0.66f);
				renderer.renderStandardBlock(block, x, y, z);
			}
			//right
			if(world.getBlock(x-1, y, z) == ModBlocks.particleAccelerator || world.getBlock(x-1, y, z) == ModBlocks.atomicPulveriser || world.getBlock(x-1, y, z) == ModBlocks.ionStorage || world.getBlock(x-1, y, z) == ModBlocks.vertexGenerator || world.getBlock(x-1, y, z) == ModBlocks.carbonGenerator || world.getBlock(x-1, y, z) == ModBlocks.stellarGenerator){
				renderer.setRenderBounds(-0.15f, 0.33f, 0.33f, 0.33f, 0.66f, 0.66f);
				renderer.renderStandardBlock(block, x, y, z);
			}
			//front
			if(world.getBlock(x, y, z+1) == ModBlocks.particleAccelerator || world.getBlock(x, y, z+1) == ModBlocks.atomicPulveriser || world.getBlock(x, y, z+1) == ModBlocks.ionStorage || world.getBlock(x, y, z+1) == ModBlocks.vertexGenerator || world.getBlock(x, y, z+1) == ModBlocks.carbonGenerator || world.getBlock(x, y, z+1) == ModBlocks.stellarGenerator){
				renderer.setRenderBounds(0.33f, 0.33f, 0.33f, 0.66f, 0.66f, 1.15f);
				renderer.renderStandardBlock(block, x, y, z);
			}
			//behind
			if(world.getBlock(x, y, z-1) == ModBlocks.particleAccelerator || world.getBlock(x, y, z-1) == ModBlocks.atomicPulveriser || world.getBlock(x, y, z-1) == ModBlocks.ionStorage || world.getBlock(x, y, z-1) == ModBlocks.vertexGenerator || world.getBlock(x, y, z-1) == ModBlocks.carbonGenerator || world.getBlock(x, y, z-1) == ModBlocks.stellarGenerator){
				renderer.setRenderBounds(0.33f, 0.33f, -0.15f, 0.66f, 0.66f, 0.33f);
				renderer.renderStandardBlock(block, x, y, z);
			}
		}
		return true;
	}

	public int getRenderId(){
		return renderId;
	}

	public static void renderInvBlock(RenderBlocks renderblocks, Block block, int meta){
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId){
		return true;
	}
}