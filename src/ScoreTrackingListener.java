package levels;

import basic.Counter;
import game.Ball;
import game.Block;
import game.HitListener;
/**
 * ScoreTrackingListener class - update this counter when blocks are being hit and removed.
 * @author noy shriki
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructor.
     * @param scoreCounter - the number of points in the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
       this.currentScore = scoreCounter;
    }
    /**
     * Hitting a block is worth 5 points.
     * this function will increase 5 points when hitting a block.
     * @param beingHit - block that was hit.
     * @param hitter - ball that hitter it.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }
}
