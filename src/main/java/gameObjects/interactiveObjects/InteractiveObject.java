package gameObjects.interactiveObjects;

import game.state.State;
import gameObjects.GameObject;
import gameObjects.entity.Entity;
import graphics.ImageLoader;

public abstract class InteractiveObject extends GameObject {

    String name;

    public InteractiveObject(String name, String imageFile){
        this.name = name;
        this.sprite = ImageLoader.loadImage(imageFile);
        this.defaultSprite = sprite;
    }

    public InteractiveObject(String name, String imageFile, int x, int y){
        super(x, y, imageFile);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getInteractingMessage();

    public abstract void interactWith(State state, Entity entity);
}
