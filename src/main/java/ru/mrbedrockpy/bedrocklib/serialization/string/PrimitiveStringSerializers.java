package ru.mrbedrockpy.bedrocklib.serialization.string;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveStringSerializers {

    public static List<StringSerializer<?>> getSerializers() {
        return new ArrayList<>(List.of(
                getPrimitiveByteSerializer(),
                getPrimitiveShortSerializer(),
                getPrimitiveIntSerializer(),
                getPrimitiveLongSerializer(),
                getPrimitiveFloatSerializer(),
                getPrimitiveDoubleSerializer(),
                getPrimitiveBoolSerializer(),
                getPrimitiveCharSerializer(),
                getByteSerializer(),
                getShortSerializer(),
                getIntegerSerializer(),
                getLongSerializer(),
                getFloatSerializer(),
                getDoubleSerializer(),
                getBooleanSerializer(),
                getCharacterSerializer(),
                getStringSerializer()
        ));
    }

    public static StringSerializer<String> getStringSerializer() {
        return new StringSerializer<>(String.class, s -> s, s -> s);
    }

    public static StringSerializer<Byte> getPrimitiveByteSerializer() {
        return new StringSerializer<>(byte.class, String::valueOf, Byte::parseByte);
    }

    public static StringSerializer<Short> getPrimitiveShortSerializer() {
        return new StringSerializer<>(short.class, String::valueOf, Short::parseShort);
    }

    public static StringSerializer<Integer> getPrimitiveIntSerializer() {
        return new StringSerializer<>(int.class, String::valueOf, Integer::parseInt);
    }

    public static StringSerializer<Long> getPrimitiveLongSerializer() {
        return new StringSerializer<>(long.class, String::valueOf, Long::parseLong);
    }

    public static StringSerializer<Float> getPrimitiveFloatSerializer() {
        return new StringSerializer<>(float.class, String::valueOf, Float::parseFloat);
    }

    public static StringSerializer<Double> getPrimitiveDoubleSerializer() {
        return new StringSerializer<>(double.class, String::valueOf, Double::parseDouble);
    }

    public static StringSerializer<Boolean> getPrimitiveBoolSerializer() {
        return new StringSerializer<>(boolean.class, String::valueOf, Boolean::parseBoolean);
    }

    public static StringSerializer<Character> getPrimitiveCharSerializer() {
        return new StringSerializer<>(char.class, String::valueOf, s -> s.charAt(0));
    }

    public static StringSerializer<Byte> getByteSerializer() {
        return new StringSerializer<>(Byte.class, String::valueOf, Byte::parseByte);
    }

    public static StringSerializer<Short> getShortSerializer() {
        return new StringSerializer<>(Short.class, String::valueOf, Short::parseShort);
    }

    public static StringSerializer<Integer> getIntegerSerializer() {
        return new StringSerializer<>(Integer.class, String::valueOf, Integer::parseInt);
    }

    public static StringSerializer<Long> getLongSerializer() {
        return new StringSerializer<>(Long.class, String::valueOf, Long::parseLong);
    }

    public static StringSerializer<Float> getFloatSerializer() {
        return new StringSerializer<>(Float.class, String::valueOf, Float::parseFloat);
    }

    public static StringSerializer<Double> getDoubleSerializer() {
        return new StringSerializer<>(Double.class, String::valueOf, Double::parseDouble);
    }

    public static StringSerializer<Boolean> getBooleanSerializer() {
        return new StringSerializer<>(Boolean.class, String::valueOf, Boolean::parseBoolean);
    }

    public static StringSerializer<Character> getCharacterSerializer() {
        return new StringSerializer<>(Character.class, String::valueOf, s -> s.charAt(0));
    }
}
