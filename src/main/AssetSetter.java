package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.Obj_Door;

public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp){
        this.gp = gp;
    }

    //Obejct set
    public void setObject(){

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