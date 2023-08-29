package ui;

import game.Game;
import game.state.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UIText {

    protected Font arial;
    protected int x;
    protected int y;
    protected String text;

    public UIText() {
    }

    public UIText(String text, int fontSize, double xValue, double yValue){
        arial = new Font("Arial", Font.PLAIN, fontSize);
        x = (int) (Game.SCREEN_WIDTH * xValue);
        y = (int) (Game.SCREEN_HEIGHT * yValue);
        this.text = text;
    }

    public void draw(Graphics2D g2, State state){
        g2.setFont(arial);
        g2.setColor(Color.WHITE);
        Rectangle2D r = arial.getStringBounds(text, g2.getFontRenderContext());
        g2.drawString(text, (int) (x - r.getWidth() / 2), y);
    }

}
