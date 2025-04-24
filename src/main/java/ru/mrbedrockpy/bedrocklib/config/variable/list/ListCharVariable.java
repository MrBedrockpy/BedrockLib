package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

public class ListCharVariable extends ListVariable<Character> {

    public ListCharVariable(String path) {
        super(path);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getCharacterList(getPath()));
    }

}
