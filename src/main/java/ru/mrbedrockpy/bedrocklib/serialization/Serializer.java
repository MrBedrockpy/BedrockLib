package ru.mrbedrockpy.bedrocklib.serialization;

import lombok.AllArgsConstructor;
import ru.mrbedrockpy.bedrocklib.manager.ManagerItem;

import java.util.function.Function;

@AllArgsConstructor
public class Serializer<T> implements ManagerItem<Class<?>> {

    private final Class<T> dataType;
    private final Function<T, String> serializer;
    private final Function<String, T> deserializer;

    public String serialize(T object) {
        return serializer.apply(object);
    }

    public T deserialize(String data) {
        return deserializer.apply(data);
    }

    @Override
    public Class<?> getId() {
        return dataType;
    }
}
