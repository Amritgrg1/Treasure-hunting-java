package main;

import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.*;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    Gamepanel gp;
    public AssetSetter(Gamepanel gp){
        this.gp = gp;
    }

    //Obejct set
    public void setObject(){
        int mapNum = 0;
        int i = 0;

        gp.Obj[mapNum][i] = new Obj_Axe(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*33;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*7;
        i++;

        gp.Obj[mapNum][i] = new Obj_Lantern(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*18;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*20;
        i++;

        gp.Obj[mapNum][i] = new Obj_Door(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*14;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*28;
        i++;

        gp.Obj[mapNum][i] = new Obj_Door(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*12;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*12;
        i++;

        gp.Obj[mapNum][i] = new Obj_Chest(gp, new Obj_Key(gp));
        gp.Obj[mapNum][i].WorldX = gp.tileSize*20;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*22;
        i++;

        gp.Obj[mapNum][i] = new Obj_Potion_Red(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*21;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*20;
        i++;

        gp.Obj[mapNum][i] = new Obj_Potion_Red(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*20;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*20;
        i++;

        gp.Obj[mapNum][i] = new Obj_Potion_Red(gp);
        gp.Obj[mapNum][i].WorldX = gp.tileSize*17;
        gp.Obj[mapNum][i].WorldY = gp.tileSize*21;
        i++;
    }
    public void setNPC() {
        int mapNum = 0;
        int i =0;
        //MAP 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].WorldX = gp.tileSize*21;
        gp.npc[mapNum][i].WorldY = gp.tileSize*21;
        i++;

        //MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].WorldX = gp.tileSize*12;
        gp.npc[mapNum][i].WorldY = gp.tileSize*7;
        i++;
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*21;
        gp.monster[mapNum][i].WorldY = gp.tileSize*38;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*13;
        gp.monster[mapNum][i].WorldY = gp.tileSize*33;
        i++;


        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*38;
        gp.monster[mapNum][i].WorldY = gp.tileSize*7;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.tileSize*38;
        gp.monster[mapNum][i].WorldY = gp.tileSize*42;
        i++;

//        mapNum =1;
//        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//        gp.monster[mapNum][i].WorldX = gp.titleSize*38;
//        gp.monster[mapNum][i].WorldY = gp.titleSize*42;
//        i++;

    }


    public void setInteractiveTile(){

        int mapNum = 0;
        int i = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 12); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 21); i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 12, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 41); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 40); i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 40); i++;

    }
}