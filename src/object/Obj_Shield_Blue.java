package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Shield_Blue extends Entity {
    public Obj_Shield_Blue(Gamepanel gp) {
        super(gp);

        type = type_shield;
        name = "Blue Shield";
        down1 = setup("/objects/shield_blue", gp.titleSize, gp.titleSize);
        defenseValue = 1;
        description = "[" + name + "]\n A shiny blue shield.";
    }
}
