package gameObjects.interactiveObjects;

import game.state.State;
import gameObjects.GameObject;
import gameObjects.entity.Entity;

public abstract class InteractiveObject extends GameObject {


    public InteractiveObject(String imageFile, int x, int y){
        super(x, y, imageFile);
    }

    public abstract String getInteractingMessage();

    public abstract void interactWith(State state, Entity entity);
}
