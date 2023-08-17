package game;

import gameObjects.entity.player.Player;
import input.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    // Screen Settings
    final private int ORIGINAL_TILE_SIZE = 16;
    final private int SCALE = 4;
    final private int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final private int MAX_SCREEN_COL = 16;
    final private int MAX_SCREEN_ROW = 12;
    final private int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;    // 768 Pixels
    final private int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;   // 576 Pixels

    final private int FPS = 60;

    private KeyHandler key = new KeyHandler();
    private Thread gameThread;
    private Player player = new Player(this, key);

    public Game() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(this, g2);
        g2.dispose();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long currentTime;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public int getTileSize() {
        return TILE_SIZE;
    }
}
