package level;

import display.Renderer;
import game.state.GameState;
import gameObjects.GameObject;
import gameObjects.entity.player.Player;

import java.awt.*;
import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.Map;

public abstract class Level {

    private Map map;
    protected ArrayList<GameObject> gameObjects;

    public Level(Player player){
        this.gameObjects = new ArrayList<>();
        gameObjects.add(player);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void update(GameState state) {
        for(GameObject gameObject: gameObjects){
            gameObject.update(state);
        }
    }

    public void draw(GameState state, Graphics2D g2, Renderer renderer) {
        for(GameObject gameObject: gameObjects){
            renderer.renderObject(state, g2, gameObject);
        }
    }
}
