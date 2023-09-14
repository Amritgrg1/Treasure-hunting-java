package environment;

import main.Gamepanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    Gamepanel gp;
    BufferedImage darknessFilter;

    public Lighting(Gamepanel gp, int circleSize) {
        //Create Buffered Image
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();

        //Create Screensized rectangle Area
        Area screenArea =  new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));

        //Center x & y of light circle
        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;

        //top left x & y of light circle
        double x = centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        //Create Light Circle Shape
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        //Create Light Circle Area
        Area lightArea = new Area(circleShape);

        //Subtract Light Circle from Screen rectangle
        screenArea.subtract(lightArea);

        //Create Gradation effect within Light Cirle
        Color color[] = new Color[12];
        float fraction[] = new float[12];

        color[0] = new Color(0,0,0,0.1f);
        color[1] = new Color(0,0,0,0.42f);
        color[2] = new Color(0,0,0,0.52f);
        color[3] = new Color(0,0,0,0.61f);
        color[4] = new Color(0,0,0,0.69f);
        color[5] = new Color(0,0,0,0.76f);
        color[6] = new Color(0,0,0,0.82f);
        color[7] = new Color(0,0,0,0.87f);
        color[8] = new Color(0,0,0,0.91f);
        color[9] = new Color(0,0,0,0.94f);
        color[10] = new Color(0,0,0,0.96f);
        color[11] = new Color(0,0,0,0.98f);


        fraction[0] = 0f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;

        //Create gradation paint seeting for Light Circle
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);

        //Set Gradation data on g2
        g2.setPaint(gPaint);

        //Draw Light circle
        g2.fill(lightArea);

//        Set coloe black to draw rectangle
//        g2.setColor(new Color(0,0,0,0.95f));

        //Draw screen rectangle without Light Circle Area
        g2.fill(screenArea);

        g2.dispose();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}