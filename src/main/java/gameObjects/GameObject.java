package gameObjects;

import game.Game;
import gameObjects.graphics.Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameObject {

    protected int x, y;
    protected BufferedImage sprite;
    protected Animation animation;
    protected BufferedImage defaultSprite;

    public void update() {
        if(this.animation != null){
            animation.update(this);
        }
    }

    public void setSprite(BufferedImage sprite){
        this.sprite = sprite;
    }

    public void setAnimation(Animation animation){
        this.animation = animation;
    }

    public BufferedImage[] loadImages(String[] imagePaths){
        BufferedImage[] images = new BufferedImage[imagePaths.length];

        for(int i = 0; i < imagePaths.length; i++){
            String path = imagePaths[i];

            try {
                if(getClass().getResourceAsStream(path) == null){
                    System.out.println("Couldn't find resource at " + path);
                }
                images[i] = ImageIO.read(getClass().getResourceAsStream(path));
            } catch (IOException e) {
                System.out.println("Couldn't load image at path " + path);
            }
        }

        return images;
    }

    public BufferedImage loadImage(String imagePath){
        BufferedImage image = null;

        try {
            if(getClass().getResourceAsStream(imagePath) == null){
                System.out.println("Couldn't find resource at " + imagePath);
            }
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            System.out.println("Couldn't load image at path " + imagePath);
        }

        return image;
    }

    public void draw(Game game, Graphics2D g2) {
        g2.drawImage(sprite, x, y, game.getTileSize(), game.getTileSize(), null);
    }

}
