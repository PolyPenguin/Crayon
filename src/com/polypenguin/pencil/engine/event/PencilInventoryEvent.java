package com.polypenguin.pencil.engine.event;

import com.polypenguin.pencil.engine.PencilPlayer;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

/**
 * @author Matthias Kovacic
 *
 * PencilEvent that handles inventory action.
 */
public class PencilInventoryEvent extends PencilEvent {

    private static final HandlerList handlers = new HandlerList();
    private PencilPlayer player;
    private Inventory inventory;
    private int slot;

    /**
     * Constructor that gets all the information
     * needed to perform further action.
     *
     * @param player The player that has clicked the inventory.
     * @param inventory The inventory that has been clicked in.
     * @param slot The slot-number that has been clicked.
     */
    public PencilInventoryEvent(PencilPlayer player, Inventory inventory, int slot) {
        this.player = player;
        this.inventory = inventory;
        this.slot = slot;
    }

    @Override
    public PencilPlayer getPlayer() {
        return player;
    }

    /**
     * @return Get the inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @return Get the slot.
     */
    public int getSlot() {
        return slot;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
