package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Boots extends Entity {
    Gamepanel gp;
    //boots functions
    public Obj_Boots(Gamepanel gp) {
        super(gp);
        name = "Boots";
        down1 = setup("objects/boots", gp.tileSize, gp.tileSize);
    }
}