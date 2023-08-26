package object;

import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends SuperObject{
    Gamepanel gp;
    public Obj_Chest(Gamepanel gp){
        this.gp = gp;
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            uTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
