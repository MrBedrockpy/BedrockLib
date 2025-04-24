package ru.mrbedrockpy.bedrocklib.config.variable.bukkit;

import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class ColorVariable extends ConfigVariable<Color> {

    public ColorVariable(String path) {
        super(path);
    }

    @Override
    public Class<Color> getType() {
        return Color.class;
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getColor(getPath()));
    }
}
