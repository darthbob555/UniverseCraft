package com.universeCraft.items;

import java.util.Set;

import com.google.common.collect.Sets;
import com.universeCraft.main.UniverseCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemBlackholiumPaxel extends ItemTool {
	
	private static Set blocks = Sets.newHashSet(new Block[] {Blocks.acacia_stairs, Blocks.activator_rail, Blocks.anvil, Blocks.beacon, Blocks.bed, Blocks.birch_stairs, Blocks.bookshelf, Blocks.brewing_stand, Blocks.brick_block, Blocks.brick_stairs, Blocks.brown_mushroom, Blocks.brown_mushroom_block, Blocks.cactus, Blocks.cake, Blocks.carpet, Blocks.carrots, Blocks.cauldron, Blocks.chest, Blocks.clay, Blocks.coal_block, Blocks.coal_ore, Blocks.cobblestone, Blocks.cobblestone_wall, Blocks.cocoa, Blocks.crafting_table, Blocks.dark_oak_stairs, Blocks.daylight_detector, Blocks.deadbush, Blocks.detector_rail, Blocks.diamond_block, Blocks.diamond_ore, Blocks.dirt, Blocks.dispenser, Blocks.double_plant, Blocks.double_stone_slab, Blocks.double_wooden_slab, Blocks.dropper, Blocks.emerald_block, Blocks.emerald_ore, Blocks.enchanting_table, Blocks.end_stone, Blocks.ender_chest, Blocks.farmland, Blocks.fence, Blocks.fence_gate, Blocks.flower_pot, Blocks.furnace, Blocks.glass, Blocks.glass_pane, Blocks.glowstone, Blocks.gold_block, Blocks.gold_ore, Blocks.golden_rail, Blocks.grass, Blocks.gravel, Blocks.hardened_clay, Blocks.hay_block, Blocks.heavy_weighted_pressure_plate, Blocks.hopper, Blocks.ice, Blocks.iron_bars, Blocks.iron_block, Blocks.iron_door, Blocks.iron_ore, Blocks.jukebox, Blocks.jungle_stairs, Blocks.ladder, Blocks.lapis_block, Blocks.lapis_ore, Blocks.leaves, Blocks.lever, Blocks.light_weighted_pressure_plate, Blocks.log, Blocks.melon_block, Blocks.melon_stem, Blocks.mob_spawner, Blocks.monster_egg, Blocks.mossy_cobblestone, Blocks.mycelium, Blocks.nether_brick, Blocks.nether_brick_fence, Blocks.nether_brick_stairs, Blocks.nether_wart, Blocks.netherrack, Blocks.noteblock, Blocks.oak_stairs, Blocks.obsidian, Blocks.packed_ice, Blocks.piston, Blocks.planks, Blocks.potatoes, Blocks.powered_comparator, Blocks.powered_repeater, Blocks.pumpkin, Blocks.pumpkin_stem, Blocks.quartz_block, Blocks.quartz_ore, Blocks.quartz_ore, Blocks.quartz_stairs, Blocks.rail, Blocks.red_flower, Blocks.red_mushroom, Blocks.red_mushroom_block, Blocks.redstone_block, Blocks.redstone_lamp, Blocks.redstone_ore, Blocks.redstone_torch, Blocks.redstone_torch, Blocks.redstone_wire, Blocks.reeds, Blocks.sand, Blocks.sandstone, Blocks.sandstone_stairs, Blocks.sapling, Blocks.skull, Blocks.snow, Blocks.snow_layer, Blocks.soul_sand, Blocks.sponge, Blocks.spruce_stairs, Blocks.stained_glass, Blocks.stained_glass_pane, Blocks.stained_hardened_clay, Blocks.standing_sign, Blocks.sticky_piston, Blocks.stone, Blocks.stone_brick_stairs, Blocks.stone_button, Blocks.stone_pressure_plate, Blocks.stone_slab, Blocks.stone_stairs, Blocks.stonebrick, Blocks.tallgrass, Blocks.tnt, Blocks.torch, Blocks.trapdoor, Blocks.trapped_chest, Blocks.tripwire, Blocks.tripwire_hook, Blocks.unlit_redstone_torch, Blocks.unpowered_comparator, Blocks.unpowered_repeater, Blocks.vine, Blocks.wall_sign, Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.wooden_button, Blocks.wooden_door, Blocks.wooden_pressure_plate, Blocks.wooden_slab, Blocks.wool, Blocks.yellow_flower});
	
	public ItemBlackholiumPaxel(ToolMaterial material, String name){
		super(3, material, blocks);
		setUnlocalizedName(UniverseCraft.MODID + "_" + "BlackholiumPaxel");
		setTextureName(UniverseCraft.MODID + ":" + "BlackholiumPaxel");
		setCreativeTab(UniverseCraft.universeCraft);
		setHarvestLevel("pickaxe", 100);
		setHarvestLevel("axe", 100);
		setHarvestLevel("shovel", 100);
	}
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta){
		return 100000000000000F;
	}
}
