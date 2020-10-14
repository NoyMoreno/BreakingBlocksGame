package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprite.Sprite;
/**
 * class WideEasyBackground - background of wide easy level.
 * @author noy shriki.
 */
public class WideEasyBackground implements Sprite {
    public static final int BORDER_THICK = 30;
    public static final int WIDTH = 800;
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(550, BORDER_THICK - 5, "Level Name: WideEsay ", 20);
        // set the rectangle the will fill the background.
        d.setColor(Color.white);
        d.fillRectangle(BORDER_THICK, 30 + 25, WIDTH - BORDER_THICK * 2,  WIDTH - BORDER_THICK - 25);
        for (int i = 0; i < 100; i++) {
           d.setColor(new Color(250, 237, 160));
           d.drawLine(150, 150, 740 / 100 * i + 30, 360);
        }
        // crate the background
        d.setColor(new Color(250, 237, 160));
        // Color s = new Color();
        d.fillCircle(150, 150, 85);
        d.setColor(new Color(250, 250, 150));
        d.fillCircle(150, 150, 65);
        d.setColor(new Color(240, 230, 100));
        d.fillCircle(150, 150, 50);
    }
    @Override
    public void timePassed() {
    }

}
