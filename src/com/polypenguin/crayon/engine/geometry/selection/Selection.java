package com.polypenguin.crayon.engine.geometry.selection;

import com.polypenguin.crayon.engine.geometry.Vector;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 */
public abstract interface Selection {

    public enum SelectionType {
        SINGLE,
        DOUBLE,
        MULTI
    }


    Vector getNativeMinimum();

    Vector getNativeMaximum();

    org.bukkit.util.Vector getBukkitMinimum();

    org.bukkit.util.Vector getBukkitMaximum();

    int getBlocks();

    int getWidth();

    int getHeight();

    int getLength();

    boolean contains(Vector vector);

    ArrayList<Vector> getVectors();

    /*
    ArrayList<CuboidSelection> getWalls();
    */

    SelectionType getSelectionType();

}
