package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Key extends Entity {

    public Obj_Key(Gamepanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/objects/Key");

    }
}
