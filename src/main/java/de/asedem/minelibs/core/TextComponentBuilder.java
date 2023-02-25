package de.asedem.minelibs.core;

import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TextComponentBuilder {

    protected TextComponent currentBuilding;

    public TextComponentBuilder() {
        currentBuilding = new TextComponent();
    }

    public TextComponentBuilder(@NotNull String text) {
        currentBuilding = new TextComponent(text);
    }

    public TextComponentBuilder(@NotNull TextComponent base) {
        currentBuilding = base;
    }

    @NotNull
    public TextComponent toTextComponent() {
        return currentBuilding;
    }

    public void showToPlayer(Player player) {
        player.spigot().sendMessage(currentBuilding);
    }

    @NotNull
    public TextComponentBuilder setText(@NotNull String text) {
        currentBuilding.setText(text);
        return this;
    }

    @NotNull
    public TextComponentBuilder setClickEvent(@NotNull ClickEvent.Action action, @NotNull String value) {
        currentBuilding.setClickEvent(new ClickEvent(action, value));
        return this;
    }

    @NotNull
    public TextComponentBuilder setHoverEvent(@NotNull HoverEvent.Action action, @NotNull BaseComponent[] value) {
        currentBuilding.setHoverEvent(new HoverEvent(action, value));
        return this;
    }

    @NotNull
    public TextComponentBuilder setHoverEvent(@NotNull HoverEvent.Action action, @NotNull String value) {
        currentBuilding.setHoverEvent(new HoverEvent(action, new ComponentBuilder(value).create()));
        return this;
    }

    @NotNull
    public TextComponentBuilder setHoverEvent(@NotNull HoverEvent.Action action, @NotNull ComponentBuilder value) {
        currentBuilding.setHoverEvent(new HoverEvent(action, new ComponentBuilder(value).create()));
        return this;
    }

    @NotNull
    public TextComponentBuilder setHoverEvent(@NotNull HoverEvent.Action action, @NotNull BaseComponent value) {
        currentBuilding.setHoverEvent(new HoverEvent(action, new ComponentBuilder(value).create()));
        return this;
    }

    @NotNull
    public TextComponentBuilder setExtra(@NotNull List<BaseComponent> components) {
        currentBuilding.setExtra(components);
        return this;
    }
}
