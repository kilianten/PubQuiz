package display;

import game.Game;
import gameObjects.GameObject;
import map.Tile;
import game.state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    public void renderObject(State state, Graphics2D graphics, GameObject gameObject){
        graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getX() - state.getCamera().getX(),
                gameObject.getY() - state.getCamera().getY(),
                Game.TILE_SIZE,
                Game.TILE_SIZE,
                null
        );
        if(true && gameObject.getCollisionBox() != null){
            graphics.drawRect(
                    (int) gameObject.getCollisionBox().getX() - state.getCamera().getX(),
                    (int) gameObject.getCollisionBox().getY()  - state.getCamera().getY(),
                    gameObject.getCollisionBox().width,
                    gameObject.getCollisionBox().height);
        }
    }

    public void renderMap(State state, Graphics2D graphics) {
        Tile[][] map = state.getGameMap().getMap();
        Camera camera = state.getCamera();

        int startX = Math.max(0, camera.getX() / Game.TILE_SIZE - Game.TILE_SIZE);
        int startY = Math.max(0, camera.getY() / Game.TILE_SIZE - Game.TILE_SIZE);
        int endX = Math.min(map.length, camera.getX() / Game.TILE_SIZE + Game.SCREEN_WIDTH / Game.TILE_SIZE + Game.TILE_SIZE);
        int endY = Math.min(map[0].length, camera.getY() / Game.TILE_SIZE + Game.SCREEN_HEIGHT / Game.TILE_SIZE + Game.TILE_SIZE);

        for(int x = startX; x < endX; x++){
            for(int y = startY; y < endY; y++){
                int drawPositionX = x * Game.TILE_SIZE - camera.getX();
                int drawPositionY = y * Game.TILE_SIZE - camera.getY();

                BufferedImage image = state.getGameMap().getTileSprite(x, y);

                graphics.drawImage(state.getGameMap().getTileSprite(x, y),
                        drawPositionX,
                        drawPositionY,
                        Game.TILE_SIZE,
                        Game.TILE_SIZE,
                        null
                );
            }
        }

    }

}
