package animation;

import java.awt.Color;

import basic.Counter;
import biuoop.DrawSurface;
import sprite.SpriteCollection;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 * @author noy shriki
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Counter counter;
    private long startTime;
    private String levelName;
    /**
     * Contractor.
     * @param numOfSeconds - number of seconders.
     * @param countFrom - countFrom back to 1.
     * @param gameScreen - game screen.
     * @param name - level name.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, String name) {
      this.countFrom = countFrom;
      this.gameScreen = gameScreen;
      // get the number in mili seconds.
      this.numOfSeconds = numOfSeconds * 1000;
      this.startTime = System.currentTimeMillis();
      this.counter = new Counter();
      this.counter.increase(this.countFrom);
      this.levelName = name;
    }
    /**
     * Animation of one frame.
     * @param d - draw surface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
      this.gameScreen.drawAllOn(d);
      d.setColor(Color.black);
      int xPos = d.getWidth() / 2 - 20;
      //bold
      for (int i = 1; i < 10; i++) {
          if (i % 2 == 0) {
           d.setColor(Color.black);
          }
          d.drawText(xPos + i, d.getHeight() / 2 - 300, this.counter.getValue() + "", 90);
          d.setColor(Color.lightGray);
      }
      long numOfMiliSecondsPerText = (long) (this.numOfSeconds / this.countFrom);
      long timePassed = System.currentTimeMillis() - this.startTime;
      if (timePassed >=  numOfMiliSecondsPerText) {
              this.counter.decrease(1);
              this.startTime = System.currentTimeMillis();
          }
      d.setColor(Color.white);
      d.drawText(550, 30 - 5, "Level Name:" + levelName, 20);
     }
    /**
     * @return if the animation need to stop, else otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.counter.getValue() <= 0;
    }
}
