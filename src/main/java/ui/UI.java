package ui;

import game.Game;
import game.state.GameState;
import gameObjects.entity.player.Player;
import gameObjects.interactiveObjects.InteractiveObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UI {

    private Font arial;
    private int x;
    private int y;

    public UI(){
        arial = new Font("Arial", Font.PLAIN, 15);
        x = Game.SCREEN_WIDTH / 2;
        y = Game.SCREEN_HEIGHT / 2 - Game.TILE_SIZE / 4;
    }

    public void draw(Graphics2D g2, GameState state){
        g2.setFont(arial);
        g2.setColor(Color.WHITE);
        Player player = state.getPlayer();
        InteractiveObject interactiveObject = player.getInteractiveObject();
        if(interactiveObject != null){
            Rectangle2D r = arial.getStringBounds(interactiveObject.getInteractingMessage(), g2.getFontRenderContext());
            g2.drawString(interactiveObject.getInteractingMessage(), (int) (x - r.getWidth() / 2) + Game.TILE_SIZE / 2, y);
        }
    }

}
