package de.asedem.minelibs.enchant;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EnchantmentHandler {

    @NotNull
    public static ItemStack applyEnchantmentToItem(@NotNull ItemStack itemStack, @NotNull CustomEnchantment enchantment, int level) {
        if (!enchantment.getItemTarget().includes(itemStack) && !enchantment.canEnchantItem(itemStack)) return itemStack;
        if (itemStack.getItemMeta() == null) return itemStack;
        final List<String> lore = Objects.requireNonNull(itemStack.getItemMeta()).hasLore() ?
                new LinkedList<>(Objects.requireNonNull(itemStack.getItemMeta().getLore())) :
                new LinkedList<>();
        if (lore.stream().anyMatch(line -> line.equals(enchantment.getEnchantmentString(level)))) {
            lore.remove(enchantment.getEnchantmentString(level));
            lore.add(0, enchantment.getEnchantmentString(level + 1));
        } else if (lore.stream().anyMatch(line -> line.startsWith(enchantment.getEnchantmentString()))) return itemStack;
        else lore.add(0, enchantment.getEnchantmentString(level));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
