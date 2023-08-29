package game;

import map.Tile;
import game.state.State;
import java.awt.*;

public class CollisionManager {

    private State state;

    public CollisionManager(State state){
        this.state = state;
    }

    public Tile checkTileCollisions(Rectangle rectangle){
        Tile[][] gameMap = state.getGameMap().getMap();
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
