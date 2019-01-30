package com.polypenguin.crayon.core;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.core.gui.CrayonInterface;
import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.event.CrayonInventoryEvent;
import com.polypenguin.crayon.engine.event.CrayonItemEvent;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.ItemUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BaseListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onBasicInteraction(PlayerInteractEvent event) {
        if (event.getItem() == null) {
            return;
        }

        if (ItemUtils.isCrayonItem(event.getItem())) {
            event.setCancelled(true);

            Vector target = Vector.ZERO;

            if (event.getClickedBlock() != null) {
                target = new Vector(event.getClickedBlock().getLocation(), true);
            }

            CrayonPlayer player = Crayon.getPlayerService().getPlayer(event.getPlayer());
            Bukkit.getServer().getPluginManager().callEvent(new CrayonItemEvent(
                    player,
                    event.getAction(),
                    target,
                    event.getItem()
            ));
        } else {
            return;
        }
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onBasicInventory(InventoryClickEvent event) {
        if (CrayonInterface.isCrayonInventory(event.getClickedInventory())) {
            event.setResult(Event.Result.DENY);

            Bukkit.getServer().getPluginManager().callEvent(new CrayonInventoryEvent(
                    Crayon.getPlayerService().getPlayer((Player) event.getWhoClicked()),
                    event.getClickedInventory(),
                    event.getSlot()
            ));
        }
    }

}
