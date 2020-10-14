package game;

import java.awt.Color;

import basic.Collidable;
import basic.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;
/**
 * Paddle class - description of paddle.
 * @author noy shriki
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    //private GUI gui;
    private Rectangle rect;
    public static final int BORDER_THICK = 30;
    private double speed;
    /**
     * Contractor.
     * @param rect - a rectangle
     * @param speed - speed of the paddle.
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard, double speed) {
        this.rect = rect;
        this.keyboard = keyboard;
        this.speed = speed;
    }
    /**
     * getter for rectangle.
     * @return the "shape" of the paddle - a rectangle
     */
    public Rectangle getCollisionRectangle() {
       return this.rect;
    }
    /**
     * Notify the object that we collided with it at collisionPoint with  a given velocity.
     * @param collisionPoint -a collision point.
     * @param currentVelocity - a given velocity.
     * @param hitter - ball that was hit.
     * @return new velocity expected after the hit
     * (based on  the force the object inflicted on us).
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double leftX = this.rect.getLowerLeft().getX();
        double rightX = this.rect.getLowerRight().getX();
        Point[] regions = splitToFiveRegions(leftX, rightX);
        //region 1
        if (collisionPoint.getX() >= regions[0].getX() && collisionPoint.getX() <= regions[0].getY()) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            }
        //region 2
        if (collisionPoint.getX() > regions[1].getX() && collisionPoint.getX() <= regions[1].getY()) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        //region 4
        if (collisionPoint.getX() > regions[3].getX() && collisionPoint.getX() <= regions[3].getY()) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            }
        //region 5
        if (collisionPoint.getX() > regions[4].getX() && collisionPoint.getX() <= regions[4].getY()) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
        //region 3
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }
    /**
     * crate the upper left point of each region.
     * @param leftX - left x
     * @param rightX - right x
     * @return array of points - the upper left point of each region
     */
    private Point[] splitToFiveRegions(double leftX, double rightX) {
        Point[] regions = new Point[5];
        double sizeEachRegion = (rightX - leftX) / 5;
        regions[0] = new Point(leftX, leftX +  sizeEachRegion);
        regions[1] = new Point(regions[0].getY(), regions[0].getY() + sizeEachRegion);
        regions[2] = new Point(regions[1].getY(), regions[1].getY() + sizeEachRegion);
        regions[3] = new Point(regions[2].getY(), regions[2].getY() + sizeEachRegion);
        regions[4] = new Point(regions[3].getY(), regions[3].getY() + sizeEachRegion);
        return regions;
    }
    /**
     * from Sprite class.
     * draw the Paddle
     * @param d -surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        int rectX = (int) this.rect.getUpperLeft().getX();
        int rectY = (int) this.rect.getUpperLeft().getY();
        int rectWidth = (int) this.rect.getWidth();
        int rectHeight = (int) this.rect.getHeight();
        d.fillRectangle(rectX, rectY, rectWidth, rectHeight);
        d.setColor(Color.black);
        d.drawRectangle(rectX, rectY, rectWidth, rectHeight);
    }
    /**
     * what the object need to when time passed.
     */
    public void timePassed() {
        //  if keyboard is the left key - move left
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    //  if keyboard is the right key - move right
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }
    /**
     * check if the paddle is out borders in his next move
     * if he is - return , otherwise change is upper left point.
     */
    private void moveLeft() {
        Point pl = new Point(this.rect.getUpperLeft().getX() - this.speed, this.rect.getUpperLeft().getY());
        Rectangle r = new Rectangle(pl, this.rect.getWidth(), this.rect.getHeight());
        // out borders in the left side
        if (BORDER_THICK > r.getLowerLeft().getX()) {
           return;
        }
       this.rect = r;
    }
    /**
     * check if the paddle is out borders in his next move
     * if he is - return , otherwise change is upper left point.
     */
    private void moveRight() {
        Point pr = new Point(this.rect.getUpperLeft().getX() + this.speed, this.rect.getUpperLeft().getY());
        // out borders in the right side
        Rectangle r = new Rectangle(pr, this.rect.getWidth(), this.rect.getHeight());
        if (GameLevel.MAXX - BORDER_THICK < r.getLowerRight().getX()) {
            return;
         }
     this.rect = r;
    }
    /**
     * Add the paddle to our game.
     * @param gameLevel - the current game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
    /**
     * Remove paddle from game.
     * @param g - the current game
     */
    public void removeFromGame(GameLevel g) {
       g.removeCollidable(this);
       g.removeSprite(this);
    }
}
