package ru.mrbedrockpy.bedrocklib.serialization;

import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.manager.ListManager;
import ru.mrbedrockpy.bedrocklib.serialization.string.StringSerializer;

public class SerializeConfig<P extends BedrockPlugin<P>> extends ListManager<P, StringSerializer<?>, Class<?>> {
    public SerializeConfig(P plugin) {
        super(plugin);
    }
}
