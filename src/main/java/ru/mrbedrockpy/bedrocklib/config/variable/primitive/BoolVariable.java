package ru.mrbedrockpy.bedrocklib.config.variable.primitive;

import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class BoolVariable extends ConfigVariable<Boolean> {

    public BoolVariable(String path) {
        super(path);
    }

    @Override
    public Class<Boolean> getType() {
        return Boolean.class;
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getBoolean(getPath()));
    }

}
