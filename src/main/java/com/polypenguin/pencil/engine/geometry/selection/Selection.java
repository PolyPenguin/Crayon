package com.polypenguin.pencil.engine.geometry.selection;

import com.polypenguin.pencil.engine.geometry.Vector;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Interface for selections.
 */
public abstract interface Selection {

    /**
     * Types of selections.
     */
    public enum SelectionType {
        SINGLE,
        DOUBLE,
        MULTI,
        SHAPE
    }

    /**
     * @return Get the minimum native vector.
     */
    Vector getNativeMinimum();

    /**
     * Set the minimum native vector to a new value.
     *
     * @param min The value the minimum native vector should be set to.
     */
    void setNativeMinimum(Vector min);

    /**
     * @return Get the maximum native vector.
     */
    Vector getNativeMaximum();

    /**
     * Set the maximum native vector to a new value.
     *
     * @param max The value the maximum native vector should be set to.
     */
    void setNativeMaximum(Vector max);

    /**
     * @return The minimum vector in Bukkit format.
     */
    org.bukkit.util.Vector getBukkitMinimum();

    /**
     * @return The maximum vector in Bukkit format.
     */
    org.bukkit.util.Vector getBukkitMaximum();

    /**
     * @return The amount of blocks in the selection.
     */
    int getBlocks();

    /**
     * @return The width of the selection.
     */
    int getWidth();

    /**
     * @return The height of the selection.
     */
    int getHeight();

    /**
     * @return The length of the selection.
     */
    int getLength();

    /**
     * Check if the selection contains a vector.
     *
     * @param vector The vector that has to be checked.
     * @return True if the selection contains the vector.
     */
    boolean contains(Vector vector);

    /**
     * Get the vectors that make up the selection.
     *
     * @param filled Whether the selection should be seen as filled or not.
     * @return All the vectors that make up the selection.
     */
    ArrayList<Vector> getVectors(boolean filled);

    /**
     * @return The type of selection.
     */
    SelectionType getSelectionType();

}
