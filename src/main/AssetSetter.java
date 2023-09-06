package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.*;

public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp){
        this.gp = gp;
    }

    //Obejct set
    public void setObject(){
        int i = 0;
        gp.Obj[i] = new Obj_Key(gp);
        gp.Obj[i].WorldX = gp.titleSize*25;
        gp.Obj[i].WorldY = gp.titleSize*23;
        i++;

        gp.Obj[i] = new Obj_Key(gp);
        gp.Obj[i].WorldX = gp.titleSize*21;
        gp.Obj[i].WorldY = gp.titleSize*19;
        i++;

        gp.Obj[i] = new Obj_Key(gp);
        gp.Obj[i].WorldX = gp.titleSize*26;
        gp.Obj[i].WorldY = gp.titleSize*21;
        i++;

        gp.Obj[i] = new Obj_Axe(gp);
        gp.Obj[i].WorldX = gp.titleSize*33;
        gp.Obj[i].WorldY = gp.titleSize*21;
        i++;

        gp.Obj[i] = new Obj_Shield_Blue(gp);
        gp.Obj[i].WorldX = gp.titleSize*35;
        gp.Obj[i].WorldY = gp.titleSize*21;
        i++;

        gp.Obj[i] = new Obj_Potion_Red(gp);
        gp.Obj[i].WorldX = gp.titleSize*22;
        gp.Obj[i].WorldY = gp.titleSize*27;
        i++;

    }
    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].WorldX = gp.titleSize*21;
        gp.npc[0].WorldY = gp.titleSize*21;

//        gp.npc[0] = new NPC_OldMan(gp);
//        gp.npc[0].WorldX = gp.titleSize*9;
//        gp.npc[0].WorldY = gp.titleSize*10;
    }
    public void setMonster() {
        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].WorldX = gp.titleSize*21;
        gp.monster[i].WorldY = gp.titleSize*38;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].WorldX = gp.titleSize*23;
        gp.monster[i].WorldY = gp.titleSize*42;
        i++;


        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].WorldX = gp.titleSize*24;
        gp.monster[i].WorldY = gp.titleSize*37;
        i++;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].WorldX = gp.titleSize*38;
        gp.monster[i].WorldY = gp.titleSize*42;
        i++;

    }
}