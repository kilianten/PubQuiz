package gameObjects.scenery;

import game.Game;
import gameObjects.GameObject;

import java.awt.*;

public class BarCounter extends GameObject {

    public BarCounter(int x, int y) {
        super(x * Game.TILE_SIZE, y * Game.TILE_SIZE, "/scenery/pub/bar.png");
        int yCollisionOffset = 20;
        collisionBox = new Rectangle(this.x, this.y + yCollisionOffset, Game.TILE_SIZE, Game.TILE_SIZE - yCollisionOffset);
        isWalkable = false;
    }
}
