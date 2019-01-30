package com.polypenguin.crayon.engine.utils;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.core.gui.CrayonInterface;

import com.polypenguin.crayon.engine.utils.miscellaneous.MaterialSet;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class InterfaceUtils {

    public static Inventory getCrayonMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Main Menu", CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getWandItem());
        gui.setItem(11, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Position Selection"));

        return gui;
    }

    public static Inventory getWandMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Wand Menu", CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Position Selection"));
        gui.setItem(11, ItemUtils.getItem(Material.MAGMA_CREAM, 1, ChatColor.AQUA + "Generate"));
        gui.setItem(12, ItemUtils.getItem(Material.SHEARS, 1, ChatColor.AQUA + "Operations"));

        return gui;
    }

    public static Inventory getPositionMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Selection Mode", CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Single-Position Selection"));
        gui.setItem(11, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Double-Position Selection"));
        gui.setItem(12, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Multi-Position Selection"));
        gui.setItem(13, ItemUtils.getItem(Material.BONE_MEAL, 1, ChatColor.AQUA + "Reset Selection Mode"));
        gui.setItem(14, ItemUtils.getItem(Material.FEATHER, 1, ChatColor.AQUA + "Reset Selected Positions"));
        gui.setItem(15, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Reset Mode & Positions"));

        return gui;
    }

    public static Inventory getGenerateMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Generate Menu", CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Cubed Shapes"));
        gui.setItem(11, ItemUtils.getSkullItem(1, "Drink", ChatColor.AQUA + "Round Shapes"));
        gui.setItem(12, ItemUtils.getItem(Material.STICK, 1, ChatColor.AQUA + "Fill"));

        return gui;
    }

    public static Inventory getOperationsMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Operations Menu", CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Copy Selection"));
        gui.setItem(11, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Paste Selection"));
        gui.setItem(12, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Rotate Selection"));
        gui.setItem(13, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Flip Selection"));
        gui.setItem(14, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Blueprint Selection"));

        return gui;
    }

    /**
     * This method is re-used from an old Pencil Version
     *
     * @return
     */
    public static MaterialSet getMaterialsInterface() {
        Inventory stone = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Stone Materials", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        stone.setItem(45, ItemUtils.getPreviousPageItem());
        stone.setItem(53, ItemUtils.getNextPageItem());
        stone.setItem(0, ItemUtils.getItem(Material.STONE, 1, ChatColor.AQUA + "Stone", new String[0]));
        stone.setItem(1, ItemUtils.getItem(Material.GRANITE, 1, ChatColor.AQUA + "Granite", new String[0]));
        stone.setItem(2, ItemUtils.getItem(Material.DIORITE, 1, ChatColor.AQUA + "Diorite", new String[0]));
        stone.setItem(3, ItemUtils.getItem(Material.ANDESITE, 1, ChatColor.AQUA + "Andesite", new String[0]));
        stone.setItem(4, ItemUtils.getItem(Material.COBBLESTONE, 1, ChatColor.AQUA + "Cobblestone", new String[0]));
        stone.setItem(5, ItemUtils.getItem(Material.MOSSY_COBBLESTONE, 1, ChatColor.AQUA + "Mossy Cobblestone", new String[0]));
        stone.setItem(6, ItemUtils.getItem(Material.BEDROCK, 1, ChatColor.AQUA + "Bedrock", new String[0]));
        stone.setItem(7, ItemUtils.getItem(Material.BRICKS, 1, ChatColor.AQUA + "Brick", new String[0]));

        stone.setItem(9, ItemUtils.getItem(Material.POLISHED_GRANITE, 1, ChatColor.AQUA + "Polished Granite", new String[0]));
        stone.setItem(10, ItemUtils.getItem(Material.POLISHED_DIORITE, 1, ChatColor.AQUA + "Polished Diorite", new String[0]));
        stone.setItem(11, ItemUtils.getItem(Material.POLISHED_ANDESITE, 1, ChatColor.AQUA + "Polished Andesite", new String[0]));
        stone.setItem(12, ItemUtils.getItem(Material.STONE_BRICKS, 1, ChatColor.AQUA + "Stone Bricks", new String[0]));
        stone.setItem(13, ItemUtils.getItem(Material.MOSSY_STONE_BRICKS, 1, ChatColor.AQUA + "Mossy Stone Bricks", new String[0]));
        stone.setItem(14, ItemUtils.getItem(Material.CRACKED_STONE_BRICKS, 1, ChatColor.AQUA + "Cracked Stone Bricks", new String[0]));
        stone.setItem(15, ItemUtils.getItem(Material.CHISELED_STONE_BRICKS, 1, ChatColor.AQUA + "Chiseled Stone Bricks", new String[0]));

        stone.setItem(18, ItemUtils.getItem(Material.INFESTED_STONE, 1, ChatColor.AQUA + "Infested Stone", new String[0]));
        stone.setItem(19, ItemUtils.getItem(Material.INFESTED_COBBLESTONE, 1, ChatColor.AQUA + "Infested Cobblestone", new String[0]));
        stone.setItem(20, ItemUtils.getItem(Material.INFESTED_STONE_BRICKS, 1, ChatColor.AQUA + "Infested Stone Bricks", new String[0]));
        stone.setItem(21, ItemUtils.getItem(Material.INFESTED_MOSSY_STONE_BRICKS, 1, ChatColor.AQUA + "Infested Mossy Stone Bricks", new String[0]));
        stone.setItem(22, ItemUtils.getItem(Material.INFESTED_CRACKED_STONE_BRICKS, 1, ChatColor.AQUA + "Infested Cracked Stone Bricks", new String[0]));
        stone.setItem(23, ItemUtils.getItem(Material.INFESTED_CHISELED_STONE_BRICKS, 1, ChatColor.AQUA + "Infested Chiseled Stone Bricks", new String[0]));
        stone.setItem(24, ItemUtils.getItem(Material.PRISMARINE, 1, ChatColor.AQUA + "Prismarine", new String[0]));
        stone.setItem(25, ItemUtils.getItem(Material.PRISMARINE_BRICKS, 1, ChatColor.AQUA + "Prismarine Bricks", new String[0]));
        stone.setItem(26, ItemUtils.getItem(Material.DARK_PRISMARINE, 1, ChatColor.AQUA + "Dark Prismarine", new String[0]));

        stone.setItem(27, ItemUtils.getItem(Material.OBSIDIAN, 1, ChatColor.AQUA + "Obsidian", new String[0]));
        stone.setItem(28, ItemUtils.getItem(Material.GLOWSTONE, 1, ChatColor.AQUA + "Glowstone", new String[0]));
        stone.setItem(29, ItemUtils.getItem(Material.NETHERRACK, 1, ChatColor.AQUA + "Netherrack", new String[0]));
        stone.setItem(30, ItemUtils.getItem(Material.NETHER_BRICKS, 1, ChatColor.AQUA + "Nether Brick", new String[0]));
        stone.setItem(31, ItemUtils.getItem(Material.RED_NETHER_BRICKS, 1, ChatColor.AQUA + "Red Nether Bricks", new String[0]));
        stone.setItem(32, ItemUtils.getItem(Material.NETHER_WART_BLOCK, 1, ChatColor.AQUA + "Nether Wart Block", new String[0]));
        stone.setItem(33, ItemUtils.getItem(Material.SOUL_SAND, 1, ChatColor.AQUA + "Soul Sand", new String[0]));
        stone.setItem(34, ItemUtils.getItem(Material.MAGMA_BLOCK, 1, ChatColor.AQUA + "Magma Block", new String[0]));

        stone.setItem(36, ItemUtils.getItem(Material.END_STONE, 1, ChatColor.AQUA + "End Stone", new String[0]));
        stone.setItem(37, ItemUtils.getItem(Material.END_STONE_BRICKS, 1, ChatColor.AQUA + "End Stone Bricks", new String[0]));
        stone.setItem(38, ItemUtils.getItem(Material.PURPUR_BLOCK, 1, ChatColor.AQUA + "Purpur Block", new String[0]));
        stone.setItem(39, ItemUtils.getItem(Material.PURPUR_PILLAR, 1, ChatColor.AQUA + "Purpur Pillar", new String[0]));
        stone.setItem(40, ItemUtils.getItem(Material.QUARTZ_BLOCK, 1, ChatColor.AQUA + "Quartz Block", new String[0]));
        stone.setItem(41, ItemUtils.getItem(Material.QUARTZ_PILLAR, 1, ChatColor.AQUA + "Quartz Pillar", new String[0]));
        stone.setItem(42, ItemUtils.getItem(Material.CHISELED_QUARTZ_BLOCK, 1, ChatColor.AQUA + "Chiseled Quartz Block", new String[0]));
        stone.setItem(43, ItemUtils.getItem(Material.SMOOTH_QUARTZ, 1, ChatColor.AQUA + "Smooth Quartz", new String[0]));
        stone.setItem(44, ItemUtils.getItem(Material.BONE_BLOCK, 1, ChatColor.AQUA + "Bone Block", new String[0]));

        Inventory natural = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Natural Materials", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        natural.setItem(45, ItemUtils.getPreviousPageItem());
        natural.setItem(53, ItemUtils.getNextPageItem());
        natural.setItem(0, ItemUtils.getItem(Material.SAND, 1, ChatColor.AQUA + "Sand", new String[0]));
        natural.setItem(1, ItemUtils.getItem(Material.SANDSTONE, 1, ChatColor.AQUA + "Sandstone", new String[0]));
        natural.setItem(2, ItemUtils.getItem(Material.SMOOTH_SANDSTONE, 1, ChatColor.AQUA + "Smooth Sandstone", new String[0]));
        natural.setItem(3, ItemUtils.getItem(Material.CHISELED_SANDSTONE, 1, ChatColor.AQUA + "Chiseled Sandstone", new String[0]));
        natural.setItem(4, ItemUtils.getItem(Material.RED_SAND, 1, ChatColor.AQUA + "Red Sand", new String[0]));
        natural.setItem(5, ItemUtils.getItem(Material.RED_SANDSTONE, 1, ChatColor.AQUA + "Red Sandstone", new String[0]));
        natural.setItem(6, ItemUtils.getItem(Material.SMOOTH_RED_SANDSTONE, 1, ChatColor.AQUA + "Smooth Red Sandstone", new String[0]));
        natural.setItem(7, ItemUtils.getItem(Material.CHISELED_RED_SANDSTONE, 1, ChatColor.AQUA + "Chiseled Red Sandstone", new String[0]));
        natural.setItem(8, ItemUtils.getItem(Material.SNOW, 1, ChatColor.AQUA + "Snow", new String[0]));

        natural.setItem(9, ItemUtils.getItem(Material.DIRT, 1, ChatColor.AQUA + "Dirt", new String[0]));
        natural.setItem(10, ItemUtils.getItem(Material.FARMLAND, 1, ChatColor.AQUA + "Farmland", new String[0]));
        natural.setItem(11, ItemUtils.getItem(Material.COARSE_DIRT, 1, ChatColor.AQUA + "Coarse Dirt", new String[0]));
        natural.setItem(12, ItemUtils.getItem(Material.GRASS_BLOCK, 1, ChatColor.AQUA + "Grass Block", new String[0]));
        natural.setItem(13, ItemUtils.getItem(Material.GRASS_PATH, 1, ChatColor.AQUA + "Grass Path", new String[0]));
        natural.setItem(14, ItemUtils.getItem(Material.MYCELIUM, 1, ChatColor.AQUA + "Mycelium", new String[0]));
        natural.setItem(15, ItemUtils.getItem(Material.PODZOL, 1, ChatColor.AQUA + "Podzol", new String[0]));
        natural.setItem(16, ItemUtils.getItem(Material.SPONGE, 1, ChatColor.AQUA + "Sponge", new String[0]));
        natural.setItem(17, ItemUtils.getItem(Material.WET_SPONGE, 1, ChatColor.AQUA + "Wet Sponge", new String[0]));

        natural.setItem(18, ItemUtils.getItem(Material.ICE, 1, ChatColor.AQUA + "Ice", new String[0]));
        natural.setItem(19, ItemUtils.getItem(Material.PACKED_ICE, 1, ChatColor.AQUA + "Packed Ice", new String[0]));
        natural.setItem(20, ItemUtils.getItem(Material.BLUE_ICE, 1, ChatColor.AQUA + "Blue Ice", new String[0]));
        natural.setItem(21, ItemUtils.getItem(Material.COBWEB, 1, ChatColor.AQUA + "COBWEB", new String[0]));
        natural.setItem(22, ItemUtils.getItem(Material.COBBLESTONE_WALL, 1, ChatColor.AQUA + "Cobblestone Wall", new String[0]));
        natural.setItem(23, ItemUtils.getItem(Material.MOSSY_COBBLESTONE_WALL, 1, ChatColor.AQUA + "Mossy Cobblestone Wall", new String[0]));
        natural.setItem(24, ItemUtils.getItem(Material.SLIME_BLOCK, 1, ChatColor.AQUA + "Slime Block", new String[0]));

        natural.setItem(27, ItemUtils.getItem(Material.ACACIA_LEAVES, 1, ChatColor.AQUA + "Acacia Leaves", new String[0]));
        natural.setItem(28, ItemUtils.getItem(Material.BIRCH_LEAVES, 1, ChatColor.AQUA + "Birch Leaves", new String[0]));
        natural.setItem(29, ItemUtils.getItem(Material.DARK_OAK_LEAVES, 1, ChatColor.AQUA + "Dark Oak Leaves", new String[0]));
        natural.setItem(30, ItemUtils.getItem(Material.JUNGLE_LEAVES, 1, ChatColor.AQUA + "Jungle Leaves", new String[0]));
        natural.setItem(31, ItemUtils.getItem(Material.OAK_LEAVES, 1, ChatColor.AQUA + "Oak Leaves", new String[0]));
        natural.setItem(32, ItemUtils.getItem(Material.SPRUCE_LEAVES, 1, ChatColor.AQUA + "Spruce Leaves", new String[0]));
        natural.setItem(33, ItemUtils.getItem(Material.RED_MUSHROOM_BLOCK, 1, ChatColor.AQUA + "Red Mushroom Block", new String[0]));
        natural.setItem(34, ItemUtils.getItem(Material.BROWN_MUSHROOM_BLOCK, 1, ChatColor.AQUA + "Brown Mushroom Block", new String[0]));
        natural.setItem(35, ItemUtils.getItem(Material.MUSHROOM_STEM, 1, ChatColor.AQUA + "Mushroom Stem", new String[0]));

        natural.setItem(36, ItemUtils.getItem(Material.MELON, 1, ChatColor.AQUA + "Melon", new String[0]));
        natural.setItem(37, ItemUtils.getItem(Material.PUMPKIN, 1, ChatColor.AQUA + "Pumpkin", new String[0]));
        natural.setItem(38, ItemUtils.getItem(Material.CARVED_PUMPKIN, 1, ChatColor.AQUA + "Carved Pumpkin", new String[0]));
        natural.setItem(39, ItemUtils.getItem(Material.JACK_O_LANTERN, 1, ChatColor.AQUA + "Jack O Lantern", new String[0]));

        Inventory wood = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Wooden Materials", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        wood.setItem(45, ItemUtils.getPreviousPageItem());
        wood.setItem(53, ItemUtils.getNextPageItem());
        wood.setItem(0, ItemUtils.getItem(Material.ACACIA_LOG, 1, ChatColor.AQUA + "Acacia Log", new String[0]));
        wood.setItem(1, ItemUtils.getItem(Material.BIRCH_LOG, 1, ChatColor.AQUA + "Birch Log", new String[0]));
        wood.setItem(2, ItemUtils.getItem(Material.DARK_OAK_LOG, 1, ChatColor.AQUA + "Dark Oak Log", new String[0]));
        wood.setItem(3, ItemUtils.getItem(Material.JUNGLE_LOG, 1, ChatColor.AQUA + "Jungle Log", new String[0]));
        wood.setItem(4, ItemUtils.getItem(Material.OAK_LOG, 1, ChatColor.AQUA + "Oak Log", new String[0]));
        wood.setItem(5, ItemUtils.getItem(Material.SPRUCE_LOG, 1, ChatColor.AQUA + "Spruce Log", new String[0]));

        wood.setItem(9, ItemUtils.getItem(Material.STRIPPED_ACACIA_LOG, 1, ChatColor.AQUA + "Stripped Acacia Log", new String[0]));
        wood.setItem(10, ItemUtils.getItem(Material.STRIPPED_BIRCH_LOG, 1, ChatColor.AQUA + "Stripped Birch Log", new String[0]));
        wood.setItem(11, ItemUtils.getItem(Material.STRIPPED_DARK_OAK_LOG, 1, ChatColor.AQUA + "Stripped Dark Oak Log", new String[0]));
        wood.setItem(12, ItemUtils.getItem(Material.STRIPPED_JUNGLE_LOG, 1, ChatColor.AQUA + "Stripped Jungle Log", new String[0]));
        wood.setItem(13, ItemUtils.getItem(Material.STRIPPED_OAK_LOG, 1, ChatColor.AQUA + "Stripped Oak Log", new String[0]));
        wood.setItem(14, ItemUtils.getItem(Material.STRIPPED_SPRUCE_LOG, 1, ChatColor.AQUA + "Stripped Spruce Log", new String[0]));

        wood.setItem(18, ItemUtils.getItem(Material.ACACIA_PLANKS, 1, ChatColor.AQUA + "Acacia Planks", new String[0]));
        wood.setItem(19, ItemUtils.getItem(Material.BIRCH_PLANKS, 1, ChatColor.AQUA + "Birch Planks", new String[0]));
        wood.setItem(20, ItemUtils.getItem(Material.DARK_OAK_PLANKS, 1, ChatColor.AQUA + "Dark Oak Planks", new String[0]));
        wood.setItem(21, ItemUtils.getItem(Material.JUNGLE_PLANKS, 1, ChatColor.AQUA + "Jungle Planks", new String[0]));
        wood.setItem(22, ItemUtils.getItem(Material.OAK_PLANKS, 1, ChatColor.AQUA + "Oak Planks", new String[0]));
        wood.setItem(23, ItemUtils.getItem(Material.SPRUCE_PLANKS, 1, ChatColor.AQUA + "Spruce Planks", new String[0]));

        wood.setItem(27, ItemUtils.getItem(Material.ACACIA_FENCE, 1, ChatColor.AQUA + "Acacia Fence", new String[0]));
        wood.setItem(28, ItemUtils.getItem(Material.BIRCH_FENCE, 1, ChatColor.AQUA + "Birch Fence", new String[0]));
        wood.setItem(29, ItemUtils.getItem(Material.DARK_OAK_FENCE, 1, ChatColor.AQUA + "Dark Oak Fence", new String[0]));
        wood.setItem(30, ItemUtils.getItem(Material.JUNGLE_FENCE, 1, ChatColor.AQUA + "Jungle Fence", new String[0]));
        wood.setItem(31, ItemUtils.getItem(Material.OAK_FENCE, 1, ChatColor.AQUA + "Oak Fence", new String[0]));
        wood.setItem(32, ItemUtils.getItem(Material.SPRUCE_FENCE, 1, ChatColor.AQUA + "Spruce Fence", new String[0]));

        wood.setItem(36, ItemUtils.getItem(Material.ACACIA_FENCE_GATE, 1, ChatColor.AQUA + "Acacia Fence Gate", new String[0]));
        wood.setItem(37, ItemUtils.getItem(Material.BIRCH_FENCE_GATE, 1, ChatColor.AQUA + "Birch Fence Gate", new String[0]));
        wood.setItem(38, ItemUtils.getItem(Material.DARK_OAK_FENCE_GATE, 1, ChatColor.AQUA + "Dark Oak Fence Gate", new String[0]));
        wood.setItem(39, ItemUtils.getItem(Material.JUNGLE_FENCE_GATE, 1, ChatColor.AQUA + "Jungle Fence Gate", new String[0]));
        wood.setItem(40, ItemUtils.getItem(Material.OAK_FENCE_GATE, 1, ChatColor.AQUA + "Oak Fence Gate", new String[0]));
        wood.setItem(41, ItemUtils.getItem(Material.SPRUCE_FENCE_GATE, 1, ChatColor.AQUA + "Spruce Fence Gate", new String[0]));

        Inventory slab = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Slab & Stair Materials", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        slab.setItem(45, ItemUtils.getPreviousPageItem());
        slab.setItem(53, ItemUtils.getNextPageItem());
        slab.setItem(0, ItemUtils.getItem(Material.STONE_SLAB, 1, ChatColor.AQUA + "Stone Slab", new String[0]));
        slab.setItem(1, ItemUtils.getItem(Material.COBBLESTONE_SLAB, 1, ChatColor.AQUA + "Cobblestone Slab", new String[0]));
        slab.setItem(2, ItemUtils.getItem(Material.STONE_BRICK_SLAB, 1, ChatColor.AQUA + "Stone Brick Slab", new String[0]));
        slab.setItem(3, ItemUtils.getItem(Material.NETHER_BRICK_SLAB, 1, ChatColor.AQUA + "Nether Brick Slab", new String[0]));
        slab.setItem(4, ItemUtils.getItem(Material.QUARTZ_SLAB, 1, ChatColor.AQUA + "Quartz Slab", new String[0]));
        slab.setItem(5, ItemUtils.getItem(Material.SANDSTONE_SLAB, 1, ChatColor.AQUA + "Sandstone Slab", new String[0]));
        slab.setItem(6, ItemUtils.getItem(Material.RED_SANDSTONE_SLAB, 1, ChatColor.AQUA + "Red Sandstone Slab", new String[0]));
        slab.setItem(7, ItemUtils.getItem(Material.PURPUR_SLAB, 1, ChatColor.AQUA + "Purpur Slab", new String[0]));
        slab.setItem(8, ItemUtils.getItem(Material.BRICK_SLAB, 1, ChatColor.AQUA + "Brick Slab", new String[0]));

        slab.setItem(9, ItemUtils.getItem(Material.ACACIA_SLAB, 1, ChatColor.AQUA + "Acacia Slab", new String[0]));
        slab.setItem(10, ItemUtils.getItem(Material.BIRCH_SLAB, 1, ChatColor.AQUA + "Birch Slab", new String[0]));
        slab.setItem(11, ItemUtils.getItem(Material.DARK_OAK_SLAB, 1, ChatColor.AQUA + "Dark Oak Slab", new String[0]));
        slab.setItem(12, ItemUtils.getItem(Material.JUNGLE_SLAB, 1, ChatColor.AQUA + "Jungle Slab", new String[0]));
        slab.setItem(13, ItemUtils.getItem(Material.OAK_SLAB, 1, ChatColor.AQUA + "Oak Slab", new String[0]));
        slab.setItem(14, ItemUtils.getItem(Material.SPRUCE_SLAB, 1, ChatColor.AQUA + "Spruce Slab", new String[0]));

        slab.setItem(18, ItemUtils.getItem(Material.COBBLESTONE_STAIRS, 1, ChatColor.AQUA + "Cobblestone Stairs", new String[0]));
        slab.setItem(19, ItemUtils.getItem(Material.STONE_BRICK_STAIRS, 1, ChatColor.AQUA + "Stone Brick Stairs", new String[0]));
        slab.setItem(20, ItemUtils.getItem(Material.NETHER_BRICK_STAIRS, 1, ChatColor.AQUA + "Nether Brick Stairs", new String[0]));
        slab.setItem(21, ItemUtils.getItem(Material.QUARTZ_STAIRS, 1, ChatColor.AQUA + "Quartz Stairs", new String[0]));
        slab.setItem(22, ItemUtils.getItem(Material.SANDSTONE_STAIRS, 1, ChatColor.AQUA + "Sandstone Stairs", new String[0]));
        slab.setItem(23, ItemUtils.getItem(Material.RED_SANDSTONE_STAIRS, 1, ChatColor.AQUA + "Red Sandstone Stairs", new String[0]));
        slab.setItem(24, ItemUtils.getItem(Material.PURPUR_STAIRS, 1, ChatColor.AQUA + "Purpur Stairs", new String[0]));
        slab.setItem(25, ItemUtils.getItem(Material.BRICK_STAIRS, 1, ChatColor.AQUA + "Brick Stairs", new String[0]));

        slab.setItem(27, ItemUtils.getItem(Material.ACACIA_STAIRS, 1, ChatColor.AQUA + "Acacia Stairs", new String[0]));
        slab.setItem(28, ItemUtils.getItem(Material.BIRCH_STAIRS, 1, ChatColor.AQUA + "Birch Stairs", new String[0]));
        slab.setItem(29, ItemUtils.getItem(Material.DARK_OAK_STAIRS, 1, ChatColor.AQUA + "Dark Oak Stairs", new String[0]));
        slab.setItem(30, ItemUtils.getItem(Material.JUNGLE_STAIRS, 1, ChatColor.AQUA + "Jungle Stairs", new String[0]));
        slab.setItem(31, ItemUtils.getItem(Material.OAK_STAIRS, 1, ChatColor.AQUA + "Oak Stairs", new String[0]));
        slab.setItem(32, ItemUtils.getItem(Material.SPRUCE_STAIRS, 1, ChatColor.AQUA + "Spruce Stairs", new String[0]));

        Inventory coloredItemsOne = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Colored Materials 1", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        coloredItemsOne.setItem(45, ItemUtils.getPreviousPageItem());
        coloredItemsOne.setItem(53, ItemUtils.getNextPageItem());
        coloredItemsOne.setItem(0, ItemUtils.getItem(Material.BLACK_STAINED_GLASS, 1, ChatColor.AQUA + "Black Stained Glass", new String[0]));
        coloredItemsOne.setItem(1, ItemUtils.getItem(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Black Stained Glass Pane", new String[0]));
        coloredItemsOne.setItem(2, ItemUtils.getItem(Material.BLACK_WOOL, 1, ChatColor.AQUA + "Black Wool", new String[0]));
        coloredItemsOne.setItem(3, ItemUtils.getItem(Material.BLACK_TERRACOTTA, 1, ChatColor.AQUA + "Black Terracotta", new String[0]));
        coloredItemsOne.setItem(4, ItemUtils.getItem(Material.BLACK_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Black Glazed Terracotta", new String[0]));
        coloredItemsOne.setItem(5, ItemUtils.getItem(Material.BLACK_CONCRETE_POWDER, 1, ChatColor.AQUA + "Black Concrete Powder", new String[0]));
        coloredItemsOne.setItem(6, ItemUtils.getItem(Material.BLACK_CONCRETE, 1, ChatColor.AQUA + "Black Concrete", new String[0]));

        coloredItemsOne.setItem(9, ItemUtils.getItem(Material.WHITE_STAINED_GLASS, 1, ChatColor.AQUA + "White Stained Glass", new String[0]));
        coloredItemsOne.setItem(10, ItemUtils.getItem(Material.WHITE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "White Stained Glass Pane", new String[0]));
        coloredItemsOne.setItem(11, ItemUtils.getItem(Material.WHITE_WOOL, 1, ChatColor.AQUA + "White Wool", new String[0]));
        coloredItemsOne.setItem(12, ItemUtils.getItem(Material.WHITE_TERRACOTTA, 1, ChatColor.AQUA + "White Terracotta", new String[0]));
        coloredItemsOne.setItem(13, ItemUtils.getItem(Material.WHITE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "White Glazed Terracotta", new String[0]));
        coloredItemsOne.setItem(14, ItemUtils.getItem(Material.WHITE_CONCRETE_POWDER, 1, ChatColor.AQUA + "White Concrete Powder", new String[0]));
        coloredItemsOne.setItem(15, ItemUtils.getItem(Material.WHITE_CONCRETE, 1, ChatColor.AQUA + "White Concrete", new String[0]));

        coloredItemsOne.setItem(18, ItemUtils.getItem(Material.RED_STAINED_GLASS, 1, ChatColor.AQUA + "Red Stained Glass", new String[0]));
        coloredItemsOne.setItem(19, ItemUtils.getItem(Material.RED_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Red Stained Glass Pane", new String[0]));
        coloredItemsOne.setItem(20, ItemUtils.getItem(Material.RED_WOOL, 1, ChatColor.AQUA + "Red Wool", new String[0]));
        coloredItemsOne.setItem(21, ItemUtils.getItem(Material.RED_TERRACOTTA, 1, ChatColor.AQUA + "Red Terracotta", new String[0]));
        coloredItemsOne.setItem(22, ItemUtils.getItem(Material.RED_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Red Glazed Terracotta", new String[0]));
        coloredItemsOne.setItem(23, ItemUtils.getItem(Material.RED_CONCRETE_POWDER, 1, ChatColor.AQUA + "Red Concrete Powder", new String[0]));
        coloredItemsOne.setItem(24, ItemUtils.getItem(Material.RED_CONCRETE, 1, ChatColor.AQUA + "Red Concrete", new String[0]));

        coloredItemsOne.setItem(27, ItemUtils.getItem(Material.ORANGE_STAINED_GLASS, 1, ChatColor.AQUA + "Orange Stained Glass", new String[0]));
        coloredItemsOne.setItem(28, ItemUtils.getItem(Material.ORANGE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Orange Stained Glass Pane", new String[0]));
        coloredItemsOne.setItem(29, ItemUtils.getItem(Material.ORANGE_WOOL, 1, ChatColor.AQUA + "Orange Wool", new String[0]));
        coloredItemsOne.setItem(30, ItemUtils.getItem(Material.ORANGE_TERRACOTTA, 1, ChatColor.AQUA + "Orange Terracotta", new String[0]));
        coloredItemsOne.setItem(31, ItemUtils.getItem(Material.ORANGE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Orange Glazed Terracotta", new String[0]));
        coloredItemsOne.setItem(32, ItemUtils.getItem(Material.ORANGE_CONCRETE_POWDER, 1, ChatColor.AQUA + "Orange Concrete Powder", new String[0]));
        coloredItemsOne.setItem(33, ItemUtils.getItem(Material.ORANGE_CONCRETE, 1, ChatColor.AQUA + "Orange Concrete", new String[0]));

        coloredItemsOne.setItem(36, ItemUtils.getItem(Material.YELLOW_STAINED_GLASS, 1, ChatColor.AQUA + "Yellow Stained Glass", new String[0]));
        coloredItemsOne.setItem(37, ItemUtils.getItem(Material.YELLOW_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Yellow Stained Glass Pane", new String[0]));
        coloredItemsOne.setItem(38, ItemUtils.getItem(Material.YELLOW_WOOL, 1, ChatColor.AQUA + "Yellow Wool", new String[0]));
        coloredItemsOne.setItem(39, ItemUtils.getItem(Material.YELLOW_TERRACOTTA, 1, ChatColor.AQUA + "Yellow Terracotta", new String[0]));
        coloredItemsOne.setItem(40, ItemUtils.getItem(Material.YELLOW_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Yellow Glazed Terracotta", new String[0]));
        coloredItemsOne.setItem(41, ItemUtils.getItem(Material.YELLOW_CONCRETE_POWDER, 1, ChatColor.AQUA + "Yellow Concrete Powder", new String[0]));
        coloredItemsOne.setItem(42, ItemUtils.getItem(Material.YELLOW_CONCRETE, 1, ChatColor.AQUA + "Yellow Concrete", new String[0]));

        Inventory coloredItemsTwo = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Colored Materials 2", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        coloredItemsTwo.setItem(45, ItemUtils.getPreviousPageItem());
        coloredItemsTwo.setItem(53, ItemUtils.getNextPageItem());
        coloredItemsTwo.setItem(0, ItemUtils.getItem(Material.LIME_STAINED_GLASS, 1, ChatColor.AQUA + "Lime Stained Glass", new String[0]));
        coloredItemsTwo.setItem(1, ItemUtils.getItem(Material.LIME_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Lime Stained Glass Pane", new String[0]));
        coloredItemsTwo.setItem(2, ItemUtils.getItem(Material.LIME_WOOL, 1, ChatColor.AQUA + "Lime Wool", new String[0]));
        coloredItemsTwo.setItem(3, ItemUtils.getItem(Material.LIME_TERRACOTTA, 1, ChatColor.AQUA + "Lime Terracotta", new String[0]));
        coloredItemsTwo.setItem(4, ItemUtils.getItem(Material.LIME_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Lime Glazed Terracotta", new String[0]));
        coloredItemsTwo.setItem(5, ItemUtils.getItem(Material.LIME_CONCRETE_POWDER, 1, ChatColor.AQUA + "Lime Concrete Powder", new String[0]));
        coloredItemsTwo.setItem(6, ItemUtils.getItem(Material.LIME_CONCRETE, 1, ChatColor.AQUA + "Lime Concrete", new String[0]));

        coloredItemsTwo.setItem(9, ItemUtils.getItem(Material.GREEN_STAINED_GLASS, 1, ChatColor.AQUA + "Green Stained Glass", new String[0]));
        coloredItemsTwo.setItem(10, ItemUtils.getItem(Material.GREEN_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Green Stained Glass Pane", new String[0]));
        coloredItemsTwo.setItem(11, ItemUtils.getItem(Material.GREEN_WOOL, 1, ChatColor.AQUA + "Green Wool", new String[0]));
        coloredItemsTwo.setItem(12, ItemUtils.getItem(Material.GREEN_TERRACOTTA, 1, ChatColor.AQUA + "Green Terracotta", new String[0]));
        coloredItemsTwo.setItem(13, ItemUtils.getItem(Material.GREEN_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Green Glazed Terracotta", new String[0]));
        coloredItemsTwo.setItem(14, ItemUtils.getItem(Material.GREEN_CONCRETE_POWDER, 1, ChatColor.AQUA + "Green Concrete Powder", new String[0]));
        coloredItemsTwo.setItem(15, ItemUtils.getItem(Material.GREEN_CONCRETE, 1, ChatColor.AQUA + "Green Concrete", new String[0]));

        coloredItemsTwo.setItem(18, ItemUtils.getItem(Material.CYAN_STAINED_GLASS, 1, ChatColor.AQUA + "Cyan Stained Glass", new String[0]));
        coloredItemsTwo.setItem(19, ItemUtils.getItem(Material.CYAN_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Cyan Stained Glass Pane", new String[0]));
        coloredItemsTwo.setItem(20, ItemUtils.getItem(Material.CYAN_WOOL, 1, ChatColor.AQUA + "Cyan Wool", new String[0]));
        coloredItemsTwo.setItem(21, ItemUtils.getItem(Material.CYAN_TERRACOTTA, 1, ChatColor.AQUA + "Cyan Terracotta", new String[0]));
        coloredItemsTwo.setItem(22, ItemUtils.getItem(Material.CYAN_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Cyan Glazed Terracotta", new String[0]));
        coloredItemsTwo.setItem(23, ItemUtils.getItem(Material.CYAN_CONCRETE_POWDER, 1, ChatColor.AQUA + "Cyan Concrete Powder", new String[0]));
        coloredItemsTwo.setItem(24, ItemUtils.getItem(Material.CYAN_CONCRETE, 1, ChatColor.AQUA + "Cyan Concrete", new String[0]));

        coloredItemsTwo.setItem(27, ItemUtils.getItem(Material.LIGHT_BLUE_STAINED_GLASS, 1, ChatColor.AQUA + "Light Blue Stained Glass", new String[0]));
        coloredItemsTwo.setItem(28, ItemUtils.getItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Light Blue Stained Glass Pane", new String[0]));
        coloredItemsTwo.setItem(29, ItemUtils.getItem(Material.LIGHT_BLUE_WOOL, 1, ChatColor.AQUA + "Light Blue Wool", new String[0]));
        coloredItemsTwo.setItem(30, ItemUtils.getItem(Material.LIGHT_BLUE_TERRACOTTA, 1, ChatColor.AQUA + "Light Blue Terracotta", new String[0]));
        coloredItemsTwo.setItem(31, ItemUtils.getItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Light Blue Glazed Terracotta", new String[0]));
        coloredItemsTwo.setItem(32, ItemUtils.getItem(Material.LIGHT_BLUE_CONCRETE_POWDER, 1, ChatColor.AQUA + "Light Blue Concrete Powder", new String[0]));
        coloredItemsTwo.setItem(33, ItemUtils.getItem(Material.LIGHT_BLUE_CONCRETE, 1, ChatColor.AQUA + "Light Blue Concrete", new String[0]));

        coloredItemsTwo.setItem(36, ItemUtils.getItem(Material.BLUE_STAINED_GLASS, 1, ChatColor.AQUA + "Blue Stained Glass", new String[0]));
        coloredItemsTwo.setItem(37, ItemUtils.getItem(Material.BLUE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Blue Stained Glass Pane", new String[0]));
        coloredItemsTwo.setItem(38, ItemUtils.getItem(Material.BLUE_WOOL, 1, ChatColor.AQUA + "Blue Wool", new String[0]));
        coloredItemsTwo.setItem(39, ItemUtils.getItem(Material.BLUE_TERRACOTTA, 1, ChatColor.AQUA + "Blue Terracotta", new String[0]));
        coloredItemsTwo.setItem(40, ItemUtils.getItem(Material.BLUE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Blue Glazed Terracotta", new String[0]));
        coloredItemsTwo.setItem(41, ItemUtils.getItem(Material.BLUE_CONCRETE_POWDER, 1, ChatColor.AQUA + "Blue Concrete Powder", new String[0]));
        coloredItemsTwo.setItem(42, ItemUtils.getItem(Material.BLUE_CONCRETE, 1, ChatColor.AQUA + "Blue Concrete", new String[0]));

        Inventory coloredItemsThree = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Colored Materials 3", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        coloredItemsThree.setItem(45, ItemUtils.getPreviousPageItem());
        coloredItemsThree.setItem(53, ItemUtils.getNextPageItem());
        coloredItemsThree.setItem(0, ItemUtils.getItem(Material.MAGENTA_STAINED_GLASS, 1, ChatColor.AQUA + "Magenta Stained Glass", new String[0]));
        coloredItemsThree.setItem(1, ItemUtils.getItem(Material.MAGENTA_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Magenta Stained Glass Pane", new String[0]));
        coloredItemsThree.setItem(2, ItemUtils.getItem(Material.MAGENTA_WOOL, 1, ChatColor.AQUA + "Magenta Wool", new String[0]));
        coloredItemsThree.setItem(3, ItemUtils.getItem(Material.MAGENTA_TERRACOTTA, 1, ChatColor.AQUA + "Magenta Terracotta", new String[0]));
        coloredItemsThree.setItem(4, ItemUtils.getItem(Material.MAGENTA_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Magenta Glazed Terracotta", new String[0]));
        coloredItemsThree.setItem(5, ItemUtils.getItem(Material.MAGENTA_CONCRETE_POWDER, 1, ChatColor.AQUA + "Magenta Concrete Powder", new String[0]));
        coloredItemsThree.setItem(6, ItemUtils.getItem(Material.MAGENTA_CONCRETE, 1, ChatColor.AQUA + "Magenta Concrete", new String[0]));

        coloredItemsThree.setItem(9, ItemUtils.getItem(Material.PURPLE_STAINED_GLASS, 1, ChatColor.AQUA + "Purple Stained Glass", new String[0]));
        coloredItemsThree.setItem(10, ItemUtils.getItem(Material.PURPLE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Purple Stained Glass Pane", new String[0]));
        coloredItemsThree.setItem(11, ItemUtils.getItem(Material.PURPLE_WOOL, 1, ChatColor.AQUA + "Purple Wool", new String[0]));
        coloredItemsThree.setItem(12, ItemUtils.getItem(Material.PURPLE_TERRACOTTA, 1, ChatColor.AQUA + "Purple Terracotta", new String[0]));
        coloredItemsThree.setItem(13, ItemUtils.getItem(Material.PURPLE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Purple Glazed Terracotta", new String[0]));
        coloredItemsThree.setItem(14, ItemUtils.getItem(Material.PURPLE_CONCRETE_POWDER, 1, ChatColor.AQUA + "Purple Concrete Powder", new String[0]));
        coloredItemsThree.setItem(15, ItemUtils.getItem(Material.PURPLE_CONCRETE, 1, ChatColor.AQUA + "Purple Concrete", new String[0]));

        coloredItemsThree.setItem(18, ItemUtils.getItem(Material.PINK_STAINED_GLASS, 1, ChatColor.AQUA + "Pink Stained Glass", new String[0]));
        coloredItemsThree.setItem(19, ItemUtils.getItem(Material.PINK_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Pink Stained Glass Pane", new String[0]));
        coloredItemsThree.setItem(20, ItemUtils.getItem(Material.PINK_WOOL, 1, ChatColor.AQUA + "Pink Wool", new String[0]));
        coloredItemsThree.setItem(21, ItemUtils.getItem(Material.PINK_TERRACOTTA, 1, ChatColor.AQUA + "Pink Terracotta", new String[0]));
        coloredItemsThree.setItem(22, ItemUtils.getItem(Material.PINK_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Pink Glazed Terracotta", new String[0]));
        coloredItemsThree.setItem(23, ItemUtils.getItem(Material.PINK_CONCRETE_POWDER, 1, ChatColor.AQUA + "Pink Concrete Powder", new String[0]));
        coloredItemsThree.setItem(24, ItemUtils.getItem(Material.PINK_CONCRETE, 1, ChatColor.AQUA + "Pink Concrete", new String[0]));

        coloredItemsThree.setItem(27, ItemUtils.getItem(Material.LIGHT_GRAY_STAINED_GLASS, 1, ChatColor.AQUA + "Light Gray Stained Glass", new String[0]));
        coloredItemsThree.setItem(28, ItemUtils.getItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Light Gray Stained Glass Pane", new String[0]));
        coloredItemsThree.setItem(29, ItemUtils.getItem(Material.LIGHT_GRAY_WOOL, 1, ChatColor.AQUA + "Light Gray Wool", new String[0]));
        coloredItemsThree.setItem(30, ItemUtils.getItem(Material.LIGHT_GRAY_TERRACOTTA, 1, ChatColor.AQUA + "Light Gray Terracotta", new String[0]));
        coloredItemsThree.setItem(31, ItemUtils.getItem(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Light Gray Glazed Terracotta", new String[0]));
        coloredItemsThree.setItem(32, ItemUtils.getItem(Material.LIGHT_GRAY_CONCRETE_POWDER, 1, ChatColor.AQUA + "Light Gray Concrete Powder", new String[0]));
        coloredItemsThree.setItem(33, ItemUtils.getItem(Material.LIGHT_GRAY_CONCRETE, 1, ChatColor.AQUA + "Light Gray Concrete", new String[0]));

        coloredItemsThree.setItem(36, ItemUtils.getItem(Material.GRAY_STAINED_GLASS, 1, ChatColor.AQUA + "Gray Stained Glass", new String[0]));
        coloredItemsThree.setItem(37, ItemUtils.getItem(Material.GRAY_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Gray Stained Glass Pane", new String[0]));
        coloredItemsThree.setItem(38, ItemUtils.getItem(Material.GRAY_WOOL, 1, ChatColor.AQUA + "Gray Wool", new String[0]));
        coloredItemsThree.setItem(39, ItemUtils.getItem(Material.GRAY_TERRACOTTA, 1, ChatColor.AQUA + "Gray Terracotta", new String[0]));
        coloredItemsThree.setItem(40, ItemUtils.getItem(Material.GRAY_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Gray Glazed Terracotta", new String[0]));
        coloredItemsThree.setItem(41, ItemUtils.getItem(Material.GRAY_CONCRETE_POWDER, 1, ChatColor.AQUA + "Gray Concrete Powder", new String[0]));
        coloredItemsThree.setItem(42, ItemUtils.getItem(Material.GRAY_CONCRETE, 1, ChatColor.AQUA + "Gray Concrete", new String[0]));

        Inventory sea = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Sea Materials", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        sea.setItem(45, ItemUtils.getPreviousPageItem());
        sea.setItem(53, ItemUtils.getNextPageItem());
        sea.setItem(0, ItemUtils.getItem(Material.BRAIN_CORAL_BLOCK, 1, ChatColor.AQUA + "Brain Coral", new String[0]));
        sea.setItem(1, ItemUtils.getItem(Material.BUBBLE_CORAL_BLOCK, 1, ChatColor.AQUA + "Bubble Coral", new String[0]));
        sea.setItem(2, ItemUtils.getItem(Material.FIRE_CORAL_BLOCK, 1, ChatColor.AQUA + "Fire Coral", new String[0]));
        sea.setItem(3, ItemUtils.getItem(Material.HORN_CORAL_BLOCK, 1, ChatColor.AQUA + "Horn Coral", new String[0]));
        sea.setItem(4, ItemUtils.getItem(Material.TUBE_CORAL_BLOCK, 1, ChatColor.AQUA + "Tube Coral", new String[0]));

        sea.setItem(9, ItemUtils.getItem(Material.DEAD_BRAIN_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Brain Coral", new String[0]));
        sea.setItem(10, ItemUtils.getItem(Material.DEAD_BUBBLE_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Bubble Coral", new String[0]));
        sea.setItem(11, ItemUtils.getItem(Material.DEAD_FIRE_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Fire Coral", new String[0]));
        sea.setItem(12, ItemUtils.getItem(Material.DEAD_HORN_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Horn Coral", new String[0]));
        sea.setItem(13, ItemUtils.getItem(Material.DEAD_TUBE_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Tube Coral", new String[0]));

        sea.setItem(18, ItemUtils.getItem(Material.PRISMARINE_SLAB, 1, ChatColor.AQUA + "Prismarine Slab", new String[0]));
        sea.setItem(19, ItemUtils.getItem(Material.PRISMARINE_BRICK_SLAB, 1, ChatColor.AQUA + "Prismarine Brick Slab", new String[0]));
        sea.setItem(20, ItemUtils.getItem(Material.DARK_PRISMARINE_SLAB, 1, ChatColor.AQUA + "Dark Prismarine Slab", new String[0]));

        sea.setItem(27, ItemUtils.getItem(Material.PRISMARINE_STAIRS, 1, ChatColor.AQUA + "Prismarine Stairs", new String[0]));
        sea.setItem(28, ItemUtils.getItem(Material.PRISMARINE_BRICK_STAIRS, 1, ChatColor.AQUA + "Prismarine Brick Stairs", new String[0]));
        sea.setItem(29, ItemUtils.getItem(Material.DARK_PRISMARINE_STAIRS, 1, ChatColor.AQUA + "Dark Prismarine Stairs", new String[0]));

        sea.setItem(36, ItemUtils.getItem(Material.DRIED_KELP_BLOCK, 1, ChatColor.AQUA + "Dried Kelp Block", new String[0]));

        Inventory random = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Random Materials", CrayonInterface.SupportedInterfaceSize.HUGE, false, true);

        random.setItem(45, ItemUtils.getPreviousPageItem());
        random.setItem(53, ItemUtils.getNextPageItem());
        random.setItem(0, ItemUtils.getItem(Material.ANVIL, 1, ChatColor.AQUA + "Anvil", new String[0]));
        random.setItem(1, ItemUtils.getItem(Material.BEACON, 1, ChatColor.AQUA + "Beacon", new String[0]));
        random.setItem(2, ItemUtils.getItem(Material.ENDER_CHEST, 1, ChatColor.AQUA + "Ender Chest", new String[0]));
        random.setItem(3, ItemUtils.getItem(Material.CRAFTING_TABLE, 1, ChatColor.AQUA + "Crafting Table", new String[0]));
        random.setItem(4, ItemUtils.getItem(Material.ENCHANTING_TABLE, 1, ChatColor.AQUA + "Enchanting Table", new String[0]));
        random.setItem(5, ItemUtils.getItem(Material.FURNACE, 1, ChatColor.AQUA + "Furnace", new String[0]));
        random.setItem(6, ItemUtils.getItem(Material.NOTE_BLOCK, 1, ChatColor.AQUA + "Note Block", new String[0]));

        random.setItem(9, ItemUtils.getItem(Material.BARRIER, 1, ChatColor.AQUA + "Barrier", new String[0]));
        random.setItem(10, ItemUtils.getItem(Material.COBWEB, 1, ChatColor.AQUA + "Air", new String[0]));

        return new MaterialSet(stone, natural, wood, slab, coloredItemsOne, coloredItemsTwo, coloredItemsThree, sea, random);
    }
}
