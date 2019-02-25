package com.polypenguin.pencil.engine.utils.miscellaneous;

import com.polypenguin.pencil.engine.geometry.Vector;

import org.bukkit.Material;

/**
 * @author Matthias Kovacic
 *
 * Pencil Pre-States are a special kind of Pencil States.
 * They only save the outdated material.
 */
public class PencilPreState {

    private Vector offset;
    private Material material;

    public PencilPreState(Vector offset, Material material) {
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
