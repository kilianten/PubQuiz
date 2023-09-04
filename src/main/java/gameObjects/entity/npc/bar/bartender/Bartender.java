package gameObjects.entity.npc.bar.bartender;

import game.Game;
import gameObjects.entity.npc.NPC;
import graphics.ImageLoader;

public class Bartender extends NPC {

    public Bartender(){
        super(4 * Game.TILE_SIZE, 2 * Game.TILE_SIZE, "/npc/bartender/standing.png");
        defaultSprite = sprite;
    }
}
