package de.asedem.minelibs;

import de.asedem.minelibs.inventory.InventoryClickListener;
import de.asedem.minelibs.movement.AsyncPlayerMoveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MineLibs {

    /**
     * Inits the InventoryClickModule.
     * Without this the inventory Click that is set in the InventoryBuilder will not work.
     *
     * @param plugin The Plugin, where the API is used in.
     */
    public static void initInventoryClickModule(@NotNull Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), plugin);
    }

    /**
     * Inits the AsyncPlayerMoveModule.
     * Without this the AsyncPlayerMoveEvent will not work.
     *
     * @param plugin The Plugin, where the API is used in.
     */
    public static void initAsyncPlayerMoveEventModule(@NotNull Plugin plugin) {
        AsyncPlayerMoveListener.activate(plugin);
    }
}
