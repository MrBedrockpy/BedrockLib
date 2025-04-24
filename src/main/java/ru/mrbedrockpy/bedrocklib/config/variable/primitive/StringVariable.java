package ru.mrbedrockpy.bedrocklib.config.variable.primitive;

import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class StringVariable extends ConfigVariable<String> {

    public StringVariable(String path) {
        super(path);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getString(getPath()));
    }

}
