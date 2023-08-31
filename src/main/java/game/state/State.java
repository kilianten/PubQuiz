package game.state;

import display.Camera;
import display.Renderer;
import game.CollisionManager;
import gameObjects.GameObject;
import gameObjects.interactiveObjects.InteractiveObject;
import input.KeyHandler;
import map.GameMap;
import sound.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class State {

    protected GameMap gameMap = new GameMap();
    protected Camera camera;
    protected ArrayList<GameObject> gameObjects = new ArrayList<>();
    private CollisionManager collisionManager = new CollisionManager(this);
    protected KeyHandler key;
    protected Sound sound = new Sound();
    protected boolean isPaused;

    public void update() {
        checkPause();
        for(GameObject gameObject: gameObjects){
            gameObject.update(this);
        }
    }

    public abstract void draw(Graphics2D g2, Renderer renderer);


    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public ArrayList<InteractiveObject> getInteractiveGameObjects() {
        ArrayList<InteractiveObject> interactiveObjects = new ArrayList();
        for(GameObject object: gameObjects){
            if(object instanceof InteractiveObject){
                interactiveObjects.add((InteractiveObject) object);
            }
        }
        return interactiveObjects;
    }

    public Camera getCamera() {
        return camera;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public CollisionManager getCollisionManager(){
        return collisionManager;
    }

    public KeyHandler getKey() {
        return key;
    }

    public void playSound(String file){
        sound.setFile(file);
        sound.play();
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void checkPause() {
        if(key.isPressed(KeyEvent.VK_ESCAPE)){
            isPaused = true;
        }
    }

    public void unPause() {
        isPaused = false;
    }
}
