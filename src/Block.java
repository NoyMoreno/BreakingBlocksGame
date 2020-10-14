package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import basic.Collidable;
import basic.Velocity;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;
/**
 * Block class - description blocks in the game.
 * @author noy shriki
 */
public class Block implements Sprite, Collidable, HitNotifier {
    private Rectangle rect;
    private Color color;
    private int hits;
    private List<HitListener> listeners;
    public static final int MAXX = 800;
    public static final int BORDER_THICK = 30;
    /**
     * Constructor.
     * @param rect - "shape" of block
     * @param color - color of the rectangle.
     * @param hits -  the number of hits that left.
     */
    public Block(Rectangle rect, Color color, int hits) {
        this.rect = rect;
        this.color = color;
        this.hits = hits;
        this.listeners = new ArrayList<>();
        }
    /**
     * Constructor for color.
     * @return the block's color.
     */
    public Color getColor() {
        return color;
    }
    /**
     * Constructor for color.
     * @return the block's rectangle.
     */
    public Rectangle getRect() {
        return rect;
    }
    /**
     * Constructor.
     * @return the block's rectangle.
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
       double curDx = currentVelocity.getDx();
       double curDy = currentVelocity.getDy();
       boolean needChangeDx = false;
       boolean needChangeDy = false;
       // Collide in left line
       if (collisionPoint.getX() > this.getRect().getUpperLeft().getX()) {
           if (collisionPoint.getX() < this.getRect().getUpperRight().getX()) {
               needChangeDy = true;
           }
       }
       // collide in upper line
       if (collisionPoint.getY() > this.getRect().getUpperLeft().getY()) {
           if (collisionPoint.getY() < this.getRect().getLowerLeft().getY()) {
               needChangeDx = true;
           }
       }
       // There are many cases where there is a need
       //to change dx and dy, therefore we will check when any condition is true.
       if (collisionPoint.equals(this.rect.getLowerLeft())) {
           needChangeDy = true;
           needChangeDx = true;
       }
       if (collisionPoint.equals(this.rect.getUpperLeft())) {
           needChangeDy = true;
           needChangeDx = true;
       }
       if (collisionPoint.equals(this.rect.getLowerRight())) {
           needChangeDy = true;
           needChangeDx = true;
       }
       if (collisionPoint.equals(this.rect.getUpperRight())) {
           needChangeDy = true;
           needChangeDx = true;
       }
       if (needChangeDy) {
           curDy = -curDy;
           }
       if (needChangeDx) {
           curDx = -curDx;
       }
       this.hits -= 1;
       notifyHit(hitter);
       return new Velocity(curDx, curDy);
    }
    /**
     * drawOn function from interface Sprite.
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        int arg0 = (int) this.rect.getUpperLeft().getX();
        int arg1 = (int) this.rect.getUpperLeft().getY();
        d.setColor(this.color);
        d.fillRectangle(arg0, arg1, (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle(arg0, arg1, (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }
    /**
     * timePassed function from interface Sprite.
     */
    public void timePassed() {
       //No action needed
    }
    /**
     * add this block to game.
     * @param gameLevel - the current game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }
    /**
     * remove this block from the game.
     * @param game - the current game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * add listener to this list of listeners.
     * @param hl - a hit listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
       this.listeners.add(hl);
    }
    /**
     * remove listener to this list of listeners.
     * @param hl - a hit listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
      this.listeners.remove(hl);
    }
    /**
     * notify when was hit.
     * @param hitter - ball that was hit.
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> hitListeners = new ArrayList<>(this.listeners);
        for (HitListener hl:hitListeners) {
           hl.hitEvent(this, hitter);
        }
    }
    /**
     * @return the number of hit points.
     */
    public int getHitPoints() {
       return this.hits;
    }
}
