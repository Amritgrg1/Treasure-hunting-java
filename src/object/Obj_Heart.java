package object;

import entity.Entity;
import main.Gamepanel;


public class Obj_Heart extends Entity {
    Gamepanel gp;
    public Obj_Heart(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Heart";
        value = 2;
        down1 = setup("/objects/heart_full", gp.titleSize, gp.titleSize);
        image = setup("/objects/heart_full", gp.titleSize, gp.titleSize);
        image2 = setup("/objects/heart_half", gp.titleSize, gp.titleSize);
        image3 = setup("/objects/heart_blank", gp.titleSize, gp.titleSize);
    }
    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
    }
}