package game;
/**
 * interface HitListener - using observer pattern-listener.
 * @author noy shriki
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param hitter - the Ball that's doing the hitting.
     * @param beingHit - the block that was hit.
     * @author noy shriki
     */
    void hitEvent(Block beingHit, Ball hitter);
}
