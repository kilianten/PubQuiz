package gameObjects.entity.npc.bar.bartender;

import game.Game;
import gameObjects.entity.npc.NPC;
import graphics.ImageLoader;

public class Bartender extends NPC {

    public Bartender(){
        x = 4 * Game.TILE_SIZE;
        y = 2 * Game.TILE_SIZE;
        defaultSprite = ImageLoader.loadImage("/npc/bartender/standing.png");
        sprite = defaultSprite;
    }
}
