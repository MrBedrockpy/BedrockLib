package ru.mrbedrockpy.bedrocklib.serialization.string;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.mrbedrockpy.bedrocklib.serialization.ItemSerializationUtil;
import ru.mrbedrockpy.bedrocklib.serialization.Serializer;

import java.util.ArrayList;
import java.util.List;

public class BukkitStringSerializers {

    public static List<StringSerializer<?>> getSerializers() {
        return new ArrayList<>(List.of(
                getPlayerSerializer(),
                getOfflinePlayerSerializer(),
                getLocationSerializer(),
                getInventorySerializer(),
                getItemStackSerializer()
        ));
    }

    public static StringSerializer<Player> getPlayerSerializer() {
        return new StringSerializer<>(Player.class, Player::getName, Bukkit::getPlayer);
    }

    public static StringSerializer<OfflinePlayer> getOfflinePlayerSerializer() {
        return new StringSerializer<>(OfflinePlayer.class, OfflinePlayer::getName, Bukkit::getOfflinePlayer);
    }

    public static StringSerializer<Location> getLocationSerializer() {
        return new StringSerializer<>(Location.class,
                loc -> loc.getWorld() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ(),
                data -> {
                    try {
                        String[] dataArray = data.split(":");
                        return new Location(
                                Bukkit.getWorld(dataArray[0]),
                                Integer.parseInt(dataArray[1]),
                                Integer.parseInt(dataArray[2]),
                                Integer.parseInt(dataArray[3])
                        );
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public static StringSerializer<Inventory> getInventorySerializer() {
        return new StringSerializer<>(Inventory.class, ItemSerializationUtil::toBase64, ItemSerializationUtil::fromBase64);
    }

    public static StringSerializer<ItemStack> getItemStackSerializer() {
        return new StringSerializer<>(ItemStack.class, ItemSerializationUtil::itemStackToBase64, ItemSerializationUtil::itemStackFromBase64);
    }
}
