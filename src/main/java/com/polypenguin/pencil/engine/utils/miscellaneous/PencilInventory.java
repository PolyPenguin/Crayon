package com.polypenguin.pencil.engine.utils.miscellaneous;

import com.polypenguin.pencil.engine.PencilPlayer;
import org.bukkit.inventory.ItemStack;

public class PencilInventory {

    private PencilPlayer player;
    private ItemStack[] items, armor, storage, extra;

    public PencilInventory(PencilPlayer player) {
        this.player = player;
        this.items = player.getPlayer().getInventory().getContents();
        this.armor = player.getPlayer().getInventory().getArmorContents();
        this.storage = player.getPlayer().getInventory().getStorageContents();
        this.extra = player.getPlayer().getInventory().getExtraContents();
    }

    public PencilPlayer getPlayer() {
        return player;
    }

    public ItemStack[] getContents() {
        return items;
    }

    public ItemStack[] getArmorContents() {
        return armor;
    }

    public ItemStack[] getStorageContents() {
        return storage;
    }

    public ItemStack[] getExtraContents() {
        return extra;
    }

    public void prep() {
        update();
        flush();
    }

    public void flush() {
        player.getPlayer().getInventory().clear();
    }

    public void refill() {
        player.getPlayer().getInventory().setContents(items);
        player.getPlayer().getInventory().setArmorContents(armor);
        player.getPlayer().getInventory().setStorageContents(storage);
        player.getPlayer().getInventory().setExtraContents(extra);
    }

    public void update() {
        this.items = player.getPlayer().getInventory().getContents();
        this.armor = player.getPlayer().getInventory().getArmorContents();
        this.storage = player.getPlayer().getInventory().getStorageContents();
        this.extra = player.getPlayer().getInventory().getExtraContents();
    }
}
