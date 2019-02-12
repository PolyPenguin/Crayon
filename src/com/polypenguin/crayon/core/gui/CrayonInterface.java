package com.polypenguin.crayon.core.gui;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

/**
 * @author Matthias Kovacic
 *
 * Class that handles UI creation for Crayon GUIs.
 */
public class CrayonInterface {

    /**
     * Supported UI sizes for Crayon GUIs.
     */
    public enum SupportedInterfaceSize {
        SMALL(9),  MEDIUM(27),  LARGE(45),  HUGE(54);

        int size;

        SupportedInterfaceSize(int size) {
            this.size = size;
        }

        public int getSize() { return size; }
    }

    /**
     * Generates a Crayon UI with given parameters.
     *
     * @param name The name of the UI.
     * @param size The size of the UI.
     * @param isFilled Whether the UI should be filled with a filling material or not.
     * @param isCloseable Whether the UI should be closeable or not.
     * @return A newly generated Crayon UI.
     */
    public static Inventory createCrayonInterface(String name, SupportedInterfaceSize size, boolean isFilled, boolean isCloseable) {
        Inventory gui = Bukkit.createInventory(null, size.getSize(), name);

        if (isFilled) {
            while (gui.firstEmpty() != -1) {
                gui.setItem(gui.firstEmpty(), ItemUtils.getFillItem());
            }
        }

        if (isCloseable) {
            if (size == SupportedInterfaceSize.SMALL) {
                gui.setItem(8, ItemUtils.getExitItem());
            } else if (size == SupportedInterfaceSize.MEDIUM) {
                gui.setItem(16, ItemUtils.getExitItem());
            } else if (size == SupportedInterfaceSize.LARGE) {
                gui.setItem(41, ItemUtils.getExitItem());
            } else if (size == SupportedInterfaceSize.HUGE) {
                gui.setItem(49, ItemUtils.getExitItem());
            }
        }

        return gui;
    }

    /**
     * Open the inventory of a player the easy way.
     *
     * @param player The player an inventory has to be opened for.
     * @param inventory The inventory that has to be opened.
     */
    public static void openInventory(CrayonPlayer player, Inventory inventory) {
        player.getPlayer().closeInventory();
        player.getPlayer().openInventory(inventory);
        player.getPlayer().updateInventory();
    }

    /**
     * Check whether an inventory is Crayon-related or not.
     *
     * @param inventory The inventory that has to be checked.
     * @return True if the inventory is Crayon-related.
     */
    public static boolean isCrayonInventory(Inventory inventory) {
        return inventory.getName().contains("Crayon");
    }

}
