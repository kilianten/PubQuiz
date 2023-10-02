package ui;

import game.Game;
import game.state.State;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

public class UIText {

    protected Font palette;
    protected int x;
    protected int y;
    protected String text;
    protected boolean align;

    public UIText() {
    }

    public UIText(String text, int fontSize, double xValue, double yValue, boolean align){
        InputStream inputStream = getClass().getResourceAsStream("/fonts/Palette-regular.otf");

        try {
            palette = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            palette = palette.deriveFont(Font.PLAIN, 32F);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        x = (int) (Game.SCREEN_WIDTH * xValue);
        y = (int) (Game.SCREEN_HEIGHT * yValue);
        this.text = text;
        this.align = align;
    }

    public void draw(Graphics2D g2, State state){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        g2.setColor(Color.WHITE);
        double xOffset = 0;
        if(align){
            Rectangle2D r = palette.getStringBounds(text, g2.getFontRenderContext());
            xOffset = r.getWidth() / 2;
        }

        g2.drawString(text, (int) (x - xOffset), y);
    }

}
