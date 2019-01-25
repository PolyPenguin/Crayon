package com.polypenguin.crayon.engine.event;

import com.polypenguin.crayon.engine.CrayonPlayer;

import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class CrayonItemEvent extends CrayonEvent {

    private static final HandlerList handlers = new HandlerList();
    private CrayonPlayer player;
    private Action action;
    private ItemStack item;

    public CrayonItemEvent(CrayonPlayer player, Action action, ItemStack item) {
        this.player = player;
        this.action = action;
        this.item = item;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return null;
    }

    public Action getAction() {
        return action;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
