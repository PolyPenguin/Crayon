package com.polypenguin.crayon.engine.utils.miscellaneous;

import com.polypenguin.crayon.engine.geometry.Vector;
import org.bukkit.Material;

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

    public Material getMaterial() {
        return material;
    }
}
