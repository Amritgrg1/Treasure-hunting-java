package object;

import entity.Entity;
import main.Gamepanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends Entity {
    Gamepanel gp;
    public Obj_Door(Gamepanel gp){
        super(gp);
        name = "Door";
        down1 = setup("/objects/door");
        collision = true;
    }
}
