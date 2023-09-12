package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(Gamepanel gp){
        super(gp);

        type = type_sword;
        name = "Mormal Sword";
        down1 = setup("/objects/sword_normal", gp.titleSize, gp.titleSize);
        attackValue = 4;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
        price = 10;
        knockBackPower = 2;
    }
}
