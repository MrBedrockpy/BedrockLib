package ru.mrbedrockpy.bedrocklib.manager;

import java.util.Collection;

public interface CollectionManager<I extends ManagerItem<ID>, ID> {

    boolean register(I item);
    boolean registerAll(Collection<I> items);
    boolean registerAll(I... items);

    boolean unregister(I item);
    boolean unregisterAll(Collection<I> items);
    boolean unregisterAll(I... items);

}
