package entity;

import main.Gamepanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    Gamepanel gp;


    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];

    //STATE
    public int WorldX,WorldY;
    public String direction = "down";
    public int spriteNum =  1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;


    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;





    //CHARACTER ATTRIBUTES
    public int type; // 0 = player, 1 = npc, 2 = monster
    public String name;
    public int speed;
    public int maxLife;
    public int life;

    public Entity(Gamepanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void speak() {
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right" :
                direction = "left";
                break;

        }
    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.CheckTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                // we can give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

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
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = WorldX - gp.player.WorldX + gp.player.screenX;
        int screenY = WorldY - gp.player.WorldY + gp.player.screenY;

        if (WorldX + gp.titleSize > gp.player.WorldX - gp.player.screenX &&
                WorldX - gp.titleSize < gp.player.WorldX + gp.player.screenX &&
                WorldY + gp.titleSize > gp.player.WorldY - gp.player.screenY &&
                WorldY - gp.titleSize < gp.player.WorldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }

                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }

                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }

                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }
    }
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream( imagePath +".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return image;
    }
}
