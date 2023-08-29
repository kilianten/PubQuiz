package gameObjects.graphics;

import gameObjects.GameObject;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] images;
    private long lastUpdate;
    private int currentIndex;
    private boolean isCyclingAnimation;
    private int updateRate;

    public Animation(BufferedImage[] images){
        this(images, 100, false);
    }

    public Animation(BufferedImage[] images, int updateRate){
        this(images, updateRate, false);
    }

    public Animation(BufferedImage[] images, boolean isCyclingAnimation){
        this(images, 100, isCyclingAnimation);
    }

    public Animation(BufferedImage[] images, int updateRate, boolean isCyclingAnimation) {
        this.images = images;
        this.isCyclingAnimation = isCyclingAnimation;
        this.updateRate = updateRate;
        lastUpdate = System.currentTimeMillis();
    }

    public void update(GameObject gameObject) {
        if(System.currentTimeMillis() - lastUpdate  > updateRate){
            currentIndex++;
            lastUpdate = System.currentTimeMillis();
            if(currentIndex >= images.length){
                if(isCyclingAnimation) {
                    currentIndex = 0;
                    gameObject.setSprite(images[currentIndex]);
                } else {
                    gameObject.setAnimation(null);
                    gameObject.setDefaultSprite();
                }
            } else {
                gameObject.setSprite(images[currentIndex]);
            }
        }
    }

}
