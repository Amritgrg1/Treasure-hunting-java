package main;

import entity.Entity;

import java.awt.*;

public class EventHandler{
    Gamepanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

     public EventHandler(Gamepanel gp) {
         this.gp = gp;

         eventMaster = new Entity(gp);

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
         setDialogue();
     }

     public void setDialogue() {
         eventMaster.dialogues[0][0] = "You fall into a pit!";
         eventMaster.dialogues[1][0] = "You drink the water. \n Your life and mana have been recovered.\n"
                 + "(The progress has been saved)";
         eventMaster.dialogues[1][1] = "Damn, this is good water.";
     }

     public void checkEvent() {

         //Check if player move one tile
         int xDistance = Math.abs(gp.player.WorldX - previousEventX);
         int yDistance = Math.abs(gp.player.WorldY - previousEventY);
         int distance = Math.max(xDistance, yDistance);
         if(distance > gp.tileSize){
             canTouchEvent = true;
         }

        if(canTouchEvent == true){
            if (hit(0, 27, 16, "right") == true) {damagePit(gp.dialogueState);}
            else if(hit(0, 23,12,"up") == true) {healingPool(gp.dialogueState);}
            else if (hit(0, 10, 39, "any") == true) {teleport(1,12,13);} // to the merchant's house
            else if (hit(1,12,13, "any") == true) {teleport(0, 10, 39);} // to outside
            else if (hit(1, 12, 9, "up")==true) {speak(gp.npc[1][0]);}
            else if (hit(0, 12, 9, "any") == true) {teleport1(2,9,41);} // to the dungeon
            else if (hit(2,9,41, "any") == true) {teleport1(0, 12, 9);} // to outside
            else if (hit(2,8,7, "any") == true) {teleport1(3, 26, 41);} // to B2
            else if (hit(3,26,41, "any") == true) {teleport1(2, 8, 7);} //to B1

        }
     }
     public boolean hit(int map, int col, int row, String reqDirection) {
         boolean hit = false;

         if (map == gp.currentMap){
             gp.player.solidArea.x = gp.player.WorldX + gp.player.solidArea.x;
             gp.player.solidArea.y = gp.player.WorldY + gp.player.solidArea.y;
             eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
             eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;

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
     public void damagePit(int gameState) {
         gp.playSE(6);
         gp.gameState = gameState;
         eventMaster.startDialogue(eventMaster,0);
         gp.player.life -= 1;
//         eventRect[col][row].eventDone = true;
         canTouchEvent = false;
     }
     public void healingPool (int gameState) {
         if(gp.keyH.enterPressed == true) {
             gp.gameState = gameState;
             gp.player.attackCanceled = true;
             gp.playSE(2);
             eventMaster.startDialogue(eventMaster,1);
             gp.player.life = gp.player.maxLife;
             gp.player.mana = gp.player.maxMana;
             gp.aSetter.setMonster();
             gp.saveLoad.save();
         }
     }
    public void teleport1(int map, int col, int row) {
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(13);
    }
    public void teleport(int map, int col, int row){
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(13);

    }
    public void speak(Entity entity){
         if (gp.keyH.enterPressed == true){
             gp.gameState = gp.dialogueState;
             gp.player.attackCanceled = true;
             entity.speak();
         }
    }

}
