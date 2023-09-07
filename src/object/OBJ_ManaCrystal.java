package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_ManaCrystal extends Entity {
    Gamepanel gp;
    public OBJ_ManaCrystal(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Mana Crystal";
        value = 1;
        down1 = setup("/objects/manacrystal_full", gp.titleSize, gp.titleSize);
        image = setup("/objects/manacrystal_full", gp.titleSize, gp.titleSize);
        image2 = setup("/objects/manacrystal_blank", gp.titleSize, gp.titleSize);
    }
    public void use(Entity entity) {
        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}
