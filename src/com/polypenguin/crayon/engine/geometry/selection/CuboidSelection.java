package com.polypenguin.crayon.engine.geometry.selection;

import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.VectorUtils;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Selection used for Cuboids.
 * Can also be used to store two vectors.
 */
public class CuboidSelection implements Selection {

    private Vector min, max;

    public CuboidSelection(Vector min, Vector max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Vector getNativeMinimum() {
        return min;
    }

    @Override
    public void setNativeMinimum(Vector min) {
        this.min = min;
    }

    @Override
    public Vector getNativeMaximum() {
        return max;
    }

    @Override
    public void setNativeMaximum(Vector max) {
        this.max = max;
    }

    @Override
    public org.bukkit.util.Vector getBukkitMinimum() {
        return new org.bukkit.util.Vector(min.getX(), min.getY(), min.getZ());
    }

    @Override
    public org.bukkit.util.Vector getBukkitMaximum() {
        return new org.bukkit.util.Vector(max.getX(), max.getY(), max.getZ());
    }

    @Override
    public int getBlocks() {
        return getVectors(true).size();
    }

    @Override
    public int getWidth() {
        return Math.abs(this.min.getBlockX() - this.max.getBlockX());
    }

    @Override
    public int getHeight() {
        return Math.abs(this.min.getBlockY() - this.max.getBlockY());
    }

    @Override
    public int getLength() {
        return Math.abs(this.min.getBlockZ() - this.max.getBlockZ());
    }

    @Override
    public boolean contains(Vector vector) {
        return vector.containedWithinBlock(min, max);
    }

    @Override
    public ArrayList<Vector> getVectors(boolean filled) {
        return VectorUtils.getCuboid(this);
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.DOUBLE;
    }
}
