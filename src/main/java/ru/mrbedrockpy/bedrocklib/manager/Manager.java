package ru.mrbedrockpy.bedrocklib.manager;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

@Getter
public abstract class Manager<P extends JavaPlugin> implements Listener {

    private final P plugin;

    public Manager(P plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }
}
