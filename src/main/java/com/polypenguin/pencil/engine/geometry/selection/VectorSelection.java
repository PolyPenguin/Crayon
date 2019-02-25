package com.polypenguin.pencil.engine.geometry.selection;

import com.polypenguin.pencil.engine.geometry.Vector;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Matthias Kovacic
 *
 * Selection used for single-vector selections.
 */
public class VectorSelection implements Selection {

    private Vector min;

    public VectorSelection(Vector min) {
        this.min = min;
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
        return min;
    }

    @Override
    public void setNativeMaximum(Vector max) {
        this.min = max;
    }

    @Override
    public org.bukkit.util.Vector getBukkitMinimum() {
        return new org.bukkit.util.Vector(min.getX(), min.getY(), min.getZ());
    }

    @Override
    public org.bukkit.util.Vector getBukkitMaximum() {
        return new org.bukkit.util.Vector(min.getX(), min.getY(), min.getZ());
    }

    @Override
    public int getBlocks() {
        return 1;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public boolean contains(Vector vector) {
        return ((vector.getBlockX() == min.getBlockX()) &&
                (vector.getBlockY() == min.getBlockY()) &&
                (vector.getBlockZ() == min.getBlockZ()));
    }

    @Override
    public ArrayList<Vector> getVectors(boolean filled) {
        return new ArrayList<>(Collections.singleton(min));
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SINGLE;
    }
}
