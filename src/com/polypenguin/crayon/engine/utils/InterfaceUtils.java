package com.polypenguin.crayon.engine.utils;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.core.gui.CrayonInterface;

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
        gui.setItem(12, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Operations"));

        return gui;
    }

    public static Inventory getPositionMenu() {
        Inventory gui = CrayonInterface.createCrayonInterface(Crayon.getPrefix(), CrayonInterface.SupportedInterfaceSize.MEDIUM, true, true);

        gui.setItem(10, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Single-Position Selection"));
        gui.setItem(11, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Double-Position Selection"));
        gui.setItem(12, ItemUtils.getSkullItem(1, "flashlight", ChatColor.AQUA + "Multi-Position Selection"));
        gui.setItem(14, ItemUtils.getItem(Material.BONE_MEAL, 1, ChatColor.AQUA + "Reset Position Mode"));
        gui.setItem(15, ItemUtils.getItem(Material.FEATHER, 1, ChatColor.AQUA + "Reset Selected Positions"));

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

}
