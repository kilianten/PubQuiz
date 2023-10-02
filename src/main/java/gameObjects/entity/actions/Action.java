package gameObjects.entity.actions;

import game.state.GameState;
import gameObjects.entity.Entity;

public abstract class Action {

    protected String nextAction;
    protected String furtherAction;

    public Action(Entity entity, String nextActionString){
        entity.setAnimation(null);
        this.nextAction = nextActionString;
    }

    public Action(Entity entity, String nextActionString, String furtherAction){
        entity.setAnimation(null);
        this.nextAction = nextActionString;
        this.furtherAction = furtherAction;
    }

    public abstract void update(GameState state, Entity entity);

    public abstract boolean shouldTransition(GameState state, Entity currentCharacter);

    public String getNextState() {
        return nextAction;
    }

}
