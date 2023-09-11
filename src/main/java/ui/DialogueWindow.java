package ui;

import game.Game;

import java.awt.*;

public class DialogueWindow {

    int x = Game.TILE_SIZE * 2;
    int y = Game.TILE_SIZE * 1;
    int width = Game.SCREEN_WIDTH - (Game.TILE_SIZE * 4);
    int height = Game.TILE_SIZE * 3;

    public void draw(Graphics2D graphics2D){
        Color color = new Color(0, 0, 0, 255);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x, y, width, height,  35, 35);

        color = new Color(255, 255, 255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

}
