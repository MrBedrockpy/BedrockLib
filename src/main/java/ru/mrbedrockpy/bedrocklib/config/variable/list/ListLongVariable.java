package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListLongVariable extends ListVariable<Long> {

    public ListLongVariable(String path) {
        super(path);
    }

    public ListLongVariable(String path, List<Long> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getLongList(getPath()));
    }

}
