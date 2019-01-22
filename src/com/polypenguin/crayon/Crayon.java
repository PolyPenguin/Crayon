package com.polypenguin.crayon;

import com.polypenguin.crayon.core.CommandService;
import com.polypenguin.crayon.core.service.PermissionService;
import com.polypenguin.crayon.core.service.PlayerService;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Matthias Kovacic
 *
 * Main Crayon core.
 */
public class Crayon extends JavaPlugin {

    private static PermissionService permissionService;
    private static PlayerService playerService;

    private static Metrics metrics;

    /**
     * Called when the plugin has to be enabled.
     *
     * Initiates different services, settings, etc.
     */
    @Override
    public void onEnable() {
        super.onEnable();

        System.out.println("[Crayon] Loading Crayon");

        permissionService = new PermissionService();
        playerService = new PlayerService();

        getCommand("crayon").setExecutor(new CommandService());

        metrics = new Metrics(getCrayon());
    }

    /**
     * Called when the plugin has to be disabled.
     */
    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Return Crayon in a Bukkit plugin format.
     *
     * @return The Crayon plugin.
     */
    public static Plugin getCrayon() {
        return Bukkit.getServer().getPluginManager().getPlugin("Crayon");
    }

    /**
     * Prefix that is used throughout Crayon.
     *
     * @return The Crayon prefix.
     */
    public static String getPrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Crayon ✎" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE;
    }

    public static PermissionService getPermissionService() {
        return permissionService;
    }

    public static PlayerService getPlayerService() {
        return playerService;
    }

}
