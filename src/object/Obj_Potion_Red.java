package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Potion_Red extends Entity {

    Gamepanel gp;
    int value = 5;
    public Obj_Potion_Red(Gamepanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        down1 = setup("/objects/potion_red", gp.titleSize, gp.titleSize);
        description = "[Red Potion]\n Heals your life by" + value + ".";
    }

    public void use(Entity entity) {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
}

