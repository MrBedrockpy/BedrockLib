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
import java.util.List;

public abstract class Menu implements Listener {

    protected final List<Window> windows = new ArrayList<>();

    public Menu(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public final void open(Player player) {
        Gui gui = new Gui.Builder()
                .title(getTitle())
                .size(getSize())
                .build();
        settingItems(gui);
        Window window = new Window(player, gui);
        windows.add(window);
        window.open();
    }

    protected abstract String getTitle();

    protected abstract int getSize();

    protected void settingItems(Gui gui) {}

    protected void onClose(Window window) {}

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!containsInventory(event.getInventory())) return;
        if (event.getCurrentItem() == null) return;
        Item item = getGuiByPlayer((Player) event.getWhoClicked()).getItems().get(event.getSlot());
        if (item == null) return;
        item.onClick(this, (Player) event.getWhoClicked(), event);
        event.setCancelled(true);
    }

    protected boolean containsInventory(Inventory inventory) {
        for (Window window: windows) if (window.getPlayer().getInventory() == inventory) return true;
        return false;
    }

    protected Gui getGuiByPlayer(Player player) {
        for (Window window: windows) if (window.getPlayer() == player) return window.getGui();
        return null;
    }

    @EventHandler
    public final void onInventoryClose(InventoryCloseEvent event) {
        for (Window window: windows) {
            if (window.getPlayer() != event.getPlayer()) continue;
            onClose(window);
            window.close();
            windows.remove(window);
        }
    }
}
