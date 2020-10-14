package animation;

import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * class PauseScreen - PauseScreen animation.
 * @author noy shriki
 */
public class PauseScreen implements Animation {
    /**
    * Animation of one frame.
    * @param d
    */
    @Override
    public void doOneFrame(DrawSurface d) {
       d.setColor(new Color(250, 237, 160));
       d.fillRectangle(0, 0, 800, 800);
       d.setColor(Color.red);
       d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 40);
    }
    /**
     * @return if the animation need to stop, else otherwise.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
