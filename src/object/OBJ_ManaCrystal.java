package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_ManaCrystal extends Entity {
    Gamepanel gp;
    public OBJ_ManaCrystal(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        image = setup("/objects/manacrystal_full", gp.titleSize, gp.titleSize);
        image2 = setup("/objects/manacrystal_blank", gp.titleSize, gp.titleSize);
    }
}
