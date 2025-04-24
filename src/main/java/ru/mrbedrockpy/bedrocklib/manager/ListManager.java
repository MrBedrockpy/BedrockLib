package ru.mrbedrockpy.bedrocklib.manager;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ListManager<P extends JavaPlugin, I extends ManagerItem<ID>, ID> extends Manager<@NotNull P> {

    protected final List<I> list = new ArrayList<>();

    public ListManager(P plugin) {
        super(plugin);
    }

    public void register(I item) {
        list.add(item);
    }

    public void unregister(I item) {
        list.remove(item);
    }

    public List<I> getItems() {
        return new ArrayList<>(list);
    }

    public I getById(ID id) {
        for (I item: list) {
            if (item.getId().equals(id)) return item;
        }
        return null;
    }

}
