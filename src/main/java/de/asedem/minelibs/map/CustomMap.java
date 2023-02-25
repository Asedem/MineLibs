package de.asedem.minelibs.map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

public class CustomMap {

    private boolean permanentlySaved = false;
    private MapRenderer renderer;

    public CustomMap(@NotNull MapRenderer renderer) {
        this.renderer = renderer;
    }

    @NotNull
    public MapRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(@NotNull MapRenderer renderer) {
        this.renderer = renderer;
    }

    @NotNull
    public ItemStack getMapItem(@NotNull World world) {
        final MapView mapView = Bukkit.createMap(world);
        mapView.getRenderers().forEach(mapView::removeRenderer);
        mapView.addRenderer(this.renderer);
        final ItemStack map = new ItemStack(Material.FILLED_MAP);
        final MapMeta mapMeta = (MapMeta) map.getItemMeta();
        assert mapMeta != null;
        mapMeta.setMapView(mapView);
        map.setItemMeta(mapMeta);
        return map;
    }

    public boolean isPermanentlySaved() {
        return permanentlySaved;
    }

    public void setPermanentlySaved(boolean permanentlySaved) {
        this.permanentlySaved = permanentlySaved;
    }
}
