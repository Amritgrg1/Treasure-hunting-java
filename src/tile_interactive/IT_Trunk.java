package tile_interactive;
import main.Gamepanel;

public class IT_Trunk extends InteractiveTile{
    Gamepanel gp;

    public IT_Trunk(Gamepanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.WorldX = gp.titleSize * col;
        this.WorldY = gp.titleSize * row;

        down1 = setup("/tile_interactive/trunk", gp.titleSize, gp.titleSize);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
