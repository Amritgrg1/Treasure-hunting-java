package main;

import object.Obj_Chest;
import object.Obj_Door;
import object.Obj_Key;

public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.Obj[0] = new Obj_Key();
        gp.Obj[0].worldX = 23 * gp.titleSize;
        gp.Obj[0].worldY = 7 * gp.titleSize;

        gp.Obj[1] = new Obj_Key();
        gp.Obj[1].worldX = 23 * gp.titleSize;
        gp.Obj[1].worldY = 40 * gp.titleSize;

        gp.Obj[2] = new Obj_Key();
        gp.Obj[2].worldX = 38 * gp.titleSize;
        gp.Obj[2].worldY = 8 * gp.titleSize;

        gp.Obj[3] = new Obj_Door();
        gp.Obj[3].worldX = 10 * gp.titleSize;
        gp.Obj[3].worldY = 11 * gp.titleSize;

        gp.Obj[4] = new Obj_Door();
        gp.Obj[4].worldX = 8 * gp.titleSize;
        gp.Obj[4].worldY = 28 * gp.titleSize;

        gp.Obj[5] = new Obj_Door();
        gp.Obj[5].worldX = 12 * gp.titleSize;
        gp.Obj[5].worldY = 22 * gp.titleSize;

        gp.Obj[6] = new Obj_Chest();
        gp.Obj[6].worldX = 10 * gp.titleSize;
        gp.Obj[6].worldY = 7 * gp.titleSize;
    }
}
