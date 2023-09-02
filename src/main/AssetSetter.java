package main;

import entity.NPC_OldMan;
import object.Obj_Door;

public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.Obj[0] = new Obj_Door(gp);
        gp.Obj[0].WorldX = gp.titleSize*21;
        gp.Obj[0].WorldY = gp.titleSize*22;

        gp.Obj[1] = new Obj_Door(gp);
        gp.Obj[1].WorldX = gp.titleSize*23;
        gp.Obj[1].WorldY = gp.titleSize*25;
    }
    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].WorldX = gp.titleSize*21;
        gp.npc[0].WorldY = gp.titleSize*21;

        gp.npc[1] = new NPC_OldMan(gp);
        gp.npc[1].WorldX = gp.titleSize*11;
        gp.npc[1].WorldY = gp.titleSize*21;

        gp.npc[2] = new NPC_OldMan(gp);
        gp.npc[2].WorldX = gp.titleSize*31;
        gp.npc[2].WorldY = gp.titleSize*21;

        gp.npc[3] = new NPC_OldMan(gp);
        gp.npc[3].WorldX = gp.titleSize*21;
        gp.npc[3].WorldY = gp.titleSize*11;

        gp.npc[4] = new NPC_OldMan(gp);
        gp.npc[4].WorldX = gp.titleSize*21;
        gp.npc[4].WorldY = gp.titleSize*31;
    }
}