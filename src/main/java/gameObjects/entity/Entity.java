package gameObjects.entity;

import game.Game;
import gameObjects.GameObject;

import java.awt.image.BufferedImage;

public abstract class Entity extends GameObject {

    protected int speed;
    protected int collisionBoxOffsetX = 4;
    protected int collisionBoxOffsetY = 8;
    protected Game game;

    public Entity(Game game){
        this.game = game;
    }

    public void update(){
        super.update();
        if(isWalking()) {
            handleMotion();
            adjustCollisionBox();
        } else {
            sprite = getDefaultSprite();
            animation = null;
        }
    }

    public boolean isWalking() {
        return false;
    }

    public void handleMotion(){
    }

    public BufferedImage getDefaultSprite(){
        return sprite;
    }

    public void adjustCollisionBox(){
        collisionBox.x = x + (collisionBoxOffsetX * Game.SCALE);
        collisionBox.y = y + (collisionBoxOffsetY * Game.SCALE);
    }

}
