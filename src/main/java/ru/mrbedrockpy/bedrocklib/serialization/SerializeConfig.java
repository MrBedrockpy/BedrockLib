package ru.mrbedrockpy.bedrocklib.serialization;

import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.manager.ListManager;

public class SerializeConfig<P extends BedrockPlugin<P>> extends ListManager<P, Serializer<?>, Class<?>> {
    public SerializeConfig(P plugin) {
        super(plugin);
    }
}
