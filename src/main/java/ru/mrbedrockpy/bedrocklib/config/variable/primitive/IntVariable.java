package ru.mrbedrockpy.bedrocklib.config.variable.primitive;

import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class IntVariable extends ConfigVariable<Integer> {

    public IntVariable(String path) {
        super(path, 0);
    }

    public IntVariable(String path, int defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getInt(getPath()));
    }

}
