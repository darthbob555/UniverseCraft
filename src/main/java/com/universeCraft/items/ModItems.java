package com.universeCraft.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static Item Particles;
	public static Item StarBase;
	
	public static ToolMaterial condensed = EnumHelper.addToolMaterial("condensed", 2, 750, 6.5F, 2.0F, 10);
	public static ToolMaterial singularium = EnumHelper.addToolMaterial("singularium", 3, 2000, 8.5F, 4.0F, 14);
	public static ToolMaterial blackholium = EnumHelper.addToolMaterial("blackholium", 5, 100000, 100F, 10F, 100);
	public static ToolMaterial blackholiumS = EnumHelper.addToolMaterial("blackholiumS", 5, 100000, 100F, 250F, 100);
	
	static ArmorMaterial darkMatter = EnumHelper.addArmorMaterial("darkmatter", 18, new int[] { 3, 6, 5, 3 }, 0);
	static ArmorMaterial blackholiumArmor = EnumHelper.addArmorMaterial("blackholiumArmor", 10000, new int[] { 5, 8, 7, 4 }, 1000);
	
	public static Item darkMatterHelmet;
	public static Item darkMatterChestplate;
	public static Item darkMatterLeggings;
	public static Item darkMatterBoots;
	
	public static Item blackholiumHelmet;
	public static Item blackholiumChestplate;
	public static Item blackholiumLeggings;
	public static Item blackholiumBoots;
	
	public static Item blackholiumPickaxe;
	public static Item blackholiumSword;
	public static Item blackholiumAxe;
	public static Item blackholiumShovel;
	public static Item blackholiumPaxel;
	
	public static Item condensedPickaxe;
	public static Item condensedSword;
	public static Item condensedAxe;
	public static Item condensedShovel;
	public static Item condensedPaxel;
	
	public static Item singulariumPickaxe;
	public static Item singulariumSword;
	public static Item singulariumAxe;
	public static Item singulariumShovel;
	public static Item singulariumPaxel;
	
	public static final void loadItems(){
		Particles = new MetadataItems("particle");
		StarBase = new ItemStarBase();
		
		condensedPickaxe = new ItemCondensedPickaxe(condensed, "CondensedPickaxe");
    	condensedSword = new ItemCondensedSword(condensed, "CondensedSword");
    	condensedAxe = new ItemCondensedAxe(condensed, "CondensedAxe");
    	condensedShovel = new ItemCondensedShovel(condensed, "CondensedShovel");
    	condensedPaxel = new ItemCondensedPaxel(condensed, "CondensedPaxel");
    	
    	singulariumPickaxe = new ItemSingulariumPickaxe(singularium, "SingulariumPickaxe");
    	singulariumSword = new ItemSingulariumSword(singularium, "SingulariumSword");
    	singulariumAxe = new ItemSingulariumAxe(singularium, "SingulariumAxe");
    	singulariumShovel = new ItemSingulariumShovel(singularium, "SingulariumShovel");
    	singulariumPaxel = new ItemSingulariumPaxel(singularium, "SingulariumPaxel");
    	
    	blackholiumPickaxe = new ItemBlackholiumPickaxe(blackholium, "BlackholiumPickaxe");
    	blackholiumSword = new ItemBlackholiumSword(blackholiumS, "BlackholiumSword");
    	blackholiumAxe = new ItemBlackholiumAxe(blackholium, "BlackholiumAxe");
    	blackholiumShovel = new ItemBlackholiumShovel(blackholium, "BlackholiumShovel");
    	blackholiumPaxel = new ItemBlackholiumPaxel(blackholium, "BlackholiumPaxel");
    	
    	darkMatterHelmet = new DarkMatterArmor(darkMatter, 0, "darkMatterHelmet");
    	darkMatterChestplate = new DarkMatterArmor(darkMatter, 1, "darkMatterChestplate");
    	darkMatterLeggings = new DarkMatterArmor(darkMatter, 2, "darkMatterLeggings");
    	darkMatterBoots = new DarkMatterArmor(darkMatter, 3, "darkMatterBoots");

    	blackholiumHelmet = new BlackholiumArmor(blackholiumArmor, 0, "blackholiumHelmet");
    	blackholiumChestplate = new BlackholiumArmor(blackholiumArmor, 1, "blackholiumChestplate");
    	blackholiumLeggings = new BlackholiumArmor(blackholiumArmor, 2, "blackholiumLeggings");
    	blackholiumBoots = new BlackholiumArmor(blackholiumArmor, 3, "blackholiumBoots");

		GameRegistry.registerItem(Particles, "Particle");
		GameRegistry.registerItem(StarBase, "StarBase");
		
		GameRegistry.registerItem(condensedPickaxe, "CondensedPickaxe");
    	GameRegistry.registerItem(condensedSword, "CondensedSword");
    	GameRegistry.registerItem(condensedAxe, "CondensedAxe");
    	GameRegistry.registerItem(condensedShovel, "CondensedShovel");
    	GameRegistry.registerItem(condensedPaxel, "CondensedPaxel");
    	
    	GameRegistry.registerItem(singulariumPickaxe, "SingulariumPickaxe");
    	GameRegistry.registerItem(singulariumSword, "SingulariumSword");
    	GameRegistry.registerItem(singulariumAxe, "SingulariumAxe");
    	GameRegistry.registerItem(singulariumShovel, "SingulariumShovel");
    	GameRegistry.registerItem(singulariumPaxel, "SingulariumPaxel");
    	
    	GameRegistry.registerItem(blackholiumPickaxe, "BlackholiumPickaxe");
    	GameRegistry.registerItem(blackholiumSword, "BlackholiumSword");
    	GameRegistry.registerItem(blackholiumAxe, "BlackholiumAxe");
    	GameRegistry.registerItem(blackholiumShovel, "BlackholiumShovel");
    	GameRegistry.registerItem(blackholiumPaxel, "BlackholiumPaxel");
    	
    	GameRegistry.registerItem(darkMatterHelmet, "DarkMatterHelmet");
    	GameRegistry.registerItem(darkMatterChestplate, "DarkMatterChestplate");
    	GameRegistry.registerItem(darkMatterLeggings, "DarkMatterLeggings");
    	GameRegistry.registerItem(darkMatterBoots, "DarkMatterBoots");
    	
    	GameRegistry.registerItem(blackholiumHelmet, "BlackholiumHelmet");
    	GameRegistry.registerItem(blackholiumChestplate, "BlackholiumChestplate");
    	GameRegistry.registerItem(blackholiumLeggings, "BlackholiumLeggings");
    	GameRegistry.registerItem(blackholiumBoots, "BlackholiumBoots");
    	
//    	ItemStack book = new ItemStack(Items.written_book);
//		book.setTagCompound(new NBTTagCompound());
//		book.getTagCompound().setString("author", "Darthbob555");
//		book.getTagCompound().setString("title", "Guide To UniverseCraft");
//		NBTTagList pages = new NBTTagList();
//		pages.appendTag(new NBTTagString(black + underline + "UniverseCraft" + "                                                    A Mod For Exploring The Universe"));
//		pages.appendTag(new NBTTagString("Introduction:                                    Welcome to your guide for UniverseCraft (this is a temporary placholder for a book that will be (hopefully) coming in the future)."));
//		pages.appendTag(new NBTTagString("Stars:                                           Stars cannot be crafted directly; you will need to craft stellar mater first and then right clicking on the item. The star you get is random. Stars have many different traits; Starsize-[the size of the star {0-6}]; Solar Mass-[the mass of the star {0.45-90}]; Solar Radii-[{0.08-16}]"));
//		book.setTagInfo("pages", pages);
//		GameRegistry.addShapelessRecipe(book, Items.book, Items.diamond);
	}
}
