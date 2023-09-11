package gameObjects.entity;

import game.Game;
import game.state.State;
import gameObjects.GameObject;
import gameObjects.entity.actions.ActionManager;

import java.awt.image.BufferedImage;

public abstract class Entity extends GameObject {

    protected int speed;
    protected int collisionBoxOffsetX = 4;
    protected int collisionBoxOffsetY = 8;
    protected ActionManager actionManager;

    public Entity(int x, int y){
        super(x, y);
        actionManager = new ActionManager();
    }

    public Entity(int x, int y, String imagePath) {
        super(x, y, imagePath);
        actionManager = new ActionManager();
    }

    public void update(State state){
        super.update(state);
        actionManager.update(state, this);
    }

    public BufferedImage getDefaultSprite(){
        return sprite;
    }

    public void adjustCollisionBox(){
        collisionBox.x = x + (collisionBoxOffsetX * Game.SCALE);
        collisionBox.y = y + (collisionBoxOffsetY * Game.SCALE);
    }

}
