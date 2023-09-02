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
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].WorldX = gp.titleSize*23;
        gp.monster[0].WorldY = gp.titleSize*36;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].WorldX = gp.titleSize*23;
        gp.monster[1].WorldY = gp.titleSize*37;

//        gp.monster[0] = new MON_GreenSlime(gp);
//        gp.monster[0].WorldX = gp.titleSize*11;
//        gp.monster[0].WorldY = gp.titleSize*10;
//
//        gp.monster[1] = new MON_GreenSlime(gp);
//        gp.monster[1].WorldX = gp.titleSize*11;
//        gp.monster[1].WorldY = gp.titleSize*11;
    }
}