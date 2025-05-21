package ru.mrbedrockpy.bedrocklib.config.variable.primitive;

import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class LongVariable extends ConfigVariable<Long> {

    public LongVariable(String path) {
        super(path, 0L);
    }

    public LongVariable(String path, long defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public Class<Long> getType() {
        return Long.class;
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getLong(getPath()));
    }

}
