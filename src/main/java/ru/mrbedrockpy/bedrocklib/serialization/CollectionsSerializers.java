package ru.mrbedrockpy.bedrocklib.serialization;

import java.io.*;
import java.util.*;

public class CollectionsSerializers {

    public static List<Serializer<?>> getCollectionsSerializers() {
        return new ArrayList<>(List.of(
                new ListSerializer(),
                new SetSerializer(),
                new MapSerializer()
        ));
    }

    public static Serializer<List> getListSerializer() {
        return new ListSerializer();
    }

    public static Serializer<Set> getSetSerializer() {
        return new SetSerializer();
    }

    public static Serializer<Map> getMapSerializer() {
        return new MapSerializer();
    }

    public static class ListSerializer extends Serializer<List> {

        public ListSerializer() {
            super(List.class,
                    (list) -> {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                            oos.writeObject(list);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return Base64.getEncoder().encodeToString(baos.toByteArray());
                    },
                    (data) -> {
                        byte[] bytes = Base64.getDecoder().decode(data);
                        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                            return (List<?>) ois.readObject();
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    public static class SetSerializer extends Serializer<Set> {

        public SetSerializer() {
            super(Set.class,
                    (set) -> {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                            oos.writeObject(set);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return Base64.getEncoder().encodeToString(baos.toByteArray());
                    },
                    (data) -> {
                        byte[] bytes = Base64.getDecoder().decode(data);
                        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                            return (Set<?>) ois.readObject();
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }

    public static class MapSerializer extends Serializer<Map> {

        public MapSerializer() {
            super(Map.class,
                    (map) -> {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                            oos.writeObject(map);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return Base64.getEncoder().encodeToString(baos.toByteArray());
                    },
                    (data) -> {
                        byte[] bytes = Base64.getDecoder().decode(data);
                        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                        try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                            return (Map<?, ?>) ois.readObject();
                        } catch (IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }
}
