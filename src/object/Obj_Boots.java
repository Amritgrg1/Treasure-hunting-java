package object;

import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;
public class Obj_Boots extends SuperObject{
    Gamepanel gp;
    //boots functions
    public Obj_Boots(Gamepanel gp) {
        this.gp = gp;
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            uTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
