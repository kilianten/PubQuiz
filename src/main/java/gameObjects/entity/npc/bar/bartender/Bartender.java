package gameObjects.entity.npc.bar.bartender;

import game.Game;
import game.state.GameState;
import game.state.State;
import gameObjects.entity.actions.Stand;
import gameObjects.entity.npc.NPC;

public class Bartender extends NPC {

    public Bartender(){
        super(4 * Game.TILE_SIZE, 2 * Game.TILE_SIZE, "/npc/bartender/standing.png", "Chuck");
        defaultSprite = sprite;
        actionManager.setAction(new Stand(this, "checkForEmpties"));
        wantsToTalk = true;
    }

    @Override
    public void update(GameState state) {
        actionManager.update(state, this);
    }

    @Override
    public String[] talkTo() {
        return new String[]{"Hey, what can I get you?", "I haven't got all day... I have other customers... Probably...\nJust get out of here"};
    }

    protected void setSpriteHeight(){
        this.spriteHeight = 64;
    }
}
