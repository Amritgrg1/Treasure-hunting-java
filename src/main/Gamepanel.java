package main;

import javax.swing.*;
import java.awt.*;

public class Gamepanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16
    final int scale = 3;

    final int titleSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = titleSize * maxScreenCol; // 768 pixel
    final int screenHeight = titleSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    // set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public Gamepanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while (gameThread!= null){
            currentTime = System.nanoTime();
            delta+= (currentTime -lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer>=1000000000){
                System.out.println("FPS"+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if(keyH.upPressed == true){
            playerY -=playerSpeed;
        }
        else if (keyH.downPressed == true){
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed == true){
            playerX-=playerSpeed;
        }
        else if (keyH.rightPressed == true){
            playerX+= playerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX,playerY,titleSize,titleSize);
        g2.dispose();
    }
}
