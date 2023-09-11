package entity;

import main.Gamepanel;
import object.*;

import java.awt.*;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(Gamepanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height =32;

        getImage();
        setDialogue();
        setItems();
    }
    public void getImage(){

        up1 = setup("/npc/merchant_down_1", gp.titleSize, gp.titleSize);
        up2 = setup("/npc/merchant_down_2", gp.titleSize, gp.titleSize);
        down1 = setup("/npc/merchant_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/npc/merchant_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/npc/merchant_down_1", gp.titleSize, gp.titleSize);
        left2 = setup("/npc/merchant_down_2", gp.titleSize, gp.titleSize);
        right1 = setup("/npc/merchant_down_1", gp.titleSize, gp.titleSize);
        right2 = setup("/npc/merchant_down_2", gp.titleSize, gp.titleSize);

    }
    public void setDialogue() {
        dialogues[0] = "He he, so you found me. \nI have some good stuff. \nDo you want to trade??";
//        dialogues[1] = "Do you want to trade??";
    }

    public void setItems(){
        inventory.add(new Obj_Potion_Red(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new Obj_Axe(gp));
        inventory.add(new Obj_Shield_Blue(gp));
        inventory.add(new Obj_Key(gp));
    }

    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }


}
