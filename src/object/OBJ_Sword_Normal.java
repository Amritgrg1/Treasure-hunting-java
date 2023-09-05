package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(Gamepanel gp){
        super(gp);

        name = "Mormal Sword";
        down1 = setup("/objects/sword_normal", gp.titleSize, gp.titleSize);
        attackValue = 4;
        description = "[" + name + "]\nAn old sword.";

    }
}
