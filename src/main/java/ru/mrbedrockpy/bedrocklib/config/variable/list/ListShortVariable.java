package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

public class ListShortVariable extends ListVariable<Short> {

    public ListShortVariable(String path) {
        super(path);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getShortList(getPath()));
    }

}
