package ru.mrbedrockpy.bedrocklib.config.variable.list;

import ru.mrbedrockpy.bedrocklib.config.variable.ConfigVariable;

import java.util.Collections;
import java.util.List;

public abstract class ListVariable<T> extends ConfigVariable<List<T>> {

    public ListVariable(String path) {
        super(path);
    }

    @Override
    public final Class<List<T>> getType() {
        return (Class<List<T>>) Collections.EMPTY_LIST.getClass();
    }
}
