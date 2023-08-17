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

    public void update(Game game) {
        this.x = objectWithFocus.getX() - (game.getScreenWidth()/ 2);
        this.y = objectWithFocus.getY() - (game.getScreenHeight() / 2);
        calculateViewBounds(game);
    }

    private void calculateViewBounds(Game game) {
        viewBounds = new Rectangle(
                x,
                y,
                game.getScreenWidth(),
                game.getScreenHeight()
        );
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
