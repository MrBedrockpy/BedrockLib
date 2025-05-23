package ru.mrbedrockpy.bedrocklib.config.variable.primitive;

import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class DoubleVariable extends ConfigVariable<Double> {

    public DoubleVariable(String path) {
        super(path, 0D);
    }

    public DoubleVariable(String path, double defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getDouble(getPath()));
    }

}
