package de.asedem.minelibs.core;

import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.HumanEntity;
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

    public void showToHumanEntity(HumanEntity humanEntity) {
        humanEntity.spigot().sendMessage(currentBuilding);
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
    public TextComponentBuilder setHoverEventText(@NotNull BaseComponent[] value) {
        currentBuilding.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(value)));
        return this;
    }

    @NotNull
    public TextComponentBuilder setHoverEventText(@NotNull String value) {
        currentBuilding.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(value)));
        return this;
    }

    @NotNull
    public TextComponentBuilder setHoverEventItem(@NotNull String id, int count, @NotNull ItemTag tag) {
        currentBuilding.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new Item(id, count, tag)));
        return this;
    }

    @NotNull
    public TextComponentBuilder setHoverEventEntity(@NotNull String type, @NotNull String id, @NotNull BaseComponent name) {
        currentBuilding.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new Entity(type, id, name)));
        return this;
    }

    @NotNull
    public TextComponentBuilder setExtra(@NotNull List<BaseComponent> components) {
        currentBuilding.setExtra(components);
        return this;
    }
}
