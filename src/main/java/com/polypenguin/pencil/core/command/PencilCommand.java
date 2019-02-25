package com.polypenguin.pencil.core.command;

import com.polypenguin.pencil.engine.PencilPlayer;

import org.bukkit.permissions.Permission;

/**
 * @author Matthias Kovacic
 *
 * An abstract command used for Pencil-related commands.
 */
public abstract class PencilCommand {

    /**
     * Execute specific command.
     *
     * @param player Player that executes the command.
     * @param args Arguments the player has provided.
     */
    public abstract void onCommand(PencilPlayer player, String[] args);

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getArguments();

    public abstract Permission getPermission();

}
