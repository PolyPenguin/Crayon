package com.polypenguin.crayon.engine.geometry.selection;

import com.polypenguin.crayon.engine.geometry.Vector;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 */
public abstract interface Selection {

    public enum SelectionType {
        SINGLE,
        DOUBLE,
        MULTI,
        SHAPE
    }

    Vector getNativeMinimum();

    void setNativeMinimum(Vector min);

    Vector getNativeMaximum();

    void setNativeMaximum(Vector max);

    org.bukkit.util.Vector getBukkitMinimum();

    org.bukkit.util.Vector getBukkitMaximum();

    int getBlocks();

    int getWidth();

    int getHeight();

    int getLength();

    boolean contains(Vector vector);

    ArrayList<Vector> getVectors(boolean filled);

    /*
    ArrayList<CuboidSelection> getWalls();
    */

    SelectionType getSelectionType();

}
