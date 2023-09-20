package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Boots extends Entity {
    public  static final String objName = "Boots";
    Gamepanel gp;
    //boots functions
    public Obj_Boots(Gamepanel gp) {
        super(gp);
        name = objName;
        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
    }
}