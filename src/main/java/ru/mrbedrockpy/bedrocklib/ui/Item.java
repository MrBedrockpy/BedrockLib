package ru.mrbedrockpy.bedrocklib.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class Item {

    public abstract ItemBuilder getItemIcon();

    public abstract void onClick(Menu menu, Player player, InventoryClickEvent event);

}
