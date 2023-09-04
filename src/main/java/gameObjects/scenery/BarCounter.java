package gameObjects.scenery;

import game.Game;
import gameObjects.GameObject;

public class BarCounter extends GameObject {

    public BarCounter(int x, int y) {
        super(x * Game.TILE_SIZE, y * Game.TILE_SIZE, "/scenery/pub/bar.png");
    }
}
