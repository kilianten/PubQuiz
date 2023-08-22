package gameObjects.interactiveObjects;

import gameObjects.GameObject;
import graphics.ImageLoader;

public class InteractiveObject extends GameObject {

    String name;

    public InteractiveObject(String name, String imageFile){
        this.name = name;
        this.sprite = ImageLoader.loadImage(imageFile);
        System.out.println(sprite);
    }

}
