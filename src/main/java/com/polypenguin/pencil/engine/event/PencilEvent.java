package com.polypenguin.pencil.engine.event;

import com.polypenguin.pencil.engine.PencilPlayer;

import org.bukkit.event.Event;

/**
 * @author Matthias Kovacic
 *
 * Class that forces all Events to be able to get
 * the PencilPlayer from the Event.
 */
public abstract class PencilEvent extends Event {

    /**
     * Get the event executor as a PencilPlayer.
     *
     * @return The PencilPlayer;
     */
    public abstract PencilPlayer getPlayer();

}
