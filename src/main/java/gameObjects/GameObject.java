package gameObjects;

import game.Game;
import gameObjects.graphics.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import game.state.State;

public class GameObject {

    protected int x, y;
    protected BufferedImage sprite;
    protected Animation animation;
    protected BufferedImage defaultSprite;
    protected Rectangle collisionBox;
    protected boolean canCollide = false;
    protected String direction;

    public void update(State state) {
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

    public boolean isFacing(GameObject other){
        double distanceX = getX() - other.getX();
        double distanceY = getY() - other.getY();
        double length = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        distanceX = distanceX == 0 ? 0 : distanceX/length;
        distanceY = distanceY == 0 ? 0 : distanceY/length;
        double dotProduct = distanceX * getXDirection() + distanceY * getYDirection();
        return dotProduct < 0;
    }

    public int getYDirection(){
        if(direction == "up"){
            return -1;
        } else {
            return 1;
        }
    }

    public int getXDirection(){
        if(direction == "right"){
            return 1;
        } else {
            return -1;
        }
    }

    public void setDefaultSprite() {
        setSprite(defaultSprite);
    }
}
