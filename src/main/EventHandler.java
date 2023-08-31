package main;

import java.awt.*;

public class EventHandler {
    Gamepanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
     public EventHandler(Gamepanel gp) {
         this.gp = gp;

         eventRect = new Rectangle();
         eventRect.x = 23;
         eventRect.y = 23;
         eventRect.width = 2;
         eventRect.height = 2;
         eventRectDefaultX = eventRect.x;
         eventRectDefaultY = eventRect.y;
     }

     public void checkEvent() {
//         if (hit(27, 16, "right") == true) {damagePit(gp.dialogueState);}
         if (hit(27, 16, "right") == true) {teleport(gp.dialogueState);}
         if(hit(23,12,"up") == true) {healingPool(gp.dialogueState);}
     }
     public boolean hit(int eventCol, int eventRow, String reqDirection) {
         boolean hit = false;

         gp.player.solidArea.x = gp.player.WorldX + gp.player.solidArea.x;
         gp.player.solidArea.y = gp.player.WorldY + gp.player.solidArea.y;
         eventRect.x = eventCol*gp.titleSize + eventRect.x;
         eventRect.y = eventRow*gp.titleSize + eventRect.y;

         if(gp.player.solidArea.intersects(eventRect))  {
             if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                 hit = true;
             }
         }

         gp.player.solidArea.x = gp.player.solidAreaDefaultX;
         gp.player.solidArea.y = gp.player.solidAreaDefaultY;
         eventRect.x = eventRectDefaultX;
         eventRect.y = eventRectDefaultY;

         return hit;
     }
     public void teleport(int gameState) {

         gp.gameState = gameState;
         gp.ui.currentDialogue = "Teleport!";
         gp.player.WorldX = gp.titleSize*37;
         gp.player.WorldY = gp.titleSize*10;
     }
     public void damagePit(int gameState) {
         gp.gameState = gameState;
         gp.ui.currentDialogue = "You fall into a pit!";
         gp.player.life -= 1;
     }
     public void healingPool (int gameState) {
         if(gp.keyH.enterPressed == true) {
             gp.gameState = gameState;
             gp.ui.currentDialogue = "You drink the water. \n Your life has been recovered.";
             gp.player.life = gp.player.maxLife;
         }
     }


}
