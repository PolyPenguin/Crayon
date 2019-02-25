package com.polypenguin.pencil.core.service;

import com.polypenguin.pencil.Pencil;
import com.polypenguin.pencil.engine.PencilPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Matthias Kovacic
 *
 * Service that handles player to Pencil-player conversions.
 */
public class PlayerService {

    private HashMap<UUID, PencilPlayer> players;

    /**
     * Basic constructor that initiates the service.
     */
    public PlayerService() {
        players = new HashMap<>();

        init();
    }

    /**
     * Prepares Pencil and all online players to use Pencil.
     */
    protected void init() {
        players.clear();

        for (Player player : Bukkit.getOnlinePlayers()) {
            players.put(player.getUniqueId(), new PencilPlayer(player));
        }
    }

    public void addPlayer(Player player) {
        if (getPlayer(player) != null) {
            //TODO: Make this notification-possible

            player.kickPlayer(Pencil.getPrefix() + ChatColor.RED + "Oops! Please join again.");
        }

        players.put(player.getUniqueId(), new PencilPlayer(player));
    }

    public void removePlayer(Player player) {
        if (getPlayer(player) == null) {
            throw new IllegalStateException("[Pencil] Console Notification: Illegal State of " + player.getName() + ", no Pencil ID found!");
        }

        players.remove(player.getUniqueId());
    }

    /**
     * Finds a PencilPlayer when a player is given.
     *
     * @param player The player that has to be found.
     * @return The player if it has been found.
     */
    public PencilPlayer getPlayer(Player player) {
        if (players.containsKey(player.getUniqueId())) {
            return players.get(player.getUniqueId());
        }

        return null;
    }
}
