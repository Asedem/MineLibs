package de.asedem.minelibs.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) return;

        if (!InventoryBuilder.cancelClickEvent.isEmpty() &&
                InventoryBuilder.cancelClickEvent.contains(event.getWhoClicked().getUniqueId()))
            event.setCancelled(true);

        if (InventoryBuilder.clickableItems.isEmpty() ||
                !InventoryBuilder.clickableItems.containsKey(event.getWhoClicked().getUniqueId())) return;

        InventoryBuilder.clickableItems
                .get(event.getWhoClicked().getUniqueId())
                .stream()
                .filter(clickableItem -> clickableItem.shift() == event.isShiftClick())
                .filter(clickableItem -> clickableItem.index() == event.getSlot())
                .findFirst()
                .ifPresent(clickableItem -> clickableItem.playerConsumer().accept((Player) event.getWhoClicked()));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryBuilder.clickableItems.remove(event.getPlayer().getUniqueId());
        InventoryBuilder.cancelClickEvent.remove(event.getPlayer().getUniqueId());
    }
}
