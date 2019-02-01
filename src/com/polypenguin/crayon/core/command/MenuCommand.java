package com.polypenguin.crayon.core.command;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.utils.ItemUtils;

import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

/**
 * @author Matthias Kovacic
 *
 * Command which handles requests for the Crayon menu.
 */
public class MenuCommand extends CrayonCommand {

    @Override
    public void onCommand(CrayonPlayer player, String[] args) {
        if (player.getPlayer().getInventory().firstEmpty() != -1) {
            player.getPlayer().getInventory().addItem(ItemUtils.getMenuItem());
            player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "You received the Crayon menu");
        } else {
            player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Seems like you inventory is full");
        }
    }

    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public String getDescription() {
        return "Gives you the Crayon Menu";
    }

    @Override
    public String getArguments() {
        return "";
    }

    @Override
    public Permission getPermission() {
        return Crayon.getPermissionService().getMenuPermission();
    }
}
