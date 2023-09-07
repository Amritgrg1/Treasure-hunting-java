package entity;

import main.Gamepanel;
import main.KeyHandler;
import main.UtilityTool;
import object.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity{
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(Gamepanel gp, KeyHandler keyH){
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.titleSize/2);
        screenY = gp.screenHeight/2 - (gp.titleSize/2);

        //Solid Area
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }
    public void setDefaultValues(){
         WorldX = gp.titleSize * 23;
         WorldY = gp.titleSize * 21;
//        WorldX = gp.titleSize * 10;
//        WorldY = gp.titleSize * 13;
         speed = 4;
         direction = "down";

         //Player status
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1;  //more strength more damage
        dexterity = 1; //more dexterity less damage taken
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new Obj_Fireball(gp);
//        projectile = new OBJ_Rock(gp);
        attack = getAttack();   //total attack value is decided by strength and weapon
        defense = getDefense();  //total defense value is decided by dexterity and shield

    }
    public void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new Obj_Key(gp));
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage(){

        up1 = setup("/player/boy_up_1", gp.titleSize, gp.titleSize);
        up2 = setup("/player/boy_up_2", gp.titleSize, gp.titleSize);
        down1 = setup("/player/boy_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/player/boy_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/player/boy_left_1", gp.titleSize, gp.titleSize);
        left2 = setup("/player/boy_left_2", gp.titleSize, gp.titleSize);
        right1 = setup("/player/boy_right_1", gp.titleSize, gp.titleSize);
        right2 = setup("/player/boy_right_2", gp.titleSize, gp.titleSize);
    }

    public void getPlayerAttackImage(){
        if (currentWeapon.type == type_sword){
            attackUp1 = setup("/player/boy_attack_up_1", gp.titleSize, gp.titleSize*2);
            attackUp2 = setup("/player/boy_attack_up_2", gp.titleSize, gp.titleSize*2);
            attackDown1 = setup("/player/boy_attack_down_1", gp.titleSize, gp.titleSize*2);
            attackDown2 = setup("/player/boy_attack_down_2", gp.titleSize, gp.titleSize*2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.titleSize*2, gp.titleSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.titleSize*2, gp.titleSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.titleSize*2, gp.titleSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.titleSize*2, gp.titleSize);
        }
        if (currentWeapon.type == type_axe){
            attackUp1 = setup("/player/boy_axe_up_1", gp.titleSize, gp.titleSize*2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.titleSize, gp.titleSize*2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.titleSize, gp.titleSize*2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.titleSize, gp.titleSize*2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.titleSize*2, gp.titleSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.titleSize*2, gp.titleSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.titleSize*2, gp.titleSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.titleSize*2, gp.titleSize);
        }
    }

    public void update(){
        if (attacking == true){
            attacking();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true)
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

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);


            // CHECK EVENT
            gp.eHandler.checkEvent();


            //if collision is false, player can move
            if(collisionOn == false && keyH.enterPressed == false){
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

            if (keyH.enterPressed == true && attackCanceled == false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;

            }
            attackCanceled = false;

            gp.keyH.enterPressed = false;

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
        else {
            standCounter++;
            if(standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
            }
        }

        if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30
                && projectile.haveResource(this) == true) {
            //Set Default Coordinates, Direction & User
            projectile.set(WorldX, WorldY, direction, true, this);

            // SUBTRACT THE COST (MANA, AMMO ETC.)
            projectile.subtractResource(this);

            //Add it to list
            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;

            gp.playSE(10);
        }

        // THIS needs to be outside of key if statement!
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }
    public void attacking(){
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <=25){
            spriteNum = 2;
            //Save the current player position
            int currentWorldX = WorldX;
            int currentWorldY = WorldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/Y for the attacking
            switch (direction){
                case "up": WorldY -= attackArea.height; break;
                case "down": WorldX += attackArea.width; break;
                case "left": WorldX -= attackArea.width; break;
                case "right": WorldX += attackArea.width; break;
            }

            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //check monster collision with the updated worldX/Y and solidArea
            int mosterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(mosterIndex, attack);

            WorldX = currentWorldX;
            WorldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i){
        if (i != 999)
        {
            String text;

            if (inventory.size() != maxInventorySize) {
                inventory.add(gp.Obj[i]);
                gp.playSE(1);
                text = "Got a " + gp.Obj[i].name + "!";
            } else {
                text = "You cannot carry any more!";
            }
            gp.ui.addMessage(text);
            gp.Obj[i] =null;
        }
    }
    public void interactNPC(int i) {
        if(gp.keyH.enterPressed == true) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if(i != 999) {

            if(invincible == false && gp.monster[i].dying == false) {
                gp.playSE(6);

                int damage = gp.monster[i].attack - defense;
                if(damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i, int attack){
        if (i != 999){
            if (gp.monster[i].invincible == false){
                gp.playSE(5);

                int damage = attack - gp.monster[i].defense;
                if(damage < 0) {
                    damage = 0;
                }
                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void checkLevelUp() {
        if(exp > nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\n"
                    + "You feel stronger!";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_consumable) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction){
            case "up":
                if (attacking == false){
                    if (spriteNum == 1){image = up1;}
                    if (spriteNum == 2){image = up2;}
                }
                if (attacking == true){
                    tempScreenY = screenY - gp.titleSize;
                    if (spriteNum == 1){image = attackUp1;}
                    if (spriteNum == 2){image = attackUp2;}
                }
                break;
            case "down":
                if (attacking == false){
                    if (spriteNum == 1){image = down1;}
                    if (spriteNum ==2){image = down2;}
                }
                if (attacking == true){
                    if (spriteNum == 1){image = attackDown1;}
                    if (spriteNum == 2){image = attackDown2;}
                }

                break;
            case "left":
                if (attacking == false){
                    if (spriteNum == 1){image = left1;}
                    if (spriteNum == 2){image = left2;}
                }
                if (attacking == true){
                    tempScreenX = screenX - gp.titleSize;
                    if (spriteNum == 1){image = attackLeft1;}
                    if (spriteNum == 2){image = attackLeft2;}
                }

                break;
            case "right":
                if (attacking == false){
                    if (spriteNum == 1){image = right1;}
                    if (spriteNum == 2){image = right2;}
                }
                if (attacking == true){
                    if (spriteNum == 1){image = attackRight1;}
                    if (spriteNum == 2){image = attackRight2;}
                }

                break;
        }

        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

        // DEBUG
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
}
