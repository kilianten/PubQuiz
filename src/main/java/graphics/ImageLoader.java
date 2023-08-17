package graphics;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
        BufferedImage image = null;

        try {
            if(Game.class.getResourceAsStream(imagePath) == null){
                System.out.println("Couldn't find resource at " + imagePath);
            } else {
                image = ImageIO.read(Game.class.getResourceAsStream(imagePath));
            }
        } catch (IOException e) {
            System.out.println("Couldn't load image at path " + imagePath);
        }

        return image;
    }
}
