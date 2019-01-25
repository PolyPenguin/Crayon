package com.polypenguin.crayon.engine.event;

import com.polypenguin.crayon.engine.CrayonPlayer;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

public class CrayonInventoryEvent extends CrayonEvent {

    private static final HandlerList handlers = new HandlerList();
    private CrayonPlayer player;
    private Inventory inventory;
    private int slot;

    public CrayonInventoryEvent(CrayonPlayer player, Inventory inventory, int slot) {
        this.player = player;
        this.inventory = inventory;
        this.slot = slot;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

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
