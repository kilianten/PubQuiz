package gameObjects.entity;

import game.Game;
import game.state.State;
import gameObjects.GameObject;

import java.awt.image.BufferedImage;

public abstract class Entity extends GameObject {

    protected int speed;
    protected int collisionBoxOffsetX = 4;
    protected int collisionBoxOffsetY = 8;

    public Entity(){
    }

    public void update(State state){
        super.update(state);
        if(isWalking(state)) {
            handleMotion(state);
            adjustCollisionBox();
        } else {
            sprite = getDefaultSprite();
            animation = null;
        }
    }

    public boolean isWalking(State state) {
        return false;
    }

    public void handleMotion(State state){
    }

    public BufferedImage getDefaultSprite(){
        return sprite;
    }

    public void adjustCollisionBox(){
        collisionBox.x = x + (collisionBoxOffsetX * Game.SCALE);
        collisionBox.y = y + (collisionBoxOffsetY * Game.SCALE);
    }

}
