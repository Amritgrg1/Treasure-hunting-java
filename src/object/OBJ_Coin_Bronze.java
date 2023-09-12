package object;

import entity.Entity;
import main.Gamepanel;

public class OBJ_Coin_Bronze extends Entity {
    Gamepanel gp;
    public OBJ_Coin_Bronze(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Bronze Coin";
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.titleSize, gp.titleSize);
    }

    public boolean use(Entity entity) {
       gp.playSE(1);
       gp.ui.addMessage("Coin +" + value);
       gp.player.coin += value;
       return true;
    }
}
