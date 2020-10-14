package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprite.Sprite;
/**
 * class FinalForBackGround - background of final for level.
 * @author noy shriki.
 */
public class FinalForBackGround implements Sprite {
    public static final int BORDER_THICK = 30;
    public static final int WIDTH = 800;
    /**
    * draw the background.
    * @param d -  DrawSurface.
    */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(100, 100, 230));
        d.fillRectangle(BORDER_THICK, 30 + 25, WIDTH - BORDER_THICK * 2,  WIDTH - BORDER_THICK - 25);
        // rain left
        d.setColor(new Color(250, 237, 160));
        for (int i = 0; i < 10; i++) {
            d.drawLine(200 - (10 * i), 550, 160 - (10 * i), 800);
        }
        // clouds left
        d.setColor(new Color(215, 218, 204));
        d.fillCircle(200, 530, 60);
        d.fillCircle(100, 500, 30);
        d.fillCircle(130, 490, 37);
        d.setColor(new Color(198, 200, 190));
        d.fillCircle(100, 550, 50);
        d.fillCircle(100, 500, 30);
        d.setColor(new Color(200, 200, 200));
        d.fillCircle(100, 500, 30);
        d.fillCircle(150, 580, 55);
        // rain left
        d.setColor(new Color(250, 237, 160));
        for (int i = 0; i < 10; i++) {
            d.drawLine(700 - (10 * i), 650, 620 - (10 * i), 800);
        }
        // clouds in the right side
        d.setColor(new Color(215, 218, 204));
        d.fillCircle(615, 605, 24);
        d.fillCircle(630, 648, 26);
        d.fillCircle(650, 615, 22);
        d.setColor(new Color(198, 200, 190));
        d.fillCircle(670, 660, 35);
        d.setColor(new Color(190, 190, 190));
        d.fillCircle(690, 640, 40);
        d.setColor(new Color(200, 200, 200));
        d.setColor(new Color(215, 218, 204));
        d.fillCircle(600, 650, 30);
    }
    /**
     * things that happen when time passed.
     */
    @Override
    public void timePassed() {
        // do nothing.
    }
}
