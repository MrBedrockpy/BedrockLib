package ru.mrbedrockpy.bedrocklib.db;

import lombok.Getter;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.SerializeConfig;
import ru.mrbedrockpy.bedrocklib.manager.ListManager;
import ru.mrbedrockpy.bedrocklib.manager.ManagerItem;

import java.io.*;
import java.util.*;

@Getter
public class DataBase<P extends BedrockPlugin> extends ListManager<P, DataTable<P, ? extends ManagerItem<?>, ?>, Class<?>> {

    private final List<DataTable<P, ?, ?>> tables = new ArrayList<>();
    private final SerializeConfig<? extends BedrockPlugin> serializeConfig;
    private final File file;

    public DataBase(P plugin, File file, SerializeConfig<? extends BedrockPlugin> serializeConfig) {
        super(plugin);
        this.file = file;
        this.serializeConfig = serializeConfig;
    }

    public <T extends ManagerItem<ID>, ID> DataTable<P, T, ID> createTableIfNotExists(Class<T> dataType) {
        DataTable<P, T, ID> table = getTable(dataType);
        if (table != null) return table;
        table = new DataTable<>(serializeConfig, dataType, List.of());
        tables.add(table);
        return table;
    }

    public <T extends ManagerItem<ID>, ID> DataTable<P, T, ID> getTable(Class<T> clazz) {
        for (DataTable<P, ?, ?> table: tables) {
            if (table.getDataType().equals(clazz)) return (DataTable<P, T, ID>) table;
        }
        return null;
    }

    public List<DataTable<P, ?, ?>> load() {
        tables.clear();
        StringBuilder sb = new StringBuilder();
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) throw new RuntimeException("Failed to create file: " + file.getAbsolutePath());
                return tables;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) sb.append(scanner.nextLine()).append("\n");
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String stringTable: new String(Base64.getDecoder().decode(sb.toString().trim())).split("\n\n")) {
            String[] arrayTable = stringTable.split("\n");
            try {
                Class<?> dataType = Class.forName(arrayTable[0]);
                tables.add(new DataTable<>(serializeConfig, dataType, Arrays.asList(arrayTable).subList(1, arrayTable.length)));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return tables;
    }

    public void save() {
        try {
            if (!file.exists()) if (!file.createNewFile()) throw new RuntimeException("Failed to create file: " + file.getAbsolutePath());
            List<String> stringTables = new ArrayList<>();
            for (DataTable<P, ?, ?> dataTable: tables) stringTables.add(dataTable.getDataType().getName() + "\n" + String.join("\n", dataTable.serialize()));
            FileWriter fw = new FileWriter(file);
            fw.write(Base64.getEncoder().encodeToString(String.join("\n\n", stringTables).getBytes()));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
