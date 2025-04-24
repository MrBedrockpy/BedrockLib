package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

public class ListStringVariable extends ListVariable<String> {

    public ListStringVariable(String path) {
        super(path);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getStringList(getPath()));
    }

}
