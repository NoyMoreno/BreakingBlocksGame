package levels;
import java.util.List;
import basic.Velocity;
import game.Block;
import sprite.Sprite;
/**
 * interface LevelInformation - Contract for the existence of these fields at each stage.
 * @author noy shriki
 */
public interface LevelInformation {
    /**
     * @return number of balls.
     */
    int numberOfBalls();
    /**
     * The initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * @return paddle speed.
     */
    int paddleSpeed();
    /**
     * @return paddle width.
     */
    int paddleWidth();
    /**
     * the level name will be displayed at the top of the screen.
     * @return name level.
     */
    String levelName();
    /**
     * @return the background that fit to this level.
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block containsits size, color and location.
     * @return array of blocks.
     */
    List<Block> blocks();
    /**
     * Number of levels that should be removed,before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return number of blocks.
     */
    int numberOfBlocksToRemove();
}
