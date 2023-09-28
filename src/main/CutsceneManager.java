package main;

import entity.PlayerDummy;
import monster.MON_SkeletonLord;
import object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {
    Gamepanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    //Scene NUmber
    public final int NA = 0;
    public final int skeletonLord = 1;

    public CutsceneManager(Gamepanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        switch (sceneNum) {
            case skeletonLord: scene_skeletonLord(); break;
        }
    }
    public void scene_skeletonLord() {
        if (scenePhase == 0) {
            gp.bossBattleOn = true;

            //shut the iron door
            for (int i = 0; i < gp.Obj[1].length; i++) {
                if (gp.Obj[gp.currentMap][i] == null) {
                    gp.Obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.Obj[gp.currentMap][i].WorldX = gp.tileSize * 25;
                    gp.Obj[gp.currentMap][i].WorldY = gp.tileSize * 28;
                    gp.Obj[gp.currentMap][i].temp = true;
                    gp.playSE(21);
                    break;
                }
            }
            // Search a vacant slot for Dummy
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].WorldX = gp.player.WorldX;
                    gp.npc[gp.currentMap][i].WorldY = gp.player.WorldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;

            scenePhase++;
        }
        if (scenePhase == 1) {
            gp.player.WorldY -= 2;

            if (gp.player.WorldY < gp.tileSize * 16) {
                scenePhase++;
            }
        }

        if (scenePhase == 2) {
            //Search the BOSS
            for (int i = 0; i < gp.monster[1].length; i++) {
                if (gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName) {
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }

        if (scenePhase == 3) {
            //BOSS speaks
            gp.ui.drawDialogueScreen();
        }

        if (scenePhase == 4) {
            //Return to the player

            //Search thr Dummy
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
                    //Restore the player position
                    gp.player.WorldX = gp.npc[gp.currentMap][i].WorldX;
                    gp.player.WorldY = gp.npc[gp.currentMap][i].WorldY;

                    // Delete the Dummy
                    gp.npc[gp.currentMap][i] = null;
                    break;

                }
            }

            //Start drawing the Dummy
            gp.player.drawing = true;

            //Reset
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            //Change the music
            gp.stopMusic();
            gp.playMusic(22);
        }
    }
}