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
        gui.setItem(12, ItemUtils.getItem(Material.WRITABLE_BOOK, 1, ChatColor.AQUA + "History"));

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

    public static Inventory getHistoryMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "History Menu", CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getLatestRedo());
        gui.setItem(11, ItemUtils.getLatestUndo());
        gui.setItem(13, ItemUtils.getItem(Material.BOOKSHELF, 1, ChatColor.AQUA + "Timeline"));
        gui.setItem(14, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "History Settings"));

        return gui;
    }

    public static Inventory getCuboidShapesMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Cuboid Shapes", CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Cube"));
        gui.setItem(11, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Cuboid"));
        gui.setItem(12, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Pyramid"));
        gui.setItem(13, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Prism"));
        return gui;
    }

    public static Inventory getSphericalShapesMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Spherical Shapes", CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "Drink", ChatColor.AQUA + "Sphere"));
        gui.setItem(11, ItemUtils.getSkullItem(1, "Drink", ChatColor.AQUA + "Ellipsoid"));
        gui.setItem(12, ItemUtils.getSkullItem(1, "Drink", ChatColor.AQUA + "Cylinder"));

        return gui;
    }

    public static Inventory getCubeDimensionMenu(int scale) {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Cube Scale", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        gui.setItem(13, ItemUtils.getSkullItem(1, "MHF_ArrowUp", ChatColor.AQUA + "+ 1"));
        gui.setItem(22, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Scale = " + scale));
        gui.setItem(31, ItemUtils.getSkullItem(1, "MHF_ArrowDown", ChatColor.AQUA + "- 1"));

        gui.setItem(26, ItemUtils.getSkullItem(1, "MHF_ArrowRight", ChatColor.AQUA + "Next"));

        return gui;
    }

    public static Inventory getCuboidDimensionMenu(int scaleX, int scaleY, int scaleZ) {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Cuboid Scale", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "MHF_ArrowUp", ChatColor.AQUA + "+ 1"));
        gui.setItem(19, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Scale X = " + scaleX));
        gui.setItem(28, ItemUtils.getSkullItem(1, "MHF_ArrowDown", ChatColor.AQUA + "- 1"));

        gui.setItem(13, ItemUtils.getSkullItem(1, "MHF_ArrowUp", ChatColor.AQUA + "+ 1"));
        gui.setItem(22, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Scale Y = " + scaleY));
        gui.setItem(31, ItemUtils.getSkullItem(1, "MHF_ArrowDown", ChatColor.AQUA + "- 1"));

        gui.setItem(16, ItemUtils.getSkullItem(1, "MHF_ArrowUp", ChatColor.AQUA + "+ 1"));
        gui.setItem(25, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Scale Z = " + scaleZ));
        gui.setItem(34, ItemUtils.getSkullItem(1, "MHF_ArrowDown", ChatColor.AQUA + "- 1"));

        gui.setItem(26, ItemUtils.getSkullItem(1, "MHF_ArrowRight", ChatColor.AQUA + "Next"));

        return gui;
    }

    public static Inventory getSphereDimensionMenu(int scale) {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Sphere Scale", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        gui.setItem(13, ItemUtils.getSkullItem(1, "MHF_ArrowUp", ChatColor.AQUA + "+ 1"));
        gui.setItem(22, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Scale = " + scale));
        gui.setItem(31, ItemUtils.getSkullItem(1, "MHF_ArrowDown", ChatColor.AQUA + "- 1"));

        gui.setItem(26, ItemUtils.getSkullItem(1, "MHF_ArrowRight", ChatColor.AQUA + "Next"));

        return gui;
    }

    public static Inventory getEllipsoidDimensionMenu(int scaleX, int scaleY, int scaleZ) {
        Inventory gui = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Ellipsoid Scale", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "MHF_ArrowUp", ChatColor.AQUA + "+ 1"));
        gui.setItem(19, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Scale X = " + scaleX));
        gui.setItem(28, ItemUtils.getSkullItem(1, "MHF_ArrowDown", ChatColor.AQUA + "- 1"));

        gui.setItem(13, ItemUtils.getSkullItem(1, "MHF_ArrowUp", ChatColor.AQUA + "+ 1"));
        gui.setItem(22, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Scale Y = " + scaleY));
        gui.setItem(31, ItemUtils.getSkullItem(1, "MHF_ArrowDown", ChatColor.AQUA + "- 1"));

        gui.setItem(16, ItemUtils.getSkullItem(1, "MHF_ArrowUp", ChatColor.AQUA + "+ 1"));
        gui.setItem(25, ItemUtils.getItem(Material.PAPER, 1, ChatColor.AQUA + "Scale Z = " + scaleZ));
        gui.setItem(34, ItemUtils.getSkullItem(1, "MHF_ArrowDown", ChatColor.AQUA + "- 1"));

        gui.setItem(26, ItemUtils.getSkullItem(1, "MHF_ArrowRight", ChatColor.AQUA + "Next"));

        return gui;
    }

    public static MaterialSet getMaterialsInterface() {
        Inventory stone = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Stone Materials", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        stone.setItem(45, ItemUtils.getPreviousPageItem());
        stone.setItem(53, ItemUtils.getNextPageItem());
        stone.setItem(0, ItemUtils.getItem(Material.STONE, 1, ChatColor.AQUA + "Stone"));
        stone.setItem(1, ItemUtils.getItem(Material.GRANITE, 1, ChatColor.AQUA + "Granite"));
        stone.setItem(2, ItemUtils.getItem(Material.DIORITE, 1, ChatColor.AQUA + "Diorite"));
        stone.setItem(3, ItemUtils.getItem(Material.ANDESITE, 1, ChatColor.AQUA + "Andesite"));
        stone.setItem(4, ItemUtils.getItem(Material.COBBLESTONE, 1, ChatColor.AQUA + "Cobblestone"));
        stone.setItem(5, ItemUtils.getItem(Material.MOSSY_COBBLESTONE, 1, ChatColor.AQUA + "Mossy Cobblestone"));
        stone.setItem(6, ItemUtils.getItem(Material.BEDROCK, 1, ChatColor.AQUA + "Bedrock"));
        stone.setItem(7, ItemUtils.getItem(Material.BRICKS, 1, ChatColor.AQUA + "Brick"));

        stone.setItem(9, ItemUtils.getItem(Material.POLISHED_GRANITE, 1, ChatColor.AQUA + "Polished Granite"));
        stone.setItem(10, ItemUtils.getItem(Material.POLISHED_DIORITE, 1, ChatColor.AQUA + "Polished Diorite"));
        stone.setItem(11, ItemUtils.getItem(Material.POLISHED_ANDESITE, 1, ChatColor.AQUA + "Polished Andesite"));
        stone.setItem(12, ItemUtils.getItem(Material.STONE_BRICKS, 1, ChatColor.AQUA + "Stone Bricks"));
        stone.setItem(13, ItemUtils.getItem(Material.MOSSY_STONE_BRICKS, 1, ChatColor.AQUA + "Mossy Stone Bricks"));
        stone.setItem(14, ItemUtils.getItem(Material.CRACKED_STONE_BRICKS, 1, ChatColor.AQUA + "Cracked Stone Bricks"));
        stone.setItem(15, ItemUtils.getItem(Material.CHISELED_STONE_BRICKS, 1, ChatColor.AQUA + "Chiseled Stone Bricks"));

        stone.setItem(18, ItemUtils.getItem(Material.INFESTED_STONE, 1, ChatColor.AQUA + "Infested Stone"));
        stone.setItem(19, ItemUtils.getItem(Material.INFESTED_COBBLESTONE, 1, ChatColor.AQUA + "Infested Cobblestone"));
        stone.setItem(20, ItemUtils.getItem(Material.INFESTED_STONE_BRICKS, 1, ChatColor.AQUA + "Infested Stone Bricks"));
        stone.setItem(21, ItemUtils.getItem(Material.INFESTED_MOSSY_STONE_BRICKS, 1, ChatColor.AQUA + "Infested Mossy Stone Bricks"));
        stone.setItem(22, ItemUtils.getItem(Material.INFESTED_CRACKED_STONE_BRICKS, 1, ChatColor.AQUA + "Infested Cracked Stone Bricks"));
        stone.setItem(23, ItemUtils.getItem(Material.INFESTED_CHISELED_STONE_BRICKS, 1, ChatColor.AQUA + "Infested Chiseled Stone Bricks"));
        stone.setItem(24, ItemUtils.getItem(Material.PRISMARINE, 1, ChatColor.AQUA + "Prismarine"));
        stone.setItem(25, ItemUtils.getItem(Material.PRISMARINE_BRICKS, 1, ChatColor.AQUA + "Prismarine Bricks"));
        stone.setItem(26, ItemUtils.getItem(Material.DARK_PRISMARINE, 1, ChatColor.AQUA + "Dark Prismarine"));

        stone.setItem(27, ItemUtils.getItem(Material.OBSIDIAN, 1, ChatColor.AQUA + "Obsidian"));
        stone.setItem(28, ItemUtils.getItem(Material.GLOWSTONE, 1, ChatColor.AQUA + "Glowstone"));
        stone.setItem(29, ItemUtils.getItem(Material.NETHERRACK, 1, ChatColor.AQUA + "Netherrack"));
        stone.setItem(30, ItemUtils.getItem(Material.NETHER_BRICKS, 1, ChatColor.AQUA + "Nether Brick"));
        stone.setItem(31, ItemUtils.getItem(Material.RED_NETHER_BRICKS, 1, ChatColor.AQUA + "Red Nether Bricks"));
        stone.setItem(32, ItemUtils.getItem(Material.NETHER_WART_BLOCK, 1, ChatColor.AQUA + "Nether Wart Block"));
        stone.setItem(33, ItemUtils.getItem(Material.SOUL_SAND, 1, ChatColor.AQUA + "Soul Sand"));
        stone.setItem(34, ItemUtils.getItem(Material.MAGMA_BLOCK, 1, ChatColor.AQUA + "Magma Block"));

        stone.setItem(36, ItemUtils.getItem(Material.END_STONE, 1, ChatColor.AQUA + "End Stone"));
        stone.setItem(37, ItemUtils.getItem(Material.END_STONE_BRICKS, 1, ChatColor.AQUA + "End Stone Bricks"));
        stone.setItem(38, ItemUtils.getItem(Material.PURPUR_BLOCK, 1, ChatColor.AQUA + "Purpur Block"));
        stone.setItem(39, ItemUtils.getItem(Material.PURPUR_PILLAR, 1, ChatColor.AQUA + "Purpur Pillar"));
        stone.setItem(40, ItemUtils.getItem(Material.QUARTZ_BLOCK, 1, ChatColor.AQUA + "Quartz Block"));
        stone.setItem(41, ItemUtils.getItem(Material.QUARTZ_PILLAR, 1, ChatColor.AQUA + "Quartz Pillar"));
        stone.setItem(42, ItemUtils.getItem(Material.CHISELED_QUARTZ_BLOCK, 1, ChatColor.AQUA + "Chiseled Quartz Block"));
        stone.setItem(43, ItemUtils.getItem(Material.SMOOTH_QUARTZ, 1, ChatColor.AQUA + "Smooth Quartz"));
        stone.setItem(44, ItemUtils.getItem(Material.BONE_BLOCK, 1, ChatColor.AQUA + "Bone Block"));

        Inventory natural = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Natural Materials", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        natural.setItem(45, ItemUtils.getPreviousPageItem());
        natural.setItem(53, ItemUtils.getNextPageItem());
        natural.setItem(0, ItemUtils.getItem(Material.SAND, 1, ChatColor.AQUA + "Sand"));
        natural.setItem(1, ItemUtils.getItem(Material.SANDSTONE, 1, ChatColor.AQUA + "Sandstone"));
        natural.setItem(2, ItemUtils.getItem(Material.SMOOTH_SANDSTONE, 1, ChatColor.AQUA + "Smooth Sandstone"));
        natural.setItem(3, ItemUtils.getItem(Material.CHISELED_SANDSTONE, 1, ChatColor.AQUA + "Chiseled Sandstone"));
        natural.setItem(4, ItemUtils.getItem(Material.RED_SAND, 1, ChatColor.AQUA + "Red Sand"));
        natural.setItem(5, ItemUtils.getItem(Material.RED_SANDSTONE, 1, ChatColor.AQUA + "Red Sandstone"));
        natural.setItem(6, ItemUtils.getItem(Material.SMOOTH_RED_SANDSTONE, 1, ChatColor.AQUA + "Smooth Red Sandstone"));
        natural.setItem(7, ItemUtils.getItem(Material.CHISELED_RED_SANDSTONE, 1, ChatColor.AQUA + "Chiseled Red Sandstone"));
        natural.setItem(8, ItemUtils.getItem(Material.SNOW, 1, ChatColor.AQUA + "Snow"));

        natural.setItem(9, ItemUtils.getItem(Material.DIRT, 1, ChatColor.AQUA + "Dirt"));
        natural.setItem(10, ItemUtils.getItem(Material.FARMLAND, 1, ChatColor.AQUA + "Farmland"));
        natural.setItem(11, ItemUtils.getItem(Material.COARSE_DIRT, 1, ChatColor.AQUA + "Coarse Dirt"));
        natural.setItem(12, ItemUtils.getItem(Material.GRASS_BLOCK, 1, ChatColor.AQUA + "Grass Block"));
        natural.setItem(13, ItemUtils.getItem(Material.GRASS_PATH, 1, ChatColor.AQUA + "Grass Path"));
        natural.setItem(14, ItemUtils.getItem(Material.MYCELIUM, 1, ChatColor.AQUA + "Mycelium"));
        natural.setItem(15, ItemUtils.getItem(Material.PODZOL, 1, ChatColor.AQUA + "Podzol"));
        natural.setItem(16, ItemUtils.getItem(Material.SPONGE, 1, ChatColor.AQUA + "Sponge"));
        natural.setItem(17, ItemUtils.getItem(Material.WET_SPONGE, 1, ChatColor.AQUA + "Wet Sponge"));

        natural.setItem(18, ItemUtils.getItem(Material.ICE, 1, ChatColor.AQUA + "Ice"));
        natural.setItem(19, ItemUtils.getItem(Material.PACKED_ICE, 1, ChatColor.AQUA + "Packed Ice"));
        natural.setItem(20, ItemUtils.getItem(Material.BLUE_ICE, 1, ChatColor.AQUA + "Blue Ice"));
        natural.setItem(21, ItemUtils.getItem(Material.COBWEB, 1, ChatColor.AQUA + "COBWEB"));
        natural.setItem(22, ItemUtils.getItem(Material.COBBLESTONE_WALL, 1, ChatColor.AQUA + "Cobblestone Wall"));
        natural.setItem(23, ItemUtils.getItem(Material.MOSSY_COBBLESTONE_WALL, 1, ChatColor.AQUA + "Mossy Cobblestone Wall"));
        natural.setItem(24, ItemUtils.getItem(Material.SLIME_BLOCK, 1, ChatColor.AQUA + "Slime Block"));

        natural.setItem(27, ItemUtils.getItem(Material.ACACIA_LEAVES, 1, ChatColor.AQUA + "Acacia Leaves"));
        natural.setItem(28, ItemUtils.getItem(Material.BIRCH_LEAVES, 1, ChatColor.AQUA + "Birch Leaves"));
        natural.setItem(29, ItemUtils.getItem(Material.DARK_OAK_LEAVES, 1, ChatColor.AQUA + "Dark Oak Leaves"));
        natural.setItem(30, ItemUtils.getItem(Material.JUNGLE_LEAVES, 1, ChatColor.AQUA + "Jungle Leaves"));
        natural.setItem(31, ItemUtils.getItem(Material.OAK_LEAVES, 1, ChatColor.AQUA + "Oak Leaves"));
        natural.setItem(32, ItemUtils.getItem(Material.SPRUCE_LEAVES, 1, ChatColor.AQUA + "Spruce Leaves"));
        natural.setItem(33, ItemUtils.getItem(Material.RED_MUSHROOM_BLOCK, 1, ChatColor.AQUA + "Red Mushroom Block"));
        natural.setItem(34, ItemUtils.getItem(Material.BROWN_MUSHROOM_BLOCK, 1, ChatColor.AQUA + "Brown Mushroom Block"));
        natural.setItem(35, ItemUtils.getItem(Material.MUSHROOM_STEM, 1, ChatColor.AQUA + "Mushroom Stem"));

        natural.setItem(36, ItemUtils.getItem(Material.MELON, 1, ChatColor.AQUA + "Melon"));
        natural.setItem(37, ItemUtils.getItem(Material.PUMPKIN, 1, ChatColor.AQUA + "Pumpkin"));
        natural.setItem(38, ItemUtils.getItem(Material.CARVED_PUMPKIN, 1, ChatColor.AQUA + "Carved Pumpkin"));
        natural.setItem(39, ItemUtils.getItem(Material.JACK_O_LANTERN, 1, ChatColor.AQUA + "Jack O Lantern"));

        Inventory wood = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Wooden Materials", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        wood.setItem(45, ItemUtils.getPreviousPageItem());
        wood.setItem(53, ItemUtils.getNextPageItem());
        wood.setItem(0, ItemUtils.getItem(Material.ACACIA_LOG, 1, ChatColor.AQUA + "Acacia Log"));
        wood.setItem(1, ItemUtils.getItem(Material.BIRCH_LOG, 1, ChatColor.AQUA + "Birch Log"));
        wood.setItem(2, ItemUtils.getItem(Material.DARK_OAK_LOG, 1, ChatColor.AQUA + "Dark Oak Log"));
        wood.setItem(3, ItemUtils.getItem(Material.JUNGLE_LOG, 1, ChatColor.AQUA + "Jungle Log"));
        wood.setItem(4, ItemUtils.getItem(Material.OAK_LOG, 1, ChatColor.AQUA + "Oak Log"));
        wood.setItem(5, ItemUtils.getItem(Material.SPRUCE_LOG, 1, ChatColor.AQUA + "Spruce Log"));

        wood.setItem(9, ItemUtils.getItem(Material.STRIPPED_ACACIA_LOG, 1, ChatColor.AQUA + "Stripped Acacia Log"));
        wood.setItem(10, ItemUtils.getItem(Material.STRIPPED_BIRCH_LOG, 1, ChatColor.AQUA + "Stripped Birch Log"));
        wood.setItem(11, ItemUtils.getItem(Material.STRIPPED_DARK_OAK_LOG, 1, ChatColor.AQUA + "Stripped Dark Oak Log"));
        wood.setItem(12, ItemUtils.getItem(Material.STRIPPED_JUNGLE_LOG, 1, ChatColor.AQUA + "Stripped Jungle Log"));
        wood.setItem(13, ItemUtils.getItem(Material.STRIPPED_OAK_LOG, 1, ChatColor.AQUA + "Stripped Oak Log"));
        wood.setItem(14, ItemUtils.getItem(Material.STRIPPED_SPRUCE_LOG, 1, ChatColor.AQUA + "Stripped Spruce Log"));

        wood.setItem(18, ItemUtils.getItem(Material.ACACIA_PLANKS, 1, ChatColor.AQUA + "Acacia Planks"));
        wood.setItem(19, ItemUtils.getItem(Material.BIRCH_PLANKS, 1, ChatColor.AQUA + "Birch Planks"));
        wood.setItem(20, ItemUtils.getItem(Material.DARK_OAK_PLANKS, 1, ChatColor.AQUA + "Dark Oak Planks"));
        wood.setItem(21, ItemUtils.getItem(Material.JUNGLE_PLANKS, 1, ChatColor.AQUA + "Jungle Planks"));
        wood.setItem(22, ItemUtils.getItem(Material.OAK_PLANKS, 1, ChatColor.AQUA + "Oak Planks"));
        wood.setItem(23, ItemUtils.getItem(Material.SPRUCE_PLANKS, 1, ChatColor.AQUA + "Spruce Planks"));

        wood.setItem(27, ItemUtils.getItem(Material.ACACIA_FENCE, 1, ChatColor.AQUA + "Acacia Fence"));
        wood.setItem(28, ItemUtils.getItem(Material.BIRCH_FENCE, 1, ChatColor.AQUA + "Birch Fence"));
        wood.setItem(29, ItemUtils.getItem(Material.DARK_OAK_FENCE, 1, ChatColor.AQUA + "Dark Oak Fence"));
        wood.setItem(30, ItemUtils.getItem(Material.JUNGLE_FENCE, 1, ChatColor.AQUA + "Jungle Fence"));
        wood.setItem(31, ItemUtils.getItem(Material.OAK_FENCE, 1, ChatColor.AQUA + "Oak Fence"));
        wood.setItem(32, ItemUtils.getItem(Material.SPRUCE_FENCE, 1, ChatColor.AQUA + "Spruce Fence"));

        wood.setItem(36, ItemUtils.getItem(Material.ACACIA_FENCE_GATE, 1, ChatColor.AQUA + "Acacia Fence Gate"));
        wood.setItem(37, ItemUtils.getItem(Material.BIRCH_FENCE_GATE, 1, ChatColor.AQUA + "Birch Fence Gate"));
        wood.setItem(38, ItemUtils.getItem(Material.DARK_OAK_FENCE_GATE, 1, ChatColor.AQUA + "Dark Oak Fence Gate"));
        wood.setItem(39, ItemUtils.getItem(Material.JUNGLE_FENCE_GATE, 1, ChatColor.AQUA + "Jungle Fence Gate"));
        wood.setItem(40, ItemUtils.getItem(Material.OAK_FENCE_GATE, 1, ChatColor.AQUA + "Oak Fence Gate"));
        wood.setItem(41, ItemUtils.getItem(Material.SPRUCE_FENCE_GATE, 1, ChatColor.AQUA + "Spruce Fence Gate"));

        Inventory slab = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Slab & Stair Materials", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        slab.setItem(45, ItemUtils.getPreviousPageItem());
        slab.setItem(53, ItemUtils.getNextPageItem());
        slab.setItem(0, ItemUtils.getItem(Material.STONE_SLAB, 1, ChatColor.AQUA + "Stone Slab"));
        slab.setItem(1, ItemUtils.getItem(Material.COBBLESTONE_SLAB, 1, ChatColor.AQUA + "Cobblestone Slab"));
        slab.setItem(2, ItemUtils.getItem(Material.STONE_BRICK_SLAB, 1, ChatColor.AQUA + "Stone Brick Slab"));
        slab.setItem(3, ItemUtils.getItem(Material.NETHER_BRICK_SLAB, 1, ChatColor.AQUA + "Nether Brick Slab"));
        slab.setItem(4, ItemUtils.getItem(Material.QUARTZ_SLAB, 1, ChatColor.AQUA + "Quartz Slab"));
        slab.setItem(5, ItemUtils.getItem(Material.SANDSTONE_SLAB, 1, ChatColor.AQUA + "Sandstone Slab"));
        slab.setItem(6, ItemUtils.getItem(Material.RED_SANDSTONE_SLAB, 1, ChatColor.AQUA + "Red Sandstone Slab"));
        slab.setItem(7, ItemUtils.getItem(Material.PURPUR_SLAB, 1, ChatColor.AQUA + "Purpur Slab"));
        slab.setItem(8, ItemUtils.getItem(Material.BRICK_SLAB, 1, ChatColor.AQUA + "Brick Slab"));

        slab.setItem(9, ItemUtils.getItem(Material.ACACIA_SLAB, 1, ChatColor.AQUA + "Acacia Slab"));
        slab.setItem(10, ItemUtils.getItem(Material.BIRCH_SLAB, 1, ChatColor.AQUA + "Birch Slab"));
        slab.setItem(11, ItemUtils.getItem(Material.DARK_OAK_SLAB, 1, ChatColor.AQUA + "Dark Oak Slab"));
        slab.setItem(12, ItemUtils.getItem(Material.JUNGLE_SLAB, 1, ChatColor.AQUA + "Jungle Slab"));
        slab.setItem(13, ItemUtils.getItem(Material.OAK_SLAB, 1, ChatColor.AQUA + "Oak Slab"));
        slab.setItem(14, ItemUtils.getItem(Material.SPRUCE_SLAB, 1, ChatColor.AQUA + "Spruce Slab"));

        slab.setItem(18, ItemUtils.getItem(Material.COBBLESTONE_STAIRS, 1, ChatColor.AQUA + "Cobblestone Stairs"));
        slab.setItem(19, ItemUtils.getItem(Material.STONE_BRICK_STAIRS, 1, ChatColor.AQUA + "Stone Brick Stairs"));
        slab.setItem(20, ItemUtils.getItem(Material.NETHER_BRICK_STAIRS, 1, ChatColor.AQUA + "Nether Brick Stairs"));
        slab.setItem(21, ItemUtils.getItem(Material.QUARTZ_STAIRS, 1, ChatColor.AQUA + "Quartz Stairs"));
        slab.setItem(22, ItemUtils.getItem(Material.SANDSTONE_STAIRS, 1, ChatColor.AQUA + "Sandstone Stairs"));
        slab.setItem(23, ItemUtils.getItem(Material.RED_SANDSTONE_STAIRS, 1, ChatColor.AQUA + "Red Sandstone Stairs"));
        slab.setItem(24, ItemUtils.getItem(Material.PURPUR_STAIRS, 1, ChatColor.AQUA + "Purpur Stairs"));
        slab.setItem(25, ItemUtils.getItem(Material.BRICK_STAIRS, 1, ChatColor.AQUA + "Brick Stairs"));

        slab.setItem(27, ItemUtils.getItem(Material.ACACIA_STAIRS, 1, ChatColor.AQUA + "Acacia Stairs"));
        slab.setItem(28, ItemUtils.getItem(Material.BIRCH_STAIRS, 1, ChatColor.AQUA + "Birch Stairs"));
        slab.setItem(29, ItemUtils.getItem(Material.DARK_OAK_STAIRS, 1, ChatColor.AQUA + "Dark Oak Stairs"));
        slab.setItem(30, ItemUtils.getItem(Material.JUNGLE_STAIRS, 1, ChatColor.AQUA + "Jungle Stairs"));
        slab.setItem(31, ItemUtils.getItem(Material.OAK_STAIRS, 1, ChatColor.AQUA + "Oak Stairs"));
        slab.setItem(32, ItemUtils.getItem(Material.SPRUCE_STAIRS, 1, ChatColor.AQUA + "Spruce Stairs"));

        Inventory coloredItemsOne = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Colored Materials 1", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        coloredItemsOne.setItem(45, ItemUtils.getPreviousPageItem());
        coloredItemsOne.setItem(53, ItemUtils.getNextPageItem());
        coloredItemsOne.setItem(0, ItemUtils.getItem(Material.BLACK_STAINED_GLASS, 1, ChatColor.AQUA + "Black Stained Glass"));
        coloredItemsOne.setItem(1, ItemUtils.getItem(Material.BLACK_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Black Stained Glass Pane"));
        coloredItemsOne.setItem(2, ItemUtils.getItem(Material.BLACK_WOOL, 1, ChatColor.AQUA + "Black Wool"));
        coloredItemsOne.setItem(3, ItemUtils.getItem(Material.BLACK_TERRACOTTA, 1, ChatColor.AQUA + "Black Terracotta"));
        coloredItemsOne.setItem(4, ItemUtils.getItem(Material.BLACK_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Black Glazed Terracotta"));
        coloredItemsOne.setItem(5, ItemUtils.getItem(Material.BLACK_CONCRETE_POWDER, 1, ChatColor.AQUA + "Black Concrete Powder"));
        coloredItemsOne.setItem(6, ItemUtils.getItem(Material.BLACK_CONCRETE, 1, ChatColor.AQUA + "Black Concrete"));

        coloredItemsOne.setItem(9, ItemUtils.getItem(Material.WHITE_STAINED_GLASS, 1, ChatColor.AQUA + "White Stained Glass"));
        coloredItemsOne.setItem(10, ItemUtils.getItem(Material.WHITE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "White Stained Glass Pane"));
        coloredItemsOne.setItem(11, ItemUtils.getItem(Material.WHITE_WOOL, 1, ChatColor.AQUA + "White Wool"));
        coloredItemsOne.setItem(12, ItemUtils.getItem(Material.WHITE_TERRACOTTA, 1, ChatColor.AQUA + "White Terracotta"));
        coloredItemsOne.setItem(13, ItemUtils.getItem(Material.WHITE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "White Glazed Terracotta"));
        coloredItemsOne.setItem(14, ItemUtils.getItem(Material.WHITE_CONCRETE_POWDER, 1, ChatColor.AQUA + "White Concrete Powder"));
        coloredItemsOne.setItem(15, ItemUtils.getItem(Material.WHITE_CONCRETE, 1, ChatColor.AQUA + "White Concrete"));

        coloredItemsOne.setItem(18, ItemUtils.getItem(Material.RED_STAINED_GLASS, 1, ChatColor.AQUA + "Red Stained Glass"));
        coloredItemsOne.setItem(19, ItemUtils.getItem(Material.RED_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Red Stained Glass Pane"));
        coloredItemsOne.setItem(20, ItemUtils.getItem(Material.RED_WOOL, 1, ChatColor.AQUA + "Red Wool"));
        coloredItemsOne.setItem(21, ItemUtils.getItem(Material.RED_TERRACOTTA, 1, ChatColor.AQUA + "Red Terracotta"));
        coloredItemsOne.setItem(22, ItemUtils.getItem(Material.RED_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Red Glazed Terracotta"));
        coloredItemsOne.setItem(23, ItemUtils.getItem(Material.RED_CONCRETE_POWDER, 1, ChatColor.AQUA + "Red Concrete Powder"));
        coloredItemsOne.setItem(24, ItemUtils.getItem(Material.RED_CONCRETE, 1, ChatColor.AQUA + "Red Concrete"));

        coloredItemsOne.setItem(27, ItemUtils.getItem(Material.ORANGE_STAINED_GLASS, 1, ChatColor.AQUA + "Orange Stained Glass"));
        coloredItemsOne.setItem(28, ItemUtils.getItem(Material.ORANGE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Orange Stained Glass Pane"));
        coloredItemsOne.setItem(29, ItemUtils.getItem(Material.ORANGE_WOOL, 1, ChatColor.AQUA + "Orange Wool"));
        coloredItemsOne.setItem(30, ItemUtils.getItem(Material.ORANGE_TERRACOTTA, 1, ChatColor.AQUA + "Orange Terracotta"));
        coloredItemsOne.setItem(31, ItemUtils.getItem(Material.ORANGE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Orange Glazed Terracotta"));
        coloredItemsOne.setItem(32, ItemUtils.getItem(Material.ORANGE_CONCRETE_POWDER, 1, ChatColor.AQUA + "Orange Concrete Powder"));
        coloredItemsOne.setItem(33, ItemUtils.getItem(Material.ORANGE_CONCRETE, 1, ChatColor.AQUA + "Orange Concrete"));

        coloredItemsOne.setItem(36, ItemUtils.getItem(Material.YELLOW_STAINED_GLASS, 1, ChatColor.AQUA + "Yellow Stained Glass"));
        coloredItemsOne.setItem(37, ItemUtils.getItem(Material.YELLOW_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Yellow Stained Glass Pane"));
        coloredItemsOne.setItem(38, ItemUtils.getItem(Material.YELLOW_WOOL, 1, ChatColor.AQUA + "Yellow Wool"));
        coloredItemsOne.setItem(39, ItemUtils.getItem(Material.YELLOW_TERRACOTTA, 1, ChatColor.AQUA + "Yellow Terracotta"));
        coloredItemsOne.setItem(40, ItemUtils.getItem(Material.YELLOW_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Yellow Glazed Terracotta"));
        coloredItemsOne.setItem(41, ItemUtils.getItem(Material.YELLOW_CONCRETE_POWDER, 1, ChatColor.AQUA + "Yellow Concrete Powder"));
        coloredItemsOne.setItem(42, ItemUtils.getItem(Material.YELLOW_CONCRETE, 1, ChatColor.AQUA + "Yellow Concrete"));

        Inventory coloredItemsTwo = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Colored Materials 2", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        coloredItemsTwo.setItem(45, ItemUtils.getPreviousPageItem());
        coloredItemsTwo.setItem(53, ItemUtils.getNextPageItem());
        coloredItemsTwo.setItem(0, ItemUtils.getItem(Material.LIME_STAINED_GLASS, 1, ChatColor.AQUA + "Lime Stained Glass"));
        coloredItemsTwo.setItem(1, ItemUtils.getItem(Material.LIME_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Lime Stained Glass Pane"));
        coloredItemsTwo.setItem(2, ItemUtils.getItem(Material.LIME_WOOL, 1, ChatColor.AQUA + "Lime Wool"));
        coloredItemsTwo.setItem(3, ItemUtils.getItem(Material.LIME_TERRACOTTA, 1, ChatColor.AQUA + "Lime Terracotta"));
        coloredItemsTwo.setItem(4, ItemUtils.getItem(Material.LIME_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Lime Glazed Terracotta"));
        coloredItemsTwo.setItem(5, ItemUtils.getItem(Material.LIME_CONCRETE_POWDER, 1, ChatColor.AQUA + "Lime Concrete Powder"));
        coloredItemsTwo.setItem(6, ItemUtils.getItem(Material.LIME_CONCRETE, 1, ChatColor.AQUA + "Lime Concrete"));

        coloredItemsTwo.setItem(9, ItemUtils.getItem(Material.GREEN_STAINED_GLASS, 1, ChatColor.AQUA + "Green Stained Glass"));
        coloredItemsTwo.setItem(10, ItemUtils.getItem(Material.GREEN_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Green Stained Glass Pane"));
        coloredItemsTwo.setItem(11, ItemUtils.getItem(Material.GREEN_WOOL, 1, ChatColor.AQUA + "Green Wool"));
        coloredItemsTwo.setItem(12, ItemUtils.getItem(Material.GREEN_TERRACOTTA, 1, ChatColor.AQUA + "Green Terracotta"));
        coloredItemsTwo.setItem(13, ItemUtils.getItem(Material.GREEN_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Green Glazed Terracotta"));
        coloredItemsTwo.setItem(14, ItemUtils.getItem(Material.GREEN_CONCRETE_POWDER, 1, ChatColor.AQUA + "Green Concrete Powder"));
        coloredItemsTwo.setItem(15, ItemUtils.getItem(Material.GREEN_CONCRETE, 1, ChatColor.AQUA + "Green Concrete"));

        coloredItemsTwo.setItem(18, ItemUtils.getItem(Material.CYAN_STAINED_GLASS, 1, ChatColor.AQUA + "Cyan Stained Glass"));
        coloredItemsTwo.setItem(19, ItemUtils.getItem(Material.CYAN_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Cyan Stained Glass Pane"));
        coloredItemsTwo.setItem(20, ItemUtils.getItem(Material.CYAN_WOOL, 1, ChatColor.AQUA + "Cyan Wool"));
        coloredItemsTwo.setItem(21, ItemUtils.getItem(Material.CYAN_TERRACOTTA, 1, ChatColor.AQUA + "Cyan Terracotta"));
        coloredItemsTwo.setItem(22, ItemUtils.getItem(Material.CYAN_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Cyan Glazed Terracotta"));
        coloredItemsTwo.setItem(23, ItemUtils.getItem(Material.CYAN_CONCRETE_POWDER, 1, ChatColor.AQUA + "Cyan Concrete Powder"));
        coloredItemsTwo.setItem(24, ItemUtils.getItem(Material.CYAN_CONCRETE, 1, ChatColor.AQUA + "Cyan Concrete"));

        coloredItemsTwo.setItem(27, ItemUtils.getItem(Material.LIGHT_BLUE_STAINED_GLASS, 1, ChatColor.AQUA + "Light Blue Stained Glass"));
        coloredItemsTwo.setItem(28, ItemUtils.getItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Light Blue Stained Glass Pane"));
        coloredItemsTwo.setItem(29, ItemUtils.getItem(Material.LIGHT_BLUE_WOOL, 1, ChatColor.AQUA + "Light Blue Wool"));
        coloredItemsTwo.setItem(30, ItemUtils.getItem(Material.LIGHT_BLUE_TERRACOTTA, 1, ChatColor.AQUA + "Light Blue Terracotta"));
        coloredItemsTwo.setItem(31, ItemUtils.getItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Light Blue Glazed Terracotta"));
        coloredItemsTwo.setItem(32, ItemUtils.getItem(Material.LIGHT_BLUE_CONCRETE_POWDER, 1, ChatColor.AQUA + "Light Blue Concrete Powder"));
        coloredItemsTwo.setItem(33, ItemUtils.getItem(Material.LIGHT_BLUE_CONCRETE, 1, ChatColor.AQUA + "Light Blue Concrete"));

        coloredItemsTwo.setItem(36, ItemUtils.getItem(Material.BLUE_STAINED_GLASS, 1, ChatColor.AQUA + "Blue Stained Glass"));
        coloredItemsTwo.setItem(37, ItemUtils.getItem(Material.BLUE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Blue Stained Glass Pane"));
        coloredItemsTwo.setItem(38, ItemUtils.getItem(Material.BLUE_WOOL, 1, ChatColor.AQUA + "Blue Wool"));
        coloredItemsTwo.setItem(39, ItemUtils.getItem(Material.BLUE_TERRACOTTA, 1, ChatColor.AQUA + "Blue Terracotta"));
        coloredItemsTwo.setItem(40, ItemUtils.getItem(Material.BLUE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Blue Glazed Terracotta"));
        coloredItemsTwo.setItem(41, ItemUtils.getItem(Material.BLUE_CONCRETE_POWDER, 1, ChatColor.AQUA + "Blue Concrete Powder"));
        coloredItemsTwo.setItem(42, ItemUtils.getItem(Material.BLUE_CONCRETE, 1, ChatColor.AQUA + "Blue Concrete"));

        Inventory coloredItemsThree = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Colored Materials 3", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        coloredItemsThree.setItem(45, ItemUtils.getPreviousPageItem());
        coloredItemsThree.setItem(53, ItemUtils.getNextPageItem());
        coloredItemsThree.setItem(0, ItemUtils.getItem(Material.MAGENTA_STAINED_GLASS, 1, ChatColor.AQUA + "Magenta Stained Glass"));
        coloredItemsThree.setItem(1, ItemUtils.getItem(Material.MAGENTA_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Magenta Stained Glass Pane"));
        coloredItemsThree.setItem(2, ItemUtils.getItem(Material.MAGENTA_WOOL, 1, ChatColor.AQUA + "Magenta Wool"));
        coloredItemsThree.setItem(3, ItemUtils.getItem(Material.MAGENTA_TERRACOTTA, 1, ChatColor.AQUA + "Magenta Terracotta"));
        coloredItemsThree.setItem(4, ItemUtils.getItem(Material.MAGENTA_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Magenta Glazed Terracotta"));
        coloredItemsThree.setItem(5, ItemUtils.getItem(Material.MAGENTA_CONCRETE_POWDER, 1, ChatColor.AQUA + "Magenta Concrete Powder"));
        coloredItemsThree.setItem(6, ItemUtils.getItem(Material.MAGENTA_CONCRETE, 1, ChatColor.AQUA + "Magenta Concrete"));

        coloredItemsThree.setItem(9, ItemUtils.getItem(Material.PURPLE_STAINED_GLASS, 1, ChatColor.AQUA + "Purple Stained Glass"));
        coloredItemsThree.setItem(10, ItemUtils.getItem(Material.PURPLE_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Purple Stained Glass Pane"));
        coloredItemsThree.setItem(11, ItemUtils.getItem(Material.PURPLE_WOOL, 1, ChatColor.AQUA + "Purple Wool"));
        coloredItemsThree.setItem(12, ItemUtils.getItem(Material.PURPLE_TERRACOTTA, 1, ChatColor.AQUA + "Purple Terracotta"));
        coloredItemsThree.setItem(13, ItemUtils.getItem(Material.PURPLE_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Purple Glazed Terracotta"));
        coloredItemsThree.setItem(14, ItemUtils.getItem(Material.PURPLE_CONCRETE_POWDER, 1, ChatColor.AQUA + "Purple Concrete Powder"));
        coloredItemsThree.setItem(15, ItemUtils.getItem(Material.PURPLE_CONCRETE, 1, ChatColor.AQUA + "Purple Concrete"));

        coloredItemsThree.setItem(18, ItemUtils.getItem(Material.PINK_STAINED_GLASS, 1, ChatColor.AQUA + "Pink Stained Glass"));
        coloredItemsThree.setItem(19, ItemUtils.getItem(Material.PINK_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Pink Stained Glass Pane"));
        coloredItemsThree.setItem(20, ItemUtils.getItem(Material.PINK_WOOL, 1, ChatColor.AQUA + "Pink Wool"));
        coloredItemsThree.setItem(21, ItemUtils.getItem(Material.PINK_TERRACOTTA, 1, ChatColor.AQUA + "Pink Terracotta"));
        coloredItemsThree.setItem(22, ItemUtils.getItem(Material.PINK_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Pink Glazed Terracotta"));
        coloredItemsThree.setItem(23, ItemUtils.getItem(Material.PINK_CONCRETE_POWDER, 1, ChatColor.AQUA + "Pink Concrete Powder"));
        coloredItemsThree.setItem(24, ItemUtils.getItem(Material.PINK_CONCRETE, 1, ChatColor.AQUA + "Pink Concrete"));

        coloredItemsThree.setItem(27, ItemUtils.getItem(Material.LIGHT_GRAY_STAINED_GLASS, 1, ChatColor.AQUA + "Light Gray Stained Glass"));
        coloredItemsThree.setItem(28, ItemUtils.getItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Light Gray Stained Glass Pane"));
        coloredItemsThree.setItem(29, ItemUtils.getItem(Material.LIGHT_GRAY_WOOL, 1, ChatColor.AQUA + "Light Gray Wool"));
        coloredItemsThree.setItem(30, ItemUtils.getItem(Material.LIGHT_GRAY_TERRACOTTA, 1, ChatColor.AQUA + "Light Gray Terracotta"));
        coloredItemsThree.setItem(31, ItemUtils.getItem(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Light Gray Glazed Terracotta"));
        coloredItemsThree.setItem(32, ItemUtils.getItem(Material.LIGHT_GRAY_CONCRETE_POWDER, 1, ChatColor.AQUA + "Light Gray Concrete Powder"));
        coloredItemsThree.setItem(33, ItemUtils.getItem(Material.LIGHT_GRAY_CONCRETE, 1, ChatColor.AQUA + "Light Gray Concrete"));

        coloredItemsThree.setItem(36, ItemUtils.getItem(Material.GRAY_STAINED_GLASS, 1, ChatColor.AQUA + "Gray Stained Glass"));
        coloredItemsThree.setItem(37, ItemUtils.getItem(Material.GRAY_STAINED_GLASS_PANE, 1, ChatColor.AQUA + "Gray Stained Glass Pane"));
        coloredItemsThree.setItem(38, ItemUtils.getItem(Material.GRAY_WOOL, 1, ChatColor.AQUA + "Gray Wool"));
        coloredItemsThree.setItem(39, ItemUtils.getItem(Material.GRAY_TERRACOTTA, 1, ChatColor.AQUA + "Gray Terracotta"));
        coloredItemsThree.setItem(40, ItemUtils.getItem(Material.GRAY_GLAZED_TERRACOTTA, 1, ChatColor.AQUA + "Gray Glazed Terracotta"));
        coloredItemsThree.setItem(41, ItemUtils.getItem(Material.GRAY_CONCRETE_POWDER, 1, ChatColor.AQUA + "Gray Concrete Powder"));
        coloredItemsThree.setItem(42, ItemUtils.getItem(Material.GRAY_CONCRETE, 1, ChatColor.AQUA + "Gray Concrete"));

        Inventory sea = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Sea Materials", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        sea.setItem(45, ItemUtils.getPreviousPageItem());
        sea.setItem(53, ItemUtils.getNextPageItem());
        sea.setItem(0, ItemUtils.getItem(Material.BRAIN_CORAL_BLOCK, 1, ChatColor.AQUA + "Brain Coral"));
        sea.setItem(1, ItemUtils.getItem(Material.BUBBLE_CORAL_BLOCK, 1, ChatColor.AQUA + "Bubble Coral"));
        sea.setItem(2, ItemUtils.getItem(Material.FIRE_CORAL_BLOCK, 1, ChatColor.AQUA + "Fire Coral"));
        sea.setItem(3, ItemUtils.getItem(Material.HORN_CORAL_BLOCK, 1, ChatColor.AQUA + "Horn Coral"));
        sea.setItem(4, ItemUtils.getItem(Material.TUBE_CORAL_BLOCK, 1, ChatColor.AQUA + "Tube Coral"));

        sea.setItem(9, ItemUtils.getItem(Material.DEAD_BRAIN_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Brain Coral"));
        sea.setItem(10, ItemUtils.getItem(Material.DEAD_BUBBLE_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Bubble Coral"));
        sea.setItem(11, ItemUtils.getItem(Material.DEAD_FIRE_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Fire Coral"));
        sea.setItem(12, ItemUtils.getItem(Material.DEAD_HORN_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Horn Coral"));
        sea.setItem(13, ItemUtils.getItem(Material.DEAD_TUBE_CORAL_BLOCK, 1, ChatColor.AQUA + "Dead Tube Coral"));

        sea.setItem(18, ItemUtils.getItem(Material.PRISMARINE_SLAB, 1, ChatColor.AQUA + "Prismarine Slab"));
        sea.setItem(19, ItemUtils.getItem(Material.PRISMARINE_BRICK_SLAB, 1, ChatColor.AQUA + "Prismarine Brick Slab"));
        sea.setItem(20, ItemUtils.getItem(Material.DARK_PRISMARINE_SLAB, 1, ChatColor.AQUA + "Dark Prismarine Slab"));

        sea.setItem(27, ItemUtils.getItem(Material.PRISMARINE_STAIRS, 1, ChatColor.AQUA + "Prismarine Stairs"));
        sea.setItem(28, ItemUtils.getItem(Material.PRISMARINE_BRICK_STAIRS, 1, ChatColor.AQUA + "Prismarine Brick Stairs"));
        sea.setItem(29, ItemUtils.getItem(Material.DARK_PRISMARINE_STAIRS, 1, ChatColor.AQUA + "Dark Prismarine Stairs"));

        sea.setItem(36, ItemUtils.getItem(Material.DRIED_KELP_BLOCK, 1, ChatColor.AQUA + "Dried Kelp Block"));

        Inventory random = CrayonInterface.createCrayonInterface(
                Crayon.getPrefix() + ChatColor.GREEN + "Random Materials", CrayonInterface.SupportedInterfaceSize.HUGE, true, true);

        random.setItem(45, ItemUtils.getPreviousPageItem());
        random.setItem(53, ItemUtils.getNextPageItem());
        random.setItem(0, ItemUtils.getItem(Material.ANVIL, 1, ChatColor.AQUA + "Anvil"));
        random.setItem(1, ItemUtils.getItem(Material.BEACON, 1, ChatColor.AQUA + "Beacon"));
        random.setItem(2, ItemUtils.getItem(Material.ENDER_CHEST, 1, ChatColor.AQUA + "Ender Chest"));
        random.setItem(3, ItemUtils.getItem(Material.CRAFTING_TABLE, 1, ChatColor.AQUA + "Crafting Table"));
        random.setItem(4, ItemUtils.getItem(Material.ENCHANTING_TABLE, 1, ChatColor.AQUA + "Enchanting Table"));
        random.setItem(5, ItemUtils.getItem(Material.FURNACE, 1, ChatColor.AQUA + "Furnace"));
        random.setItem(6, ItemUtils.getItem(Material.NOTE_BLOCK, 1, ChatColor.AQUA + "Note Block"));

        random.setItem(9, ItemUtils.getItem(Material.BARRIER, 1, ChatColor.AQUA + "Barrier"));
        random.setItem(10, ItemUtils.getItem(Material.COBWEB, 1, ChatColor.AQUA + "Air"));

        return new MaterialSet(stone, natural, wood, slab, coloredItemsOne, coloredItemsTwo, coloredItemsThree, sea, random);
    }
}
