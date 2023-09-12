package entity;

import main.Gamepanel;
import main.UtilityTool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Entity {
    Gamepanel gp;


    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
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
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;


    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;



    //CHARACTER ATTRIBUTES
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int  defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower = 0;

    //Type
    public int type; // 0 = player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;


    public Entity(Gamepanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void damageReaction(){}
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

    public void use(Entity entity) {}
    public void checkDrop() {}
    public void dropItem(Entity droppedItem) {
        for (int i = 0; i < gp.Obj[1].length; i++) {
            if(gp.Obj[gp.currentMap][i] == null) {
                gp.Obj[gp.currentMap][i] = droppedItem;
                gp.Obj[gp.currentMap][i].WorldX = WorldX; // the dead monster's WorldX
                gp.Obj[gp.currentMap][i].WorldY = WorldY;
                break;
            }
        }
    }

    public Color getParticleColor() {
        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int size = 0; //^ pixels
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision(){
        //Collision Checker
        collisionOn = false;
        gp.cChecker.CheckTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true) {
            damagePlayer(attack);
        }
    }
    public void update() {

        if (knockBack == true) {

            checkCollision();

            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (collisionOn == false) {
                switch (gp.player.direction) {
                    case "up": WorldY -= speed; break;
                    case "down": WorldY += speed; break;
                    case "left": WorldX -= speed; break;
                    case "right": WorldX += speed; break;
                }

            }

            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else {
            setAction();
            checkCollision();

            //if collision is false, player can move
            if(collisionOn == false){
                switch (direction){
                    case "up": WorldY -= speed; break;
                    case "down": WorldY += speed; break;
                    case "left": WorldX -= speed; break;
                    case "right": WorldX += speed; break;
                }
            }

        }

        spriteCounter++;
        if(spriteCounter > 24){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }
    public void damagePlayer(int attack) {
        if (gp.player.invincible == false) {
            // we can give damage
            gp.playSE(6);

            int damage = attack - gp.player.defense;
            if(damage < 0) {
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
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
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    break;
            }

            //Monster Health Bar
            if (type == 2 && hpBarOn == true){
                double oneScale = (double)gp.titleSize/maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1,screenY - 16, gp.titleSize+2, 12 );

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX,screenY - 15, (int)hpBarValue, 10 );

                hpBarCounter++;
                if (hpBarCounter > 600){
                    hpBarCounter =0;
                    hpBarOn = false;
                }
            }

            if(invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying == true){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1f);

        }
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;

        if (dyingCounter <= i){changeAlpha(g2, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2, 1f);}
        if (dyingCounter > i*2 && dyingCounter <= i*3){changeAlpha(g2, 0f);}
        if (dyingCounter > i*3 && dyingCounter <= i*4){changeAlpha(g2, 1f);}
        if (dyingCounter > i*4 && dyingCounter <= i*5){changeAlpha(g2, 0f);}
        if (dyingCounter > i*5 && dyingCounter <= i*6){changeAlpha(g2, 1f);}
        if (dyingCounter > i*6 && dyingCounter <= i*7){changeAlpha(g2, 0f);}
        if (dyingCounter > i*7 && dyingCounter <= i*8){changeAlpha(g2, 1f);}
        if (dyingCounter > i*8){
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
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
    public void searchPath(int goalCol, int goalRow){
        int startCol = (WorldX + solidArea.x)/gp.titleSize;
        int startRow = (WorldY + solidArea.y)/gp.titleSize;

        gp.pFinder.setNode(startCol, startRow, goalCol, goalRow, this);
        if (gp.pFinder.search() == true){
            //Next WorldX & WorldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.titleSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.titleSize;
            //Entity's solidArea position
            int enLeftX = WorldX + solidArea.x;
            int enRightX = WorldX + solidArea.x + solidArea.width;
            int enTopY = WorldY + solidArea.y;
            int enBottomY = WorldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.titleSize){
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX <= nextX && enRightX < nextX + gp.titleSize){
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.titleSize) {
                //left or right
                if (enLeftX > nextX){
                    direction = "left";
                }
                if (enLeftX < nextX){
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                //up or left
                direction = "up";
                checkCollision();
                if (collision == true){
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                //up or right
                direction = "up";
                checkCollision();
                if (collision == true){
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX){
                //down or left
                direction = "down";
                checkCollision();
                if (collision == true){
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX){
                //down or right
                direction = "down";
                checkCollision();
                if (collision == true){
                    direction = "right";
                }
            }
//            //If reaches goal, stop search
//            int nextCol = gp.pFinder.pathList.get(0).col;
//            int nextRow = gp.pFinder.pathList.get(0).row;
//            if (nextCol == goalCol && nextRow == goalRow){
//                onPath = false;
//            }
        }
    }
}
