package ru.mrbedrockpy.bedrocklib.config.variable.bukkit;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

public class LocationVariable extends ConfigVariable<Location> {

    public LocationVariable(String path) {
        super(path, new Location(null, 0, 0, 0));
    }

    public LocationVariable(String path, Location defaultValue) {
        super(path, defaultValue);
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
