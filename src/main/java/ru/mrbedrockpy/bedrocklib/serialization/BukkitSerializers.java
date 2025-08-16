package ru.mrbedrockpy.bedrocklib.serialization;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BukkitSerializers {

    public static List<Serializer<?>> getBukkitSerializers() {
        return new ArrayList<>(List.of(
                getPlayerSerializer(),
                getOfflinePlayerSerializer(),
                getLocationSerializer()
        ));
    }

    public static Serializer<Player> getPlayerSerializer() {
        return new Serializer<>(Player.class, Player::getName, Bukkit::getPlayer);
    }

    public static Serializer<OfflinePlayer> getOfflinePlayerSerializer() {
        return new Serializer<>(OfflinePlayer.class, OfflinePlayer::getName, Bukkit::getOfflinePlayer);
    }

    public static Serializer<Location> getLocationSerializer() {
        return new Serializer<>(Location.class,
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
}
