package levels;
import java.awt.Color;

import animation.Animation;
import basic.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation {
    public static final int BORDER_THICK = 30;
    public static final int WIDTH = 800;
    private Counter score;
    private biuoop.KeyboardSensor keyBoard;
    //private boolean stop;
    private boolean won;
    private boolean stop;
    public EndScreen(biuoop.KeyboardSensor keyBoard, Counter score, boolean won) {
      this.score = score;
      this.keyBoard = keyBoard;
      this.won = won;
      this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(BORDER_THICK, 30 + 25, WIDTH - BORDER_THICK * 2,  WIDTH - BORDER_THICK - 25);
        if (won) {
            for (int i = 1; i < 8; i++) {
                d.setColor(Color.gray);
                if (i % 2 ==0) {
                    d.setColor(Color.red);
                }
                d.drawText(180 + i, 300 + i, "YOU WIN !", 80);
            }
        } else {
            d.setColor(new Color(240, 248, 255));
            for (int i = 1; i < 8; i ++) {
                d.drawText(150 + i, 300 + i, "GAME OVER", 80);
            }
        }
        d.setColor(Color.red);
        for (int i = 1; i < 2; i ++) {
            d.drawText(150 + i, 100 + i, "Your Score Is: " + this.score.getValue(), 40);
        }
        if (this.keyBoard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
       return this.stop;
    }

}
