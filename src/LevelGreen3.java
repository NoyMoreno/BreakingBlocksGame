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
 * class LevelGreen3 - description of level green 3.
 * @author noy shriki
 */
public class LevelGreen3 implements LevelInformation {
    public static final int BORDER_THICK = 30;
    public static final int ROWS = 6;
    public static final int ROW_RECT_W = 50;
    public static final int ROWS_POSITION = 6;
    public static final int MAXX = 800;
    private Sprite background;
    /**
     * Contractor.
     */
    public LevelGreen3() {
        background = new Green3Background();
    }
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new ArrayList<>();
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(-30, 6));
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(30, 6));
        return initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 250;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
     // get different colors
        Color[] colors = getArrayColors();
        int blocksRow = 12;
        List<Block> blocks = new ArrayList<>();
        // add blocks
        int xRow = MAXX -  BORDER_THICK - ROW_RECT_W;
        int yRow = BORDER_THICK * ROWS_POSITION;
        for (int iRow = 0; iRow < ROWS; iRow++) {
            blocks.addAll(addRowBlocks(blocksRow, colors[iRow], xRow, yRow, iRow));
            yRow += BORDER_THICK;
            blocksRow -= 1;
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
    private List<Block> addRowBlocks(int blocksRow, Color blocksColor, int xRow, int yRow, int index) {
        Rectangle[] blocksInRows = new Rectangle[blocksRow];
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < blocksRow; i++) {
            blocksInRows[i] = new  Rectangle(new Point(xRow, yRow), ROW_RECT_W, BORDER_THICK);
            xRow -= ROW_RECT_W;
        }
        for (int i = 0; i < blocksRow; i++) {
            Block block = new Block(blocksInRows[i], blocksColor, 1);
            blocks.add(block);
        }
        return  blocks;
    }
    /**
     * create array of colors to each row.
     * @return array if colors
     */
    private Color[] getArrayColors() {
      Color[] colors = new Color[ROWS];
      colors[0] = Color.gray;
      colors[1] = Color.red;
      colors[2] = Color.yellow;
      colors[3] = Color.blue;
      colors[4] = Color.red;
      colors[5] = Color.orange;
      return colors;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 57;
    }
}
