package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprite.Sprite;
/**
 * class Green3Background - background of green3 level.
 * @author noy shriki.
 */
public class Green3Background implements Sprite {
    public static final int BORDER_THICK = 30;
    public static final int WIDTH = 800;
    /**
     * draw the background.
     * @param d -  DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(550, BORDER_THICK - 5, "Level Name: Green level ", 20);
        // green background
        d.setColor(Color.green);
        d.fillRectangle(BORDER_THICK, 30 + 25, WIDTH - BORDER_THICK * 2,  WIDTH - BORDER_THICK - 25);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(60 + 20, 570, 80, 800 - 570);
        d.fillRectangle(60 + 20 + 30, 300,  20, 800 - 300);
        d.setColor(Color.black);
        d.fillRectangle(60, 600, 120 + 10, 200);
        // sun
        d.setColor(new Color(250, 237, 160));
        d.fillCircle(60 + 20 + 30 + 20 / 2, 300, 15);
        d.setColor(Color.red);
        d.fillCircle(60 + 20 + 30 + 20 / 2, 300, 10);
        d.setColor(Color.white);
        d.fillCircle(60 + 20 + 30 + 20 / 2, 300, 5);
        // draw the inner rectangles
        d.setColor(Color.white);
        int initX = 60;
        int xPos = 60;
        int yPos = 600;
        int space = 9;
        int spaceY = 10;
        int flag = 0;
        int i;
        while (flag != 6) {
            for (i = 0; i < 6; i++) {
                d.fillRectangle(xPos + space, yPos + spaceY, 10, 40);
                xPos += 10;
                space += 10;
            }
            spaceY += 50;
            xPos = initX;
            space = 9;
            flag++;
        }
    }
    /**
     * things that happen when time passed.
     */
    @Override
    public void timePassed() {
        // do nothing.
    }

}
