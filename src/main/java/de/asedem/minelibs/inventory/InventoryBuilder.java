package de.asedem.minelibs.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class InventoryBuilder {

    protected static final Map<UUID, List<ClickableItem>> clickableItems = new HashMap<>();
    protected static final List<UUID> cancelClickEvent = new ArrayList<>();

    protected Inventory currentBuilding;

    protected List<ClickableItem> list = new LinkedList<>();
    protected boolean cancelClick = false;

    public InventoryBuilder() {
        currentBuilding = Bukkit.createInventory(null, InventoryType.CHEST);
    }

    public InventoryBuilder(@NotNull Inventory base) {
        currentBuilding = base;
    }

    public InventoryBuilder(@NotNull InventoryType inventoryType) {
        currentBuilding = Bukkit.createInventory(null, inventoryType);
    }

    public InventoryBuilder(int inventorySize) {
        currentBuilding = Bukkit.createInventory(null, inventorySize);
    }

    public InventoryBuilder(@NotNull InventoryHolder owner, InventoryType inventoryType) {
        currentBuilding = Bukkit.createInventory(owner, inventoryType);
    }

    public InventoryBuilder(@NotNull InventoryHolder owner, int inventorySize) {
        currentBuilding = Bukkit.createInventory(owner, inventorySize);
    }

    public InventoryBuilder(InventoryType inventoryType, @NotNull String title) {
        currentBuilding = Bukkit.createInventory(null, inventoryType, title);
    }

    public InventoryBuilder(int inventorySize, @NotNull String title) {
        currentBuilding = Bukkit.createInventory(null, inventorySize, title);
    }

    public InventoryBuilder(@NotNull InventoryHolder owner, InventoryType inventoryType, @NotNull String title) {
        currentBuilding = Bukkit.createInventory(owner, inventoryType, title);
    }

    public InventoryBuilder(@NotNull InventoryHolder owner, int inventorySize, @NotNull String title) {
        currentBuilding = Bukkit.createInventory(owner, inventorySize, title);
    }

    @NotNull
    public Inventory toInventory() {
        return currentBuilding;
    }

    public void openToHumanEntity(@NotNull HumanEntity humanEntity) {
        humanEntity.openInventory(currentBuilding);
        InventoryBuilder.cancelClickEvent.remove(humanEntity.getUniqueId());
        InventoryBuilder.clickableItems.remove(humanEntity.getUniqueId());
        if (this.cancelClick) InventoryBuilder.cancelClickEvent.add(humanEntity.getUniqueId());
        if (!this.list.isEmpty()) InventoryBuilder.clickableItems.put(humanEntity.getUniqueId(), this.list);
    }

    @NotNull
    public InventoryBuilder fillBlanks(@NotNull Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7"));
        itemStack.setItemMeta(itemMeta);
        for (int i = 0; i < currentBuilding.getSize(); i++)
            if (currentBuilding.getItem(i) == null || Objects.requireNonNull(currentBuilding.getItem(i)).getType() == Material.AIR)
                currentBuilding.setItem(i, itemStack);
        return this;
    }

    @NotNull
    public InventoryBuilder addItem(@NotNull ItemStack itemStack) {
        currentBuilding.addItem(itemStack);
        return this;
    }

    @Deprecated
    @NotNull
    public InventoryBuilder addItem(@NotNull ItemStack itemStack, Consumer<Player> clickAction) {
        currentBuilding.addItem(itemStack);

        /*if (title == null) return this;
        if (InventoryBuilder
                .clickableItems
                .stream()
                .noneMatch(itemStack1 -> itemStack1.inventory().equals(title)))
            InventoryBuilder.clickableItems.add(new ClickableItem(title, currentBuilding.getHolder(), 0, clickAction));*/
        return this;
    }

    @NotNull
    public InventoryBuilder setItem(int index, @NotNull ItemStack itemStack) {
        currentBuilding.setItem(index, itemStack);
        return this;
    }

    @NotNull
    public InventoryBuilder setItem(int index, @NotNull ItemStack itemStack, Consumer<Player> clickAction) {
        currentBuilding.setItem(index, itemStack);
        this.list.add(new ClickableItem(index, false, clickAction));
        return this;
    }

    @NotNull
    public InventoryBuilder setItem(int index, @NotNull ItemStack itemStack, Consumer<Player> clickAction, Consumer<Player> shiftClickAction) {
        currentBuilding.setItem(index, itemStack);
        this.list.add(new ClickableItem(index, false, clickAction));
        this.list.add(new ClickableItem(index, true, shiftClickAction));
        return this;
    }

    @NotNull
    public InventoryBuilder setItems(int startIndex, int endIndex, @NotNull ItemStack itemStack) {
        for (int i = startIndex; i <= endIndex; i++) currentBuilding.setItem(i, itemStack);
        return this;
    }

    @NotNull
    public InventoryBuilder addItems(@NotNull List<ItemStack> itemStacks) {
        itemStacks.forEach(itemStack -> currentBuilding.addItem(itemStack));
        return this;
    }

    @NotNull
    public InventoryBuilder setItems(int startIndex, @NotNull List<ItemStack> itemStacks) {
        AtomicInteger index = new AtomicInteger(startIndex);
        itemStacks.forEach(itemStack -> {
            if (index.get() < currentBuilding.getSize()) {
                currentBuilding.setItem(index.get(), itemStack);
                index.set(index.get() + 1);
            }
        });
        return this;
    }

    @NotNull
    public InventoryBuilder setMaxStackSize(int size) {
        currentBuilding.setMaxStackSize(size);
        return this;
    }

    @NotNull
    public InventoryBuilder setContents(@NotNull ItemStack[] items) {
        currentBuilding.setContents(items);
        return this;
    }

    @NotNull
    public InventoryBuilder setStorageContents(@NotNull ItemStack[] items) {
        currentBuilding.setStorageContents(items);
        return this;
    }

    public boolean isCancelClick() {
        return cancelClick;
    }

    @NotNull
    public InventoryBuilder setCancelClick(boolean cancelClick) {
        this.cancelClick = cancelClick;
        return this;
    }
}
