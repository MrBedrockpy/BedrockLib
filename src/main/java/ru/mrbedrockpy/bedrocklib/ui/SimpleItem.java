package ru.mrbedrockpy.bedrocklib.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public class SimpleItem extends AbstractItem {

    private final ItemBuilder itemBuilder;

    public SimpleItem(Material material) {
        this.itemBuilder = new ItemBuilder(material);
    }

    public SimpleItem(Material material, int amount) {
        this.itemBuilder = new ItemBuilder(material, amount);
    }

    public SimpleItem(ItemStack itemStack) {
        this.itemBuilder = new ItemBuilder(itemStack);
    }

    @Override
    public void handleClick(Menu menu) {}

    @Override
    public ItemBuilder getItemBuilder(Player viewer) {
        return itemBuilder;
    }
}
