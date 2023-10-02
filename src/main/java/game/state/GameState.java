package game.state;

import display.Camera;
import display.Renderer;
import game.CollisionManager;
import game.Game;
import gameObjects.GameObject;
import gameObjects.entity.player.Player;
import input.KeyHandler;
import level.Level;
import level.levels.BarLevel;
import ui.DialogueWindow;
import ui.UITextItemPickup;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameState extends State {

    private Player player;
    private UITextItemPickup ui = new UITextItemPickup();
    private DialogueWindow dialogueWindow;
    private String mode;
    private Level level;
    private CollisionManager collisionManager;

    public GameState(KeyHandler key){
        this.key = key;

        player = new Player();
        camera = new Camera(player);
        level = new BarLevel(player);
        checkIfObjectsMissingSpriteHeight();
        collisionManager = new CollisionManager(this);
    }
    public CollisionManager getCollisionManager(){
        return collisionManager;
    }

    private void sortGameObjects() {
        getGameObjects().sort(Comparator.comparing(GameObject::getRenderLevel).thenComparing(gameObject -> gameObject.getYRender()));
    }

    public void update(Game game) {
        super.update(game);
        sortGameObjects();
        if(mode != "dialogue"){
            level.update(this);
            camera.update();
        } else {
            if(!dialogueWindow.hasMoreDialogue(key)){
                exitDialogue();
            };
        }
    }

    @Override
    public void draw(Graphics2D g2, Renderer renderer) {
        renderer.renderMap(this, g2);
        level.draw(this, g2, renderer);
        if(mode == "dialogue"){
            dialogueWindow.draw(g2);
        } else {
            ui.draw(g2, this);
        }
    }

    public Player getPlayer() {
        return player;
    }


    public void setMode(String dialogue) {
        this.mode = dialogue;
    }

    public void enterDialogue(String[] speechText) {
        setMode("dialogue");
        dialogueWindow = new DialogueWindow(speechText);
    }

    public void exitDialogue() {
        setMode(null);
        dialogueWindow = null;
    }

    public ArrayList<GameObject> getGameObjects() {
        return level.getGameObjects();
    }

    public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz){
        return getGameObjects().stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

    public void checkIfObjectsMissingSpriteHeight(){
        for(GameObject gameObject: level.getGameObjects()){
            gameObject.checkIfSpriteHeightSet();
        }
    }


}
