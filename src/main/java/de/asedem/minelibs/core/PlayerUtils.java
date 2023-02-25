package de.asedem.minelibs.core;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerUtils {

    private PlayerUtils() {
    }

    /**
     * Checks if the Player is on the ground.
     *
     * @param player The player to check.
     * @return True if the Player is io the ground.
     */
    public static boolean isOnGround(@NotNull Player player) {
        Location location = player.getLocation();
        double y = location.getY();
        if (y == Math.floor(y) && !Double.isInfinite(y)) return false;
        return !location.add(0.0, -1.0, 0.0).getBlock().getType().equals(Material.AIR);
    }

    /**
     * Teleports the player to the nearest block down, that is found on the y axes.
     *
     * @param player The Player to teleport.
     */
    public static void teleportToNearestBlockDownY(@NotNull Player player) {
        Location location = player.getLocation();
        while (!location.getBlock().getType().equals(Material.AIR) &&
                location.getBlockY() > Objects.requireNonNull(location.getWorld()).getMinHeight())
            location = location.add(0.0, -1.0, 0.0);
        Location playerLocation = player.getLocation();
        playerLocation.setY(location.getY() + 1.0);
        player.teleport(playerLocation);
    }

    /**
     * Forces a Player to look at another Player.
     *
     * @param player The Player you want to control.
     * @param target The Player who should be watched.
     */
    public static void lookAt(@NotNull Player player, @NotNull Player target) {
        Vector direction = getVector(player).subtract(getVector(target)).normalize();
        double x = direction.getX();
        double y = direction.getY();
        double z = direction.getZ();

        Location changed = player.getLocation().clone();
        changed.setYaw(180 - toDegree(Math.atan2(x, z)));
        changed.setPitch(90 - toDegree(Math.acos(y)));

        player.teleport(changed);
    }

    /**
     * Turns an angle into degrees
     *
     * @param angle The angle to turn into degrees
     * @return The degree count from the angle
     */
    private static float toDegree(double angle) {
        return (float) Math.toDegrees(angle);
    }

    /**
     * Gets the Vector where an Entity is looking to
     * (If the entity is a player you get the vector of the eye direction)
     *
     * @param entity The entity to get the vector from
     * @return The vector from the entity
     */
    @NotNull
    private static Vector getVector(@NotNull Entity entity) {
        if (entity instanceof Player player) return player.getEyeLocation().toVector();
        else return entity.getLocation().toVector();
    }
}
