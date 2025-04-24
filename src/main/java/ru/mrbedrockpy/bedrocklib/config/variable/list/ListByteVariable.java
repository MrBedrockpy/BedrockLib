package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

public class ListByteVariable extends ListVariable<Byte> {

    public ListByteVariable(String path) {
        super(path);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getByteList(getPath()));
    }

}
