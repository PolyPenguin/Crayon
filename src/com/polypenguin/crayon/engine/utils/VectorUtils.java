package com.polypenguin.crayon.engine.utils;

import com.polypenguin.crayon.engine.geometry.Vector;
import org.bukkit.ChatColor;

public class VectorUtils {

    public String toString(Vector vector) {
        return ChatColor.DARK_GRAY + "[" + ChatColor.RED +
                vector.getBlockX() + ChatColor.WHITE + ", " + ChatColor.GREEN +
                vector.getBlockY() + ChatColor.WHITE + ", " + ChatColor.BLUE +
                vector.getBlockZ() + ChatColor.DARK_GRAY + "]";
    }

}
