package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Key extends Entity {
    Gamepanel gp;

    public Obj_Key(Gamepanel gp){
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Key";
        down1 = setup("/objects/Key", gp.titleSize, gp.titleSize);
        description = "[" + name + "]\nIt opens a door.";
        price = 100;
    }
    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.Obj, "Door");

        if (objIndex != 999) {
            gp.ui.currentDialogue = "You use the " + name + " and open the door";
            gp.playSE(3);
            gp.Obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            gp.ui.currentDialogue = "What are you doing?";
            return false;
        }
    }
}