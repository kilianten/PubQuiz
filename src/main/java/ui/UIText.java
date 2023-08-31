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
    protected boolean align;

    public UIText() {
    }

    public UIText(String text, int fontSize, double xValue, double yValue, boolean align){
        arial = new Font("Arial", Font.PLAIN, fontSize);
        x = (int) (Game.SCREEN_WIDTH * xValue);
        y = (int) (Game.SCREEN_HEIGHT * yValue);
        this.text = text;
        this.align = align;
    }

    public void draw(Graphics2D g2, State state){
        g2.setFont(arial);
        g2.setColor(Color.WHITE);
        double xOffset = 0;
        if(align){
            Rectangle2D r = arial.getStringBounds(text, g2.getFontRenderContext());
            xOffset = r.getWidth() / 2;
        }

        g2.drawString(text, (int) (x - xOffset), y);
    }

}
