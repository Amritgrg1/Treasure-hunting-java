package main;

import entity.NPC_OldMan;

public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp){
        this.gp = gp;
    }

    public void setObject(){

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
    }
}
