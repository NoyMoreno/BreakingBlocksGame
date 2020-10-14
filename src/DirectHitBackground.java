package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprite.Sprite;
/**
 * class DirectHitBackground - background of direct hit level.
 * @author noy shriki.
 */
public class DirectHitBackground implements Sprite {
    public static final int WIDTH = 800;
    public static final int BORDER_THICK = 30;
    /**
     * draw the background.
     * @param d -  DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(550, BORDER_THICK - 5, "Level Name: Dirct Hit ", 20);
        // set the rectangle the will fill the background.
        d.setColor(Color.black);
        d.fillRectangle(BORDER_THICK, 30 + 25, WIDTH - BORDER_THICK * 2,  WIDTH - BORDER_THICK - 25);
        d.setColor(Color.black);
        d.fillCircle(400, 300, 160);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 300, 160);
        d.setColor(Color.black);
        d.fillCircle(400, 300, 120);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 300, 120);
        d.setColor(Color.black);
        d.fillCircle(400, 300, 80);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 300, 80);
        // up line
        d.setColor(Color.BLUE);
        d.drawLine(400, 300 - 20 - 10, 400, 100);
        //bottom line
        d.setColor(Color.BLUE);
        d.drawLine(400, 300 + 20 + 10, 400, 500);
        // right line
        d.setColor(Color.BLUE);
        d.drawLine(400 - 20 - 10, 300, 200, 300);
        // left line
        d.setColor(Color.BLUE);
        d.drawLine(400 + 20 + 10, 300, 600, 300);
    }
    /**
     * things that happen when time passed.
     */
    @Override
    public void timePassed() {
        // do nothing.
    }
}
