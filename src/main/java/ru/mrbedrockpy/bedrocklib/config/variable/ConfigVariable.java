package ru.mrbedrockpy.bedrocklib.config.variable;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.Serializable;

public abstract class ConfigVariable<T> {

    private final String path;
    private T value;

    public ConfigVariable(String path) {
        this.path = path;
    }

    public abstract Class<T> getType();

    public abstract void load(FileConfiguration config);
    public void save(FileConfiguration config) {
        config.set(getPath(), get());
    }

    public final T get() {
        return value;
    }
    public final void set(T value) {
        this.value = value;
    }

    public final String getPath() {
        return this.path;
    }

}
