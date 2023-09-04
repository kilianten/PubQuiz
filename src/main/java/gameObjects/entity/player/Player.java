package gameObjects.entity.player;

import gameObjects.entity.Entity;
import gameObjects.graphics.Animation;
import gameObjects.interactiveObjects.InteractiveObject;
import graphics.ImageLoader;

import game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Optional;
import game.state.State;
import input.KeyHandler;

public class Player extends Entity implements PlayerImages {

    private BufferedImage[] walkingDownImages;
    private BufferedImage[] walkingRightImages;
    private BufferedImage[] walkingLeftImages;
    private BufferedImage[] walkingUpImages;

    private BufferedImage standingLeftSprite;
    private BufferedImage standingUpSprite;

    private InteractiveObject interactiveObject;

    public Player(){
        super(100, 1000);
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
        direction = "down";
        sprite = defaultSprite;
        interactiveObject = null;
    }

    public void update(State state) {
        super.update(state);
        checkNearbyInteractiveObjects(state);
        handleInput(state);
    }

    private void handleInput(State state) {
        if(state.getKey().isPressed(KeyEvent.VK_E) && interactiveObject != null){
            interactiveObject.interactWith(state, this);
        }
    }

    private void checkNearbyInteractiveObjects(State state) {
        Optional<InteractiveObject> nearObject = findNearestInteractiveObject(state);
        if(nearObject.isPresent()){
            interactiveObject = nearObject.get();
        } else {
            interactiveObject = null;
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

    public void handleMotion(State state){
        int newX = 0, newY = 0;

        if(state.getKey().isCurrentlyPressed(KeyEvent.VK_S)){
            if(state.getKey().isPressed(KeyEvent.VK_S)){
                animation = new Animation(walkingDownImages, true);
            }
            direction = "down";
            newY += speed;
        } else if(state.getKey().isCurrentlyPressed(KeyEvent.VK_D)){
            if(state.getKey().isPressed(KeyEvent.VK_D)){
                animation = new Animation(walkingRightImages, true);
            }
            direction = "right";
            newX += speed;
        } else if(state.getKey().isCurrentlyPressed(KeyEvent.VK_A)){
            if(state.getKey().isPressed(KeyEvent.VK_A)){
                animation = new Animation(walkingLeftImages, true);
            }
            direction = "left";
            newX -= speed;
        } else if(state.getKey().isCurrentlyPressed(KeyEvent.VK_W)){
            if(state.getKey().isPressed(KeyEvent.VK_W)){
                animation = new Animation(walkingUpImages, true);
            }
            direction = "up";
            newY -= speed;
        }

        Rectangle newCollisionBox = new Rectangle(collisionBox.x + newX, collisionBox.y + newY, collisionBox.width, collisionBox.height);
        if(state.getCollisionManager().checkTileCollisions(newCollisionBox) == null){
            x += newX;
            y += newY;
        }
    }

    public boolean isWalking(State state){
        return state.getKey().isCurrentlyPressed(KeyEvent.VK_W) |
                state.getKey().isCurrentlyPressed(KeyEvent.VK_A) |
                state.getKey().isCurrentlyPressed(KeyEvent.VK_S) |
                state.getKey().isCurrentlyPressed(KeyEvent.VK_D);
    }

    public Optional<InteractiveObject> findNearestInteractiveObject(State state) {
        return state.getInteractiveGameObjects().stream()
                .filter(gameObject -> distanceTo(gameObject) < Game.TILE_SIZE * 1.5)
                .filter(gameObject -> isFacing(gameObject))
                .min(Comparator.comparingDouble(gameObject -> distanceTo(gameObject)));
    }

    public InteractiveObject getInteractiveObject() {
        return interactiveObject;
    }

}
