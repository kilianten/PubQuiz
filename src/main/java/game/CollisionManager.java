package game;

import map.Tile;

import java.awt.*;

public class CollisionManager {

    Game game;

    public CollisionManager(Game game){
        this.game = game;
    }

    public Tile checkTileCollisions(Rectangle rectangle){
        Tile[][] gameMap = game.getGameMap().getMap();
        for(int i = 0; i < gameMap.length; i++){
            for(int j = 0; j < gameMap[0].length; j++) {
                Tile tile = gameMap[i][j];
                if(tile.hasCollision() && rectangle.intersects(tile.getCollisionBox(i, j))){
                    return tile;
                }
            }
        }
        return null;
    }

}
