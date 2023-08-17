package tile;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TileManager {

    Game game;
    Map<String, Tile> tiles;

    public TileManager(Game game) {
        this.game = game;
        tiles = new HashMap<>();
        getTileImage();
    }

    public void getTileImage() {
        createTile("grass", "/tiles/grass.png");
        createTile("brick", "/tiles/brick.png");
        createTile("dirt", "/tiles/dirt.png");
        createTile("wood", "/tiles/wood.png");
    }

    public void createTile(String tileName, String imagePath){
        tiles.put(tileName, new Tile(game.loadImage(imagePath)));
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(tiles.get("grass").getSprite(), 0, 0, game.getTileSize(), game.getTileSize(), null);
        g2.drawImage(tiles.get("brick").getSprite(), 0, game.getTilePosition(1), game.getTileSize(), game.getTileSize(), null);
        g2.drawImage(tiles.get("dirt").getSprite(), 0,  game.getTilePosition(2), game.getTileSize(), game.getTileSize(), null);
        g2.drawImage(tiles.get("wood").getSprite(), 0,  game.getTilePosition(3), game.getTileSize(), game.getTileSize(), null);
    }

}
