package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListByteVariable extends ListVariable<Byte> {

    public ListByteVariable(String path) {
        super(path);
    }

    public ListByteVariable(String path, List<Byte> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getByteList(getPath()));
    }

}
