package object;

import main.Gamepanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, Gamepanel gp){
        int screenX = worldX - gp.player.WorldX + gp.player.screenX;
        int screenY = worldY - gp.player.WorldY + gp.player.screenY;

        if(worldX + gp.titleSize > gp.player.WorldX - gp.player.screenX  &&
                worldX - gp.titleSize < gp.player.WorldX + gp.player.screenX &&
                worldY + gp.titleSize > gp.player.WorldY - gp.player.screenY &&
                worldY - gp.titleSize < gp.player.WorldY + gp.player.screenY)
        {
            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }
    }
}
