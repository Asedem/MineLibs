package de.asedem.minelibs.color;

/**
 * A holder class for RGB values
 *
 * @param red   red color
 * @param green green color
 * @param blue  blue color
 */
public record RGB(
        int red,
        int green,
        int blue
) {

    /**
     * Return the RGB values as an int array (red, green, blue)
     *
     * @return The RGB values as an int array
     */
    public int[] asIntArray() {
        return new int[]{red, green, blue};
    }
}
