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
 * class LevelFinalFor - description of level final for.
 * @author noy shriki
 */
public class LevelFinalFour implements LevelInformation {
    public static final int BORDER_THICK = 30;
    public static final int ROWS = 6;
    public static final double ROW_RECT_W = 49.333;
    public static final int ROWS_POSITION = 7;
    public static final int MAXX = 800;
    private Sprite background;
    /**
     * Contractor.
     */
    public LevelFinalFour() {
        background = new FinalForBackGround();
    }
    @Override
    public int numberOfBalls() {
       return 3;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new ArrayList<>();
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(30, 4));
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(0, 4));
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(-30, 4));
        return initialBallVelocities;
    }
    @Override
    public int paddleSpeed() {
        return 20;
    }
    @Override
    public int paddleWidth() {
        return 200;
    }
    @Override
    public String levelName() {
        return "Final Four";
    }
    @Override
    public Sprite getBackground() {
        return this.background;
    }
    @Override
    public List<Block> blocks() {
        Color[] colors = getArrayColors();
        int blocksRow = 15;
        List<Block> blocks = new ArrayList<>();
        // add blocks
        double xRow = MAXX -  BORDER_THICK - ROW_RECT_W;
        int yRow = BORDER_THICK * ROWS_POSITION;
        for (int iRow = 0; iRow < ROWS; iRow++) {
            blocks.addAll(addRowBlocks(blocksRow, colors[iRow], xRow, yRow, iRow));
            yRow += BORDER_THICK;
        }
        return blocks;
    }
    /**
     * create all the blocks in one row.
     * @param blocksRow - number of blocks in the current row.
     * @param blocksColor - color of all the blocks in current row.
     * @param xRow - x of upper left current rectangle
     * @param yRow - y of upper left current rectangle
     * @param index  - the row number
     * @return list of blocks.
     */
    private List<Block> addRowBlocks(int blocksRow, Color blocksColor, double xRow, int yRow, int index) {
        Rectangle[] blocksInRows = new Rectangle[blocksRow];
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < blocksRow; i++) {
            blocksInRows[i] = new  Rectangle(new Point(xRow, yRow), 49.3333, BORDER_THICK);
            xRow -= 49.3333;
        }
        for (int i = 0; i < blocksRow; i++) {
            if (index == 0) {
                Block block = new Block(blocksInRows[i], blocksColor, 2);
                blocks.add(block);
            } else {
            Block block = new Block(blocksInRows[i], blocksColor, 1);
            blocks.add(block);
            }
        }
        return  blocks;
    }
    /**
     * create array of colors to each row.
     * @return array if colors
     */
    private Color[] getArrayColors() {
      Color[] colors = new Color[ROWS];
      colors[0] = Color.cyan;
      colors[1] = Color.green;
      colors[2] = Color.red;
      colors[3] = Color.magenta;
      colors[4] = Color.orange;
      colors[5] = Color.blue;
      return colors;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return 15 * 6;
    }
}
