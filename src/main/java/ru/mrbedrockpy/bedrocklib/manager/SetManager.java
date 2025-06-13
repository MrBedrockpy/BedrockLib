package ru.mrbedrockpy.bedrocklib.manager;

import ru.mrbedrockpy.bedrocklib.BedrockPlugin;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SetManager<P extends BedrockPlugin, I extends ManagerItem<ID>, ID> extends Manager<P> implements CollectionManager<I, ID> {

    protected final Set<I> set = new HashSet<>();

    public SetManager(P plugin) {
        super(plugin);
    }

    @Override
    public boolean register(I item) {
        return set.add(item);
    }

    @Override
    public boolean registerAll(Collection<I> items) {
        return set.addAll(items);
    }

    @Override
    public boolean registerAll(I... items) {
        return registerAll(Arrays.asList(items));
    }

    @Override
    public boolean unregister(I item) {
        return set.remove(item);
    }

    @Override
    public boolean unregisterAll(Collection<I> items) {
        return set.removeAll(items);
    }

    @Override
    public boolean unregisterAll(I... items) {
        return unregisterAll(Arrays.asList(items));
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
