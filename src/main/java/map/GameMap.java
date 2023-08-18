package map;

import game.Game;
import graphics.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;


public class GameMap {

    private Tile[][] map;
    private TileManager tileManager;

    Map<Integer, String> HEX_TO_TILE_MAP = Map.ofEntries(
            entry(0xffbc5f5f, "brick"),
            entry(0xff7c3F34, "wood"),
            entry(0xff1F911a, "grass")
    );

    public GameMap() {
        tileManager = new TileManager();
        loadMap("/maps/map01.png");
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

    public void loadMap(String mapPath){
        BufferedImage mapImage = ImageLoader.loadImage(mapPath);
        map = new Tile[mapImage.getWidth()][mapImage.getHeight()];
        for(int i = 0; i < mapImage.getWidth(); i++){
            for(int j = 0; j < mapImage.getHeight(); j++){
                String tileType = HEX_TO_TILE_MAP.get(mapImage.getRGB(i, j));
                if(tileType.equals("grass")){
                    tileType = randomiseGrass();
                }
                map[i][j] = tileManager.getTile(tileType);
            }
        }
    }

    public Tile[][] getMap() {
        return map;
    }

    public BufferedImage getTileSprite(int x, int y){
        return map[x][y].getSprite();
    }
}
