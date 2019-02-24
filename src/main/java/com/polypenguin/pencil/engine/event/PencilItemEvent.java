package com.polypenguin.pencil.engine.event;

import com.polypenguin.pencil.engine.PencilPlayer;

import com.polypenguin.pencil.engine.geometry.Vector;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class PencilItemEvent extends PencilEvent {

    private static final HandlerList handlers = new HandlerList();
    private PencilPlayer player;
    private Action action;
    private Vector target;
    private ItemStack item;

    /**
     * Constructor that gets all the information
     * needed to perform further action.
     *
     * @param player The player that has interacted.
     * @param action The action the player has performed (Bukkit).
     * @param target The vector of the clicked block.
     * @param item The item the player had in his hand.
     */
    public PencilItemEvent(PencilPlayer player, Action action, Vector target, ItemStack item) {
        this.player = player;
        this.action = action;
        this.target = target;
        this.item = item;
    }

    @Override
    public PencilPlayer getPlayer() {
        return player;
    }

    /**
     * @return Get the action.
     */
    public Action getAction() {
        return action;
    }

    /**
     * @return Get the target.
     */
    public Vector getTarget() {
        return target;
    }

    /**
     * @return Get the item.
     */
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
