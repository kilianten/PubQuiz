package gameObjects;

import game.Game;
import gameObjects.graphics.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {

    protected int x, y;
    protected BufferedImage sprite;
    protected Animation animation;
    protected BufferedImage defaultSprite;
    protected Rectangle collisionBox;
    protected boolean canCollide = false;

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
        if(sprite == null){
            System.out.println("SPRITE IS NULL: " + this);
        }
        return sprite.getWidth() * Game.SCALE;
    }

    public int getHeight(){
        return sprite.getHeight() * Game.SCALE;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public Rectangle getCollisionBox(){
        return collisionBox;
    }

    public boolean collidesWith(Rectangle otherCollisionBox){
        return collisionBox.intersects(otherCollisionBox.getBounds());
    }

    public double distanceTo(GameObject other){
        double deltaX = this.getX() - other.getX();
        double deltaY = this.getY() - other.getY();

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
