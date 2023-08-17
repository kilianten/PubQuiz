package gameObjects.entity.player;


import gameObjects.entity.Entity;
import gameObjects.graphics.Animation;
import input.KeyHandler;
import game.Game;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Entity implements PlayerImages {

    private Game game;
    private KeyHandler key;

    private BufferedImage[] walkingDownImages;
    private BufferedImage[] walkingRightImages;
    private BufferedImage[] walkingLeftImages;
    private BufferedImage[] walkingUpImages;

    private BufferedImage standingLeftSprite;
    private BufferedImage standingUpSprite;

    public Player(Game game, KeyHandler key){
        this.game = game;
        this.key = key;
        setDefaultValues();
        loadImages(game);
    }

    public void loadImages(Game game){
        walkingDownImages = game.loadImages(WALKING_DOWN_IMAGES);
        walkingRightImages = game.loadImages(WALKING_RIGHT_IMAGES);
        walkingLeftImages = game.loadImages(WALKING_LEFT_IMAGES);
        walkingUpImages = game.loadImages(WALKING_UP_IMAGES);
        defaultSprite = game.loadImage("/player/standing.png");
        standingLeftSprite = game.loadImage("/player/standingLeft.png");
        standingUpSprite = game.loadImage("/player/standingUp.png");
    }

    public void setDefaultValues(){
        speed = 4;
        x = 10;
        y = 10;
        direction = "down";
        sprite = defaultSprite;
    }

    public void update() {
        super.update();
        if(isWalking()) {
            handleMotion();
        } else {
            sprite = getDefaultSprite();
            animation = null;
        }
    }

    public BufferedImage getDefaultSprite(){
        switch (direction) {
            case "left":
                return standingLeftSprite;
            case "up":
                return standingUpSprite;
            default:
                return defaultSprite;
        }
    }

    public void handleMotion(){
        if(key.isCurrentlyPressed(KeyEvent.VK_S)){
            if(key.isPressed(KeyEvent.VK_S)){
                animation = new Animation(walkingDownImages, true);
            }
            direction = "down";
            y += speed;
        } else if(key.isCurrentlyPressed(KeyEvent.VK_D)){
            if(key.isPressed(KeyEvent.VK_D)){
                animation = new Animation(walkingRightImages, true);
            }
            direction = "right";
            x += speed;
        } else if(key.isCurrentlyPressed(KeyEvent.VK_A)){
            if(key.isPressed(KeyEvent.VK_A)){
                animation = new Animation(walkingLeftImages, true);
            }
            direction = "left";
            x -= speed;
        } else if(key.isCurrentlyPressed(KeyEvent.VK_W)){
            if(key.isPressed(KeyEvent.VK_W)){
                animation = new Animation(walkingUpImages, true);
            }
            direction = "up";
            y -= speed;
        }


    }

    public boolean isWalking(){
        return key.isCurrentlyPressed(KeyEvent.VK_W) |
                key.isCurrentlyPressed(KeyEvent.VK_A) |
                key.isCurrentlyPressed(KeyEvent.VK_S) |
                key.isCurrentlyPressed(KeyEvent.VK_D);
    }

}
