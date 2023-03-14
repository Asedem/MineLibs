package de.asedem.minelibs.enchant;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public enum RomanNumber {
    ONE(1, "I"),
    TWO(2, "II"),
    THREE(3, "III"),
    FOUR(4, "IV"),
    FIVE(5, "V"),
    SIX(6, "VI"),
    SEVEN(7, "VII"),
    EIGHT(8, "VIII"),
    NINE(9, "IX"),
    TEN(10, "X");

    private final int numeric;
    private final String written;

    RomanNumber(int numeric, @NotNull String written) {
        this.numeric = numeric;
        this.written = written;
    }

    public int getNumeric() {
        return numeric;
    }

    @NotNull
    public String getWritten() {
        return written;
    }

    @Nullable
    public static RomanNumber getByNumeric(int numeric) {
        return Arrays.stream(RomanNumber.values())
                .filter(romanNumber -> romanNumber.numeric == numeric)
                .findFirst()
                .orElse(null);
    }

    @Nullable
    public static RomanNumber getByWritten(@NotNull String written) {
        return Arrays.stream(RomanNumber.values())
                .filter(romanNumber -> romanNumber.written.equals(written))
                .findFirst()
                .orElse(null);
    }

    @NotNull
    public static String getWrittenByNumeric(int numeric) {
        return Arrays.stream(RomanNumber.values())
                .filter(romanNumber -> romanNumber.numeric == numeric)
                .map(RomanNumber::getWritten)
                .findFirst()
                .orElse("enchantment.level." + numeric);
    }
}
