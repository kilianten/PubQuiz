package game.state;

import display.Renderer;
import input.KeyHandler;

import java.awt.*;

public class PauseState extends State {

    public PauseState(KeyHandler key){
       this.key = key;
    }

    @Override
    public void draw(Graphics2D g2, Renderer renderer) {

    }
}
