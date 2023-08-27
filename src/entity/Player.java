package entity;

import main.Gamepanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;

    public Player(Gamepanel gp, KeyHandler keyH){
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.titleSize/2);
        screenY = gp.screenHeight/2 - (gp.titleSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
         WorldX = gp.titleSize * 23;
         WorldY = gp.titleSize * 21;
         speed = 4;
         direction = "down";
    }
    public void getPlayerImage(){

        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
        {
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if (keyH.downPressed == true){
                direction = "down";
            }
            else if (keyH.leftPressed == true){
                direction = "left";
            }
            else if (keyH.rightPressed == true){
                direction = "right";
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.CheckTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //if collision is false, player can move
            if(collisionOn == false){
                switch (direction){
                    case "up":
                        WorldY -=speed;
                        break;
                    case "down":
                        WorldY += speed;
                        break;
                    case "left":
                        WorldX-=speed;
                        break;
                    case "right":
                        WorldX+= speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int i){
        if (i != 999)
        {

        }
    }
    public void interactNPC(int i) {
        if (i != 999)
        {
            System.out.println("hitting");
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum ==2){
                    image = down2;
                }

                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }

                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }

                break;
        }

        g2.drawImage(image,screenX,screenY,null);
    }
}
