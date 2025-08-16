package ru.mrbedrockpy.bedrocklib.config;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;
import ru.mrbedrockpy.bedrocklib.manager.Manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Getter
public abstract class AbstractConfig<P extends BedrockPlugin> extends Manager<P> {

    private final FileConfiguration config;
    private final String name;
    private boolean createdNow = false;

    private final List<ConfigVariable<?>> variables;

    public AbstractConfig(P plugin, String config) {
        super(plugin);
        File customConfigFile = new File(plugin.getDataFolder(), config);
        if (!customConfigFile.exists()) {
            createdNow = true;
            customConfigFile.getParentFile().mkdirs();
            try {
                if (!customConfigFile.createNewFile()) throw new RuntimeException(new IOException("Failed to create config file: " + config));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        FileConfiguration customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            plugin.getLogger().log(Level.WARNING, e.toString());
        }
        this.config = YamlConfiguration.loadConfiguration(customConfigFile);
        this.name = config;
        this.variables = new ArrayList<>();
        this.load();
    }

    public <V extends ConfigVariable<?>> V register(V variable) {
        this.variables.add(variable);
        if (createdNow) variable.save(config);
        try {
            this.config.save(getPlugin().getDataFolder() + "/" + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return variable;
    }

    public void load() {
        this.variables.forEach(variable -> variable.load(config));
    }

    public void save() {
        try {
            this.variables.forEach(variable -> variable.save(config));
            this.config.save(getPlugin().getDataFolder() + "/" + name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
