package sprite;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * SpriteCollection class - methods of collection of sprite.
 * @author noy shriki
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * Constructor.
     * create new array of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Add sprite object to list.
     * @param s - sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * remove sprite object from list.
     * @param s - sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
      List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
      for (Sprite s : spritesCopy) {
          s.timePassed();
      }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d -surface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.drawOn(d);
        }
    }
}
