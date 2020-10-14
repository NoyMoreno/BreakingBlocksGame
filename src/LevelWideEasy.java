package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import basic.Velocity;
import game.Block;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;
/**
 * class LevelWideEasy - description of level wide easy.
 * @author noy shriki
 */
public class LevelWideEasy implements LevelInformation {
    private Sprite background;
    /**
     * Contractor.
     */
    public LevelWideEasy() {
        background = new WideEasyBackground();
    }
    @Override
    public int numberOfBalls() {
        return 8;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 1; i <= numberOfBalls() / 2; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(-50 + (5 * i), 6);
            velocities.add(v);
        }
        for (int i = numberOfBalls() / 2; i < numberOfBalls(); i++) {
            Velocity v = Velocity.fromAngleAndSpeed(9 + (5 * i), 6);
            velocities.add(v);
        }
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 10;
    }
    @Override
    public int paddleWidth() {
        //gui length - borders.
        return 550;
    }
    @Override
    public String levelName() {
        return "Wide Easy ";
    }
    @Override
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * create array of colors to each row.
     * @return array if colors
     */
    private Color[] arrayOfColors() {
          Color[] colors = new Color[15];
          colors[0] = Color.red;
          colors[1] = Color.red;
          colors[2] = Color.orange;
          colors[3] = Color.orange;
          colors[4] = Color.yellow;
          colors[5] = Color.yellow;
          colors[6] = Color.green;
          colors[7] = Color.green;
          colors[8] = Color.green;
          colors[9] = Color.cyan;
          colors[10] = Color.cyan;
          colors[11] = Color.pink;
          colors[12] = Color.pink;
          colors[13] = Color.blue;
          colors[14] = Color.blue;
          return colors;
    }
    @Override
    public List<Block> blocks() {
        Color[] colors = new Color[15];
        colors = arrayOfColors();
        List<Block> blocks = new ArrayList<>();
        double xPos = 30;
        int hits = 1;
        int j = 0;
        for (int iblock = 0; iblock < 15; iblock++) {
            Rectangle b = new Rectangle(new Point(xPos, 360), 49.33, 25);
            Block block = new Block(b, colors[j], hits);
            blocks.add(block);
            xPos += 49.33;
            j++;
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 12;
    }
}
