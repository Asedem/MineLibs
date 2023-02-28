package de.asedem.minelibs.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

class AsyncPlayerMoveListener {

    private static final Map<Player, Location> previous = new HashMap<>();

    private AsyncPlayerMoveListener() {
    }

    public static void activate(@NotNull Plugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () ->
                Bukkit.getOnlinePlayers().forEach(player -> {
                    if (previous.containsKey(player) && (!previous.get(player).equals(player.getLocation()))) {
                        AsyncPlayerMoveEvent asyncPlayerMoveEvent =
                                new AsyncPlayerMoveEvent(true, player, previous.get(player), player.getLocation());
                        Bukkit.getPluginManager().callEvent(asyncPlayerMoveEvent);
                        if (!asyncPlayerMoveEvent.isCancelled())
                            Bukkit.getScheduler().runTask(plugin, () -> player.teleport(previous.get(player)));
                    }
                    previous.put(player, player.getLocation());
                }), 1, 1);
    }
}
