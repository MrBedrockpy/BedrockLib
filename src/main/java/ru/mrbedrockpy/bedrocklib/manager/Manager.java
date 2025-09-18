package ru.mrbedrockpy.bedrocklib.manager;

import lombok.Getter;
import org.bukkit.event.Listener;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;

@Getter
public abstract class Manager<P extends BedrockPlugin<P>> implements Listener {

    private final P plugin;

    public Manager(P plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }
}
