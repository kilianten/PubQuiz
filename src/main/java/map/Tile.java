package map;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage sprite;
    private boolean hasCollision;

    public Tile(BufferedImage sprite) {
        this(sprite, false);
    }

    public Tile(BufferedImage sprite, boolean hasCollision) {
        this.sprite = sprite;
        this.hasCollision = hasCollision;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public BufferedImage getSprite(){
        return sprite;
    }

    public Rectangle getCollisionBox(int xGrid, int yGrid) {
        return new Rectangle(
                xGrid * Game.TILE_SIZE,
                yGrid * Game.TILE_SIZE,
                sprite.getWidth(),
                sprite.getHeight());
    }
}
