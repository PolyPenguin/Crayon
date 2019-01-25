package com.polypenguin.crayon.engine.utils;

import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.geometry.selection.CuboidSelection;
import com.polypenguin.crayon.engine.geometry.selection.Selection;

import org.bukkit.ChatColor;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Miscellaneous utilities that handle vectors related stuff.
 */
public class VectorUtils {

    /**
     * Create a custom String version of a vector.
     *
     * @param vector The used vector.
     * @return A string which contains X, Y and Z values of the vector.
     */
    public static String toString(Vector vector) {
        return ChatColor.DARK_GRAY + "[" + ChatColor.RED +
                vector.getBlockX() + ChatColor.WHITE + ", " + ChatColor.GREEN +
                vector.getBlockY() + ChatColor.WHITE + ", " + ChatColor.BLUE +
                vector.getBlockZ() + ChatColor.DARK_GRAY + "]";
    }

    /**
     * Get the vectors of a list of selections.
     * This would be extremely helpful when doing multiple operations
     *
     * @param selections The selections which vectors should be added.
     * @return An ArrayList of vectors that make up the selections.
     */
    public static ArrayList<Vector> getTrueVectors(ArrayList<Selection> selections) {
        ArrayList<Vector> vectors = new ArrayList<>();

        for (Selection selection : selections) {
            for (Vector vector : selection.getVectors(true)) {
                vectors.add(vector);
            }
        }

        return vectors;
    }

    /**
     * Get the vectors for a cuboid created by two given vectors.
     *
     * @param selection Contains the two vectors which should be used.
     * @param isFilled Whether or not the cuboid should be filled or not.
     * @return An ArrayList of vectors that make up the cuboid.
     */
    public static ArrayList<Vector> getCuboid(CuboidSelection selection, boolean isFilled) {
        return isFilled ? getCuboidFilled(selection) : getCuboidUnfilled(selection);
    }

    /**
     * Algorithm for a filled cuboid.
     *
     * @param selection Contains the two vectors which should be used as a reference.
     * @return An ArrayList of vectors that make up the cuboid.
     */
    private static ArrayList<Vector> getCuboidFilled(CuboidSelection selection) {
        Vector min = selection.getNativeMinimum();
        Vector max = selection.getNativeMaximum();

        ArrayList<Vector> vectors = new ArrayList();

        for(int x = Math.max(max.getBlockX(), min.getBlockX()); x >= Math.min(min.getBlockX(), max.getBlockX()); --x) {
            for(int y = Math.max(max.getBlockY(), min.getBlockY()); y >= Math.min(min.getBlockY(), max.getBlockY()); --y) {
                for(int z = Math.max(max.getBlockZ(), min.getBlockZ()); z >= Math.min(min.getBlockZ(), max.getBlockZ()); --z) {
                    vectors.add(new Vector(x, y, z));
                }
            }
        }

        return vectors;
    }

    /**
     * Algorithm for an unfilled cuboid.
     *
     * @param selection Contains the two vectors which should be used as a reference.
     * @return An ArrayList of vectors that make up the cuboid.
     */
    private static ArrayList<Vector> getCuboidUnfilled(CuboidSelection selection) {
        ArrayList<Vector> vectors = new ArrayList<>();

        for (Selection sub : getCuboidWalls(selection)) {
            for (Vector vector : sub.getVectors(false)) {
                vectors.add(vector);
            }
        }

        return vectors;
    }

    /**
     * Get the walls of a cuboid selection in different selections.
     *
     * @param selection The cuboid selection walls need to be made for.
     * @return The walls in an ArrayList.
     */
    public static ArrayList<Selection> getCuboidWalls(CuboidSelection selection) {
        Vector min = selection.getNativeMinimum();
        Vector max = selection.getNativeMaximum();

        ArrayList<Selection> selections = new ArrayList<>();

        selections.add(new CuboidSelection(min.setVectorX(min.getX()), max.setVectorX(min.getX())));
        selections.add(new CuboidSelection(min.setVectorX(max.getX()), max.setVectorX(max.getX())));
        selections.add(new CuboidSelection(min.setVectorZ(min.getZ()), max.setVectorZ(min.getZ())));
        selections.add(new CuboidSelection(min.setVectorZ(max.getZ()), max.setVectorZ(max.getZ())));
        selections.add(new CuboidSelection(min.setVectorY(min.getY()), max.setVectorY(min.getY())));
        selections.add(new CuboidSelection(min.setVectorY(max.getY()), max.setVectorY(max.getY())));

        return selections;
    }

}
