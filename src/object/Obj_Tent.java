package object;

import entity.Entity;
import main.Gamepanel;

import java.security.PublicKey;

public class Obj_Tent extends Entity {
    Gamepanel gp;
    public Obj_Tent(Gamepanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Tent";
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        description = "[Tent\nYou can sleep until\nnext morning.";
        price = 300;
        stackable = true;
    }
    public boolean use(Entity entity) {
        gp.gameState = gp.sleepState;
        gp.playSE(14);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
}
