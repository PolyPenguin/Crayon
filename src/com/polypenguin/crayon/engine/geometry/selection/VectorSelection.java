package com.polypenguin.crayon.engine.geometry.selection;

import com.polypenguin.crayon.engine.geometry.Vector;

import java.util.ArrayList;
import java.util.Collections;

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
    public Vector getNativeMaximum() {
        return min;
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
