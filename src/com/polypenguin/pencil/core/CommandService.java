package com.polypenguin.pencil.core;

import com.polypenguin.pencil.Pencil;
import com.polypenguin.pencil.core.command.PencilCommand;
import com.polypenguin.pencil.core.command.MenuCommand;
import com.polypenguin.pencil.engine.PencilPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Matthias Kovacic
 *
 * Service that handles all Pencil related commands.
 */
public class CommandService implements CommandExecutor {

    private ArrayList<PencilCommand> commands;

    /**
     * Constructor that adds all Pencil commands in the game.
     */
    public CommandService() {
        commands = new ArrayList<>();
        commands.add(new MenuCommand());
    }

    /**
     * Custom method that allows for a more controlled way over command handling.
     *
     * @param sender The sender of the command.
     * @param cmd The command that has been send.
     * @param arguments The arguments the sender has provided.
     * @return True when a command was successfully processed.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arguments) {
        if (cmd.getName().equalsIgnoreCase("pencil")) {
            if (sender instanceof Player) {
                PencilPlayer player = Pencil.getPlayerService().getPlayer((Player) sender);

                if ((arguments.length == 0) || arguments[0].equalsIgnoreCase("help")) {
                    for (PencilCommand command : commands) {
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "/" + command.getName() + " " + command.getArguments() +
                                ChatColor.WHITE + " - " + ChatColor.YELLOW + command.getDescription());
                    }

                    return true;
                }

                for (PencilCommand command : commands) {
                    if (command.getName().equals(arguments[0])) {
                        if (Pencil.getPermissionService().hasPermission(player, command.getPermission()) || player.getPlayer().isOp()) {
                            String[] args = Arrays.copyOfRange(arguments, 1, arguments.length);

                            command.onCommand(player, args);

                            return true;
                        } else {
                            sender.sendMessage(Pencil.getPrefix() + ChatColor.RED + "Oops! Seems like you don't have the permission to do that!");
                        }
                    }
                }
            } else {
                sender.sendMessage(Pencil.getPrefix() + ChatColor.RED + "Oops! Only players can execute commands!");
            }
        }

        return true;
    }
}
