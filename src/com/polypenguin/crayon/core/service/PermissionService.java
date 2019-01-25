package com.polypenguin.crayon.core.service;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.engine.CrayonPlayer;

import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

/**
 * @author Matthias Kovacic
 *
 * Service that handles all user-permission related stuff.
 */
public class PermissionService {

    private Permission menu_Perm = new Permission("crayon.guest.menu");

    /**
     * Quick check to check a permission for a player.
     *
     * @param player Player the permission has to be checked for.
     * @param permission The permission that has to be checked.
     * @return True when the player has permission.
     */
    public boolean hasPermission(CrayonPlayer player, Permission permission) {
        if (player.getPlayer().hasPermission(permission)) {
            return true;
        } else {
            player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Oops! Seems like you don't have permission to do this!");

            return false;
        }
    }

    public Permission getMenuPermission() {
        return menu_Perm;
    }
}
