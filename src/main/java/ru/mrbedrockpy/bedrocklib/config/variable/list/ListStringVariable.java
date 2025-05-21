package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListStringVariable extends ListVariable<String> {

    public ListStringVariable(String path) {
        super(path);
    }

    public ListStringVariable(String path, List<String> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getStringList(getPath()));
    }

}
