package de.asedem.minelibs.math;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A help object to work with polygonal regions in minecraft
 *
 * @param points the corner points of the polygonal area
 */
public record Region(
        @NotNull List<@NotNull Point> points
) {

    /**
     * Initialize a new Region without minding about the constructing parameters
     *
     * @return a new Region with an empty List of points
     */
    @NotNull
    public static Region initialize() {
        return new Region(new ArrayList<>());
    }

    /**
     * Converts a Bukkit location to a Point from java.awt
     *
     * @param location the Bukkit location
     * @return the Point from the java.awt package
     */
    @NotNull
    public static Point locationToPoint(@NotNull Location location) {
        return new Point(location.getBlockX(), location.getBlockZ());
    }

    /**
     * Converts a Point from java.awt to a Bukkit location
     *
     * @param point the Point from java.awt
     * @param world the world to convert
     * @param height the height of the location
     * @return the Bukkit location
     */
    @NotNull
    public static Location pointToLocation(@NotNull Point point, @NotNull World world, int height) {
        return new Location(world, point.getX(), height, point.getY());
    }

    /**
     * Converts the given list of Points to a Polygon
     *
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
     *
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
        final int result = (int) (sum + 0.5D);
        return result > 0 ? result : (result * -1) + 1;
    }

    /**
     * Should give the Vectors to the next point
     *
     * @param heightVariation The height variation of the Vector
     * @return the vector
     */
    @Nullable
    public List<Vector> vectorized(int heightVariation) {
        if (this.invalid()) return null;
        final List<Vector> vectors = new ArrayList<>();
        for (int i = 0; i < this.points().size(); i++) {
            final Point self = this.points().get(i);
            Point next;
            if (i == this.points().size() - 1) next = this.points().get(0);
            else next = this.points().get(i + 1);
            vectors.add(this.toVector(self, next, heightVariation));
        }
        return vectors;
    }

    /**
     * Draws points around the given edgePoints
     *
     * @param space the space between the generated points
     * @return the generated points
     */
    @NotNull
    public List<Point> wayPoints(int space) {
        if (this.invalid()) return Collections.emptyList();
        final List<Point> wayPoints = new ArrayList<>();
        for (int i = 0; i < this.points().size(); i++) {
            final Point self = this.points().get(i);
            Point next;
            if (i == this.points().size() - 1) next = this.points().get(0);
            else next = this.points().get(i + 1);
            final double distance = this.toVector(self, next, 0).length();
            final Vector selfVector = new Vector(self.x, 0, self.y);
            final Vector nextVector = new Vector(next.x, 0, next.y);
            final Vector vec = nextVector.clone()
                    .subtract(selfVector)
                    .normalize()
                    .multiply(space);
            wayPoints.add(new Point(selfVector.getBlockX(), selfVector.getBlockZ()));
            for (double j = 0; j < distance; selfVector.add(vec)) {
                wayPoints.add(new Point(selfVector.getBlockX(), selfVector.getBlockZ()));
                j += space;
            }
        }
        return wayPoints;
    }

    /**
     * ConvertsTwo points to a vector
     *
     * @param self the fist point to convert
     * @param next the second point to convert
     * @param heightVariation the variation in height
     * @return the responded vector
     */
    @NotNull
    private Vector toVector(@NotNull Point self, @NotNull Point next, int heightVariation) {
        return new Vector(next.x - self.x, heightVariation, next.y - self.y);
    }

    /**
     * Checks if the value is invalid
     *
     * @return true if the value is invalid
     */
    public boolean invalid() {
        return this.points().size() <= 2;
    }
}
