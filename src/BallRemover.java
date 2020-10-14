package game;

import basic.Counter;
/**
 * BllRemover class - used for removing balls for the game.
 * @author noy shriki
 */
public class BallRemover implements HitListener {
    private Counter availabeBalls;
    private GameLevel g;
    /**
     * Contractor.
     * @param gameLevel - current game.
     * @param availabeBalls - remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter availabeBalls) {
        this.availabeBalls = availabeBalls;
        this.g = gameLevel;
    }
    /**
     * identify when a ball reaches the bottom of the screen and remove it from game.
     * this listener will remove from the the balls that is being removed from the game.
     * @param beingHit - block that was hit.
     * @param hitter - ball that hitter it.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(g);
        this.availabeBalls.decrease(1);
    }
}
