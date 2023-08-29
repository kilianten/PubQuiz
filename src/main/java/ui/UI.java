package ui;

import game.Game;
import gameObjects.entity.player.Player;
import gameObjects.interactiveObjects.InteractiveObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UI {

    private Font arial;
    private int x;
    private int y;

    public UI(Game game){
        arial = new Font("Arial", Font.PLAIN, 15);
        x = game.getScreenWidth() / 2;
        y = game.getScreenHeight() / 2 - Game.TILE_SIZE / 4;
    }

    public void draw(Graphics2D g2, Game game){
        g2.setFont(arial);
        g2.setColor(Color.WHITE);
        Player player = game.getPlayer();
        InteractiveObject interactiveObject = player.getInteractiveObject();
        if(interactiveObject != null){
            Rectangle2D r = arial.getStringBounds(interactiveObject.getInteractingMessage(), g2.getFontRenderContext());
            g2.drawString(interactiveObject.getInteractingMessage(), (int) (x - r.getWidth() / 2) + Game.TILE_SIZE / 2, y);
        }
    }

}
