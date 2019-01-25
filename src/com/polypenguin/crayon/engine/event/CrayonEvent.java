package com.polypenguin.crayon.engine.event;

import com.polypenguin.crayon.engine.CrayonPlayer;

import org.bukkit.event.Event;

/**
 * @author Matthias Kovacic
 *
 * Class that forces all Events to be able to get
 * the CrayonPlayer from the Event.
 */
public abstract class CrayonEvent extends Event {

    /**
     * Get the event executor as a CrayonPlayer.
     *
     * @return The CrayonPlayer;
     */
    public abstract CrayonPlayer getPlayer();

}
