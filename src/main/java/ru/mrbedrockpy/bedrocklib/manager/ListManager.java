package ru.mrbedrockpy.bedrocklib.manager;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class ListManager<P extends BedrockPlugin, I extends ManagerItem<ID>, ID> extends Manager<P> implements CollectionManager<I, ID> {

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
        return list.removeAll(items);
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

    public List<I> getItems() {
        return new ArrayList<>(list);
    }

    public I getById(ID id) {
        for (I item: list) {
            if (item.getId().equals(id)) return item;
        }
        return null;
    }

    public void load(String connection) {
        try {
            ConnectionSource source = new JdbcConnectionSource(connection);
            Dao<I, ID> dao = DaoManager.createDao(source, (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
            if (!dao.isTableExists()) {
                TableUtils.createTable(dao);
                return;
            }
            list.addAll(dao.queryForAll());
            source.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String connection) {
        try {
            ConnectionSource source = new JdbcConnectionSource(connection);
            Dao<I, ID> dao = DaoManager.createDao(source, (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
            if (!dao.isTableExists()) {
                TableUtils.createTable(dao);
                return;
            }
            list.forEach(item -> {
                try {
                    dao.createOrUpdate(item);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            source.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
