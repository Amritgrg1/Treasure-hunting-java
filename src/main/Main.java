package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Gamepanel gamepanel = new Gamepanel();
        frame.add(gamepanel);
        frame.pack();

        frame.setTitle("2D Adventure");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        gamepanel.setupGame();
        gamepanel.startGameThread();
    }
}