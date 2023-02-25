package de.asedem.minelibs.map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class CustomScaledMap {

    private boolean permanentlySaved = false;
    private List<MapRenderer> renderer;

    public CustomScaledMap(List<MapRenderer> renderer) {
        this.renderer = renderer;
    }

    @NotNull
    public List<MapRenderer> getRenderer() {
        return renderer;
    }

    public void setRenderer(@NotNull List<MapRenderer> renderer) {
        this.renderer = renderer;
    }

    @NotNull
    public List<ItemStack> getMapItems(@NotNull World world) {
        List<ItemStack> items = new LinkedList<>();
        this.renderer.forEach(mapRenderer -> {
            final MapView mapView = Bukkit.createMap(world);
            mapView.getRenderers().forEach(mapView::removeRenderer);
            mapView.addRenderer(mapRenderer);
            final ItemStack map = new ItemStack(Material.FILLED_MAP);
            final MapMeta mapMeta = (MapMeta) map.getItemMeta();
            assert mapMeta != null;
            mapMeta.setMapView(mapView);
            map.setItemMeta(mapMeta);
            items.add(map);
        });
        return items;
    }

    public boolean isPermanentlySaved() {
        return permanentlySaved;
    }

    public void setPermanentlySaved(boolean permanentlySaved) {
        this.permanentlySaved = permanentlySaved;
    }
}
