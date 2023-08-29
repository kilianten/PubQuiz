package graphics;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {

    public static BufferedImage[] loadImages(String[] imagePaths){
        BufferedImage[] images = new BufferedImage[imagePaths.length];

        for(int i = 0; i < imagePaths.length; i++){
            String path = imagePaths[i];
            images[i] = loadImage(path);
        }
        return images;
    }

    public static BufferedImage loadImage(String imagePath){
        BufferedImage image;
        BufferedImage scaledImage = null;

        try {
            InputStream inputStream = Game.class.getResourceAsStream(imagePath);
            if(inputStream == null){
                System.out.println("Couldn't find resource at " + imagePath);
            } else {
                image = ImageIO.read(inputStream);
                scaledImage = new BufferedImage(Game.TILE_SIZE, Game.TILE_SIZE, image.getType());
                Graphics2D g2 = scaledImage.createGraphics();
                g2.drawImage(image, 0, 0, Game.TILE_SIZE, Game.TILE_SIZE, null);
            }
        } catch (IOException e) {
            System.out.println("Couldn't load image at path " + imagePath);
        }

        return scaledImage;
    }
}
