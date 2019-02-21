package com.polypenguin.pencil.core.service;

import com.polypenguin.pencil.Pencil;
import com.polypenguin.pencil.core.BaseListener;
import com.polypenguin.pencil.core.UtilityListener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * @author Matthias Kovacic
 *
 * Service that handles all event-listening tasks.
 */
public class ListenerService {

    /**
     * Constructor that register the necessary listeners in order
     * for Pencil to work.
     */
    public ListenerService() {
        Bukkit.getServer().getPluginManager().registerEvents(new BaseListener(), Pencil.getPencil());
        Bukkit.getServer().getPluginManager().registerEvents(new UtilityListener(), Pencil.getPencil());
    }

    /**
     * Register a listener after base registering in the constructor.
     *
     * @param listener The listener that has to be registered.
     */
    public void registerEvents(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, Pencil.getPencil());
    }
}
