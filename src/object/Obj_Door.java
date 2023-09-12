package object;

import entity.Entity;
import main.Gamepanel;

public class Obj_Door extends Entity {
    Gamepanel gp;
    public Obj_Door(Gamepanel gp){
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = "Door";
        down1 = setup("/objects/door", gp.titleSize, gp.titleSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open this.";
    }
}