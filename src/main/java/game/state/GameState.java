package game.state;

import display.Camera;
import display.Renderer;
import gameObjects.GameObject;
import gameObjects.entity.player.Player;
import gameObjects.interactiveObjects.Book;
import input.KeyHandler;
import ui.UI;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends State {

    private Player player = new Player();
    private UI ui = new UI();

    public GameState(KeyHandler key){
        camera = new Camera(player);
        gameObjects.add(new Book("Principia"));
        this.key = key;
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
