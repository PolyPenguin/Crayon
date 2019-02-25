package com.polypenguin.pencil.core.file;

import com.polypenguin.pencil.Pencil;
import com.polypenguin.pencil.engine.Clipboard;
import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.geometry.Vector;
import com.polypenguin.pencil.engine.geometry.selection.Selection;
import com.polypenguin.pencil.engine.utils.VectorUtils;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilPreState;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

public class PencilFile {

    /**
     * Save the current player clipboard as a PBF
     * or PencilFile.
     *
     * @param name The name the blueprint should be saved as.
     * @param player The player from which the clipboard should be saved.
     * @param saveAir Whether or not AIR should be contained within the save (Reduced memory?).
     */
    @SuppressWarnings({"unused"})
    private static void createBlueprint(String name, PencilPlayer player, boolean saveAir) {
        File directory = new File(Pencil.getPencil().getDataFolder() + "/blueprints");

        if (!directory.exists()) {
            directory.mkdir();
        }

        File blueprint = createFile(
                new File(directory + System.getProperty("file.separator") + name + ".yml")
        );

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

        boolean isRenamed = blueprint.renameTo(new File(directory + System.getProperty("file.separator") + name + ".pbf"));

        if (isRenamed) {
            player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Your clipboard has been saved to a blueprint as \"" + name + "\"");
        }
    }

    /**
     * Save the current player selection as a PTF
     * or PencilTextureFile.
     *
     * @param name The name the texture should be saved as.
     * @param player The player from which the selection should be saved.
     * @param selection The selection which should be saved.
     */
    private static void createTexture(String name, PencilPlayer player, Selection selection) {
        File directory = new File(Pencil.getPencil().getDataFolder() + "/textures");

        if (!directory.exists()) {
            directory.mkdir();
        }

        File texture = createFile(
                new File(directory + System.getProperty("file.separator") + name + ".yml")
        );

        YamlConfiguration config = YamlConfiguration.loadConfiguration(texture);
        World world = player.getPlayer().getWorld();

        config.set("blueprint.owner", player.getPlayer().getName());
        config.set("blueprint.size", selection.getBlocks());
        config.set("blueprint.calibration.minimum.x", selection.getNativeMinimum().getBlockX());
        config.set("blueprint.calibration.minimum.y", selection.getNativeMinimum().getBlockY());
        config.set("blueprint.calibration.minimum.z", selection.getNativeMinimum().getBlockZ());
        config.set("blueprint.calibration.maximum.x", selection.getNativeMaximum().getBlockX());
        config.set("blueprint.calibration.maximum.y", selection.getNativeMaximum().getBlockY());
        config.set("blueprint.calibration.maximum.z", selection.getNativeMaximum().getBlockZ());

        int i = 0;

        for (Vector vector : selection.getVectors(true)) {
            Vector offset = VectorUtils.getOffset(selection.getNativeMinimum(), vector);

            config.set("blueprint.blocks.offset." + i + ".x", offset.getBlockX());
            config.set("blueprint.blocks.offset." + i + ".x", offset.getBlockY());
            config.set("blueprint.blocks.offset." + i + ".x", offset.getBlockZ());
            config.set("blueprint.blocks.offset." + i + ".material", world.getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).getType().toString());

            i++;
        }

        try {
            config.save(texture);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        boolean isRenamed = texture.renameTo(new File(directory + System.getProperty("file.separator") + name + ".ptf"));

        if (isRenamed) {
            player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Your selection has been saved to a texture as \"" + name + "\"");
        }
    }

    public static File createFile(File file) {
        checkNotNull(file);
        try
        {
            if (file.createNewFile())
            {
                Pencil.getPencil().getLogger().fine(file.toString() + " created.");
            } else
            {
                Pencil.getPencil().getLogger().fine(file.toString() + " already exists.");
            }
            return file;
        } catch (IOException e)
        {
            e.printStackTrace();
            return file;
        }
    }

    public static void createPencilFile(String name, PencilPlayer player, boolean saveAir, Selection selection) {
        if (selection != null) {
            createTexture(name, player, selection);
        } else {
            createBlueprint(name, player, saveAir);
        }
    }

}
