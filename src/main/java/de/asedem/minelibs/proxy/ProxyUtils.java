package de.asedem.minelibs.proxy;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class ProxyUtils {

    public ProxyUtils() {
    }

    public static boolean isBungee(Plugin plugin) {
        ConfigurationSection settings = plugin.getServer().spigot().getConfig().getConfigurationSection("settings");
        if (settings == null) return false;
        return settings.getBoolean("settings.bungeecord");
    }
}
