package com.polypenguin.crayon.core.service;

import com.polypenguin.crayon.engine.CrayonPlayer;
import org.bukkit.Bukkit;
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
