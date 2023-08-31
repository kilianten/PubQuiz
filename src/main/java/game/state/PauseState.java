package game.state;

import display.Renderer;
import game.Game;
import graphics.ImageLoader;
import input.KeyHandler;
import ui.UIText;
import ui.UITextPauseSelect;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class PauseState extends State {

    private UIText pausedText;
    private int selectedIndex = 0;
    private UITextPauseSelect[] selectTexts = new UITextPauseSelect[3];
    private BufferedImage[] beerImages;
    private String[] beerImagesPath = {"/objects/beerPint/beer.png", "/objects/beerPint/beer01.png", "/objects/beerPint/beer02.png", "/objects/beerPint/beer03.png", "/objects/beerPint/beer04.png"};

    public PauseState(KeyHandler key){
       this.key = key;
        pausedText = new UIText("PAUSED", 50, .2, .3, false);
        selectTexts[0] = new UITextPauseSelect("Resume", 35, .3, .4, false);
        selectTexts[0].select();
        selectTexts[1] = new UITextPauseSelect("Options", 35, .35, .5, false);
        selectTexts[2] = new UITextPauseSelect("Exit", 35, .3, .6, false);
        beerImages = ImageLoader.loadImages(beerImagesPath);
    }

    public void update(){
        if(getKey().isPressed(KeyEvent.VK_S)){
            selectTexts[selectedIndex].deselect();
            selectedIndex++;
            if(selectedIndex >= selectTexts.length){
                selectedIndex = 0;
            }
            selectTexts[selectedIndex].select();
        } else if(getKey().isPressed(KeyEvent.VK_W)){
            selectTexts[selectedIndex].deselect();
            selectedIndex--;
            if(selectedIndex < 0){
                selectedIndex = selectTexts.length - 1;
            }
            selectTexts[selectedIndex].select();
        } else if(getKey().isPressed(KeyEvent.VK_ENTER)){
            handleEnter();
        } else if(getKey().isPressed(KeyEvent.VK_ESCAPE)){
            isPaused = true;
        }
    }

    private void handleEnter() {
        switch(selectedIndex){
            case 0:
                isPaused = true;
                break;
            case 1:
                isPaused = true;
                break;
            case 2:
                System.exit(1);
        }
    }

    @Override
    public void draw(Graphics2D g2, Renderer renderer) {
        pausedText.draw(g2, this);
        for(UITextPauseSelect text: selectTexts){
            text.draw(g2, this);
        }
        g2.drawImage(beerImages[selectedIndex], selectTexts[selectedIndex].getX() - Game.TILE_SIZE, (int) (selectTexts[selectedIndex].getY() - (Game.TILE_SIZE * .75)), null);
    }
}
