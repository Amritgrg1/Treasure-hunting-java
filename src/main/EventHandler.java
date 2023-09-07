package main;

import java.awt.*;

public class EventHandler {
    Gamepanel gp;
    EventRect eventRect[][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

     public EventHandler(Gamepanel gp) {
         this.gp = gp;

         eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

         int col =0;
         int row =0;
         while(col < gp.maxWorldCol && row < gp.maxWorldRow){

             eventRect[col][row] = new EventRect();
             eventRect[col][row].x = 23;
             eventRect[col][row].y = 23;
             eventRect[col][row].width = 2;
             eventRect[col][row].height = 2;
             eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
             eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

             col++;
             if(col == gp.maxWorldCol){
                 col = 0;
                 row++;
             }
         }

     }

     public void checkEvent() {

         //Check if player move one tile
         int xDistance = Math.abs(gp.player.WorldX - previousEventX);
         int yDistance = Math.abs(gp.player.WorldY - previousEventY);
         int distance = Math.max(xDistance, yDistance);
         if(distance > gp.titleSize){
             canTouchEvent = true;
         }

        if(canTouchEvent == true){
            if (hit(27, 16, "right") == true) {damagePit(27, 16, gp.dialogueState);}
            if (hit(10, 12, "any") == true) {teleport(10,12, gp.dialogueState);}
            if(hit(23,12,"up") == true) {healingPool(23, 12, gp.dialogueState);}
        }
     }
     public boolean hit(int col, int row, String reqDirection) {
         boolean hit = false;

         gp.player.solidArea.x = gp.player.WorldX + gp.player.solidArea.x;
         gp.player.solidArea.y = gp.player.WorldY + gp.player.solidArea.y;
         eventRect[col][row].x = col*gp.titleSize + eventRect[col][row].x;
         eventRect[col][row].y = row*gp.titleSize + eventRect[col][row].y;

         if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false)  {
             if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                 hit = true;

                 previousEventX = gp.player.WorldX;
                 previousEventY = gp.player.WorldY;
             }
         }

         gp.player.solidArea.x = gp.player.solidAreaDefaultX;
         gp.player.solidArea.y = gp.player.solidAreaDefaultY;
         eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
         eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

         return hit;
     }
     public void teleport(int col, int row,int gameState) {

         gp.gameState = gameState;
         gp.ui.currentDialogue = "Teleport!";
         gp.player.WorldX = gp.titleSize*37;
         gp.player.WorldY = gp.titleSize*10;
     }
     public void damagePit(int col, int row, int gameState) {
         gp.playSE(6);
         gp.gameState = gameState;
         gp.ui.currentDialogue = "You fall into a pit!";
         gp.player.life -= 1;
//         eventRect[col][row].eventDone = true;
         canTouchEvent = false;
     }
     public void healingPool (int col, int row,int gameState) {
         if(gp.keyH.enterPressed == true) {
             gp.gameState = gameState;
             gp.player.attackCanceled = true;
             gp.playSE(2);
             gp.ui.currentDialogue = "You drink the water. \n Your life and mana have been recovered.";
             gp.player.life = gp.player.maxLife;
             gp.player.mana = gp.player.maxMana;
             gp.aSetter.setMonster();
         }
     }


}
