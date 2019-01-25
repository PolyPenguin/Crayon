package com.polypenguin.crayon.engine;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.core.gui.CrayonInterface;
import com.polypenguin.crayon.engine.event.CrayonInventoryEvent;
import com.polypenguin.crayon.engine.event.CrayonItemEvent;
import com.polypenguin.crayon.engine.utils.InterfaceUtils;
import com.polypenguin.crayon.engine.utils.ItemUtils;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class CrayonListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInteract(CrayonItemEvent event) {
        if (event.getItem().equals(ItemUtils.getMenuItem())) {
            CrayonInterface.openInventory(event.getPlayer(), InterfaceUtils.getCrayonMenu());
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInventory(CrayonInventoryEvent event) {
        CrayonPlayer player = event.getPlayer();
        Inventory inventory = event.getInventory();
        int slot = event.getSlot();

        if (inventory.getName().contains("Menu")) {
            if (inventory.getName().contains("Main")) {
                if (slot == 10) {
                    if (player.getPlayer().getInventory().firstEmpty() != -1) {
                        player.getPlayer().getInventory().addItem(ItemUtils.getWandItem());
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "You received the Crayon menu.");
                    } else {
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Seems like you inventory is full!");
                    }
                } else if (slot == 11) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getPositionMenu());
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Wand")) {
                if (slot == 10) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getPositionMenu());
                } else if (slot == 11) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getGenerateMenu());
                }  else if (slot == 12) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getOperationsMenu());
                }  else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        }
    }

}
