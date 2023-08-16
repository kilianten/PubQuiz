import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final private int ORIGINAL_TILE_SIZE = 16;
    final private int SCALE = 3;
    final private int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final private int MAX_SCREEN_COL = 16;
    final private int MAX_SCREEN_ROW = 12;
    final private int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;    // 768 Pixels
    final private int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;   // 576 Pixels

    final private int FPS = 60;

    private KeyHandler key = new KeyHandler();
    private Thread gameThread;

    private int playerX = 10;
    private int playerY = 10;
    private int playerSpeed = 4;

    public GamePanel() {
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
        if(key.upPressed){
            playerY -= playerSpeed;
        }
        else if(key.downPressed){
            playerY += playerSpeed;
        }
        else if(key.leftPressed){
            playerX -= playerSpeed;
        }
        else if(key.rightPressed){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
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
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

}
