package levels;

import java.awt.Color;

import basic.Counter;
import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;
/**
 * LivesIndicator class - which will be in charge of displaying the current "lives".
 * @author noy shriki
 */
public class LivesIndicator implements Sprite {
    public static final int MAXX = 800;
    public static final int BORDER_THICK = 30;
    private Counter lives;
    /**
     * Constructor.
     * @param lives - available lives.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }
    /**
     * drawOn function from interface Sprite.
     * @param d - surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(150, BORDER_THICK - 5, "Lives: " + this.lives.getValue(), 20);
    }
    /**
     * timePassed function from interface Sprite.
     */
    @Override
    public void timePassed() {
        //do nothing.
    }
    /**
     * add this counter to game.
     * @param gameLevel - the current game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

}
