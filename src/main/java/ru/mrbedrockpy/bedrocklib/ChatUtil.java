package ru.mrbedrockpy.bedrocklib;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class ChatUtil {

    public static String format(char colorChar, String text) {
        return ChatColor.translateAlternateColorCodes(colorChar, text);
    }

    public static String format(String text) {
        return format('&', text);
    }

    public static List<String> format(char colorChar, List<String> text) {
        text.replaceAll(line -> ChatColor.translateAlternateColorCodes(colorChar, line));
        return text;
    }

    public static List<String> format(List<String> text) {
        return format('&', text);
    }

    public static String[] format(char colorChar, String[] text) {
        for (int i = 0; i < text.length; i++) text[i] = ChatColor.translateAlternateColorCodes(colorChar, text[i]);
        return text;
    }

    public static String[] format(String... text) {
        return format('&', text);
    }

    public static String applyPlaceholders(String text, Map<String, String> placeholdersMap) {
        for (String arg : placeholdersMap.keySet()) {
            text = text.replaceAll(arg, placeholdersMap.get(arg));
        }

        return format(text);
    }

    public static List<String> applyPlaceholders(List<String> text, Map<String, String> placeholdersMap) {
        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);

            for (String arg : placeholdersMap.keySet()) {
                line = line.replaceAll(arg, placeholdersMap.get(arg));
            }

            text.set(i, format(line));
        }

        return text;
    }

    public static String[] applyPlaceholders(String[] text, Map<String, String> placeholdersMap) {
        for (int i = 0; i < text.length; i++) {
            for (String arg : placeholdersMap.keySet()) {
                text[i] = format(text[i].replaceAll(arg, placeholdersMap.get(arg)));
            }
        }

        return text;
    }

    public static <T> T[] listToArray(List<T> l, Class<T> type) {
        if (l == null) return null;
        try {
            T[] arr = (T[]) Array.newInstance(type, l.size());
            for (int i = 0; i < l.size(); i++) arr[i] = l.get(i);
            return arr;
        } catch (Exception e) {
            return null;
        }
    }
}
