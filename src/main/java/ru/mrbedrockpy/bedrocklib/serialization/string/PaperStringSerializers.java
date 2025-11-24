package ru.mrbedrockpy.bedrocklib.serialization.string;

import net.kyori.adventure.text.TextComponent;
import ru.mrbedrockpy.bedrocklib.serialization.Serializer;
import ru.mrbedrockpy.bedrocklib.util.MiniMessageUtil;

import java.util.ArrayList;
import java.util.List;

public class PaperStringSerializers {

    public static List<StringSerializer<?>> getSerializers() {
        return new ArrayList<>(List.of(
                getTextComponentSerializer()
        ));
    }

    public static StringSerializer<TextComponent> getTextComponentSerializer() {
        return new StringSerializer<>(TextComponent.class, MiniMessageUtil::toString, MiniMessageUtil::fromString);
    }

}
