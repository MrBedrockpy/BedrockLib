package ru.mrbedrockpy.bedrocklib.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Gui {

    private final String[] structure;
    private final Map<Character, AbstractItem> items;

    public Gui(String[] structure) {
        this.structure = structure;
        this.items = new HashMap<>();
        if (!checkValidStructure()) throw new RuntimeException("Invalid structure!");
    }

    private boolean checkValidStructure() {
        if (structure.length < 1 || structure.length > 6) return false;
        for (String line: structure)
            if (line.length() != 9)
                return false;
        return true;
    }

    public Inventory update(Menu menu) {
        Inventory inventory = menu.getInventory();
        if (inventory == null) inventory = Bukkit.createInventory(menu.getViewer(), structure.length * 9);
        for (int row = 0; row < structure.length; row++) {
            for (int column = 0; column < 9; column++) {
                int slot = row * 9 + column;
                inventory.clear(slot);
                char symbol = structure[row].charAt(column);
                if (symbol == ' ') continue;
                AbstractItem item = items.get(symbol);
                if (item == null) continue;
                inventory.setItem(slot, item.getItemBuilder(menu.getViewer()).get());
            }
        }
        return inventory;
    }

    public void addIngredient(char symbol, AbstractItem item) {
        if (symbol == ' ') return;
        this.items.put(symbol, item);
    }

    public void addIngredient(char symbol, ItemStack stack) {
        this.addIngredient(symbol, new SimpleItem(stack));
    }

    public void addIngredient(char symbol, Material material) {
        this.addIngredient(symbol, new SimpleItem(material));
    }

    public String[] getStructure() {
        return structure.clone();
    }
}
