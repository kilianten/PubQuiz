package tile;

import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage sprite;
    private boolean walkable;

    public Tile(BufferedImage sprite) {
        this(sprite, false);
    }

    public Tile(BufferedImage sprite, boolean walkable) {
        this.sprite = sprite;
        this.walkable = walkable;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public BufferedImage getSprite(){
        return sprite;
    }

}
