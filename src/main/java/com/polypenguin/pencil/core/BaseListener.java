package com.polypenguin.pencil.core;

import com.polypenguin.pencil.Pencil;
import com.polypenguin.pencil.core.gui.PencilInterface;
import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.event.PencilInventoryEvent;
import com.polypenguin.pencil.engine.event.PencilItemEvent;
import com.polypenguin.pencil.engine.geometry.Vector;
import com.polypenguin.pencil.engine.utils.ItemUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Matthias Kovacic
 *
 * Listener that listens for basic player interaction
 * and passes it to Pencil as a Pencil-related event.
 */
public class BaseListener implements Listener {

    /**
     * Listens for basic player interaction.
     */
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

            PencilPlayer player = Pencil.getPlayerService().getPlayer(event.getPlayer());
            Bukkit.getServer().getPluginManager().callEvent(new PencilItemEvent(
                    player,
                    event.getAction(),
                    target,
                    event.getItem()
            ));
        }
    }

    /**
     * Listens for basic player inventory action.
     */
    @EventHandler (priority = EventPriority.LOW)
    public void onBasicInventory(InventoryClickEvent event) {
        if (PencilInterface.isCrayonInventory(event.getClickedInventory())) {
            event.setResult(Event.Result.DENY);

            Bukkit.getServer().getPluginManager().callEvent(new PencilInventoryEvent(
                    Pencil.getPlayerService().getPlayer((Player) event.getWhoClicked()),
                    event.getClickedInventory(),
                    event.getSlot()
            ));
        }
    }

}
