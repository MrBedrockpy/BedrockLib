package ru.mrbedrockpy.bedrocklib.ui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Gui {

    private String title;
    private int size;
    private final Map<Integer, Item> items;

    public Gui() {
        items = new HashMap<>();
    }

    public Gui(int size) {
        items = new HashMap<>();
        this.size = size;
    }

    public Gui(String title) {
        items = new HashMap<>();
        this.title = title;
    }

    public Gui(String title, int size) {
        items = new HashMap<>();
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size % 9 == 0) this.size = size;
    }

    public Map<Integer, Item> getItems() {
        return items;
    }

    public void setItem(int slot, Item item) {
        items.put(slot, item);
    }

    public Inventory getInventory() {
        int maxKey = Integer.MIN_VALUE;
        for (int key: items.keySet()) if (key > maxKey) maxKey = key;
        if (maxKey >= size) throw new IndexOutOfBoundsException("Key too large");
        Inventory inventory = Bukkit.createInventory(new InventoryHolder(), size, title);
        items.forEach((slot, item) -> inventory.setItem(slot, item.getItemIcon().build()));
        return inventory;
    }

    public static final class Builder {

        private final Gui gui;

        public Builder() {
            gui = new Gui();
        }

        public Builder title(String title) {
            gui.setTitle(title);
            return this;
        }

        public Builder size(int size) {
            gui.setSize(size);
            return this;
        }

        public Builder setItem(int slot, Item item) {
            gui.setItem(slot, item);
            return this;
        }

        public Gui build() {
            return gui;
        }

    }

}
