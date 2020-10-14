package sprite;
import biuoop.DrawSurface;
/**
 * interface Sprite - a description of the behavior of sprite things.
 * @author noy shriki
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d - surface
     */
     void drawOn(DrawSurface d);
     /**
      *  notify the sprite that time has passed.
      */
     void timePassed();
}
