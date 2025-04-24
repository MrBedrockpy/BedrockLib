package ru.mrbedrockpy.bedrocklib.manager;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class SetManager<P extends JavaPlugin, I extends ManagerItem<ID>, ID> extends Manager<@NotNull P> {

    protected final Set<I> set = new HashSet<>();

    public SetManager(P plugin) {
        super(plugin);
    }

    public boolean register(I item) {
        return set.add(item);
    }

    public boolean unregister(I item) {
        return set.remove(item);
    }

    public Set<I> getItems() {
        return new HashSet<>(set);
    }

    public I getById(ID id) {
        for (I item: set) {
            if (item.getId().equals(id)) return item;
        }
        return null;
    }

}
