package de.asedem.minelibs.enchant;

import de.asedem.minelibs.color.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.List;
import java.util.Objects;

public abstract class CustomEnchantment {

    /**
     * Gets the namespace key from the Enchantment
     *
     * @return the namespace key
     */
    @NotNull
    public abstract String getKey();

    /**
     * Gets the name of the Enchantment<p>
     * This will be displayed over the Lore of the Item
     *
     * @return the name of the Enchantment
     */
    @NotNull
    public abstract String getName();

    /**
     * Gets the maximum Level of the Enchantment<p>
     * If the number is 0 the Enchantment is not level able
     *
     * @return the maximum Level of the Enchantment
     */
    @Range(from = 0, to = 256)
    public abstract int getMaxLevel();

    /**
     * Gives a list of the Bukkit enchantments that the Enchantment conflicts with
     *
     * @return a list of the Bukkit enchantments that the Enchantment conflicts with
     */
    @NotNull
    public abstract List<Enchantment> conflictsWithBukkit();


    /**
     * Gives a list of the custom enchantments that the Enchantment conflicts with
     *
     * @return a list of the custom enchantments that the Enchantment conflicts with
     */
    @NotNull
    public abstract List<CustomEnchantment> conflictsWithCustom();

    /**
     * Gives if the Enchantment is a Curse<p>
     * The Item with the Enchantment will be displayed red above the lore
     *
     * @return true if the Enchantment is a Curse
     */
    public abstract boolean isCurse();

    /**
     * Gives an Item Target that you can apply the Enchantment to
     *
     * @return an Item Target that you can apply the Enchantment to
     */
    @NotNull
    public abstract EnchantmentTarget getItemTarget();

    /**
     * Specifies if the Enchantment can be applied to a specific ItemStack
     *
     * @param itemStack the ItemStack that is checked
     * @return if the Enchantment can be applied to the itemStack
     */
    public abstract boolean canEnchantItem(@NotNull ItemStack itemStack);

    /**
     * Get the Lore string
     *
     * @return the lore string
     */
    @NotNull
    public String getEnchantmentString() {
        return ChatColor.translate('&', (this.isCurse() ? "&c" : "&7") + this.getName());
    }

    /**
     * Get the Lore string for a specific level
     *
     * @param level the level behind the name
     * @return the lore string
     */
    @NotNull
    public String getEnchantmentString(int level) {
        return ChatColor.translate('&', (this.isCurse() ? "&c" : "&7") + this.getName() + " " + RomanNumber.getWrittenByNumeric(level));
    }
}
