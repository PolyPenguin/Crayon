package com.polypenguin.pencil.engine.geometry.selection;

import com.polypenguin.pencil.engine.geometry.Vector;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Selection used for null, when no selection is present.
 */
public class NullSelection implements Selection {

    @Override
    public Vector getNativeMinimum() {
        return null;
    }

    @Override
    public Vector getNativeMaximum() {
        return null;
    }

    @Override
    public org.bukkit.util.Vector getBukkitMinimum() {
        return null;
    }

    @Override
    public void setNativeMinimum(Vector min) {
        return;
    }

    @Override
    public org.bukkit.util.Vector getBukkitMaximum() {
        return null;
    }

    @Override
    public void setNativeMaximum(Vector min) {
        return;
    }

    @Override
    public int getBlocks() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public boolean contains(Vector vector) {
        return false;
    }

    @Override
    public ArrayList<Vector> getVectors(boolean filled) {
        return null;
    }

    @Override
    public SelectionType getSelectionType() {
        return null;
    }
}
