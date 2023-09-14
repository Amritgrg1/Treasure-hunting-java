package environment;

import main.Gamepanel;

import java.awt.*;

public class EnvironmentManager {
    Gamepanel gp;
    Lighting lighting;

    public EnvironmentManager(Gamepanel gp) {
        this.gp = gp;
    }

    public void setup() {
        lighting = new Lighting(gp, 300); //576(Screen size) or 300(small size)
    }

    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }

}
