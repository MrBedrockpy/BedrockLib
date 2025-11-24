package ru.mrbedrockpy.bedrocklib.serialization;

import lombok.AllArgsConstructor;
import ru.mrbedrockpy.bedrocklib.manager.ManagerItem;

import java.util.function.Function;

@AllArgsConstructor
public abstract class Serializer<T, S> implements ManagerItem<Class<?>> {

    private final Class<T> dataType;
    private final Function<T, S> serializer;
    private final Function<S, T> deserializer;

    public S serialize(T object) {
        return serializer.apply(object);
    }
    public T deserialize(S data) {
        return deserializer.apply(data);
    }

    public Class<T> getId() {
        return dataType;
    }
}
