package ru.mrbedrockpy.bedrocklib.config.singleton.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfigData {

    private final String name;
    private final ConfigFieldData<?>[] fields;
    private final ConfigData[] categories;

}
