package game;

import basic.Counter;
/**
 * BlockRemover class - used for removing blocks for the game.
 * @author noy shriki
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * Contractor.
     * @param gameLevel - current game.
     * @param removedBlocks - counter for block that remove.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.game = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    /**
    * Blocks that are hit and reach 0 hit-points should be removed from the game.
    * this listener will remove from the the block that is being removed from the game.
    * @param beingHit - block that was hit.
    * @param hitter - ball that hitter it.
    */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(game);
            this.remainingBlocks.decrease(1);
        }
    }
}
