package object;

import entity.Entity;
import entity.Projectile;
import main.Gamepanel;

public class Obj_Fireball extends Projectile {
    Gamepanel gp;
    public Obj_Fireball(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/fireball_up_1", gp.titleSize, gp.titleSize);
        up2 = setup("/projectile/fireball_up_2", gp.titleSize, gp.titleSize);
        down1 = setup("/projectile/fireball_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/projectile/fireball_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/projectile/fireball_left_1", gp.titleSize, gp.titleSize);
        left2 = setup("/projectile/fireball_left_2", gp.titleSize, gp.titleSize);
        right1 = setup("/projectile/fireball_right_1", gp.titleSize, gp.titleSize);
        right2 = setup("/projectile/fireball_right_2", gp.titleSize, gp.titleSize);

    }
    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if(user.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user) {
        user.mana -= useCost;
    }
}
