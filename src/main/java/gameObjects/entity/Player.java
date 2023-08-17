package gameObjects.entity;


import gameObjects.graphics.Animation;
import input.KeyHandler;
import game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private Game game;
    private KeyHandler key;

    private BufferedImage[] walkingDownImages;

    private final String[] WALKING_DOWN_IMAGES = {"/player/standing.png", "/player/walkingDown01.png", "/player/standing.png", "/player/walkingDown02.png"};

    public Player(Game game, KeyHandler key){
        this.game = game;
        this.key = key;
        setDefaultValues();
        walkingDownImages = loadImages(WALKING_DOWN_IMAGES);
    }

    public void setDefaultValues(){
        speed = 2;
        x = 10;
        y = 10;
        direction = "down";
        defaultSprite = loadImage("/player/standing.png");
        sprite = defaultSprite;
    }

    public void update() {
        super.update();
        if(isWalking()) {
            handleMotion();
        } else {
            sprite = defaultSprite;
            animation = null;
        }
    }

    public void handleMotion(){
        if(key.isCurrentlyPressed(KeyEvent.VK_S)){
            direction = "down";
            y += speed;
        }

        if(key.isPressed(KeyEvent.VK_S)){
            animation = new Animation(walkingDownImages, true);
        }
    }

    public boolean isWalking(){
        return key.isCurrentlyPressed(KeyEvent.VK_W) |
                key.isCurrentlyPressed(KeyEvent.VK_A) |
                key.isCurrentlyPressed(KeyEvent.VK_S) |
                key.isCurrentlyPressed(KeyEvent.VK_D);
    }

}
