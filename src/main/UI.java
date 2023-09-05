package main;


import entity.Entity;
import object.Obj_Heart;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static java.awt.Font.createFont;

public class   UI {
    Gamepanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
//    public String message = "";
//    int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first screen, 1:the second screen
    public int slotCol = 0;
    public int slotRow = 0;

    public UI(Gamepanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = createFont(Font.TRUETYPE_FONT,is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create HUD Object
        Entity heart = new Obj_Heart(gp);
        heart_full =heart.image;
        heart_half =heart.image2;
        heart_blank =heart.image3;
    }
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
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
            drawPlayerlife();
            drawMessage();
        }
        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
//            drawPlayerlife();  hiding while game is paused
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawPlayerlife();
            drawDialogueScreen();
        }

        //CHARACTER STATE
        if (gp.gameState == gp.characterState){
            drawCharcaterScreen();
            drawInventory();
        }

    }

    public void drawPlayerlife() {

        int x = gp.titleSize/2;
        int y = gp.titleSize/2;
        int i = 0;

        //Draw Max heart
        while(i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.titleSize;
        }

        // Reset
        x = gp.titleSize/2;
        y = gp.titleSize/2;
        i = 0;

        //Draw Current life
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.titleSize;
        }

    }
    public void drawMessage() {
        int messaageX = gp.titleSize;
        int messageY = gp.titleSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i < message.size(); i++) {
            if(message.get(i) != null) {

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messaageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messaageX, messageY);

                int counter = messageCounter.get(i) + 1; // messageCounter++
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if(messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }

            }
        }
    }
    public void drawTitleScreen() {

        if(titleScreenState == 0) {
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
            if(commandNum == 0){
                g2.drawString(">", x-gp.titleSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.titleSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.titleSize, y);
            }

            text = "QUIT GAME";
            x = getXforCenteredText(text);
            y += gp.titleSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.titleSize, y);
            }
        } else if (titleScreenState == 1) {

            // Class selection screen
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "Select your class!";
            int x = getXforCenteredText(text);
            int y = gp.titleSize*3;
            g2.drawString(text, x, y);


            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.titleSize*3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x-gp.titleSize, y);
            }
            text = "Thief";
            x = getXforCenteredText(text);
            y += gp.titleSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x-gp.titleSize, y);
            }
            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += gp.titleSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x-gp.titleSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.titleSize*2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x-gp.titleSize, y);
            }
        }

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

    public void drawCharcaterScreen(){
        //CREATE A FRAME
        final int frameX = gp.titleSize;
        final int frameY = gp.titleSize;
        final int frameWidth = gp.titleSize*5;
        final int frameHeight = gp.titleSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.titleSize;
        final int lineHeight = 35;

        //NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        //Resey textY
        textY = frameY + gp.titleSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlighToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.titleSize, textY - 14, null);
        textY += gp.titleSize;
        g2.drawImage(gp.player.currentShield.down1, tailX- gp.titleSize, textY - 14, null)
        ;

    }
    public void drawInventory() {

        // FRAME
        int frameX = gp.titleSize*9;
        int frameY = gp.titleSize;
        int frameWidth = gp.titleSize*6;
        int frameHeight = gp.titleSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.titleSize+3;

        // DRAW PLAYER'S ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++) {
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }

        }

        // CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.titleSize;
        int cursorHeight = gp.titleSize;

        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10,10);

        // DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.titleSize*3;
        drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);

        // DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.titleSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()) {
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }
    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
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
    public int getXforAlighToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x= tailX -length;
        return x;
    }
}
