package com.polypenguin.crayon.core.gui;

import com.polypenguin.crayon.Crayon;
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
    public static enum SupportedInterfaceSize {
        SMALL(9),  MEDIUM(27),  LARGE(45),  HUGE(56);

        private int size;

        private SupportedInterfaceSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return this.size;
        }
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
        Inventory gui = Bukkit.createInventory(null, size.getSize(), Crayon.getPrefix() + name);
        if (isFilled) {
            while (gui.firstEmpty() != -1) {
                gui.setItem(gui.firstEmpty(), ItemUtils.getFillItem());
            }
        }

        if (isCloseable) {
            switch (size) {
                case SMALL:
                    gui.setItem(8, ItemUtils.getExitItem());
                case MEDIUM:
                    gui.setItem(16, ItemUtils.getExitItem());
                case LARGE:
                    gui.setItem(41, ItemUtils.getExitItem());
                case HUGE:
                    gui.setItem(52, ItemUtils.getExitItem());
            }
        }
        return gui;
    }

    public static void openInventory(CrayonPlayer player, Inventory inventory) {
        player.getPlayer().closeInventory();
        player.getPlayer().openInventory(inventory);
        player.getPlayer().updateInventory();
    }

    public static boolean isCrayonInventory(Inventory inventory) {
        return inventory.getName().contains(Crayon.getPrefix());
    }

}
