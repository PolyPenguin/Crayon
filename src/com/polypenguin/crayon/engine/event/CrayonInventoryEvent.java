package com.polypenguin.crayon.engine.event;

import com.polypenguin.crayon.engine.CrayonPlayer;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

/**
 * @author Matthias Kovacic
 *
 * CrayonEvent that handles inventory action.
 */
public class CrayonInventoryEvent extends CrayonEvent {

    private static final HandlerList handlers = new HandlerList();
    private CrayonPlayer player;
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
    public CrayonInventoryEvent(CrayonPlayer player, Inventory inventory, int slot) {
        this.player = player;
        this.inventory = inventory;
        this.slot = slot;
    }

    @Override
    public CrayonPlayer getPlayer() {
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
