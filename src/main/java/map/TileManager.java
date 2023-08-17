package map;

import graphics.ImageLoader;
import java.util.HashMap;
import java.util.Map;

public class TileManager {

    private Map<String, Tile> tileMap;

    public TileManager() {
        tileMap = new HashMap<>();
        getTileImages();
    }

    public Tile getTile(String tileName){
        return tileMap.get(tileName);
    }

    public void getTileImages() {
        createTile("grass", "/tiles/grass.png");
        createTile("brick", "/tiles/brick.png");
        createTile("dirt", "/tiles/dirt.png");
        createTile("wood", "/tiles/wood.png");
        createTile("grassFlower01", "/tiles/grassFlower01.png");
        createTile("grassFlower02", "/tiles/grassFlower02.png");
        createTile("grassFlower03", "/tiles/grassFlower03.png");
        createTile("grassRock01", "/tiles/grassRock01.png");
        createTile("grassRock02", "/tiles/grassRock02.png");
        createTile("grassDirt", "/tiles/grassDirt.png");
    }

    public void createTile(String tileName, String imagePath){
        tileMap.put(tileName, new Tile(ImageLoader.loadImage(imagePath)));
    }
}
