package com.polypenguin.crayon.core.command;

import com.polypenguin.crayon.engine.CrayonPlayer;

import org.bukkit.permissions.Permission;

/**
 * @author Matthias Kovacic
 *
 * An abstract command used for Crayon-related commands.
 */
public abstract class CrayonCommand {

    /**
     * Execute specific command.
     *
     * @param player Player that executes the command.
     * @param args Arguments the player has provided.
     */
    public abstract void onCommand(CrayonPlayer player, String[] args);

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getArguments();

    public abstract Permission getPermission();

}
