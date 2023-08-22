package display;

import game.Game;
import gameObjects.GameObject;
import map.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    public void renderObject(Game game, Graphics2D graphics, GameObject gameObject){
        graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getX() - game.getCamera().getX(),
                gameObject.getY() - game.getCamera().getY(),
                gameObject.getWidth(),
                gameObject.getHeight(),
                null
        );
        if(true && gameObject.getCollisionBox() != null){
            graphics.drawRect(
                    (int) gameObject.getCollisionBox().getX() - game.getCamera().getX(),
                    (int) gameObject.getCollisionBox().getY()  - game.getCamera().getY(),
                    gameObject.getCollisionBox().width,
                    gameObject.getCollisionBox().height);
        }
    }

    public void renderMap(Game game, Graphics2D graphics) {
        Tile[][] map = game.getGameMap().getMap();
        Camera camera = game.getCamera();

        int startX = Math.max(0, camera.getX() / Game.TILE_SIZE - Game.TILE_SIZE);
        int startY = Math.max(0, camera.getY() / Game.TILE_SIZE - Game.TILE_SIZE);
        int endX = Math.min(map.length, camera.getX() / Game.TILE_SIZE + game.getScreenWidth() / Game.TILE_SIZE + Game.TILE_SIZE);
        int endY = Math.min(map[0].length, camera.getY() / Game.TILE_SIZE + game.getScreenHeight() / Game.TILE_SIZE + Game.TILE_SIZE);

        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                int drawPositionX = x * Game.TILE_SIZE - camera.getX();
                int drawPositionY = y * Game.TILE_SIZE - camera.getY();

                BufferedImage image = game.getGameMap().getTileSprite(x, y);

                graphics.drawImage(game.getGameMap().getTileSprite(x, y),
                        drawPositionX,
                        drawPositionY,
                        image.getWidth() * Game.SCALE,
                        image.getHeight() * Game.SCALE,
                        null
                );
            }
        }

    }

}
