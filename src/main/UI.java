package main;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import static java.awt.Font.createFont;

public class UI {
    Gamepanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";


    public UI(Gamepanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/fony/x12y16pxMaruMonica.ttf");
            maruMonica = createFont(Font.TRUETYPE_FONT,is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        //Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //Play state
        if (gp.gameState == gp.playState){
            //later
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

    }

    public void drawTitleScreen() {

        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,56F));
        String text = "Blue Boy Adventure";
        int x = getXforCenteredText(text);
        int y = gp.titleSize*3;

        //Shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        //Main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //Blue boy image
        x = gp.screenWidth/2 - (gp.titleSize*2)/2;
        y += gp.titleSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.titleSize*2, gp.titleSize*2, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.titleSize*4;
        g2.drawString(text, x, y);

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);

        text = "QUIT GAME";
        x = getXforCenteredText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);

    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public  void drawDialogueScreen() {
        // WINDOW
        int x = gp.titleSize*2;
        int y = gp.titleSize/2;
        int width = gp.screenWidth - (gp.titleSize*4);
        int height = gp.titleSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.titleSize;
        y += gp.titleSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x= gp.screenWidth/2 - length/2;
        return x;
    }
}
