package game.state;

import display.Camera;
import display.Renderer;
import game.CollisionManager;
import game.Game;
import gameObjects.GameObject;
import input.KeyHandler;
import level.map.GameMap;
import sound.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class State {

    protected GameMap gameMap = new GameMap();
    protected Camera camera;
    protected KeyHandler key;
    protected Sound sound = new Sound();

    public void update(Game game) {
        checkPause(game);
    }

    public abstract void draw(Graphics2D g2, Renderer renderer);

    public Camera getCamera() {
        return camera;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public KeyHandler getKey() {
        return key;
    }

    public void playSound(String file){
        sound.setFile(file);
        sound.play();
    }

    public void checkPause(Game game) {
        if(key.isPressed(KeyEvent.VK_ESCAPE)){
            game.setState("pauseState");
        }
    }

}
