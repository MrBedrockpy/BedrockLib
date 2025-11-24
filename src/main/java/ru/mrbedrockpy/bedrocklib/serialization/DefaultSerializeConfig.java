package ru.mrbedrockpy.bedrocklib.serialization;

import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.serialization.string.BukkitStringSerializers;
import ru.mrbedrockpy.bedrocklib.serialization.string.PaperStringSerializers;
import ru.mrbedrockpy.bedrocklib.serialization.string.PrimitiveStringSerializers;

public class DefaultSerializeConfig<P extends BedrockPlugin<P>> extends SerializeConfig<P> {

    public DefaultSerializeConfig(P plugin) {
        super(plugin);
        registerAll(PrimitiveStringSerializers.getSerializers());
        registerAll(BukkitStringSerializers.getSerializers());
        registerAll(PaperStringSerializers.getSerializers());
    }
}
