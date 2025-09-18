package ru.mrbedrockpy.bedrocklib.manager;

import ru.mrbedrockpy.bedrocklib.BedrockPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListManager<P extends BedrockPlugin<P>, I extends ManagerItem<ID>, ID> extends Manager<P> implements CollectionManager<I, ID> {

    protected final List<I> list = new ArrayList<>();

    public ListManager(P plugin) {
        super(plugin);
    }

    @Override
    public boolean register(I item) {
        return list.add(item);
    }

    @Override
    public boolean registerAll(Collection<I> items) {
        return list.addAll(items);
    }

    @Override
    public boolean registerAll(I... items) {
        return registerAll(Arrays.asList(items));
    }

    @Override
    public boolean unregister(I item) {
        return list.remove(item);
    }

    @Override
    public boolean unregisterAll(Collection<I> items) {
        return list.removeAll(items);
    }

    @Override
    public boolean unregisterAll(I... items) {
        return unregisterAll(Arrays.asList(items));
    }

    @Override
    public I getById(ID id) {
        for (I item : list) {
            if (item.getId().equals(id)) return item;
        }
        return null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    public List<I> getItems() {
        return new ArrayList<>(list);
    }
}
