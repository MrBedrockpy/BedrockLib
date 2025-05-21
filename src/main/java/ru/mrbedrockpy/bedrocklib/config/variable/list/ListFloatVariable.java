package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListFloatVariable extends ListVariable<Float> {

    public ListFloatVariable(String path) {
        super(path);
    }

    public ListFloatVariable(String path, List<Float> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getFloatList(getPath()));
    }

}
