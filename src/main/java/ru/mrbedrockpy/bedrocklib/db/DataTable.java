package ru.mrbedrockpy.bedrocklib.db;

import lombok.Getter;
import ru.mrbedrockpy.bedrocklib.BedrockPlugin;
import ru.mrbedrockpy.bedrocklib.SerializeConfig;
import ru.mrbedrockpy.bedrocklib.Serializer;
import ru.mrbedrockpy.bedrocklib.manager.ListManager;
import ru.mrbedrockpy.bedrocklib.manager.ManagerItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class DataTable<P extends BedrockPlugin, T extends ManagerItem<ID>, ID> extends ListManager<P, T, ID> implements ManagerItem<Class<?>> {

    private final List<T> dtos = new ArrayList<>();
    private final SerializeConfig<? extends BedrockPlugin> serializeConfig;
    private final Class<T> dataType;

    public DataTable(P plugin, SerializeConfig<? extends BedrockPlugin> serializeConfig, Class<?> dataType, List<String> data) {
        super(plugin);
        this.serializeConfig = serializeConfig;
        this.dataType = (Class<T>) dataType;
        data.forEach(this::parseFromData);
    }

    public List<String> serialize() {
        List<String> list = new ArrayList<>();
        dtos.forEach(dto -> {
            List<String> fields = new ArrayList<>();
            Arrays.stream(dataType.getDeclaredFields()).forEach(field -> {
                field.setAccessible(true);
                serializeConfig.getItems().forEach(serializer -> {
                    if (!field.getType().equals(serializer.getId())) return;
                    try {
                        fields.add(serialize((Serializer<T>) serializer, field.get(dto)));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
            list.add(String.join(":", fields));
        });
        return list;
    }

    private <F> String serialize(Serializer<F> serializer, Object value) {
        return serializer.serialize((F) value);
    }

    private void parseFromData(String data) {
        try {
            String[] parts = data.split(":");
            T instance = dataType.getDeclaredConstructor().newInstance();
            Field[] fields = dataType.getDeclaredFields();
            int partIndex = 0;
            for (Field field : fields) {
                Class<?> fieldType = field.getType();
                Serializer<?> serializer = serializeConfig.getById(fieldType);
                if (serializer == null) throw new RuntimeException("Unsupported type: " + fieldType.getName());
                if (partIndex >= parts.length) throw new RuntimeException("Not enough data parts for field: " + field.getName());
                String part = parts[partIndex++];
                Object value = serializer.deserialize(part);
                field.setAccessible(true);
                field.set(instance, value);
            }
            if (partIndex != parts.length) throw new RuntimeException("Unused data parts detected");
            dtos.add(instance);
        } catch (Exception e) {
            throw new RuntimeException("Deserialization error" + e.getMessage());
        }
    }

    @Override
    public Class<?> getId() {
        return dataType;
    }
}
