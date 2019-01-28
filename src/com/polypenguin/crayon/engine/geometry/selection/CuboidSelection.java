package com.polypenguin.crayon.engine.geometry.selection;

import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.VectorUtils;

import java.util.ArrayList;

public class CuboidSelection implements Selection {

    private Vector min, max;
    private ArrayList<Vector> filled, unfilled;

    public CuboidSelection(Vector min, Vector max) {
        this.min = min;
        this.max = max;

        //Get the vectors for the cuboid to keep down memory usage as much as possible.
        this.filled = VectorUtils.getCuboid(this, true);
        this.unfilled = VectorUtils.getTrueVectors(VectorUtils.getCuboidWalls(this));
    }

    @Override
    public Vector getNativeMinimum() {
        return min;
    }

    @Override
    public Vector getNativeMaximum() {
        return max;
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
        return (filled) ? this.filled : unfilled;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.DOUBLE;
    }
}
