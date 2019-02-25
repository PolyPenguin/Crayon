package com.polypenguin.pencil.core.lang;

import com.polypenguin.pencil.core.settings.Settings;

public class Translator {

    public enum PluginLanguage {
        ENGLISH("English"),
        CHINESE("Chinese"),
        FRENCH("French");

        String name;

        PluginLanguage(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private PluginLanguage language;

    public Translator() {
        for (PluginLanguage lang : PluginLanguage.values()) {
            if (lang.getName().equalsIgnoreCase(Settings.getConfig().get("settings.language"))) {
                language = lang;
            }
        }
    }

    public void translate(String msg) {

    }

}
