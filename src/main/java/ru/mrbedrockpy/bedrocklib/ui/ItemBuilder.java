package ru.mrbedrockpy.bedrocklib.ui;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.mrbedrockpy.bedrocklib.ChatUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Block block, int amount) {
        this.itemStack = new ItemStack(block.getType(), amount);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Block block) {
        this.itemStack = new ItemStack(block.getType());
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder displayName(String value) {
        this.itemMeta.setDisplayName(value);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        itemMeta.setLore(ChatUtil.format(lore));
        return this;
    }

    public ItemBuilder setLore(String[] lore) {
        itemMeta.setLore(Arrays.asList(ChatUtil.format(lore)));
        return this;
    }

    public ItemBuilder setLore(String lore) {
        itemMeta.setLore(Collections.singletonList(ChatUtil.format(lore)));
        return this;
    }

    public ItemBuilder setLore(List<String> lore, Map<String, String> placeholdersMap) {
        itemMeta.setLore(ChatUtil.applyPlaceholders(lore, placeholdersMap));
        return this;
    }

    public ItemBuilder setLore(String[] lore, Map<String, String> placeholdersMap) {
        itemMeta.setLore(Arrays.asList(ChatUtil.applyPlaceholders(lore, placeholdersMap)));
        return this;
    }

    public ItemBuilder setLore(String lore, Map<String, String> placeholdersMap) {
        itemMeta.setLore(Collections.singletonList(ChatUtil.applyPlaceholders(lore, placeholdersMap)));
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int lvl) {
        itemMeta.addEnchant(enchantment, lvl, true);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        itemMeta.removeEnchant(enchantment);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean b) {
        itemMeta.setUnbreakable(b);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder removeItemFlags(ItemFlag... itemFlags) {
        itemMeta.removeItemFlags(itemFlags);
        return this;
    }

    public ItemStack get() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
