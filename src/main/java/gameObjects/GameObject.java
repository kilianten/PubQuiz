package gameObjects;

import display.Camera;
import game.Game;
import gameObjects.graphics.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    protected int x, y;
    protected BufferedImage sprite;
    protected Animation animation;
    protected BufferedImage defaultSprite;
    protected Game game;

    public void update() {
        if(this.animation != null){
            animation.update(this);
        }
    }

    public void setSprite(BufferedImage sprite){
        this.sprite = sprite;
    }

    public void setAnimation(Animation animation){
        this.animation = animation;
    }


    public void draw(Game game, Graphics2D g2) {
        g2.drawImage(sprite, x, y, game.getTileSize(), game.getTileSize(), null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth(){
        return sprite.getWidth() * Game.SCALE;
    }

    public int getHeight(){
        return sprite.getHeight() * Game.SCALE;
    }

    public int getRenderPositionX(Camera camera) {
        return x - camera.getX();
    }

    public int getRenderPositionY(Camera camera) {
        return y - camera.getY();
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
