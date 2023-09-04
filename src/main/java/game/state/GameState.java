package game.state;

import display.Camera;
import display.Renderer;
import gameObjects.GameObject;
import gameObjects.entity.npc.bar.bartender.Bartender;
import gameObjects.entity.player.Player;
import gameObjects.interactiveObjects.BeerPint;
import gameObjects.interactiveObjects.Book;
import gameObjects.scenery.BarCounter;
import input.KeyHandler;
import ui.UIText;
import ui.UITextItemPickup;

import java.awt.*;

public class GameState extends State {

    private Player player = new Player();
    private UITextItemPickup ui = new UITextItemPickup();

    public GameState(KeyHandler key){
        camera = new Camera(player);
        gameObjects.add(new Book("Principia"));
        gameObjects.add(new BeerPint("Beer"));
        gameObjects.add(new BarCounter(3, 3));
        gameObjects.add(new BarCounter(4, 3));
        gameObjects.add(new BarCounter(5, 3));
        this.key = key;
        gameObjects.add(new Bartender());
    }

    public void update() {
        super.update();
        player.update(this);
        camera.update();
    }

    @Override
    public void draw(Graphics2D g2, Renderer renderer) {
        renderer.renderMap(this, g2);
        for(GameObject gameObject: gameObjects){
            renderer.renderObject(this, g2, gameObject);
        }
        renderer.renderObject(this, g2, player);
        ui.draw(g2, this);
    }

    public Player getPlayer() {
        return player;
    }


}
