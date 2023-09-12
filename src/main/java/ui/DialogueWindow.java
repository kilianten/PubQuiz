package ui;

import game.Game;
import input.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DialogueWindow {

    private final String[] speechText;
    private int x;
    private int y;
    private int width;
    private int height;
    private int speechIndex;

    public DialogueWindow(String[] speechText){
        this.x = Game.TILE_SIZE * 2;
        this.y = Game.TILE_SIZE * 1;
        this.width  = Game.SCREEN_WIDTH - (Game.TILE_SIZE * 4);
        this.height = Game.TILE_SIZE * 3;
        this.speechText = speechText;
        speechIndex = 0;
    }

    public void draw(Graphics2D graphics2D){
        Color color = new Color(0, 0, 0, 255);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x, y, width, height,  35, 35);

        color = new Color(255, 255, 255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 26f));
        int yOffset = 0;
        for(String line: speechText[speechIndex].split("\n")){
            graphics2D.drawString(line, x + Game.TILE_SIZE / 2, y + Game.TILE_SIZE + 5 + yOffset);
            yOffset += Game.TILE_SIZE / 2;
        }
    }

    public boolean hasMoreDialogue(KeyHandler key) {
        if(key.isPressed(KeyEvent.VK_SPACE)){
            speechIndex++;
            return speechIndex <= speechText.length - 1;
        }
        return true;
    }
}
