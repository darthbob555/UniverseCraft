package com.universeCraft.render;

import org.lwjgl.opengl.GL11;

import com.universeCraft.models.ModelCrystal;
import com.universeCraft.models.ModelGenerator;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderCrystal extends TileEntitySpecialRenderer {

	private ModelCrystal model;
	public ResourceLocation texture1 = new ResourceLocation("UniverseCraft:textures/blocks/Crystal.png");

	public RenderCrystal(){
		model = new ModelCrystal();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float scale){
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5, y+1.5, z+0.5);
		GL11.glRotated(180, 0, 0, 1);
		this.bindTexture(texture1);
		this.model.render((Entity)null, 0, -0.1f, 0, 0, 0, 0.0625f);
		GL11.glPopMatrix();
	}
}
