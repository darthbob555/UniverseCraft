package com.universeCraft.render;

import org.lwjgl.opengl.GL11;

import com.universeCraft.tileEntity.TileEntityAtomicPulveriser;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

public class GenericBlockItemRenderer implements IItemRenderer{

	private TileEntity tile;
	private TileEntitySpecialRenderer renderer;
	public GenericBlockItemRenderer(TileEntity tile, TileEntitySpecialRenderer renderer) {
		super();
		this.tile = tile;
		this.renderer = renderer;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}
}
