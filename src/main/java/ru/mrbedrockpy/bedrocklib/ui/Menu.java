package ru.mrbedrockpy.bedrocklib.ui;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@Getter
@NoArgsConstructor
public class Menu {

    private Inventory inventory;
    private Gui gui;
    private Player viewer;

    public Menu(Gui gui) {
        this.gui = gui;
    }

    public Menu(Player viewer) {
        this.viewer = viewer;
    }

    public Menu(Gui gui, Player viewer) {
        this.gui = gui;
        this.viewer = viewer;
    }

    public Menu setGui(Gui gui) {
        this.gui = gui;
        return this;
    }

    public Menu setViewer(Player viewer) {
        this.viewer = viewer;
        return this;
    }

    public void open(Player viewer) {
        this.viewer = viewer;
        open();
    }

    public void open() {
        if (gui == null) return;
        if (viewer == null) return;
        if (inventory != null) return;
        inventory = gui.update(this);
        viewer.openInventory(inventory);
    }

    public void close() {
        inventory.close();
        inventory = null;
    }
}
