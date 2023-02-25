package de.asedem.minelibs.color;

import org.jetbrains.annotations.NotNull;

/**
 * The holder class for the style of a gradiant
 *
 * @param bold          bold style
 * @param italic        italic style
 * @param strikethrough strikethrough style
 * @param underline     underline style
 * @param obfuscated    obfuscated style
 */
public record GradientStyle(
        boolean bold,
        boolean italic,
        boolean strikethrough,
        boolean underline,
        boolean obfuscated
) {

    /**
     * Creates a new gradient style, if you don't want to use the constructor
     *
     * @param bold          bold style
     * @param italic        italic style
     * @param strikethrough strikethrough style
     * @param underline     underline style
     * @param obfuscated    obfuscated style
     * @return The gradient style
     */
    @NotNull
    public static GradientStyle style(
            boolean bold,
            boolean italic,
            boolean strikethrough,
            boolean underline,
            boolean obfuscated
    ) {
        return new GradientStyle(bold, italic, strikethrough, underline, obfuscated);
    }
}
