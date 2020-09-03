package ro.nicuch.leaders.settings;

import org.bukkit.configuration.file.YamlConfiguration;
import ro.nicuch.leaders.LeadersPlugin;

import java.io.File;
import java.io.IOException;

public class LangSettings {

    public static void setup(LeadersPlugin plugin) {
        File file = new File(plugin.getDataFolder() + File.separator + "lang.yml");
        YamlConfiguration config;
        if (!file.exists()) {
            config = new YamlConfiguration();
            for (LangKey langKey : LangKey.values())
                config.set(langKey.getPath(), langKey.getDefault());
            try {
                config.save(file);
                plugin.getLogger().info("New lang file generated!");
            } catch (IOException e) {
                plugin.getLogger().warning("Failed to generate new lang file!");
                e.printStackTrace();
            }
        } else {
            config = YamlConfiguration.loadConfiguration(file);
            plugin.getLogger().info("Lang file found and loaded!");
        }
        for (LangKey langKey : LangKey.values())
            langKey.setText(config.getString(langKey.getPath(), langKey.getDefault()));
    }

    public enum LangKey {
        CONFIG_RELOADED("config-reloaded", "&aThe config was reloaded!"),
        NO_PERMISSION("no-permission", "&cYou don't have permission!");

        final String path, def;
        String text = "&cConfig not loaded yet!";

        LangKey(String path, String def) {
            this.path = path;
            this.def = def;
        }

        public final String getPath() {
            return this.path;
        }

        public final String getDefault() {
            return this.def;
        }

        public final String getText() {
            return this.text;
        }

        protected final void setText(String text) {
            this.text = text;
        }
    }
}
