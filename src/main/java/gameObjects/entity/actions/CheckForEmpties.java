package gameObjects.entity.actions;

import game.state.GameState;
import game.state.State;
import gameObjects.GameObject;
import gameObjects.entity.Entity;
import gameObjects.entity.npc.bar.bartender.Bartender;
import gameObjects.interactiveObjects.BeerPint;

import java.util.List;
import java.util.stream.Collectors;

public class CheckForEmpties extends Action {

    public CheckForEmpties(Bartender bartender){
        super(bartender, "stand", "checkForEmpties");
        System.out.println("EMPTIES");
    }

    @Override
    public void update(GameState state, Entity entity) {
        List<GameObject> pints = state.getGameObjects().stream()
                .filter(gameObject -> gameObject instanceof BeerPint)
                .filter(gameObject -> gameObject.isNear(entity, 250)).collect(Collectors.toList());
        ((BeerPint) (pints.get(0))).refill();
    }

    @Override
    public boolean shouldTransition(GameState state, Entity currentCharacter) {
        return true;
    }
}
