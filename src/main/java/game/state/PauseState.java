package game.state;

import display.Renderer;
import input.KeyHandler;
import ui.UIText;

import java.awt.*;

public class PauseState extends State {

    private UIText pausedText;

    public PauseState(KeyHandler key){
       this.key = key;
        pausedText = new UIText("PAUSED", 35, .2, .3);
    }

    @Override
    public void draw(Graphics2D g2, Renderer renderer) {
        pausedText.draw(g2, this);
    }
}
