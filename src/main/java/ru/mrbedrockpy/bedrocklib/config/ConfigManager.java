package ru.mrbedrockpy.bedrocklib.config;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ru.mrbedrockpy.bedrocklib.ChatUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class ConfigManager {

    public static ConfigManager MAIN;
    public static ConfigManager ROOMS;
    public static ConfigManager ITEMS;

    private final Configuration config;

    public ConfigManager(Configuration config) {
        this.config = config;
    }

    public ConfigManager(Plugin plugin, String config) {

        File customConfigFile = new File(plugin.getDataFolder(), config);

        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            plugin.saveResource(config, false);
        }

        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().log(Level.WARNING, e.toString());
        }

        this.config = YamlConfiguration.loadConfiguration(customConfigFile);
    }

    public String getString(String path) {
        return ChatUtil.format(config.getString(path));
    }

    public List<String> getStringList(String path) {
        return ChatUtil.format(config.getStringList(path));
    }

    public Integer getInt(String path) {
        return config.getInt(path);
    }

    public Double getDouble(String path) {
        return config.getDouble(path);
    }

    public Boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public ConfigurationSection getConfigurationSection(String path) {
        return config.getConfigurationSection(path);
    }

    public void set(String path, Object value) {
        config.set(path, value);
    }
}
