package ru.mrbedrockpy.bedrocklib.manager;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
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
