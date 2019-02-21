package com.polypenguin.pencil.engine.geometry.blueprint;

import com.polypenguin.pencil.Pencil;
import com.polypenguin.pencil.engine.Clipboard;
import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilPreState;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PencilBlueprintFile {

    /**
     * Save the current player clipboard as a PBF
     * or PencilBlueprintFile.
     *
     * @param name The name the blueprint should be saved as.
     * @param player The player from which the clipboard should be saved.
     * @param saveAir Whether or not AIR should be contained within the save (Reduced memory?).
     */
    public static void createBlueprint(String name, PencilPlayer player, boolean saveAir) {
        File directory = new File(Pencil.getPencil().getDataFolder() + "/blueprints/");

        if (!directory.exists()) {
            directory.mkdir();
        }

        File blueprint = new File(directory + name + ".yml");

        if (!blueprint.exists()) {
            try {
                blueprint.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(blueprint);
        Clipboard clipboard = player.getClipboard();

        config.set("blueprint.owner", player.getPlayer().getName());
        config.set("blueprint.size", clipboard.getSelection().getBlocks());
        config.set("blueprint.calibration.minimum.x", clipboard.getSelection().getNativeMinimum().getBlockX());
        config.set("blueprint.calibration.minimum.y", clipboard.getSelection().getNativeMinimum().getBlockY());
        config.set("blueprint.calibration.minimum.z", clipboard.getSelection().getNativeMinimum().getBlockZ());
        config.set("blueprint.calibration.maximum.x", clipboard.getSelection().getNativeMaximum().getBlockX());
        config.set("blueprint.calibration.maximum.y", clipboard.getSelection().getNativeMaximum().getBlockY());
        config.set("blueprint.calibration.maximum.z", clipboard.getSelection().getNativeMaximum().getBlockZ());

        int i = 0;

        for (PencilPreState state : clipboard.getPreStates()) {
            if (!saveAir && state.getMaterial().equals(Material.AIR)) {
                continue;
            } else {
                config.set("blueprint.blocks.offset." + i + ".x", state.getOffset().getBlockX());
                config.set("blueprint.blocks.offset." + i + ".x", state.getOffset().getBlockY());
                config.set("blueprint.blocks.offset." + i + ".x", state.getOffset().getBlockZ());
                config.set("blueprint.blocks.offset." + i + ".material", state.getMaterial().toString());
            }

            i++;
        }

        try {
            config.save(blueprint);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        boolean isRenamed = blueprint.renameTo(new File(directory + name + ".pbf"));

        if (isRenamed) {
            player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Your clipboard has been saved to a blueprint as \"" + name + "\"");
        }
    }

}
