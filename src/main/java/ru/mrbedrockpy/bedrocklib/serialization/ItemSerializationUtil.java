package ru.mrbedrockpy.bedrocklib.serialization;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemSerializationUtil {

    public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);

            bukkitOutputStream.writeInt(items.length);
            for (ItemStack item : items) {
                bukkitOutputStream.writeObject(item);
            }
            bukkitOutputStream.close();

            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            Bukkit.getLogger().severe("Failed to serialize inventory: " + e);
            return null;
        }
    }

    public static ItemStack[] itemStackArrayFromBase64(String dataString) throws IOException {
        try {
            byte[] data = Base64.getDecoder().decode(dataString);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            BukkitObjectInputStream bukkitInputStream = new BukkitObjectInputStream(byteArrayInputStream);

            int length = bukkitInputStream.readInt();
            ItemStack[] items = new ItemStack[length];
            for (int i = 0; i < length; i++) {
                items[i] = (ItemStack) bukkitInputStream.readObject();
            }
            bukkitInputStream.close();

            return items;
        } catch (Exception e) {
            Bukkit.getLogger().severe("Failed to deserialize inventory: " + e);
            return new ItemStack[0];
        }
    }

    public static String toBase64(Inventory inventory) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inventory.getSize());

            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }

            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static Inventory fromBase64(String data) {
        if (data == null) return null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());

            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }

            dataInput.close();
            return inventory;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String itemStackToBase64(ItemStack item) {
        return itemStackArrayToBase64(new ItemStack[]{item});
    }

    public static ItemStack itemStackFromBase64(String data) {
        try {
            return itemStackArrayFromBase64(data)[0];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
