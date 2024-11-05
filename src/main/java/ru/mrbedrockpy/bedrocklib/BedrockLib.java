package ru.mrbedrockpy.bedrocklib;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mrbedrockpy.bedrocklib.economy.Currencies;
import ru.mrbedrockpy.bedrocklib.util.SQLManager;

public final class BedrockLib extends JavaPlugin {

    private static BedrockLib instance;

    @Override
    public void onEnable() {
        instance = this;
        SQLManager.connect();
        Currencies.initialize();
    }

    @Override
    public void onDisable() {
        instance = null;
        SQLManager.disconnect();
    }

    public static Plugin getInstance() {
        return instance;
    }
}
