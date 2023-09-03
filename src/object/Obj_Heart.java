package object;

import entity.Entity;
import main.Gamepanel;


public class Obj_Heart extends Entity {
    public Obj_Heart(Gamepanel gp) {
        super(gp);

        name = "Heart";
        image = setup("/objects/heart_full", gp.titleSize, gp.titleSize);
        image2 = setup("/objects/heart_half", gp.titleSize, gp.titleSize);
        image3 = setup("/objects/heart_blank", gp.titleSize, gp.titleSize);
    }
}