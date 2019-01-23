package com.polypenguin.crayon.core.service;

import com.polypenguin.crayon.engine.CrayonPlayer;

import org.bukkit.permissions.Permission;

/**
 * @author Matthias Kovacic
 *
 * Service that handles all user-permission related stuff.
 */
public class PermissionService {

    /**
     * Quick check to check a permission for a player.
     *
     * @param player Player the permission has to be checked for.
     * @param permission The permission that has to be checked.
     * @return True when the player has permission.
     */
    public boolean hasPermission(CrayonPlayer player, Permission permission) {
        return player.getPlayer().hasPermission(permission);
    }

}
