package de.asedem.minelibs.map.render;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.util.List;

public class AnimatedImageRenderer extends MapRenderer {

    private List<BufferedImage> bufferedImages;
    private int count;

    public AnimatedImageRenderer(List<BufferedImage> bufferedImages) {
        this.load(bufferedImages);
    }

    public void load(List<BufferedImage> bufferedImages) {
        this.bufferedImages = bufferedImages.stream()
                .map(MapPalette::resizeImage)
                .toList();
    }

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        if (this.count >= this.bufferedImages.size()) this.count = 0;
        canvas.drawImage(0, 0, this.bufferedImages.get(this.count));
        map.setTrackingPosition(false);
        this.count++;
    }
}
