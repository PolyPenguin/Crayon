package com.polypenguin.crayon.engine.utils.miscellaneous;

import com.polypenguin.crayon.engine.geometry.Vector;

import org.bukkit.Material;

/**
 * @author Matthias Kovacic
 *
 * Crayon Pre-States are a special kind of Crayon States.
 * They only save the outdated material.
 */
public class CrayonPreState {

    private Vector offset;
    private Material material;

    public CrayonPreState(Vector offset, Material material) {
        this.offset = offset;
        this.material = material;
    }

    public Vector getOffset() {
        return offset;
    }

    public void setOffset(Vector offset) {
        this.offset = offset;
    }

    public Material getMaterial() {
        return material;
    }
}
