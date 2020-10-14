package game;
import java.util.List;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import basic.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.EndScreen;
import levels.LevelInformation;
/**
 * class GameFlow flow the levels in this game.
 * @author noy shriki
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter curScore;
    private Counter curLives;
    private boolean won;
    
    /**
     * Contractor.
     * @param ar AnimationRunner
     * @param ks KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        this.curScore = new Counter();
        this.curLives = new Counter();
        curLives.increase(1);
        won = true;
    }
    /**
     * Get list of all the levels and run this game, one level each stage.
     * @param levels - list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo: levels) {
            GameLevel level = new GameLevel(levelInfo, ks, ar, curScore, curLives);
            level.initialize();
            // level has more blocks and player has more lives
            while (this.curLives.getValue() > 0 && level.getNumberOfBlocks().getValue() > 0) {
                level.playOneTurn();
            }
            // no more lives
            if (this.curLives.getValue() <= 0) {
               won = false;
               this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                       new EndScreen(ks, curScore, won)));
               break;
            }
        }
        // won
        this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                new EndScreen(ks, curScore, won)));
    }
}
