package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Chest extends Entity {
    public Obj_Chest(Gamepanel gp){
        super(gp);
        name = "Chest";
        down1 = setup("/objects/chest");
    }
}