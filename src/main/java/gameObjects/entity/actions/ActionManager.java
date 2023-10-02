package gameObjects.entity.actions;

import game.state.GameState;
import game.state.State;
import gameObjects.entity.Entity;
import gameObjects.entity.npc.bar.bartender.Bartender;

public class ActionManager {

    private Action currentAction;

    public void update(GameState state, Entity currentCharacter){
        if(currentAction != null){
            currentAction.update(state, currentCharacter);
            if(currentAction.shouldTransition(state, currentCharacter)){
                transitionTo(currentCharacter, currentAction.getNextState(), state);
            }
        }
    }

    public void transitionTo(Entity entity, String nextState, State state) {
        switch(nextState) {
            case "checkForEmpties":
                currentAction = new CheckForEmpties((Bartender) entity);
                break;
            case "stand":
                if(currentAction.furtherAction == null){
                    currentAction = new Stand(entity);
                } else {
                    currentAction = new Stand(entity, currentAction.furtherAction);
                }
                break;
            default:
                currentAction = new Stand(entity, currentAction.furtherAction);
                break;
        }
    }

    public void setAction(Action action){
        currentAction = action;
    }
}
