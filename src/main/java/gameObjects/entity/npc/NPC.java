package gameObjects.entity.npc;

import gameObjects.entity.Entity;

public abstract class NPC extends Entity {

    protected boolean wantsToTalk;
    protected String name;

    public NPC(int x, int y, String imagePath, String name) {
        super(x, y, imagePath);
        this.name = name;
    }

    public abstract String[] talkTo();

    public void interactWith(){

    }

    public boolean wantsToTalk(){
        return wantsToTalk;
    }

    public String getName() {
        return name;
    }
}
