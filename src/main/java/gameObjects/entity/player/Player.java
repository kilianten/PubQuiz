package gameObjects.entity.player;


import gameObjects.entity.Entity;
import gameObjects.graphics.Animation;
import gameObjects.interactiveObjects.InteractiveObject;
import graphics.ImageLoader;
import input.KeyHandler;
import game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.stream.Collectors;

public class Player extends Entity implements PlayerImages {


    private KeyHandler key;

    private BufferedImage[] walkingDownImages;
    private BufferedImage[] walkingRightImages;
    private BufferedImage[] walkingLeftImages;
    private BufferedImage[] walkingUpImages;

    private BufferedImage standingLeftSprite;
    private BufferedImage standingUpSprite;

    public Player(Game game, KeyHandler key){
        super(game);
        this.key = key;
        setDefaultValues();
        this.collisionBox = new Rectangle(0, 0, 32, 32);
        adjustCollisionBox();
        loadImages();
    }

    public void loadImages(){
        walkingDownImages = ImageLoader.loadImages(WALKING_DOWN_IMAGES);
        walkingRightImages = ImageLoader.loadImages(WALKING_RIGHT_IMAGES);
        walkingLeftImages = ImageLoader.loadImages(WALKING_LEFT_IMAGES);
        walkingUpImages = ImageLoader.loadImages(WALKING_UP_IMAGES);
        defaultSprite = ImageLoader.loadImage("/player/standing.png");
        standingLeftSprite = ImageLoader.loadImage("/player/standingLeft.png");
        standingUpSprite = ImageLoader.loadImage("/player/standingUp.png");
    }

    public void setDefaultValues(){
        speed = 4;
        x = 100;
        y = 1000;
        direction = "down";
        sprite = defaultSprite;
    }

    public void update() {
        super.update();
        findNearestInteractiveObject(game);
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
        int newX = 0, newY = 0;

        if(key.isCurrentlyPressed(KeyEvent.VK_S)){
            if(key.isPressed(KeyEvent.VK_S)){
                animation = new Animation(walkingDownImages, true);
            }
            direction = "down";
            newY += speed;
        } else if(key.isCurrentlyPressed(KeyEvent.VK_D)){
            if(key.isPressed(KeyEvent.VK_D)){
                animation = new Animation(walkingRightImages, true);
            }
            direction = "right";
            newX += speed;
        } else if(key.isCurrentlyPressed(KeyEvent.VK_A)){
            if(key.isPressed(KeyEvent.VK_A)){
                animation = new Animation(walkingLeftImages, true);
            }
            direction = "left";
            newX -= speed;
        } else if(key.isCurrentlyPressed(KeyEvent.VK_W)){
            if(key.isPressed(KeyEvent.VK_W)){
                animation = new Animation(walkingUpImages, true);
            }
            direction = "up";
            newY -= speed;
        }

        Rectangle newCollisionBox = new Rectangle(collisionBox.x + newX, collisionBox.y + newY, collisionBox.width, collisionBox.height);
        if(game.getCollisionManager().checkTileCollisions(newCollisionBox) == null){
            x += newX;
            y += newY;
        }
    }

    public boolean isWalking(){
        return key.isCurrentlyPressed(KeyEvent.VK_W) |
                key.isCurrentlyPressed(KeyEvent.VK_A) |
                key.isCurrentlyPressed(KeyEvent.VK_S) |
                key.isCurrentlyPressed(KeyEvent.VK_D);
    }

    public void findNearestInteractiveObject(Game game) {
        System.out.println(game.getGameObjects().size());
        game.getGameObjects().stream()
                .filter(InteractiveObject.class::isInstance)
                .collect(Collectors.toList());
    }

}
