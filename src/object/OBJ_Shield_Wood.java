package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(Gamepanel gp) {
        super(gp);

        type = type_shield;
        name = "Wood Shield";
        down1 = setup("/objects/shield_wood", gp.titleSize, gp.titleSize);
        defenseValue = 1;
        description = "[" + name + "]\nmade by wood.";
    }
}