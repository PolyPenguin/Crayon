package com.polypenguin.crayon.engine;

import com.polypenguin.crayon.engine.manager.ActionManager;
import com.polypenguin.crayon.engine.manager.SelectionManager;

import com.polypenguin.crayon.engine.operation.CrayonOperation;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Matthias Kovacic
 *
 * Class that represents a player that used Crayon.
 */
public class CrayonPlayer {

    public enum SelectionMode {
        NA,
        SINGLE,
        DOUBLE,
        MULTI
    }

    private Player player;
    private UUID playerID;

    private SelectionMode selectionMode;
    private Clipboard clipboard;
    private CrayonOperation operation;

    private ActionManager actionManager;
    private SelectionManager selectionManager;

    /**
     * Constructor that retrieves basic info to be used by Crayon.
     *
     * @param player The represented player.
     */
    public CrayonPlayer(Player player) {
        this.player = player;
        this.playerID = player.getUniqueId();

        this.selectionMode = SelectionMode.NA;
        this.clipboard = new Clipboard(this);

        this.actionManager = new ActionManager(this);
        this.selectionManager = new SelectionManager(this);
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

    /**
     * Return the players' selection mode.
     *
     * @return The players' selection mode.
     */
    public SelectionMode getSelectionMode() {
        return selectionMode;
    }

    /**
     * Return the players' clipboard.
     *
     * @return The players' clipboard.
     */
    public Clipboard getClipboard() {
        return clipboard;
    }

    /**
     * Set the players' selection mode.
     */
    public void setSelectionMode(SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
    }

    /**
     * Return the players' operation.
     *
     * @return The players' operation.
     */
    public CrayonOperation getOperation() {
        return operation;
    }

    /**
     * Set the players' operation.
     */
    public void setOperation(CrayonOperation operation) {
        this.operation = operation;
    }

    /**
     * See the setOperation() method.
     */
    public void resetOperation() {
        setOperation(null);
    }

    /**
     * Return the player's action manager.
     *
     * @return The player's action manager.
     */
    public ActionManager getActionManager() {
        return actionManager;
    }

    /**
     * Return the player's selection manager.
     *
     * @return The player's Selection manager.
     */
    public SelectionManager getSelectionManager() {
        return selectionManager;
    }
}
