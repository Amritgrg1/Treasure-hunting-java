package main;

import entity.Entity;

public class CollisionChecker {
    Gamepanel gp;
    public CollisionChecker(Gamepanel gp){
        this.gp = gp;
    }

    public void CheckTile(Entity entity){
        int entityLeftWorldX = entity.WorldX + entity.solidArea.x;
        int entityRightWorldX = entity.WorldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.WorldY + entity.solidArea.y;
        int entityBottomWorldY = entity.WorldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.titleSize;
        int entityRightCol = entityRightWorldX/gp.titleSize;
        int entityTopRow = entityTopWorldY/gp.titleSize;
        int entityBottomRow = entityBottomWorldY/gp.titleSize;

        int tileNum1, tileNum2;
        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.titleSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.titleSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.titleSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX - entity.speed)/gp.titleSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;


        }
    }
    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.Obj.length; i++){
            if (gp.Obj[i] != null) {
                // Get entity's solid area position
                entity.solidArea.x = entity.WorldX + entity.solidArea.x;
                entity.solidArea.y = entity.WorldY + entity.solidArea.y;

                // Get the object's solid area position
                gp.Obj[i].solidArea.x = gp.Obj[i].worldX + gp.Obj[i].solidArea.x;
                gp.Obj[i].solidArea.y = gp.Obj[i].worldY + gp.Obj[i].solidArea.y;
                switch (entity.direction)
                {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.Obj[i].solidArea)){
                            if (gp.Obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.Obj[i].solidArea)){
                            if (gp.Obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.Obj[i].solidArea)){
                            if (gp.Obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.Obj[i].solidArea)){
                            if (gp.Obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.Obj[i].solidArea.x = gp.Obj[i].solidAreaDefaultX;
                gp.Obj[i].solidArea.y = gp.Obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
