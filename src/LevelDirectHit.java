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
 * class LevelDirectHit - A description of the components at each stage.
 * @author noy shriki.
 */
public class LevelDirectHit implements LevelInformation {
        private Sprite background;
        /**
         * Contractor.
         */
        public LevelDirectHit() {
            background = new DirectHitBackground();
        }
        /**
         * return number of balls.
         */
        @Override
        public int numberOfBalls() {
            return 1;
        }
        /**
         * return number of balls.
         */
        @Override
        public List<Velocity> initialBallVelocities() {
            List<Velocity> initialBallVelocities = new ArrayList<>();
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(3, 3));
            return initialBallVelocities;
        }
       /**
        * return paddle speed.
        */
        @Override
        public int paddleSpeed() {
            return 10;
        }
        /**
         * return paddle width.
         */
        @Override
        public int paddleWidth() {
            return 150;
        }

        @Override
        /**
         * @return string of level name.
         */
        public String levelName() {
            return "Direct Hit";
        }
        /**
         * @return back ground.
         */
        @Override
        public Sprite getBackground() {
            return background;
        }
        /**
         * @return list of  block.
         */
        @Override
        public List<Block> blocks() {
            List<Block> blocks = new ArrayList<>();
            Rectangle r = new Rectangle(new Point(400 - 20, 300 - 20), 45, 45);
            Block b = new Block(r, Color.red, 1);
            blocks.add(b);
            return blocks;
        }
        /**
         * number of block to remove.
         */
        @Override
        public int numberOfBlocksToRemove() {
            return 1;
        }
}
