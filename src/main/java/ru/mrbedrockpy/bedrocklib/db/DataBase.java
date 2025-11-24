package ru.mrbedrockpy.bedrocklib.db;

import lombok.Getter;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.serialization.SerializeConfig;
import ru.mrbedrockpy.bedrocklib.manager.ListManager;
import ru.mrbedrockpy.bedrocklib.manager.ManagerItem;
import ru.mrbedrockpy.bedrocklib.util.FileUtil;

import java.io.*;
import java.util.*;

@Getter
public class DataBase<P extends BedrockPlugin<P>> extends ListManager<P, DataTable<P, ? extends ManagerItem<?>, ?>, Class<?>> {

    private final SerializeConfig<? extends BedrockPlugin<P>> serializeConfig;
    private final File file;

    public DataBase(P plugin, File file, SerializeConfig<? extends BedrockPlugin<P>> serializeConfig) {
        super(plugin);
        this.file = file;
        this.serializeConfig = serializeConfig;
        load();
    }

    public <T extends ManagerItem<ID>, ID> DataTable<P, T, ID> createTableIfNotExists(Class<T> dataType) {
        DataTable<P, T, ID> table = getTable(dataType);
        if (table != null) return table;
        table = new DataTable<>(serializeConfig, dataType, List.of());
        register(table);
        return table;
    }

    public <T extends ManagerItem<ID>, ID> DataTable<P, T, ID> getTable(Class<T> clazz) {
        for (DataTable<P, ?, ?> table : getItems()) {
            if (table.getDataType().equals(clazz)) return (DataTable<P, T, ID>) table;
        }
        return null;
    }

    public void load() {
        list.clear();
        String text = FileUtil.getTextFile(file);
        if (text == null) {
            file.getParentFile().mkdirs();
            try {
                if (!file.exists() && !file.createNewFile())
                    throw new RuntimeException("Failed to create file: " + file.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        text = text.trim();
        for (String stringTable : new String(Base64.getDecoder().decode(text)).split("\n\n")) {
            String[] arrayTable = stringTable.split("\n");
            if (arrayTable.length < 2) continue;
            try {
                Class<?> dataType = Class.forName(arrayTable[0]);
                register(new DataTable<>(
                        serializeConfig, dataType,
                        Arrays.asList(arrayTable)
                                .subList(1, arrayTable.length)
                ));
            } catch (ClassNotFoundException e) {
                getPlugin().getLogger().warning("Class not found: " + arrayTable[0]);
            }
        }
    }

    public void save() {
        if (list.isEmpty()) return;
        List<String> stringTables = new ArrayList<>();
        for (DataTable<P, ?, ?> dataTable : getItems()) {
            if (dataTable.getDtos().isEmpty()) continue;
            stringTables.add(dataTable.getDataType().getName() + "\n" + String.join("\n", dataTable.serialize()));
        }
        FileUtil.setTextFile(file, Base64.getEncoder().encodeToString(String.join("\n\n", stringTables).getBytes()));
    }
}
