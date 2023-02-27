package de.asedem.minelibs;

import de.asedem.minelibs.inventory.InventoryBuilder;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MineLibs {

    /**
     * Inits the InventoryClickModule.<br>
     * Without this the inventory Click that is set in the InventoryBuilder will not work.<br><br>
     * Alternatively: InventoryBuilder.initClickModule(plugin)
     *
     * @see InventoryBuilder#initClickModule(Plugin)
     *
     * @param plugin The Plugin, where the API is used in.
     */
    public static void initInventoryClickModule(@NotNull Plugin plugin) {
        InventoryBuilder.initClickModule(plugin);
    }
}
