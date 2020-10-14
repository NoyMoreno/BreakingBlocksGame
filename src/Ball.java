package game;

import java.awt.Color;
import sprite.Sprite;
import basic.CollisionInfo;
import basic.Velocity;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
/**
 * Ball class - description of ball.
 * @author noy shriki
 */
public class Ball implements Sprite {
    private int r;
    private Point center;
    private Color color;
    private Velocity v;
    private Point initialPoint;
    private Point finalPoint;
    private GameEnvironment gameV;
    /**
     * Constructor for center point, radios, and the color of the ball.
     * @param center   - center of the circle
     * @param r        - radios of the circle.
     * @param curColor - color of the current ball.
     */
    public Ball(Point center, int r, java.awt.Color curColor) {
        this.center = center;
        this.r = r;
        this.color = curColor;
    }
    /**
     * Constructor 2 - for center point-get x and y, radios, and the color of the
     * ball.
     * @param xCenter  - x of the center of the circle
     * @param yCenter  - y of the center of the circle
     * @param r        - radios of the circle.
     * @param curColor - color of the current ball.
     */
    public Ball(int xCenter, int yCenter, int r, java.awt.Color curColor) {
        this.center = new Point((double) xCenter, (double) yCenter);
        this.r = r;
        this.color = curColor;
    }
    /**
     * Accessors for x of the ball.
     * @return x of the ball.
     */
    public int getX() {
        return (int) center.getX();
    }
    /**
     * Accessors for y of the ball.
     * @return y of the ball.
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * Accessors for the size of the ball.
     * @return the size of the ball.
     */
    public int getSize() {
        return this.r;
    }
    /**
     * setter to get GameEnvironment object.
     * @param environment - game environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameV = environment;
    }
    /**
     * draw the ball on the given DrawSurface.
     * @param surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), this.r);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.getX(), (int) this.getY(), this.r);
    }
    /**
     * setter for the color of the ball.
     * @param curColor color to set.
     */
    public void setColor(Color curColor) {
        this.color = curColor;
    }
    /**
     * setter for the Velocity of the ball.
     * @param curV current Velocity.
     */
    public void setVelocity(Velocity curV) {
        this.v = curV;
    }
    /**
     * setter for the Velocity of the ball.
     * @param dx - dx of the Velocity .
     * @param dy - dy of the Velocity .
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * getter for the Velocity of the ball.
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     * The function will move the ball one step according to the speed. If the ball
     * exceeds the boundaries - we will change direction.
     */
    public void moveOneStep() {
        if (this.getVelocity() == null) {
            return;
        }
        // if there is *no* collision we checks borders
        if (findAnyCollition()) {
            this.center = this.getVelocity().applyToPoint(this.center);
            // out of frame in the right side (for x)
            if (finalPoint != null) {
            if (this.center.getX() + this.r > this.finalPoint.getX()) {
                // if positive we need to change direction
                if (this.getVelocity().getDx() > 0) {
                    this.setVelocity((-1) * this.getVelocity().getDx(), this.getVelocity().getDy());
                }
            }
            // out of frame in the left side (for x)
            if (this.center.getX() - this.r < this.initialPoint.getX()) {
                // if negative we need to change direction
                if (this.getVelocity().getDx() < 0) {
                    this.setVelocity((-1) * this.getVelocity().getDx(), this.getVelocity().getDy());
                }
            }
            // out of frame in the top (for y)
            if (this.center.getY() - this.r < this.initialPoint.getY()) {
                // if positive we need to change direction
                if (this.getVelocity().getDy() < 0) {
                    this.setVelocity(this.getVelocity().getDx(), (-1) * this.getVelocity().getDy());
                }
            }
            // out of frame in the bottom (for y)
            if (this.center.getY() + this.r > this.finalPoint.getY()) {
                // if negative we need to change direction
                if (this.getVelocity().getDy() > 0) {
                    this.setVelocity(this.getVelocity().getDx(), (-1) * this.getVelocity().getDy());
                }
            }
            }
        } // end of if- findAnyCollition()
      this.center = this.getVelocity().applyToPoint(this.center);
       //this.center = new Point(this.getVelocity(), this.)
    }
    /**
     * The function will checks if there is any collision. with any collidable
     * object.
     * @return true if there is not collision point, false otherwise.
     */
    private boolean findAnyCollition() {
        // to find collision point we will create a trajectory from the ball center
        // and the center with his change on axis and the ball Radius in the opposite
        // direction
        double dxAdd = 1;
        double dyAdd = 1;
        if (this.getVelocity().getDx() < 0) {
            dxAdd = -1;
        }
        if (this.getVelocity().getDy() < 0) {
            dyAdd = -1;
        }
        Point cangeCenter = this.getVelocity().applyToPoint(center);
        cangeCenter = new Point(cangeCenter.getX() + dxAdd * this.r, cangeCenter.getY() + dyAdd * this.r);
        Line trajectory = new Line(this.center, cangeCenter);
        CollisionInfo curCollide = this.gameV.getClosestCollision(trajectory);
        // true means there is no collision
        if (curCollide == null || curCollide.collisionObject() == null) {
            return true;
        }
        double curX = curCollide.collisionPoint().getX();
        double curY = curCollide.collisionPoint().getY();
        // negative velocity - we add the radius and small change (0.01)
        if (this.getVelocity().getDx() < 0) {
            curX = curX + 0.01 + this.r;
            // positive velocity - we decrease the radius and small change (0.01)
        } else {
            curX = curX - 0.01 - this.r;
        }
        if (this.getVelocity().getDy() < 0) {
            curY = curY + 0.01 + this.r;
        } else {
            curY = curY - 0.01 - this.r;
        }
        // Avoid collision of the ball and the paddle
        // so that the ball "enters" paddle
        try {
            Paddle p = (Paddle) curCollide.collisionObject();
            curY = p.getCollisionRectangle().getUpperLeft().getY() - this.r - 0.01;
        } catch (Exception e) {
            // empty commend
            curY = curY;
        }
        this.center = new Point(curX, curY);
        this.setVelocity(curCollide.collisionObject().hit(curCollide.collisionPoint(), this.getVelocity(), this));
        // false means *there is* a collision point
        return false;
    }
    /**
     * getter for the initial point of the frame.
     * @return initial point.
     */
    public Point getInitialPoint() {
        return initialPoint;
    }
    /**
     * setter for the initial point of the frame.
     * @param firstPoint - initial point of the frame.
     */
    public void setInitialPoint(Point firstPoint) {
        this.initialPoint = firstPoint;
    }
    /**
     * getter for the final point of the frame.
     * @return final point.
     */
    public Point getFinalPoint() {
        return finalPoint;
    }
    /**
     * setter for the final point of the frame.
     * @param lastPoint -final point of the frame.
     */
    public void setFinalPoint(Point lastPoint) {
        this.finalPoint = lastPoint;
    }
    /**
     * from interface Sprite, when time passed the Ball need to move.
     */
    public void timePassed() {
        moveOneStep();
    }
    /**
     * from interface Sprite, add this ball to game.
     * @param g - the current game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * from interface Sprite, remove this ball to game.
     * @param g - the current game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
