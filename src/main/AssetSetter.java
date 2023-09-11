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
//        gp.Obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//        gp.Obj[mapNum][i].WorldX = gp.titleSize*25;
//        gp.Obj[mapNum][i].WorldY = gp.titleSize*23;
//        i++;

        gp.Obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.Obj[mapNum][i].WorldX = gp.titleSize*21;
        gp.Obj[mapNum][i].WorldY = gp.titleSize*19;
        i++;

        gp.Obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.Obj[mapNum][i].WorldX = gp.titleSize*26;
        gp.Obj[mapNum][i].WorldY = gp.titleSize*21;
        i++;

        gp.Obj[mapNum][i] = new Obj_Axe(gp);
        gp.Obj[mapNum][i].WorldX = gp.titleSize*33;
        gp.Obj[mapNum][i].WorldY = gp.titleSize*7;
        i++;

//        gp.Obj[mapNum][i] = new Obj_Shield_Blue(gp);
//        gp.Obj[mapNum][i].WorldX = gp.titleSize*35;
//        gp.Obj[mapNum][i].WorldY = gp.titleSize*7;
//        i++;

        gp.Obj[mapNum][i] = new Obj_Potion_Red(gp);
        gp.Obj[mapNum][i].WorldX = gp.titleSize*30;
        gp.Obj[mapNum][i].WorldY = gp.titleSize*11;
        i++;

        gp.Obj[mapNum][i] = new Obj_Heart(gp);
        gp.Obj[mapNum][i].WorldX = gp.titleSize*15;
        gp.Obj[mapNum][i].WorldY = gp.titleSize*30;
        i++;

        gp.Obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.Obj[mapNum][i].WorldX = gp.titleSize*35;
        gp.Obj[mapNum][i].WorldY = gp.titleSize*38;
        i++;

    }
    public void setNPC() {
        int mapNum = 0;
        int i =0;
        //MAP 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].WorldX = gp.titleSize*21;
        gp.npc[mapNum][i].WorldY = gp.titleSize*21;
        i++;

        //MAP 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].WorldX = gp.titleSize*12;
        gp.npc[mapNum][i].WorldY = gp.titleSize*7;
        i++;
    }

    public void setMonster() {
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.titleSize*21;
        gp.monster[mapNum][i].WorldY = gp.titleSize*38;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.titleSize*13;
        gp.monster[mapNum][i].WorldY = gp.titleSize*33;
        i++;


        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.titleSize*38;
        gp.monster[mapNum][i].WorldY = gp.titleSize*7;
        i++;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].WorldX = gp.titleSize*38;
        gp.monster[mapNum][i].WorldY = gp.titleSize*42;
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