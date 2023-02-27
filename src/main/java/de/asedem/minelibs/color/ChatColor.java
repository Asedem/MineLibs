package de.asedem.minelibs.color;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ChatColor {

    public static final org.bukkit.ChatColor BLACK = org.bukkit.ChatColor.BLACK;
    public static final org.bukkit.ChatColor DARK_BLUE = org.bukkit.ChatColor.DARK_BLUE;
    public static final org.bukkit.ChatColor DARK_GREEN = org.bukkit.ChatColor.DARK_GREEN;
    public static final org.bukkit.ChatColor DARK_AQUA = org.bukkit.ChatColor.DARK_AQUA;
    public static final org.bukkit.ChatColor DARK_RED = org.bukkit.ChatColor.DARK_RED;
    public static final org.bukkit.ChatColor DARK_PURPLE = org.bukkit.ChatColor.DARK_PURPLE;
    public static final org.bukkit.ChatColor GOLD = org.bukkit.ChatColor.GOLD;
    public static final org.bukkit.ChatColor GRAY = org.bukkit.ChatColor.GRAY;
    public static final org.bukkit.ChatColor DARK_GRAY = org.bukkit.ChatColor.DARK_GRAY;
    public static final org.bukkit.ChatColor BLUE = org.bukkit.ChatColor.BLUE;
    public static final org.bukkit.ChatColor GREEN = org.bukkit.ChatColor.GREEN;
    public static final org.bukkit.ChatColor AQUA = org.bukkit.ChatColor.AQUA;
    public static final org.bukkit.ChatColor RED = org.bukkit.ChatColor.RED;
    public static final org.bukkit.ChatColor LIGHT_PURPLE = org.bukkit.ChatColor.LIGHT_PURPLE;
    public static final org.bukkit.ChatColor YELLOW = org.bukkit.ChatColor.YELLOW;
    public static final org.bukkit.ChatColor WHITE = org.bukkit.ChatColor.WHITE;

    private ChatColor() {
    }

    /**
     * Translates the minecraft color codes to a specific character
     *
     * @param character The character in front of the color codes
     * @param text      The text to translate
     * @return The translated text
     */
    @NotNull
    public static String translate(char character, @NotNull String text) {
        return org.bukkit.ChatColor.translateAlternateColorCodes(character, text);
    }

    /**
     * Gives you a text in a gradient color
     *
     * @param startColor The start color
     * @param endColor   The end color
     * @param text       The text to colorize
     * @return The colorized text
     */
    @NotNull
    public static String asGradient(@NotNull org.bukkit.ChatColor startColor,
                                    @NotNull org.bukkit.ChatColor endColor,
                                    @NotNull String text) {
        return asGradient(startColor, endColor, text, GradientStyle.style(false, false, false, false, false));
    }

    /**
     * Gives you a text in a gradient color with a specific style
     *
     * @param startColor    The start color
     * @param endColor      The end color
     * @param text          The text to colorize
     * @param gradientStyle The style for the text
     * @return The colorized and styled text
     */
    @NotNull
    public static String asGradient(@NotNull org.bukkit.ChatColor startColor,
                                    @NotNull org.bukkit.ChatColor endColor,
                                    @NotNull String text,
                                    @NotNull GradientStyle gradientStyle) {
        Color start = startColor.asBungee().getColor();
        Color end = endColor.asBungee().getColor();
        return asGradient(start, end, text, gradientStyle);
    }

    /**
     * Gives you a text in a gradient color
     *
     * @param startColor The start color
     * @param endColor   The end color
     * @param text       The text to colorize
     * @return The colorized text
     */
    @NotNull
    public static String asGradient(@NotNull Color startColor,
                                    @NotNull Color endColor,
                                    @NotNull String text) {
        return asGradient(startColor, endColor, text, GradientStyle.style(false, false, false, false, false));
    }

    /**
     * Gives you a text in a gradient color with a specific style
     *
     * @param startColor    The start color
     * @param endColor      The end color
     * @param text          The text to colorize
     * @param gradientStyle The style for the text
     * @return The colorized and styled text
     */
    @NotNull
    public static String asGradient(@NotNull Color startColor,
                                    @NotNull Color endColor,
                                    @NotNull String text,
                                    @NotNull GradientStyle gradientStyle) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++)
            stringBuilder.append(getColorsForGradient(startColor, endColor, length, i))
                    .append(gradientStyle.bold() ? org.bukkit.ChatColor.translateAlternateColorCodes('&', "&l") : "")
                    .append(gradientStyle.italic() ? org.bukkit.ChatColor.translateAlternateColorCodes('&', "&o") : "")
                    .append(gradientStyle.strikethrough() ? org.bukkit.ChatColor.translateAlternateColorCodes('&', "&m") : "")
                    .append(gradientStyle.underline() ? org.bukkit.ChatColor.translateAlternateColorCodes('&', "&n") : "")
                    .append(gradientStyle.obfuscated() ? org.bukkit.ChatColor.translateAlternateColorCodes('&', "&k") : "")
                    .append(text.charAt(i));
        return stringBuilder.toString();
    }

    /**
     * Creates a color that is a bit darker than the given one
     *
     * @param chatColor the color to make darker
     * @return The color that is darkened
     */
    @NotNull
    public static String createNonceColor(@NotNull org.bukkit.ChatColor chatColor) {
        Color color = chatColor.asBungee().getColor();
        color = color.darker();
        return toChatColor('#' + String.format("%06X",
                0xFFFFFF & new Color(color.getRed(), color.getGreen(), color.getBlue()).getRGB()));
    }

    /**
     * Creates a color that is a bit darker than the given one
     *
     * @param color the color to make darker
     * @return The color that is darkened
     */
    @NotNull
    public static String createNonceColor(@NotNull Color color) {
        color = color.darker();
        return toChatColor('#' + String.format("%06X",
                0xFFFFFF & new Color(color.getRed(), color.getGreen(), color.getBlue()).getRGB()));
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
    private static String getColorsForGradient(@NotNull Color start, @NotNull Color end, int length, int i) {
        RGB rgb = ChatColor.getRGBForGradientAtPosition(start, end, length, i);
        return toChatColor('#' + String.format("%06X", 0xFFFFFF & new Color(rgb.red(), rgb.green(), rgb.blue()).getRGB()));
    }

    /**
     * Converts a hex code to a minecraft chat color
     *
     * @param hexCode The hex code to convert
     * @return The minecraft chat color
     */
    @NotNull
    public static String toChatColor(@NotNull String hexCode) {
        StringBuilder magic = new StringBuilder("§x");
        char[] var3 = hexCode.substring(1).toCharArray();
        for (char c : var3) magic.append('§').append(c);
        return magic.toString();
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
    private static RGB getRGBForGradientAtPosition(@NotNull Color start, @NotNull Color end, int length, int i) {
        return new RGB(
                start.getRed() + (end.getRed() - start.getRed()) / (length - 1) * i,
                start.getGreen() + (end.getGreen() - start.getGreen()) / (length - 1) * i,
                start.getBlue() + (end.getBlue() - start.getBlue()) / (length - 1) * i
        );
    }
}
