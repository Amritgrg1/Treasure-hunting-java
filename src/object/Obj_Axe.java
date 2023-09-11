package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Axe extends Entity {
    public Obj_Axe(Gamepanel gp) {
        super(gp);

        type = type_axe;
        name = "Woodcutter's Axe";
        down1 = setup("/objects/axe", gp.titleSize, gp.titleSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Woodcutter's Axe]\n A bit rusty but still \n can cut some trees.";
        price = 10;
    }
}
