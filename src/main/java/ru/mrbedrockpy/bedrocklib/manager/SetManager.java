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

    public void load(String connection) {
        try {
            ConnectionSource source = new JdbcConnectionSource(connection);
            Dao<I, ID> dao = DaoManager.createDao(source, (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
            if (!dao.isTableExists()) {
                TableUtils.createTable(dao);
                return;
            }
            set.addAll(dao.queryForAll());
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
            set.forEach(item -> {
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
