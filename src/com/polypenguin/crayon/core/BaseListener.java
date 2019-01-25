package com.polypenguin.crayon.core;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.core.gui.CrayonInterface;
import com.polypenguin.crayon.engine.event.CrayonInventoryEvent;
import com.polypenguin.crayon.engine.event.CrayonItemEvent;
import com.polypenguin.crayon.engine.utils.ItemUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BaseListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onBasicInteraction(PlayerInteractEvent event) {
        if (ItemUtils.isCrayonItem(event.getItem())) {
            Bukkit.getServer().getPluginManager().callEvent(new CrayonItemEvent(
                    Crayon.getPlayerService().getPlayer(event.getPlayer()),
                    event.getAction(),
                    event.getItem()
            ));
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBasicInventory(InventoryClickEvent event) {
        if (CrayonInterface.isCrayonInventory(event.getClickedInventory())) {
            Bukkit.getServer().getPluginManager().callEvent(new CrayonInventoryEvent(
                    Crayon.getPlayerService().getPlayer((Player) event.getWhoClicked()),
                    event.getClickedInventory(),
                    event.getSlot()
            ));
        }
    }

}
