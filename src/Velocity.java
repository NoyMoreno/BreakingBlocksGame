package basic;

import geometry.Point;
/**
 * class Velocity  - Description of Velocity.
 * @author noy shriki
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * Constructor.
     * @param  dx x speed
     * @param  dy y speed
     */
      public Velocity(double dx, double dy) {
          this.dx = dx;
          this.dy = dy;
       }
      /**
       * getters of this class.
       * @return dx.
       */
       public double getDx() {
          return this.dx;
       }
       /**
        * getters of this class.
        * @return dy.
        */
       public double getDy() {
          return this.dy;
       }
       /**
       * Take a point with position (x,y) and return a new point
       * with position (x+dx, y+dy).
       * @param p - point to position.
       * @return point with position (x+dx, y+dy).
       */
       public Point applyToPoint(Point p) {
           return new Point(p.getX() + this.dx, p.getY() + this.dy);
       }
       /**
        * crate Velocity according to angle and speed.
        * @param angle of the current velocity
        * @param speed of the current velocity
        * @return new velocity
        */
       public static Velocity fromAngleAndSpeed(double angle, double speed) {
           double dx = speed * Math.sin(Math.toRadians(angle));
           double dy = -speed * Math.cos(Math.toRadians(angle));
           return new Velocity(dx, dy);
       }
       /**
        * getter for speed.
        * @return the speed
        */
       public double getSpeed() {
           return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
       }
}
