package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.Gamepanel;


public class Obj_Heart extends SuperObject{
    Gamepanel gp;

    public Obj_Heart(Gamepanel gp) {
        this.gp = gp;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));

            image = uTool.scaleImage(image, gp.titleSize, gp.titleSize);
            image2 = uTool.scaleImage(image2, gp.titleSize, gp.titleSize);
            image3 = uTool.scaleImage(image3, gp.titleSize, gp.titleSize);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
