package ru.mrbedrockpy.bedrocklib.config.variable;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class ConfigVariable<T> {

    @Getter private final String path;
    @Getter private final T defaultValue;
    private T value = null;

    public ConfigVariable(String path, T defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public abstract Class<T> getType();

    public abstract void load(FileConfiguration config);
    public void save(FileConfiguration config) {
        if (value != null) config.set(getPath(), get());
        else config.set(getPath(), defaultValue);
    }

    public final T get() {
        return value;
    }
    public final void set(T value) {
        this.value = value;
    }

}
