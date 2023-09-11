package main;

import java.awt.*;

public class EventHandler {
    Gamepanel gp;
    EventRect eventRect[][][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

     public EventHandler(Gamepanel gp) {
         this.gp = gp;

         eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

         int map =0;
         int col =0;
         int row =0;
         while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){

             eventRect[map][col][row] = new EventRect();
             eventRect[map][col][row].x = 23;
             eventRect[map][col][row].y = 23;
             eventRect[map][col][row].width = 2;
             eventRect[map][col][row].height = 2;
             eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
             eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

             col++;
             if(col == gp.maxWorldCol){
                 col = 0;
                 row++;

                 if (row == gp.maxWorldRow){
                     row = 0;
                     map++;
                 }
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
            if (hit(0, 27, 16, "right") == true) {damagePit(gp.dialogueState);}
            else if(hit(0, 23,12,"up") == true) {healingPool(gp.dialogueState);}
//            else if (hit(0, 10, 12, "any") == true) {teleport1(0,10,37);}
            else if (hit(0, 10, 39, "any") == true) {teleport(1,12,13);}
            else if (hit(1,12,13, "any") == true) {teleport(0, 10, 39);}

        }
     }
     public boolean hit(int map, int col, int row, String reqDirection) {
         boolean hit = false;

         if (map == gp.currentMap){
             gp.player.solidArea.x = gp.player.WorldX + gp.player.solidArea.x;
             gp.player.solidArea.y = gp.player.WorldY + gp.player.solidArea.y;
             eventRect[map][col][row].x = col*gp.titleSize + eventRect[map][col][row].x;
             eventRect[map][col][row].y = row*gp.titleSize + eventRect[map][col][row].y;

             if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false)  {
                 if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                     hit = true;

                     previousEventX = gp.player.WorldX;
                     previousEventY = gp.player.WorldY;
                 }
             }

             gp.player.solidArea.x = gp.player.solidAreaDefaultX;
             gp.player.solidArea.y = gp.player.solidAreaDefaultY;
             eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
             eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
         }
         return hit;
     }
//     public void teleport1(int map, int col, int row) {
//
////         gp.gameState = gameState;
//         gp.currentMap = map;
//         gp.ui.currentDialogue = "Teleport!";
//         gp.player.WorldX = gp.titleSize * col;
//         gp.player.WorldY = gp.titleSize * row;
//     }
     public void teleport(int map, int col, int row){
         gp.currentMap = map;
         gp.player.WorldX = gp.titleSize * col;
         gp.player.WorldY = gp.titleSize * row;
         previousEventX = gp.player.WorldX;
         previousEventY = gp.player.WorldY;
         canTouchEvent = false;
         gp.playSE(13);

     }
     public void damagePit(int gameState) {
         gp.playSE(6);
         gp.gameState = gameState;
         gp.ui.currentDialogue = "You fall into a pit!";
         gp.player.life -= 1;
//         eventRect[col][row].eventDone = true;
         canTouchEvent = false;
     }
     public void healingPool (int gameState) {
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
