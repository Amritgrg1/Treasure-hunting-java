package monster;

import entity.Entity;
import main.Gamepanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import object.Obj_Heart;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    Gamepanel gp;
    public MON_GreenSlime(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = 1;
        maxLife = 20;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        up2 = setup("/monster/greenslime_down_2", gp.titleSize, gp.titleSize);
        down1 = setup("/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/monster/greenslime_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        left2 = setup("/monster/greenslime_down_2", gp.titleSize, gp.titleSize);
        right1 = setup("/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        right2 = setup("/monster/greenslime_down_2", gp.titleSize, gp.titleSize);
    }
    public void update(){
        super.update();

        int xDistance = Math.abs(WorldX - gp.player.WorldX);
        int yDistance = Math.abs(WorldY - gp.player.WorldY);
        int tileDistace = (xDistance + yDistance)/gp.titleSize;

        if (onPath == false && tileDistace < 5){
            int i = new Random().nextInt(100)+1;
            if (i > 50){
                onPath = true;
            }
        }
//        if (onPath == true && tileDistace > 20){
//            onPath = false;
//        }
    }
    public void setAction() {
//        actionLockCounter ++;

        if (onPath == true){

            int goalCol = (gp.player.WorldX + gp.player.solidArea.x)/gp.titleSize;
            int goalRow = (gp.player.WorldY + gp.player.solidArea.y)/gp.titleSize;

            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(200)+1;
            if(i > 197 && projectile.alive == false && shotAvailableCounter == 30) {
                projectile.set(WorldX, WorldY, direction, true, this);
//                gp.projectileList.add(projectile);

                // CHECK VACANCY
//                for (int ii = 0; ii < gp.projectile[1].length; ii++) {
//                    if (gp.projectile[gp.currentMap][ii] == null) {
//                        gp.projectile[gp.currentMap][ii] = projectile;
//                        break;
//                    }
//                }
                shotAvailableCounter = 0;
            }
        }
        else {
            actionLockCounter ++;

            if(actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100)+1; // pick up a number from 1 to 100

                if(i <= 25) {
                    direction = "up";
                }
                if(i > 25 && i <= 50) {
                    direction = "down";
                }
                if(i > 50 && i <= 75) {
                    direction = "left";
                }
                if(i > 75 && i <= 100) {
                    direction = "right";
                }

                actionLockCounter = 0;
            }
        }
    }

    public void damageReaction(){
        actionLockCounter = 0;
//        direction = gp.player.direction;
        onPath = true;
    }
    public void checkDrop() {
        // CAST A DIE
        int i = new Random().nextInt(100)+1;

        // SET THE MONSTER DROP
        if(i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 50 && i < 75) {
            dropItem(new Obj_Heart(gp));
        }
        if(i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
