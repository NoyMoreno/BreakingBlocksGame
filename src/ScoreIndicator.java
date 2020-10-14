package levels;

import java.awt.Color;

import basic.Counter;
import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;
/**
 * ScoreIndicator class - which will be in charge of displaying the current score.
 * @author noy shriki
 */
public class ScoreIndicator implements Sprite {
    public static final int MAXX = 800;
    public static final int BORDER_THICK = 30;
    private Counter currentScore;
    /**
     * Constructor.
     * @param currentScore - the number of points in the game.
     */
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
    }
    /**
     * drawOn function from interface Sprite.
     * @param d - surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
       d.setColor(Color.white);
       d.drawRectangle(0, 0, MAXX, BORDER_THICK);
       d.setColor(Color.DARK_GRAY);
       d.fillRectangle(0, 0, MAXX, BORDER_THICK);
       d.setColor(Color.white);
       d.drawText(350, BORDER_THICK - 5, "score: " + this.currentScore.getValue(), 20);
    }
    /**
     * timePassed function from interface Sprite.
     */
    @Override
    public void timePassed() {
        // do nothing
    }
    /**
     * add this counter to game.
     * @param gameLevel - the current game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
