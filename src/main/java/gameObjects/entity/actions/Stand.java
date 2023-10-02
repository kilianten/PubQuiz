package gameObjects.entity.actions;

import game.state.GameState;
import game.state.State;
import gameObjects.entity.Entity;

import java.util.Random;

public class Stand extends Action {

    private long startTime;
    private int durationSeconds;

    public Stand(Entity entity){
        super(entity, "stand");
        startTime = System.currentTimeMillis();
        Random random = new Random();
        durationSeconds = 2 + random.nextInt(5);
        System.out.println("new stand");
    }
    public Stand(Entity entity, String nextAction){
        super(entity, nextAction);
        startTime = System.currentTimeMillis();
        Random random = new Random();
        durationSeconds = 2 + random.nextInt(5);
    }

    @Override
    public void update(GameState state, Entity entity) {

    }

    @Override
    public boolean shouldTransition(GameState state, Entity currentCharacter) {
        return (System.currentTimeMillis() - startTime) / 1000 >= durationSeconds;
    }

}
