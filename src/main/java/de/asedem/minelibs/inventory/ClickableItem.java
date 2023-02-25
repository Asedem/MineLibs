package de.asedem.minelibs.inventory;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public record ClickableItem(
        int index,
        boolean shift,
        @NotNull Consumer<Player> playerConsumer
) {
}
