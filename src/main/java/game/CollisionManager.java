package game;

import game.state.GameState;
import level.map.Tile;
import game.state.State;
import java.awt.*;

public class CollisionManager {

    private GameState state;

    public CollisionManager(GameState state){
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

    public boolean isCollidingWithObject(Rectangle newCollisionBox) {
        return state.getGameObjects().stream()
                .filter(gameObject -> !gameObject.isWalkable())
                .anyMatch(gameObject -> gameObject.collidesWith(newCollisionBox));
    }
}
