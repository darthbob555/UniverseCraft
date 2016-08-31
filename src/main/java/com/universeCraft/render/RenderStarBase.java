package com.universeCraft.render;

import org.lwjgl.opengl.GL11;

import com.universeCraft.models.ModelStarBase;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderStarBase implements IItemRenderer {

	private ModelStarBase model;

	public RenderStarBase(){
		model = new ModelStarBase();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type){
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper){
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data){
		GL11.glPushMatrix();

		GL11.glScalef(-1F, -1F, 1F);

		switch (type){
		case INVENTORY:
			GL11.glTranslatef(0, 0.12F, 0);
			break;
		case EQUIPPED:
			GL11.glTranslatef(-0.8F, -0.2F, 0.7F);
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glTranslatef(0, -0.7F, 0.7F);
			break;
		default:
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("UniverseCraft:textures/items/StarBase.png"));
		model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
}
