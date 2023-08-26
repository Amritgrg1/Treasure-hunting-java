package tile;

import main.Gamepanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    Gamepanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(Gamepanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){

            setup(0,"grass", false);
            setup(1,"wall", true);
            setup(2,"water", true);
            setup(3,"earth", false);
            setup(4,"tree", true);
            setup(5,"sand", false);

    }
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/"+ imageName +".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.titleSize, gp.titleSize);
            tile[index].collision = collision;


        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }

            }
            br.close();

        }catch (Exception e){

        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - gp.player.WorldX + gp.player.screenX;
            int screenY = worldY - gp.player.WorldY + gp.player.screenY;

            if(worldX + gp.titleSize > gp.player.WorldX - gp.player.screenX  &&
               worldX - gp.titleSize < gp.player.WorldX + gp.player.screenX &&
               worldY + gp.titleSize > gp.player.WorldY - gp.player.screenY &&
               worldY - gp.titleSize < gp.player.WorldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
