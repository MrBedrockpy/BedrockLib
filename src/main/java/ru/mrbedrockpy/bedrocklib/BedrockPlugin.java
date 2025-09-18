package ru.mrbedrockpy.bedrocklib;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mrbedrockpy.bedrocklib.serialization.DefaultSerializeConfig;
import ru.mrbedrockpy.bedrocklib.serialization.SerializeConfig;

@Getter
@Setter
public abstract class BedrockPlugin<P extends BedrockPlugin<P>> extends JavaPlugin {

    private SerializeConfig<P> serializeConfig;

    @Override
    public final void onEnable() {
        serializeConfig = new DefaultSerializeConfig<>((P) this);
        registerConfigs();
        registerManagers();
        registerCommands();
    }

    @Override
    public final void onDisable() {
        unregisterCommands();
        saveManagers();
        saveConfigs();
    }

    protected void registerConfigs() {}
    protected void registerManagers() {}
    protected void registerCommands() {}

    protected void saveConfigs() {}
    protected void saveManagers() {}
    protected void unregisterCommands() {}
}
