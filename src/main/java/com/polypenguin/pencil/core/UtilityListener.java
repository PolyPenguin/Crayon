package com.polypenguin.pencil.core;

import com.polypenguin.pencil.Pencil;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Matthias Kovacic
 *
 * Listener that handles miscellaneous tasks.
 */
public class UtilityListener implements Listener {

    /**
     * Register the joined player as a PencilPlayer.
     * //TODO: Only call this when Pencil is used (memory management)?
     * //TODO: Maybe use some utility class to get TPS of the server
     *
     * @param event Called when the player joins.
     */
    @EventHandler (priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent event) {
        Pencil.getPlayerService().addPlayer(event.getPlayer());
    }

    /**
     * Unregister the player.
     *
     * @param event Called when the player quits.
     */
    @EventHandler (priority = EventPriority.NORMAL)
    public void onQuit(PlayerQuitEvent event) {
        Pencil.getPlayerService().removePlayer(event.getPlayer());
    }

}
