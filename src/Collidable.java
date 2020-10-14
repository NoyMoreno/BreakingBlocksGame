package basic;

import game.Ball;
import geometry.Point;
import geometry.Rectangle;
/**
 * interface Collidable - a description of the behavior of things that can be collided with.
 * @author noy shriki
 */
public interface Collidable {
    /**
     * @return "collision shape" of the object- rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with  a given velocity.
     * @param collisionPoint -a collision point.
     * @param currentVelocity - a given velocity.
     * @param hitter - the ball that was involved in the collision
     * @return new velocity expected after the hit
     * (based on  the force the object inflicted on us).
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
