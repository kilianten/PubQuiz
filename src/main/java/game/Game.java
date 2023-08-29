package game;

import display.Camera;
import display.Renderer;
import game.state.GameState;
import gameObjects.GameObject;
import gameObjects.entity.player.Player;
import gameObjects.interactiveObjects.Book;
import gameObjects.interactiveObjects.InteractiveObject;
import input.KeyHandler;
import map.GameMap;
import sound.Sound;
import ui.UI;
import game.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable {

    // Screen Settings
    final static private int ORIGINAL_TILE_SIZE = 16;
    final static public int SCALE = 4;
    static public int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final private int MAX_SCREEN_COL = 16;
    final private int MAX_SCREEN_ROW = 12;
    final private int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;    // 768 Pixels
    final private int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;   // 576 Pixels

    final private int FPS = 60;

    private GameMap gameMap = new GameMap();
    private KeyHandler key = new KeyHandler();
    private CollisionManager collisionManager = new CollisionManager(this);
    private Thread gameThread;
    private Player player = new Player(this, key);
    private Camera camera = new Camera(player);
    private Renderer renderer = new Renderer();
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private UI ui = new UI(this);
    private State state;

    private Sound sound = new Sound();

    public Game() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        this.state = new GameState();
        gameObjects.add(new Book("Principia"));
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        state.update();
        player.update();
        camera.update(this);
        for(GameObject gameObject: gameObjects){
            gameObject.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        renderer.renderMap(this, g2);
        for(GameObject gameObject: gameObjects){
            renderer.renderObject(this, g2, gameObject);
        }
        renderer.renderObject(this, g2, player);

        ui.draw(g2, this);

        g2.dispose();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long currentTime;
        long lastTime = System.nanoTime();

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public Camera getCamera() {
        return camera;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public CollisionManager getCollisionManager(){
        return collisionManager;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public ArrayList<InteractiveObject> getInteractiveGameObjects() {
        ArrayList<InteractiveObject> interactiveObjects = new ArrayList();
        for(GameObject object: gameObjects){
            if(object instanceof InteractiveObject){
                interactiveObjects.add((InteractiveObject) object);
            }
        }
        return interactiveObjects;
    }

    public void playSound(String file){
        sound.setFile(file);
        sound.play();
    }

    public Player getPlayer() {
        return player;
    }
}
