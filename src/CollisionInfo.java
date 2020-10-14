package basic;

import geometry.Point;
/**
 * class CollisionInfo  - Description of a collision.
 * @author noy shriki
 */
public class CollisionInfo {
    private Point collisionPoint;
    private  Collidable colidObj;
    /**
     * Constructor.
     * @param collisionPoint - a collision point.
     * @param colidObj - object involved in the collision
     */
    public CollisionInfo(Point collisionPoint,  Collidable colidObj) {
        this.colidObj = colidObj;
        this.collisionPoint = collisionPoint;
    }
    /**
     *  Turns the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
        }
    /**
     * Turns the collidable object involved in the collision.
     * @return  the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.colidObj;
        }
}
