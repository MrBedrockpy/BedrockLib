package ru.mrbedrockpy.bedrocklib.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Menu implements Listener {

    private final Map<Integer, Item> slots = new HashMap<>();

    private final List<Inventory> menus = new ArrayList<>();

    public Menu(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    protected abstract String getTitle();

    protected abstract int getSize();

    public final void open(Player player) {
        Inventory menu = Bukkit.createInventory(player, getSize(), getTitle());

        for (Integer slot: slots.keySet()) {
            menu.setItem(slot, slots.get(slot).getItemIcon().build());
        }

        player.openInventory(menu);
        menus.add(menu);
    }

    protected final void setItem(int slot, Item item) {
        slots.put(slot, item);
    }

    protected abstract boolean isEditable();

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!menus.contains(event.getInventory())) return;
        if (event.getCurrentItem() == null) return;
        Item item = slots.get(event.getSlot());
        item.onClick(this, (Player) event.getWhoClicked(), event);
        event.setCancelled(!isEditable());
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        menus.remove(event.getInventory());
    }
}
