package ru.mrbedrockpy.bedrocklib;

import ru.mrbedrockpy.bedrocklib.manager.ListManager;

public class SerializeConfig<P extends BedrockPlugin> extends ListManager<P, Serializer<?>, Class<?>> {

    public SerializeConfig(P plugin) {
        super(plugin);
    }
}
