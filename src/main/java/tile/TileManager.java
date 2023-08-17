package tile;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;


public class TileManager {

    private Game game;
    private Map<String, Tile> tiles;
    private String[][] map;

    Map<Integer, String> HEX_TO_TILE_MAP = Map.ofEntries(
            entry(0xffbc5f5f, "brick"),
            entry(0xff7c3F34, "wood"),
            entry(0xff1F911a, "grass")
    );

    public TileManager(Game game) {
        this.game = game;
        tiles = new HashMap<>();
        getTileImages();
        loadMap("/maps/map01.png");
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

    public void loadMap(String mapPath){
        BufferedImage mapImage = game.loadImage(mapPath);
        map = new String[mapImage.getWidth()][mapImage.getHeight()];
        for(int i = 0; i < mapImage.getWidth(); i++){
            for(int j = 0; j < mapImage.getHeight(); j++){
                String tileType = HEX_TO_TILE_MAP.get(mapImage.getRGB(i, j));
                if(tileType.equals("grass")){
                    tileType = randomiseGrass();
                }
                map[i][j] = tileType;
            }
        }


    }

    public void createTile(String tileName, String imagePath){
        tiles.put(tileName, new Tile(game.loadImage(imagePath)));
    }

    public String randomiseGrass(){
        Random random = new Random();

        switch (random.nextInt(150)){
            case 0:
                return "grassFlower01";
            case 1:
                return "grassFlower02";
            case 2:
                return "grassFlower03";
            case 3:
                return "grassRock01";
            case 4:
                return "grassRock02";
            case 5:
                return "grassDirt";
            default:
                return "grass";
        }

    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                String tileType = map[i][j];
                g2.drawImage(tiles.get(tileType).getSprite(), game.getTilePosition(i), game.getTilePosition(j), game.getTileSize(), game.getTileSize(), null);
            }
        }
    }

}
