package de.asedem.minelibs.map.render;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class StaticImageRenderer extends MapRenderer {

    private BufferedImage bufferedImage;
    private boolean done;

    public StaticImageRenderer(BufferedImage bufferedImage) {
        this.load(bufferedImage);
    }

    public StaticImageRenderer(String url) {
        this.load(url);
    }

    public void load(BufferedImage bufferedImage) {
        this.bufferedImage = MapPalette.resizeImage(bufferedImage);
    }

    public void load(String url) {
        BufferedImage image;
        try {
            image = ImageIO.read(new URL(url));
        } catch (IOException ioException) {
            return;
        }
        this.load(image);
    }

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        if (this.done) return;
        canvas.drawImage(0, 0, this.bufferedImage);
        map.setTrackingPosition(false);
        this.done = true;
    }
}
