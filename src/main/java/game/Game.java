package game;

import display.Renderer;
import game.state.GameState;
import input.KeyHandler;

import sound.Sound;
import game.state.State;

import javax.swing.*;
import java.awt.*;
public class Game extends JPanel implements Runnable {

    // Screen Settings
    final static private int ORIGINAL_TILE_SIZE = 16;
    final static public int SCALE = 4;
    static public int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final static private int MAX_SCREEN_COL = 16;
    final static private int MAX_SCREEN_ROW = 12;
    final static public int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;    // 768 Pixels
    final static public int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;   // 576 Pixels

    final private int FPS = 60;

    private KeyHandler key = new KeyHandler();
    private Thread gameThread;

    private Renderer renderer = new Renderer();
    private State state;

    public Game() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        this.state = new GameState(key);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        state.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        state.draw(g2, renderer);

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

}
