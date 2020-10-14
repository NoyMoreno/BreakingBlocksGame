package game;

import java.util.ArrayList;
import java.util.List;

import basic.Collidable;
import basic.CollisionInfo;
import geometry.Line;
import geometry.Point;
/**
 * class GameEnvironment - description of game environment.
 * @author noy shriki
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * Constructor.
     * Create new list of collidable object.
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
        }
    /**
     * add the given collidable to the environment.
     * @param c - collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
        }
    /**
     * remove the given collidable from the environment.
     * @param c - collidable object
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * find the closest collision that is going to occur and the collide object.
     * @param  trajectory - object moving from line.start() to line.end().
     * @return  If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidablesList = new ArrayList<>(this.collidables);
        Point closestCollision = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        Collidable curCollidable = null;
        for (Collidable c : collidablesList) {
          Point collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
          if (collisionPoint != null && collisionPoint.getX() != Double.POSITIVE_INFINITY) {
              double distance = collisionPoint.distance(trajectory.getStart());
              // is the first collision point
              if (closestCollision.getX() == Double.POSITIVE_INFINITY) {
                  closestCollision = collisionPoint;
                  curCollidable = c;
                  // if the current collision point us closet then the last.
              } else if (distance < closestCollision.distance(trajectory.getStart())) {
                  closestCollision = collisionPoint;
                  curCollidable = c;
              }
          }
        }
        return new CollisionInfo(closestCollision, curCollidable);
    }

}
