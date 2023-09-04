package gameObjects.interactiveObjects;

import game.state.State;
import gameObjects.entity.Entity;
import graphics.ImageLoader;

import java.awt.image.BufferedImage;

public class BeerPint extends InteractiveObject {

    private int sipsLeft = 4;
    private BufferedImage[] beerImages;

    public BeerPint(String name) {
        super(name,"/objects/beerPint/beer.png", 10, 100);
        String[] imagePaths = {"/objects/beerPint/beer04.png",
                "/objects/beerPint/beer03.png",
                "/objects/beerPint/beer02.png",
                "/objects/beerPint/beer01.png",
        "/objects/beerPint/beer.png"};
        beerImages = ImageLoader.loadImages(imagePaths);
    }

    public boolean isEmpty(){
        return sipsLeft <= 0;
    }

    @Override
    public String getInteractingMessage() {
        if(!isEmpty()){
            return "Drink beer?";
        }
        return null;
    }

    @Override
    public void interactWith(State state, Entity entity) {
        if(!isEmpty()){
            sipsLeft--;
            setSprite(beerImages[sipsLeft]);
        }
    }
}
