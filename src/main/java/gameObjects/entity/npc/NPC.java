package gameObjects.entity.npc;

import gameObjects.entity.Entity;

public class NPC extends Entity {

    public NPC(int x, int y) {
        super(x, y);
    }

    public NPC(int x, int y, String imagePath) {
        super(x, y, imagePath);
    }
}
