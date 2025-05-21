package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListShortVariable extends ListVariable<Short> {

    public ListShortVariable(String path) {
        super(path);
    }

    public ListShortVariable(String path, List<Short> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getShortList(getPath()));
    }

}
