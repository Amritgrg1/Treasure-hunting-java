package object;

import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Key extends SuperObject{
    Gamepanel gp;
    public Obj_Key(Gamepanel gp){
        this.gp = gp;
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            uTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
