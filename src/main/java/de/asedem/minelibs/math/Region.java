package de.asedem.minelibs.math;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A help object to work with polygonal regions in minecraft
 * @param points the corner points of the polygonal area
 */
public record Region(
        @NotNull List<@NotNull Point> points
) {

    /**
     * Initialize a new Region without minding about the constructing parameters
     * @return a new Region with an empty List of points
     */
    @NotNull
    public static Region initialize() {
        return new Region(new ArrayList<>());
    }

    /**
     * Converts a Bukkit location to a Point from java.awt
     * @param location the Bukkit location
     * @return the Point from the java.awt package
     */
    @NotNull
    public static Point locationToPoint(@NotNull Location location) {
        return new Point(location.getBlockX(), location.getBlockZ());
    }

    /**
     * Converts the given list of Points to a Polygon
     * @return the Polygon that is calculated
     */
    @NotNull
    public Polygon polygon() {
        Polygon polygon = new Polygon();
        this.points().forEach(point -> polygon.addPoint(point.x, point.y));
        return polygon;
    }

    /**
     * Should give the blocks, that the Region contains
     * @return the amount of blocks in the area
     */
    public int area() {
        if (this.invalid()) return 0;
        final Polygon polygon = this.polygon();
        double sum = 0;
        for (int i = 0; i < polygon.npoints; i++) {
            final int j = (i + 1) % polygon.npoints;
            sum += polygon.xpoints[i] * polygon.ypoints[j];
            sum -= polygon.ypoints[i] * polygon.xpoints[j];
        }
        sum /= 2.0D;
        return (int) (sum + 0.5D);
    }

    /**
     * Should give the Vectors to the next point
     * @param heightVariation The height variation of the Vector
     * @return the vector
     */
    @Nullable
    public List<Vector> vectorize(int heightVariation) {
        if (this.invalid()) return null;
        final List<Vector> vectors = new ArrayList<>();
        for (int i = 0; i < this.points().size(); i++) {
            final Point self = this.points().get(i);
            Point next;
            if (i == this.points().size() - 1) next = this.points().get(0);
            else next = this.points().get(i + 1);
            vectors.add(new Vector(next.x - self.x, heightVariation, next.y - self.y));
        }
        return vectors;
    }

    /**
     * Checks if the value is invalid
     * @return true if the value is invalid
     */
    public boolean invalid() {
        return this.points().size() <= 2;
    }
}
