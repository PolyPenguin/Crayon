package com.polypenguin.crayon.engine;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Matthias Kovacic
 *
 * Class that represents a player that used Crayon.
 */
public class CrayonPlayer {

    private Player player;
    private UUID playerID;

    /**
     * Constructor that retrieves basic info to be used by Crayon.
     *
     * @param player The represented player.
     */
    public CrayonPlayer(Player player) {
        this.player = player;
        this.playerID = player.getUniqueId();
    }

    /**
     * Return the original Bukkit player.
     *
     * @return The original Bukkit Player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Return the players' UUID.
     *
     * @return The players' UUID.
     */
    public UUID getPlayerID() {
        return playerID;
    }
}