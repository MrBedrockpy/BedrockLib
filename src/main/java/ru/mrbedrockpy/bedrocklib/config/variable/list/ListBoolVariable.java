package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListBoolVariable extends ListVariable<Boolean> {

    public ListBoolVariable(String path) {
        super(path);
    }

    public ListBoolVariable(String path, List<Boolean> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getBooleanList(getPath()));
    }

}
