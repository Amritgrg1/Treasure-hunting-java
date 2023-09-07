package object;

import entity.Entity;
import entity.Projectile;
import main.Gamepanel;

public class OBJ_Rock extends Projectile {
    Gamepanel gp;
    public OBJ_Rock(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        up2 = setup("/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        down1 = setup("/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        left1 = setup("/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        left2 = setup("/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        right1 = setup("/projectile/rock_down_1", gp.titleSize, gp.titleSize);
        right2 = setup("/projectile/rock_down_1", gp.titleSize, gp.titleSize);

    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if(user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }
}
