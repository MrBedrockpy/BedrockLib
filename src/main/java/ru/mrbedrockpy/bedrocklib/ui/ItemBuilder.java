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

    private ItemBuilder() {}

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public static ItemBuilder open(ItemStack itemStack) {
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder.itemStack = itemStack.clone();
        itemBuilder.itemMeta = itemStack.getItemMeta();
        return itemBuilder;
    }

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Block block, int amount) {
        itemStack = new ItemStack(block.getType(), amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Block block) {
        itemStack = new ItemStack(block.getType());
        itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder displayName(String value) {
        itemMeta.setDisplayName(value);

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

    @Deprecated
    public ItemBuilder addEnchantment(String enchantment, int lvl) {
        itemMeta.addEnchant(Enchantment.getByName(enchantment), lvl, true);
        return this;
    }

    @Deprecated
    public ItemBuilder removeEnchantment(String enchantment) {
        itemMeta.removeEnchant(Enchantment.getByName(enchantment));
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

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}