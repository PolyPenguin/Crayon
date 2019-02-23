package com.polypenguin.pencil;

import com.polypenguin.pencil.core.CommandService;
import com.polypenguin.pencil.core.lang.Translator;
import com.polypenguin.pencil.core.service.ListenerService;
import com.polypenguin.pencil.core.service.PermissionService;
import com.polypenguin.pencil.core.service.PlayerService;
import com.polypenguin.pencil.core.settings.Settings;
import com.polypenguin.pencil.engine.PencilListener;
import com.polypenguin.pencil.engine.utils.InterfaceUtils;
import com.polypenguin.pencil.engine.utils.miscellaneous.MaterialSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Matthias Kovacic
 *
 * Main Pencil core.
 */
public class Pencil extends JavaPlugin {

    private static ListenerService listenerService;
    private static PermissionService permissionService;
    private static PlayerService playerService;

    private static Metrics metrics;

    private static MaterialSet materialSet;

    /**
     * Called when the plugin has to be enabled.
     *
     * Initiates different services, settings, etc.
     */
    @Override
    public void onEnable() {
        super.onEnable();

        System.out.println("[Pencil] Loading Pencil");

        checkConfig();

        listenerService = new ListenerService();
        permissionService = new PermissionService();
        playerService = new PlayerService();

        getCommand("pencil").setExecutor(new CommandService());

        if (Settings.getConfig().get("settings.metrics") == "true") {
            metrics = new Metrics(getPencil());
        }

        listenerService.registerEvents(new PencilListener());
        materialSet = InterfaceUtils.getMaterialsInterface();
    }

    /**
     * Called when the plugin has to be disabled.
     */
    @Override
    public void onDisable() {
        super.onDisable();
    }

    /**
     * Return Pencil in a Bukkit plugin format.
     *
     * @return The Pencil plugin.
     */
    public static Plugin getPencil() {
        return Bukkit.getServer().getPluginManager().getPlugin("Pencil");
    }

    /**
     * Prefix that is used throughout Pencil.
     *
     * @return The Pencil prefix.
     */
    public static String getPrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Pencil ✎" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE;
    }

    public static ListenerService getListenerService() { return listenerService; }

    public static PermissionService getPermissionService() {
        return permissionService;
    }

    public static PlayerService getPlayerService() {
        return playerService;
    }

    public static Metrics getMetrics() {
        return metrics;
    }

    public static MaterialSet getMaterialSet() { return materialSet; }

    private void checkConfig() {
        if (Settings.getConfig().get("settings") == null) {
            Settings.getConfig().set("settings.metrics", "true");
            Settings.getConfig().set("settings.language", Translator.PluginLanguage.ENGLISH.getName());
        }
    }
}
