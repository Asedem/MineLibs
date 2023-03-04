package de.asedem.minelibs.math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    private static Region limited;
    private static Region testing1;
    private static Region testing2;

    @BeforeAll
    static void setup() {

        RegionTest.limited = Region.initialize();
        RegionTest.limited.points().add(new Point(0, 0));
        RegionTest.limited.points().add(new Point(0, 8));

        RegionTest.testing1 = Region.initialize();
        RegionTest.testing1.points().add(new Point(0, 0));
        RegionTest.testing1.points().add(new Point(4, 0));
        RegionTest.testing1.points().add(new Point(4, 4));
        RegionTest.testing1.points().add(new Point(0, 4));

        RegionTest.testing2 = Region.initialize();
        RegionTest.testing2.points().add(new Point(0, 0));
        RegionTest.testing2.points().add(new Point(0, 4));
        RegionTest.testing2.points().add(new Point(4, 4));
        RegionTest.testing2.points().add(new Point(4, 0));
    }

    @Test
    void testIfPointIsInRegion() {
        assertTrue(RegionTest.testing1.points().contains(new Point(0, 0)));
        assertTrue(RegionTest.testing1.points().contains(new Point(4, 4)));
        assertTrue(RegionTest.testing2.points().contains(new Point(0, 0)));
        assertTrue(RegionTest.testing2.points().contains(new Point(4, 4)));
    }

    @Test
    void testIfPointIsOutOfRegion() {
        assertFalse(RegionTest.testing1.points().contains(new Point(5, 0)));
        assertFalse(RegionTest.testing1.points().contains(new Point(0, 5)));
        assertFalse(RegionTest.testing2.points().contains(new Point(5, 0)));
        assertFalse(RegionTest.testing2.points().contains(new Point(0, 5)));
    }

    @Test
    void testAreaCalculation() {
        assertEquals(0, RegionTest.limited.area());
        assertEquals(16, RegionTest.testing1.area());
        assertEquals(16, RegionTest.testing2.area());
    }

    @Test
    void testVectorized() {
        assertNull(RegionTest.limited.vectorized(0));
        assertEquals(4, Objects.requireNonNull(RegionTest.testing1.vectorized(0)).get(0).length());
    }
}
