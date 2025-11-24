package ru.mrbedrockpy.bedrocklib.serialization.string;

import ru.mrbedrockpy.bedrocklib.serialization.Serializer;

import java.util.function.Function;

public class StringSerializer<T> extends Serializer<T, String> {

    public StringSerializer(Class<T> dataType, Function<T, String> serializer, Function<String, T> deserializer) {
        super(dataType, serializer, deserializer);
    }
}
