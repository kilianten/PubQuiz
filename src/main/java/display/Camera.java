package display;

import game.Game;
import gameObjects.GameObject;

import java.awt.*;

public class Camera {

    private int x;
    private int y;
    private Rectangle viewBounds;

    private GameObject objectWithFocus;

    public Camera(GameObject objectWithFocus){
        this.objectWithFocus = objectWithFocus;
    }

    public void update() {
        this.x = objectWithFocus.getX() - (Game.SCREEN_WIDTH/ 2);
        this.y = objectWithFocus.getY() - (Game.SCREEN_HEIGHT / 2);
        calculateViewBounds();
    }

    private void calculateViewBounds() {
        viewBounds = new Rectangle(
                x,
                y,
                Game.SCREEN_WIDTH,
                Game.SCREEN_HEIGHT
        );
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
