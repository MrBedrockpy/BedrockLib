package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

public class ListDoubleVariable extends ListVariable<Double> {

    public ListDoubleVariable(String path) {
        super(path);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getDoubleList(getPath()));
    }

}
