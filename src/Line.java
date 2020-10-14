package geometry;
/**
 * Line class - description of line.
 * @author noy shriki
 */
public class Line {
    private Point start;
    private Point end;
    /**
     * Constructor for two points.
     * @param  start    the start position
     * @param  end    the end position
     */
        public Line(Point start, Point end) {
           this.start = start;
           this.end = end;
        }
        /**
         * Constructor creating new points According to x and y values,gets 2 points.
         * @param  x1    the start x position
         * @param  y1    the start y position
         * @param  x2    the end x position
         * @param  y2    the end y position
         */
        public Line(double x1, double y1, double x2, double y2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        }
        /**
         * Return the distance between the start and the end points.
         * @return the length of the line.
         */
        public double length() {
            return this.start.distance(end); }
        /**
         * Return the middle  point between the start and the end points.
         * @return the the middle point of the line.
         */
        public Point middle() {
            //if middle same as start/end point.
            if (start.equals(end)) {
                return start;
            }
            return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
         }
        /**
        * Return the start point of the line.
        * @return the start point of the line.
        */
        public Point start() {
            return this.start; }
        /**
         * Return the end point of the line.
         * @return the end point of the line.
         */
        public Point end() {
            return this.end; }
        /**
         * the lines are intersect?
         * The function checks if If there is a cut point
         * @param other - a line.
         * @return true if the lines intersect, false otherwise
         */
       public boolean isIntersecting(Line other) {
           Point intersection = intersectionWith(other);
           return intersection.getX() != Double.POSITIVE_INFINITY;
       }
       /**
        * The function checks if the lines intersectingIf in infinity according to their slopes.
        *  The function checks edge case - vertical lines parallel to axes
        * @param other - a line.
        * @return true if the slopes are different , false otherwise.
        */
       private boolean isIntersectingInfinity(Line other) {
           if (other != null) {
               // vertical lines
               if (start.getX() == end.getX() && other.start.getY() == other.end.getY()) {
                   return checkXforInter(other) && checkYforInter(other);
               }
               // vertical lines - Inverse Proportion
               if (start.getY() == end.getY() && other.start.getX() == other.end.getX()) {
                   return checkXforInter(other) && checkYforInter(other);
               }
               // one line is  vertical to axes y.
               if (start.getX() == end.getX() || other.start.getX() == other.end.getX()) {
                   return true;
               }
               // if the slopes are different the lines intersect
               return getSlope() != other.getSlope();
               // if the slopes are different the lines intersect
           }
           // if other = null.
           return false;
       }
       /**
        * vertical lines - does y intersection exist?
        * @param other - a line.
        * @return true if y intersection exist , false otherwise.
        */
       private boolean checkYforInter(Line other) {
           if (other.start.getY() <= start.getY() && other.end.getY() >= start.getY()) {
               return true;
           }
           if (other.start.getY() >= start.getY() && other.end.getY() <= start.getY()) {
               return true;
           }
           return false;
       }
       /**
        * vertical lines - does x intersection exist?
        * @param other - a line.
        * @return true if x intersection exist , false otherwise.
        */
       private boolean checkXforInter(Line other) {
           if (other.start.getX() <= start.getX() && other.end.getX() >= start.getX()) {
               return true;
            }
            if (other.start.getX() >= start.getX() && other.end.getX() <= start.getX()) {
                return true;
            }
            return false;
       }
       /**
        * the lines are intersect?
        * The function checks if the lines following conditions are true:
        * 1. First,if the lines are cut in infinity
        * 2. If segments will be cut -
        * according to the cut point and the start and the end points of the segments.
        * @param other - a line.
        * @return the cut point if it exist, null otherwise.
        */
       public Point intersectionWith(Line other) {
           //Cut in infinity, check if segments will be cut
           if (isIntersectingInfinity(other)) {
               double b1, b2, m1, m2;
                // get the 'b' in the equation of a line - y = mx + b.
                b1 = getB(this);
                b2 = getB(other);
                // get the slope
                m1 = getSlope();
                m2 = other.getSlope();
                if (m1 == Double.POSITIVE_INFINITY) {
                    double interY = this.getEnd().getX() * m2 + b2;
                    Point interPoint = new Point(this.getEnd().getX(), interY);
                    if (interPointExists(interPoint, other)) {
                        return interPoint;
                    }
                }
                if (m2 == Double.POSITIVE_INFINITY) {
                    double interY = this.getEnd().getX() * m1 + b1;
                    Point interPoint = new Point(other.getEnd().getX(), interY);
                    if (interPointExists(interPoint, this)) {
                        return interPoint;
                    }
                }
                double interX = (b2 - b1) / (m1 - m2);
                double interY = m1 * interX + b1;
                Point interPoint = new Point(Math.round(interX), Math.round(interY));
                // if cut point on the first line.
                if (interPointExists(interPoint, other)) {
                        return interPoint;
                    }
                //Cut in infinity but the segments don't cut.
                return new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
             }
            //don't cut at all
            return new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
       }
       /**
        * check if intersection point are exist.
        * @param interPoint - suspicious intersection point
        * @param other - a line
        * @return true if x intersection exist , false otherwise.
        */
       private boolean interPointExists(Point interPoint, Line other) {
           if (interXinLine(other, interPoint) && interYinLine(other, interPoint)) {
               // if cut point on the second line.
                  if (interXinLine(this, interPoint) && interYinLine(this, interPoint)) {
                      return true;
                  }
              }
        return false;
    }
       /**
        * does y of the cut point of the line?
        * @param other - a line.
        * @param interPoint - the cut point.
        * @return true if y of the intersection point of the line, false otherwise.
        */
       private boolean interYinLine(Line other, Point interPoint) {
           if (other.start.getY() <= interPoint.getY() && other.end.getY() >= interPoint.getY()) {
               return true;
               }
           if (other.start.getY() >= interPoint.getY() && other.end.getY() <= interPoint.getY()) {
               return true;
           }
           return false;
       }
       /**
        * does x of the cut point of the line?
        * @param other - a line.
        * @param interPoint - the cut point.
        * @return true if x of the intersection point of the line, false otherwise.
        */
       private boolean interXinLine(Line other, Point interPoint) {
           if (other.start.getX() <= interPoint.getX() &&  other.end.getX() >= interPoint.getX()) {
               return true;
           }
           if (other.start.getX() >= interPoint.getX() && other.end.getX() <= interPoint.getX()) {
               return true;
           }
           return false;
       }
       /**
        * The function will find the 'b' in the equation of a line - y = mx + b.
        * @param l - a line.
        * @return b.
        */
       private double getB(Line l) {
           double b = l.getSlope() * (-l.start.getX()) + l.start.getY();
           return b;
        }
        /**
         * the lines are equal?
         * @param other - a line.
         * @return true is the lines are equal, false otherwise.
         */
       public boolean equals(Line other) {
           return ((this.start.equals(other.start()) && this.end.equals(other.end()))
                  || (this.end.equals(other.start()) && this.start.equals(other.end())));
        }
        /**
         * The function will get the slope of the line.
         * @return slope.
         */
       public double getSlope() {
            // Division by zero return infinity
           if (end.getX() == start.getX()) {
               return Double.POSITIVE_INFINITY;
            }
            return  ((end.getY() - start.getY()) / ((end.getX() - start.getX())));
       }
       /**
        * getters of this class.
        * @return start.
        */
       public Point getStart() {
            return start;
        }
       /**
        * getters of this class.
        * @return end point.
        */
       public Point getEnd() {
            return end;
        }
    // If this line does not intersect with the rectangle, return null.
       // Otherwise, return the closest intersection point to the
       // start of the line.
       /**
        * checks the intersection points of the line with each axis, a maximum of 2 points.
        * @param rect - a rectangle.
        * @return null,if this line does not intersect with the rectangle.
        * Otherwise, return the closest intersection point to the start of the line
        */
      public Point closestIntersectionToStartOfLine(Rectangle rect) {
            Point interPoint = null;
            // is intersection with the left line?
            Line left = new Line(rect.getUpperLeft(), rect.getLowerLeft());
            if (left.isIntersecting(this)) {
               interPoint = left.intersectionWith(this);
            }
            // is intersection with the right line?
            Line right = new Line(rect.getUpperRight(), rect.getLowerRight());
            if (right.isIntersecting(this)) {
                if (interPoint == null) {
                    interPoint = right.intersectionWith(this);
                    } else {
                    if (interPoint.distance(start) > right.intersectionWith(this).distance(start)) {
                        interPoint = right.intersectionWith(this);
                    }
                }
            }
            // is intersection with the upper line?
            Line upper = new Line(rect.getUpperRight(), rect.getUpperLeft());
            if (upper.isIntersecting(this)) {
                if (interPoint == null) {
                    interPoint = upper.intersectionWith(this);
                    } else {
                    if (interPoint.distance(start) > upper.intersectionWith(this).distance(start)) {
                        interPoint = upper.intersectionWith(this);
                    }
               }
            }
            // is intersection with the lower line?
            Line lower = new Line(rect.getLowerRight(), rect.getLowerLeft());
            if (lower.isIntersecting(this)) {
               if (interPoint == null) {
                   interPoint = lower.intersectionWith(this);
                   } else {
                   if (interPoint.distance(start) > lower.intersectionWith(this).distance(start)) {
                       interPoint = lower.intersectionWith(this);
                       }
                   }
                }
             return interPoint;
      }

}
