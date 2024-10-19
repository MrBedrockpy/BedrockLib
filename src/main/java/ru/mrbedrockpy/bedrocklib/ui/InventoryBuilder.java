package ru.mrbedrockpy.bedrocklib.ui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryBuilder {

    private final List<ItemStack> items;

    private String title;

    private int size;

    public InventoryBuilder(int lines) {
        this.title = "";
        this.items = new ArrayList<>();
        this.size = lines * 9;
    }

    public InventoryBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public InventoryBuilder addItem(ItemStack item) {
        this.items.add(item);
        return this;
    }

    public InventoryBuilder addItems(List<ItemStack> items) {
        this.items.addAll(items);
        return this;
    }

    public InventoryBuilder addItems(ItemStack... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public InventoryBuilder setSize(int lines) {
        this.size = lines * 9;
        return this;
    }

    public Inventory build() {
        return Bukkit.createInventory(new InventoryHolder(), size, title);
    }

}
