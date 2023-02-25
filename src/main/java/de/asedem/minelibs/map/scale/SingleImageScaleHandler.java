package de.asedem.minelibs.map.scale;

import de.asedem.minelibs.map.render.StaticImageRenderer;
import org.bukkit.map.MapRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SingleImageScaleHandler {

    private SingleImageScaleHandler() {
    }

    public static List<MapRenderer> getRenderers(String url, int hw) {
        try {
            return getRenderers(ImageIO.read(new URL(url)), hw);
        } catch (IOException ioException) {
            return Collections.emptyList();
        }
    }

    public static List<MapRenderer> getRenderers(BufferedImage image, int hw) {
        List<MapRenderer> list = new LinkedList<>();
        for (int j = 0; j < hw; j++) for (int i = 0; i < hw; i++) {
            if (i == 0 && j == 0) list.add(new StaticImageRenderer(crop(image, 0, 0, image.getWidth() / hw, image.getHeight() / hw)));
            else if (i == 0) list.add(new StaticImageRenderer(crop(image, 0, j * (image.getHeight() / hw), image.getWidth() / hw, image.getHeight() / hw)));
            else if (j == 0) list.add(new StaticImageRenderer(crop(image, i * (image.getWidth() / hw), 0, image.getWidth() / hw, image.getHeight() / hw)));
            else list.add(new StaticImageRenderer(crop(image, i * (image.getWidth() / hw), j * (image.getHeight() / hw), image.getWidth() / hw, image.getHeight() / hw)));
        }
        return list;
    }

    private static BufferedImage crop(BufferedImage src, int x, int y, int width, int height) {
        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics g = dest.getGraphics();
        g.drawImage(src, 0, 0, width, height, x, y, x + width, y + height, null);
        g.dispose();
        return dest;
    }
}
