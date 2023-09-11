package gameObjects;

import game.Game;
import gameObjects.graphics.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import game.state.State;
import graphics.ImageLoader;

public class GameObject {

    protected int x, y;
    protected BufferedImage sprite;
    protected Animation animation;
    protected BufferedImage defaultSprite;
    protected Rectangle collisionBox;
    protected boolean isWalkable = true;
    protected String direction;
    protected boolean interactable = true;

    public GameObject(int x, int y){
        this(x, y, null);
    }

    public GameObject(int x, int y, String imagePath){
        this.x = x;
        this.y = y;
        if(imagePath != null){
            this.sprite = ImageLoader.loadImage(imagePath);
        }
    }

    public GameObject() {
    }

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

    public boolean isWalkable() {
        return isWalkable;
    }

    public boolean isNear(GameObject gameObject, int range) {
        Rectangle proximity = new Rectangle(
                gameObject.x - (range / 2),
                gameObject.y - (range / 2),
                range,
                range
        );
        return proximity.contains(x, y);
    }
}
