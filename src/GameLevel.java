package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import basic.Collidable;
import basic.Counter;
import basic.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import levels.LivesIndicator;
import levels.ScoreIndicator;
import levels.ScoreTrackingListener;
import sprite.Sprite;
import sprite.SpriteCollection;
/**
 * class GameLevel - A description of the structure of each level.
 * @author noy shriki
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter score;
    private Counter availabeLives;
    private Paddle paddle;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private Counter availabelBlocks;
    private Counter availabeBalls;
    private List<Ball> balls;
    public static final int MAXX = 800;
    public static final int BORDER_THICK = 30;
    public static final int ROWS = 6;
    public static final int ROW_RECT_W = 50;
    public static final int ROWS_POSITION = 6;
    public static final int LIVES = 4;
    public static final int FRAMSFORSECONED = 60;
    public static final int PADDLE_THICK = 15;
   /**
    * Contractor.
    * @param level - current level.
    * @param gui - gui.
    * @param score - current score.
    * @param lives - current lives.
    * @param runner - runner;
    */
   public GameLevel(LevelInformation level, KeyboardSensor keyboardSensor, AnimationRunner runner, Counter score, 
           Counter lives) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.level = level;
        this.score = score;
        this.availabeLives = lives;
        this.availabelBlocks = new Counter();
        this.availabelBlocks.increase(this.level.blocks().size());
        this.availabeBalls = new Counter();
        //this.availabeBalls.increase(level.numberOfBalls());
        this.runner = runner;
        this.keyboard = keyboardSensor;
        addSprite(level.getBackground());
        this.balls = new ArrayList<>();
        this.running = false;
    }
   
   public Counter getNumberOfBlocks() {
       return this.availabelBlocks;
   }
   
   /**
    * add collidable object to game.
    * @param c - any collidable
    */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * add collidable object to game.
     * @param s - any sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * remove collidable object from the game.
     * @param c - any sprite
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * remove Sprite object from the game.
     * @param s - any sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        this.addSprite(level.getBackground());
        addBordres();
        addBlocks();
    }
    /**
     * create all the blocks from the current level.
     */
    private void addBlocks() {
        HitListener scoreL = new ScoreTrackingListener(this.score);
        HitListener blockR = new BlockRemover(this, this.availabelBlocks);
        for (Block b:level.blocks()) {
            b.addHitListener(scoreL);
            b.addHitListener(blockR);
            b.addToGame(this);
        }
    }
    /**
     * create paddle according to the current level.
     */
    private void addPaddle() {
        double x = MAXX / 2 - this.level.paddleWidth() / 2;
        double y = MAXX - BORDER_THICK - PADDLE_THICK;
        Rectangle rect = new Rectangle(new Point(x, y), this.level.paddleWidth(),  PADDLE_THICK);
        this.paddle = new Paddle(rect, this.keyboard, this.level.paddleSpeed());
        this.paddle.addToGame(this);
    }
    /**
     * @return array of balls fit the current level
     */
    private List<Ball> createArrayOfBalls() {
        //add the balls
        List<Ball> arrayBalls = new ArrayList<>();
        for (Velocity v :level.initialBallVelocities()) {
            Ball b = new Ball(new Point(400, 580), 8, Color.white);
            b.setVelocity(v);
            arrayBalls.add(b);
            b.setGameEnvironment(environment);
            b.addToGame(this);
            this.availabeBalls.increase(1);
          }
        return balls;
    }
    /**
     * add borders to game.
     */
    private void addBordres() {
        int x = 0, y = MAXX - BORDER_THICK;
        Rectangle[] borders = new Rectangle[5];
        // upper border
        borders[0] = new  Rectangle(new Point(x, BORDER_THICK - 5), MAXX,  BORDER_THICK);
        // lower border - a "death region"
        borders[1] = new  Rectangle(new Point(x, MAXX),  MAXX, BORDER_THICK);
        // right border
        borders[2] = new  Rectangle(new Point(y, BORDER_THICK - 5),  BORDER_THICK, MAXX);
        // left border
        borders[3] = new  Rectangle(new Point(x, BORDER_THICK - 5),  BORDER_THICK, MAXX);
        // block for show information
        borders[4] = new  Rectangle(new Point(0, 0), MAXX, BORDER_THICK - 5);
        // add to game
        for (int i = 0; i < 5; i++) {
            Block block = new Block(borders[i], Color.gray, 0);
            // add this bottom block to Hit Listener.
            if (i == 1) {
              block.addHitListener(new BallRemover(this, this.availabeBalls));
            }
            block.addToGame(this);
        }
        // add score listeners.
        new ScoreIndicator(this.score).addToGame(this);
        new LivesIndicator(this.availabeLives).addToGame(this);
    }
    /**
     * Animation of one frame.
     * @param d - DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
        // win
        if (this.availabelBlocks.getValue() == 0) {
            // Winer get 100 points
            this.score.increase(100);
            // no more block ---> stop the game.
            this.running = false;
        }
        //lost
        if (this.availabeBalls.getValue() == 0) {
            this.availabeLives.decrease(1);
            endOfTurn(balls);
            // no more balls --->stop the game.
            this.running = false;
        }
        // would like to stop.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, 
                    new PauseScreen()));
        }
        d.setColor(Color.white);
        d.drawText(550, BORDER_THICK - 5, "Level Name:" + level.levelName(), 20);
    }
    /**
     * things that happen that one turn over.
     * @param  arrayBalls - list of balls.
     */
    private void endOfTurn(List<Ball> arrayBalls) {
       for (Ball b : arrayBalls) {
           b.removeFromGame(this);
       }
        this.paddle.removeFromGame(this);
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
//    /**
//     * Run the game --> start the animation loop.
//     */
//    public void run() {
//        do {
//            playOneTurn();
//        } while (this.availabeLives.getValue() > 0);
//    }
    /**
     * playOneTurn - run one turn--> continue the animation loop.
     */
    public void playOneTurn() {
        this.balls = createArrayOfBalls();
        addPaddle();
        // set contractor with 60 frames for second and this gui.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, level.levelName()));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;
        this.runner.run(this);
    }
}
