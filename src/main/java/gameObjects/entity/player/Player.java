package gameObjects.entity.player;

import game.state.GameState;
import gameObjects.entity.Entity;
import gameObjects.entity.npc.NPC;
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

public class Player extends Entity implements PlayerImages {

    private BufferedImage[] walkingDownImages;
    private BufferedImage[] walkingRightImages;
    private BufferedImage[] walkingLeftImages;
    private BufferedImage[] walkingUpImages;

    private BufferedImage standingLeftSprite;
    private BufferedImage standingUpSprite;

    private InteractiveObject interactiveObject;
    private NPC nearbyNPC;

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
        if(isWalking(state)) {
            handleMotion(state);
            adjustCollisionBox();
        } else {
            sprite = getDefaultSprite();
            animation = null;
        }
        checkNearbyNPC(state);
        checkNearbyInteractiveObjects(state);

        handleInput(state);
    }

    private boolean checkNearbyNPC(State state) {
        Optional<NPC> nearNPC = findNearestTalkingNPC(state);
        if(nearNPC.isPresent()){
            nearbyNPC = nearNPC.get();
            return true;
        } else {
            nearbyNPC = null;
            return false;
        }
    }

    private void handleInput(State state) {
        if(state.getKey().isPressed(KeyEvent.VK_E)){
            if(nearbyNPC != null){
                ((GameState) state).enterDialogue(nearbyNPC.talkTo());

            } else if (interactiveObject != null){
                interactiveObject.interactWith(state, this);
            }
        }
    }

    private boolean checkNearbyInteractiveObjects(State state) {
        Optional<InteractiveObject> nearObject = findNearestInteractiveObject(state);
        if(nearObject.isPresent()){
            interactiveObject = nearObject.get();
            return true;
        } else {
            interactiveObject = null;
            return false;
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
        if(state.getCollisionManager().checkTileCollisions(newCollisionBox) == null
        && !state.getCollisionManager().isCollidingWithObject(newCollisionBox)) {
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
        return state.getGameObjectsOfClass(InteractiveObject.class).stream()
                .filter(gameObject -> distanceTo(gameObject) < Game.TILE_SIZE * 1.5)
                .filter(gameObject -> isFacing(gameObject))
                .min(Comparator.comparingDouble(gameObject -> distanceTo(gameObject)));
    }

    public Optional<NPC> findNearestTalkingNPC(State state) {
        return state.getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> distanceTo(npc) < Game.TILE_SIZE * 2)
                .filter(npc -> isFacing(npc))
                .filter(NPC::wantsToTalk)
                .min(Comparator.comparingDouble(gameObject -> distanceTo(gameObject)));
    }

    public InteractiveObject getInteractiveObject() {
        return interactiveObject;
    }

    public NPC getNearbyNPC() {
        return nearbyNPC;
    }

}
