package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListCharVariable extends ListVariable<Character> {

    public ListCharVariable(String path) {
        super(path);
    }

    public ListCharVariable(String path, List<Character> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getCharacterList(getPath()));
    }

}
