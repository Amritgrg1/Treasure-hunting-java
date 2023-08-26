package object;

import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends SuperObject{
    Gamepanel gp;
    public Obj_Door(Gamepanel gp){
        this.gp = gp;
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
            uTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
