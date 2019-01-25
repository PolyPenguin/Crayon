package com.polypenguin.crayon.core.service;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.core.BaseListener;
import com.polypenguin.crayon.core.UtilityListener;

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
     * for Crayon to work.
     */
    public ListenerService() {
        Bukkit.getServer().getPluginManager().registerEvents(new BaseListener(), Crayon.getCrayon());
        Bukkit.getServer().getPluginManager().registerEvents(new UtilityListener(), Crayon.getCrayon());
    }

    /**
     * Register a listener after base registering in the constructor.
     *
     * @param listener The listener that has to be registered.
     */
    public void registerEvents(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, Crayon.getCrayon());
    }
}
