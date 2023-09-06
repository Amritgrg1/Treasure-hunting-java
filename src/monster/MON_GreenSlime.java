package monster;

import entity.Entity;
import main.Gamepanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    Gamepanel gp;
    public MON_GreenSlime(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Green Slime";
        speed = 1;
        maxLife = 20;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;

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
    public void setAction() {
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

    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
