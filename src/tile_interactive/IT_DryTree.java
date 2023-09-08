package tile_interactive;

import entity.Entity;
import main.Gamepanel;

public class IT_DryTree extends InteractiveTile{
    Gamepanel gp;

    public IT_DryTree(Gamepanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.WorldX = gp.titleSize * col;
        this.WorldY = gp.titleSize * row;

        down1 = setup("/tile_interactive/drytree", gp.titleSize, gp.titleSize);
        destructible = true;
        life = 3;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;

        if (entity.currentWeapon.type == type_axe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSE() {
        gp.playSE(11);
    }

    public InteractiveTile gotDestroyedForm() {
        InteractiveTile tile = new IT_Trunk(gp, WorldX/gp.titleSize, WorldY/gp.titleSize);
        return tile;
    }
}
