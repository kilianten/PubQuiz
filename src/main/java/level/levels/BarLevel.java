package level.levels;

import game.Game;
import gameObjects.entity.npc.bar.bartender.Bartender;
import gameObjects.entity.player.Player;
import gameObjects.interactiveObjects.BeerPint;
import gameObjects.interactiveObjects.Book;
import gameObjects.scenery.BarCounter;
import level.Level;

public class BarLevel extends Level {

    public BarLevel(Player player){
        super(player);
        gameObjects.add(new Book("Principia"));
        gameObjects.add(new BarCounter(3, 3));
        gameObjects.add(new BarCounter(4, 3));
        gameObjects.add(new BarCounter(5, 3));
        gameObjects.add(new BeerPint(5 * Game.TILE_SIZE, 3 * Game.TILE_SIZE - 15));
        gameObjects.add(new BeerPint(64, 64));
        gameObjects.add(new Bartender());
    }

}
