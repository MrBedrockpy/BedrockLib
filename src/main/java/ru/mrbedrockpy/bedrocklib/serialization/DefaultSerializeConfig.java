package ru.mrbedrockpy.bedrocklib.serialization;

import ru.mrbedrockpy.bedrocklib.BedrockPlugin;

public class DefaultSerializeConfig<P extends BedrockPlugin<P>> extends SerializeConfig<P> {

    public DefaultSerializeConfig(P plugin) {
        super(plugin);
        registerAll(PrimitiveSerializers.getPrimitiveSerializers());
        registerAll(BukkitSerializers.getBukkitSerializers());
    }
}
