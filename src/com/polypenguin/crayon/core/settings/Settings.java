package com.polypenguin.crayon.core.settings;

import com.polypenguin.crayon.Crayon;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Set;

/**
 * @author Matthias Kovacic
 *
 * Handles all file related stuff.
 */
public class Settings {

    private static final Settings configuration = new Settings("config");
    private static final Settings analytics = new Settings("analytics");
    private static final Settings players = new Settings("players");

    private File file;
    private FileConfiguration config;

    public static Settings getConfig() {
        return configuration;
    }

    public static Settings getAnalytics() {
        return analytics;
    }

    public static Settings getPlayers() {
        return players;
    }

    /**
     * Constructor which creates/retrieves the file.
     *
     * @param fileName The used filename.
     */
    private Settings(String fileName) {
        if (!Crayon.getCrayon().getDataFolder().exists()) {
            Crayon.getCrayon().getDataFolder().mkdir();
        }

        this.file = new File(Crayon.getCrayon().getDataFolder(), fileName + ".yml");

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    /**
     * @return the file as a FileConfiguration.
     */
    public FileConfiguration getAsConfig() {
        return this.config;
    }

    /**
     * Return a T retrieved at a certain path;
     *
     * @param path The path is should retrieve from?
     * @param <T> Type that is castable.
     * @return T.
     */
    public <T> T get(String path) {
        return (T)this.config.get(path);
    }

    public Set<String> getKeys() {
        return this.config.getKeys(false);
    }

    /**
     * Set a value for a certain path.
     *
     * @param path The path it should save to.
     * @param value The value it should save to the path.
     */
    public void set(String path, Object value) {
        this.config.set(path, value);
        save();
    }

    /**
     * Check whether or not the config contains a certain path.
     *
     * @param path The path that has to be checked.
     * @return False is absent.
     */
    public boolean contains(String path) {
        return this.config.contains(path);
    }

    /**
     * Create a new section.
     *
     * @param path The path where the new section should be created.
     * @return The newly created section.
     */
    public ConfigurationSection createSection(String path) {
        ConfigurationSection section = this.config.createSection(path);
        save();
        return section;
    }

    /**
     * Save the file.
     */
    public void save() {
        try {
            this.config.save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
