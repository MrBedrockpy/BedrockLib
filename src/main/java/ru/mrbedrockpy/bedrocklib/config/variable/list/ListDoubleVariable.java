package ru.mrbedrockpy.bedrocklib.config.variable.list;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ListDoubleVariable extends ListVariable<Double> {

    public ListDoubleVariable(String path) {
        super(path);
    }

    public ListDoubleVariable(String path, List<Double> defaultValue) {
        super(path, defaultValue);
    }

    @Override
    public void load(FileConfiguration config) {
        set(config.getDoubleList(getPath()));
    }

}
