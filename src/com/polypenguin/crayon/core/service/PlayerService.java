package com.polypenguin.crayon.core.service;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.engine.CrayonPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Matthias Kovacic
 *
 * Service that handles player to Crayon-player conversions.
 */
public class PlayerService {

    private HashMap<UUID, CrayonPlayer> players;

    /**
     * Basic constructor that initiates the service.
     */
    public PlayerService() {
        players = new HashMap<>();

        init();
    }

    /**
     * Prepares Crayon and all online players to use Crayon.
     */
    protected void init() {
        players.clear();

        for (Player player : Bukkit.getOnlinePlayers()) {
            players.put(player.getUniqueId(), new CrayonPlayer(player));
        }
    }

    public void addPlayer(Player player) {
        if (getPlayer(player) != null) {
            //TODO: Make this notification-possible

            player.kickPlayer(Crayon.getPrefix() + ChatColor.RED + "Oops! Please join again.");
        }

        players.put(player.getUniqueId(), new CrayonPlayer(player));
    }

    public void removePlayer(Player player) {
        if (getPlayer(player) == null) {
            throw new IllegalStateException("[Crayon] Console Notification: Illegal State of " + player.getName() + ", no Crayon ID found!");
        }

        players.remove(player.getUniqueId());
    }

    /**
     * Finds a CrayonPlayer when a player is given.
     *
     * @param player The player that has to be found.
     * @return The player if it has been found.
     */
    public CrayonPlayer getPlayer(Player player) {
        if (players.containsKey(player.getUniqueId())) {
            return players.get(player.getUniqueId());
        }

        return null;
    }
}
