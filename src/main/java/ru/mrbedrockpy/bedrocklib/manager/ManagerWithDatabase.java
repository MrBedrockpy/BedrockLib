package ru.mrbedrockpy.bedrocklib.manager;

import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.db.DataBase;
import ru.mrbedrockpy.bedrocklib.db.DataTable;

import java.util.Collection;

public interface ManagerWithDatabase<P extends BedrockPlugin, I extends ManagerItem<ID>, ID> {

    default void load(DataBase<P> dataBase, Class<I> itemType, Collection<I> items) {
        DataTable<P, I, ID> table = dataBase.createTableIfNotExists(itemType);
        items.clear();
        items.addAll(table.getDtos());
    }

    default void save(DataBase<P> dataBase, Class<I> itemType, Collection<I> items) {
        DataTable<P, I, ID> table = dataBase.createTableIfNotExists(itemType);
        table.clear();
        table.registerAll(items);
    }
}
