package ru.mrbedrockpy.bedrocklib.manager;

import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;

@Getter
public abstract class RunnableManager<P extends BedrockPlugin> extends BukkitRunnable implements Listener {

    private final P plugin;

    public RunnableManager(P plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }
}
