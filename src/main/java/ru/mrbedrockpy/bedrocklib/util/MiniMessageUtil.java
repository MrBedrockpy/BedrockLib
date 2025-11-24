package ru.mrbedrockpy.bedrocklib.util;

import lombok.Setter;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class MiniMessageUtil {

    @Setter private static JavaPlugin plugin;

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static String toString(TextComponent component) {
        return miniMessage.serializeOrNull(component);
    }

    public static TextComponent fromString(String string) {
        try {
            return (TextComponent) miniMessage.deserialize(string);
        } catch (NullPointerException e) {
            plugin.getLogger().warning("Text " + string + " can't be deserialized!");
            return null;
        } catch (ClassCastException e) {
            plugin.getLogger().warning("Text " + string + "is not TextComponent");
            return null;
        }
    }
}
