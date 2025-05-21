package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListIntVariable extends ListVariable<Integer> {

    public ListIntVariable(String path) {
        super(path);
    }

    public ListIntVariable(String path, List<Integer> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getIntegerList(getPath()));
    }

}
