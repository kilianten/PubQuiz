package ui;

import game.Game;
import game.state.GameState;
import gameObjects.interactiveObjects.InteractiveObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UITextItemPickup extends UIText {

    public UITextItemPickup() {
        arial = new Font("Arial", Font.PLAIN, 20);
        x = (Game.SCREEN_WIDTH / 2);
        y = (Game.SCREEN_HEIGHT / 2) - Game.TILE_SIZE / 2;
    }

    public void draw(Graphics2D g2, GameState state){
        InteractiveObject interactiveObject = state.getPlayer().getInteractiveObject();
        if(interactiveObject != null){
            g2.setFont(arial);
            g2.setColor(Color.WHITE);
            Rectangle2D r = arial.getStringBounds(interactiveObject.getInteractingMessage(), g2.getFontRenderContext());
            g2.drawString(interactiveObject.getInteractingMessage(), (int) (x - r.getWidth() / 2) + Game.TILE_SIZE / 2, y);
        }
    }
}
