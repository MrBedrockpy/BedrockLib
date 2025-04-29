package ru.mrbedrockpy.bedrocklib.config.variable.bukkit;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class LocationVariable extends ConfigVariable<Location> {

    public LocationVariable(String path) {
        super(path);
    }

    @Override
    public Class<Location> getType() {
        return Location.class;
    }

    @Override
    public void load(FileConfiguration config) {
        set(Location.deserialize(config.getValues(true)));
    }
}
