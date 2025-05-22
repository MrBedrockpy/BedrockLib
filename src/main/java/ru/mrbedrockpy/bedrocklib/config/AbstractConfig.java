package ru.mrbedrockpy.bedrocklib.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public abstract class AbstractConfig {

    private final Plugin plugin;
    private final FileConfiguration config;
    private final String name;

    private final List<ConfigVariable<?>> variables;

    public AbstractConfig(Plugin plugin, String config) {
        File customConfigFile = new File(plugin.getDataFolder(), config);
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            try {
                if (!customConfigFile.createNewFile()) throw new RuntimeException(new IOException("Failed to create config file: " + config));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.save();
        }
        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().log(Level.WARNING, e.toString());
        }
        this.plugin = plugin;
        this.config = YamlConfiguration.loadConfiguration(customConfigFile);
        this.name = config;
        this.variables = new ArrayList<>();
        this.load();
    }

    public <V extends ConfigVariable<?>> V register(V variable) {
        this.variables.add(variable);
        return variable;
    }

    public void load() {
        this.variables.forEach(variable -> variable.load(config));
    }

    public void save() {
        try {
            this.variables.forEach(variable -> variable.save(config));
            config.save(plugin.getDataFolder() + "/" + name);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

}
