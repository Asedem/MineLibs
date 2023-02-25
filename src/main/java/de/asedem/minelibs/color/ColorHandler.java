package de.asedem.minelibs.color;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ColorHandler {

    private ColorHandler() {
    }

    /**
     * Returns the color on a specific point of the sentence
     *
     * @param start  The start color
     * @param end    The end color
     * @param length The length of the sentence
     * @param i      The position in the sentence
     * @return The color on a specific point of the sentence
     */
    @NotNull
    public static RGB getRGBForGradientAtPosition(@NotNull Color start, @NotNull Color end, int length, int i) {
        return new RGB(
                start.getRed() + (end.getRed() - start.getRed()) / (length - 1) * i,
                start.getGreen() + (end.getGreen() - start.getGreen()) / (length - 1) * i,
                start.getBlue() + (end.getBlue() - start.getBlue()) / (length - 1) * i
        );
    }
}
