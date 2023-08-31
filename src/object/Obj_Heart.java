package object;

import entity.Entity;
import main.Gamepanel;


public class Obj_Heart extends Entity {
    public Obj_Heart(Gamepanel gp) {
        super(gp);

        name = "Heart";
        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");
    }
}
