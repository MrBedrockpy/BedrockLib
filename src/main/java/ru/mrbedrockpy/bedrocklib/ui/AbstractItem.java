package ru.mrbedrockpy.bedrocklib.ui;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractItem {

    private final Set<Menu> menus = new HashSet<>();

    public abstract void handleClick(Menu menu);

    public abstract ItemBuilder getItemBuilder(Player viewer);

    public final void addMenu(Menu menu) {
        menus.add(menu);
    }

    public final void removeMenu(Menu menu) {
        menus.remove(menu);
    }

    public final Set<Menu> getMenus() {
        return new HashSet<>(menus);
    }
}
