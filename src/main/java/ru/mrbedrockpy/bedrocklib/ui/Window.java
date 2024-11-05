package ru.mrbedrockpy.bedrocklib.ui;

import org.bukkit.entity.Player;

public class Window {

    private final Player player;
    private final Gui gui;

    public Window(Player player, Gui gui) {
        this.player = player;
        this.gui = gui;
    }

    public Gui getGui() {
        return gui;
    }

    public Player getPlayer() {
        return player;
    }

    public void open() {
        player.openInventory(gui.getInventory());
    }

    public void close() {
        player.closeInventory();
    }

}
