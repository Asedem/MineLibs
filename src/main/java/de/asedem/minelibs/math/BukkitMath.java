package de.asedem.minelibs.math;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BukkitMath {

    private BukkitMath() {
    }

    /**
     * Generates a Circle with a given amount of spots around a center Location.
     *
     * @param center The Location where the center of the circle should be.
     * @param radius The radius of the circle.
     * @param amount The amount of spots around the circle that should be returned.
     * @return A few spots around the center in a circle.
     */
    @NotNull
    public static List<Location> getCircle(@NotNull Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }
}
