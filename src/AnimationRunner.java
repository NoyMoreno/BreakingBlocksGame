package animation;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * AnimationRunner class - this class is responsible for running animation object.
 * @author noy shriki
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    /**
      * Contractor.
      * @param gui - current gui.
      * @param framesPerSecond - frames per second.
      */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }
    /**
     * run the game.
     * @param animation - animation object
     */
    public void run(Animation animation) {
        // 1 ms = 10^-3 s
       long millisecondsPerFrame = (long) 1000 / this.framesPerSecond;
       // loop that activates the animation
       while (!animation.shouldStop()) {
          long startTime = System.currentTimeMillis(); // timing
          DrawSurface d = gui.getDrawSurface();
          animation.doOneFrame(d);
          gui.show(d);
          // timing
          long usedTime = System.currentTimeMillis() - startTime;
          long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
          if (milliSecondLeftToSleep > 0) {
              this.sleeper.sleepFor(milliSecondLeftToSleep);
          }
       }
    }
}
