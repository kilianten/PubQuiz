package display;

import game.Game;
import gameObjects.GameObject;

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
    }

    public void renderMap(Game game, Graphics2D graphics) {
        String[][] map = game.getTileManager().getMap();
        Camera camera = game.getCamera();

        int startX = Math.max(0, camera.getX() / Game.TILE_SIZE - Game.TILE_SIZE);
        int startY = Math.max(0, camera.getY() / Game.TILE_SIZE - Game.TILE_SIZE);
        int endX = Math.min(map.length, camera.getX() / Game.TILE_SIZE + game.getScreenWidth() / Game.TILE_SIZE + Game.TILE_SIZE);
        int endY = Math.min(map[0].length, camera.getY() / Game.TILE_SIZE + game.getScreenHeight() / Game.TILE_SIZE + Game.TILE_SIZE);

        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                int drawPositionX = x * Game.TILE_SIZE - camera.getX();
                int drawPositionY = y * Game.TILE_SIZE - camera.getY();

                BufferedImage image = game.getTileManager().getTileSprite(x, y);

                graphics.drawImage(game.getTileManager().getTileSprite(x, y),
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
