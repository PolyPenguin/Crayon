package com.polypenguin.crayon.engine.utils.miscellaneous;

import com.polypenguin.crayon.engine.geometry.Vector;
import org.bukkit.Material;

/**
 * @author Matthias Kovacic
 *
 * Crayon States save the outdated and updated
 * material for a certain vector in the world.
 */
public class CrayonState {

    private Vector vector;
    private Material first, second;

    public CrayonState(Vector vector, Material first, Material second) {
        this.vector = vector;
        this.first = first;
        this.second = second;
    }

    public Vector getVector() { return vector; }

    public Material getOutdated() {
        return first;
    }

    public Material getUpdated() {
        return second;
    }
}
