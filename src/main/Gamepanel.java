package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class Gamepanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16
    final int scale = 3;

    public final int titleSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = titleSize * maxScreenCol; // 768 pixel
    public final int screenHeight = titleSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = titleSize * maxWorldCol;
    public final int worldHeight = titleSize * maxWorldRow;

    //FPS
    int FPS = 60;

    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public SuperObject Obj[] = new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);
    KeyHandler keyH = new KeyHandler();

    public Player player = new Player(this,keyH);
    TileManager tileM = new TileManager(this);

    public Gamepanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame(){
        aSetter.setObject();
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
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //TILE
        tileM.draw(g2);

        //OBJECT
        for(int i = 0; i<Obj.length; i++){
            if(Obj[i]!= null){
                Obj[i].draw(g2,this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose();
    }
}
