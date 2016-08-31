package com.universeCraft.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnergyNode extends ModelBase{

	ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
  
  public ModelEnergyNode(){
    textureWidth = 256;
    textureHeight = 128;
    
      Shape1 = new ModelRenderer(this, 0, 40);
      Shape1.addBox(0F, 0F, 0F, 12, 12, 12);
      Shape1.setRotationPoint(-6F, -9F, -6F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 23);
      Shape2.addBox(0F, 0F, -3F, 10, 5, 10);
      Shape2.setRotationPoint(-5F, -10F, -2F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 32, 66);
      Shape3.addBox(0F, 0F, 0F, 5, 10, 10);
      Shape3.setRotationPoint(-7F, -8F, -5F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 66);
      Shape4.addBox(0F, 0F, 0F, 10, 10, 5);
      Shape4.setRotationPoint(-5F, -8F, -7F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 83);
      Shape5.addBox(0F, 0F, 0F, 10, 10, 5);
      Shape5.setRotationPoint(-5F, -8F, 2F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 53, 51);
      Shape6.addBox(0F, 0F, 0F, 8, 8, 5);
      Shape6.setRotationPoint(-4F, -7F, 3F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 101, 1);
      Shape7.addBox(0F, 0F, 0F, 8, 8, 5);
      Shape7.setRotationPoint(-4F, -7F, -8F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 3);
      Shape8.addBox(0F, 0F, 0F, 10, 5, 10);
      Shape8.setRotationPoint(-5F, -1F, -5F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 96, 15);
      Shape9.addBox(0F, 0F, 0F, 8, 5, 8);
      Shape9.setRotationPoint(-4F, -11F, -4F);
      Shape9.setTextureSize(64, 32);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 97, 31);
      Shape10.addBox(-2F, 0F, 0F, 8, 5, 8);
      Shape10.setRotationPoint(-2F, 0F, -4F);
      Shape10.setTextureSize(64, 32);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 53, 34);
      Shape11.addBox(0F, 0F, 0F, 5, 8, 8);
      Shape11.setRotationPoint(3F, -7F, -4F);
      Shape11.setTextureSize(64, 32);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 102, 48);
      Shape12.addBox(0F, 0F, 0F, 5, 8, 8);
      Shape12.setRotationPoint(-8F, -7F, -4F);
      Shape12.setTextureSize(64, 32);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 32, 87);
      Shape13.addBox(0F, 0F, 0F, 5, 10, 10);
      Shape13.setRotationPoint(2F, -8F, -5F);
      Shape13.setTextureSize(64, 32);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z){
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity){
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}