package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

public class ListBoolVariable extends ListVariable<Boolean> {

    public ListBoolVariable(String path) {
        super(path);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getBooleanList(getPath()));
    }

}
