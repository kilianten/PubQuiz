package ui;

import game.state.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class UITextPauseSelect extends UIText {

    protected boolean selected = false;
    private int borderSize = 10;

    public UITextPauseSelect(String text, int fontSize, double xValue, double yValue, boolean align){
       super(text, fontSize, xValue, yValue, align);
    }

    public void draw(Graphics2D g2, State state){
        g2.setFont(arial);
        g2.setColor(Color.WHITE);
        double xOffset = 0;
        Rectangle2D r = arial.getStringBounds(text, g2.getFontRenderContext());

        if(selected){
            g2.fillRect(x - borderSize,
                    (int) (y - r.getHeight()),
                    (int) (r.getWidth() + borderSize * 2),
                    (int) r.getHeight() + borderSize);
            g2.setColor(Color.BLACK);
        }

        if(align){

            xOffset = r.getWidth() / 2;
        }
        g2.drawString(text, (int) (x - xOffset), y);

    }

    public void deselect() {
        selected = false;
    }

    public void select() {
        selected = true;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
