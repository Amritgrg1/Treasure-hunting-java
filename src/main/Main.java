package main;

import javax.swing.*;

public class
Main {

    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        Gamepanel gamepanel = new Gamepanel();
        window.add(gamepanel);

        gamepanel.config.loadConfig();
        if (gamepanel.fullScreenOn == true) {
            window.setUndecorated(true);
        }

        window.pack();

        window.setTitle("2D Adventure");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamepanel.setupGame();
        gamepanel.startGameThread();
    }
}

//testing