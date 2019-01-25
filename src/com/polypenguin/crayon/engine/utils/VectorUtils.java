package com.polypenguin.crayon.engine.utils;

import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.geometry.selection.CuboidSelection;
import com.polypenguin.crayon.engine.geometry.selection.Selection;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class VectorUtils {

    public String toString(Vector vector) {
        return ChatColor.DARK_GRAY + "[" + ChatColor.RED +
                vector.getBlockX() + ChatColor.WHITE + ", " + ChatColor.GREEN +
                vector.getBlockY() + ChatColor.WHITE + ", " + ChatColor.BLUE +
                vector.getBlockZ() + ChatColor.DARK_GRAY + "]";
    }

    public static ArrayList<Vector> getCuboid(CuboidSelection selection, boolean isFilled) {
        return isFilled ? getCuboidFilled(selection) : getCuboidUnfilled(selection);
    }

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
     * @// FIXME: 25/01/2019 
     * 
     * @param selection
     * @return
     */
    private static ArrayList<Vector> getCuboidUnfilled(CuboidSelection selection) {
        Vector min = selection.getNativeMinimum();
        Vector max = selection.getNativeMaximum();

        ArrayList<Selection> vectors = new ArrayList<>();

        new CuboidSelection(min.setVectorX(min.getX()), max.setVectorX(min.getX()));
    }

}
