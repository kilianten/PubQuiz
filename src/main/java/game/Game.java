package game;

import display.Camera;
import display.Renderer;
import gameObjects.entity.player.Player;
import input.KeyHandler;
import tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends JPanel implements Runnable {

    // Screen Settings
    final static private int ORIGINAL_TILE_SIZE = 16;
    final static public int SCALE = 4;
    final static public int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final private int MAX_SCREEN_COL = 16;
    final private int MAX_SCREEN_ROW = 12;
    final private int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;    // 768 Pixels
    final private int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;   // 576 Pixels

    final private int FPS = 60;

    private TileManager tileManager = new TileManager(this);
    private KeyHandler key = new KeyHandler();
    private Thread gameThread;
    private Player player = new Player(this, key);
    private Camera camera = new Camera(player);
    private Renderer renderer = new Renderer();


    public Game() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
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
        camera.update(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        renderer.renderMap(this, g2);
        renderer.renderObject(this, g2, player);

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

    public int getTilePosition(int point) {
        return TILE_SIZE * point;
    }

    public BufferedImage[] loadImages(String[] imagePaths){
        BufferedImage[] images = new BufferedImage[imagePaths.length];

        for(int i = 0; i < imagePaths.length; i++){
            String path = imagePaths[i];

            images[i] = loadImage(path);
        }

        return images;
    }

    public BufferedImage loadImage(String imagePath){
        BufferedImage image = null;

        try {
            if(getClass().getResourceAsStream(imagePath) == null){
                System.out.println("Couldn't find resource at " + imagePath);
            } else {
                image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            }
        } catch (IOException e) {
            System.out.println("Couldn't load image at path " + imagePath);
        }

        return image;
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

    public TileManager getTileManager() {
        return tileManager;
    }

}
