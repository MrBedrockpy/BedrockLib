package ru.mrbedrockpy.bedrocklib;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mrbedrockpy.bedrocklib.serialization.DefaultSerializeConfig;
import ru.mrbedrockpy.bedrocklib.serialization.SerializeConfig;
import ru.mrbedrockpy.bedrocklib.util.MiniMessageUtil;

@Getter
@Setter
public abstract class BedrockPlugin<P extends BedrockPlugin<P>> extends JavaPlugin {

    private SerializeConfig<P> serializeConfig;

    @Override
    public final void onEnable() {
        MiniMessageUtil.setPlugin(this);
        this.serializeConfig = new DefaultSerializeConfig<>((P) this);
        try {this.registerConfigs();} catch (Exception e) {throw new RuntimeException(e);}
        try {this.registerManagers();} catch (Exception e) {throw new RuntimeException(e);}
        try {this.registerCommands();} catch (Exception e) {throw new RuntimeException(e);}
    }

    @Override
    public final void onDisable() {
        try {unregisterCommands();} catch (Exception e) {throw new RuntimeException(e);}
        try {saveManagers();} catch (Exception e) {throw new RuntimeException(e);}
        try {saveConfigs();} catch (Exception e) {throw new RuntimeException(e);}
    }

    protected void registerConfigs() {}
    protected void registerManagers() {}
    protected void registerCommands() {}

    protected void unregisterCommands() {}
    protected void saveManagers() {}
    protected void saveConfigs() {}
}
