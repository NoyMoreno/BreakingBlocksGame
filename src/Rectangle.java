package geometry;
import java.util.ArrayList;
import java.util.List;
/**
 * Rectangle class - description of Rectangle.
 * @author noy shriki
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
        /**
         * Constructor.
         * @param upperLeft - the upper left point
         * @param width - rectangle width
         * @param height - rectangle height
         */
        public Rectangle(Point upperLeft, double width, double height) {
            this.upperLeft = upperLeft;
            this.width = width;
            this.height = height;
        }
        /**
         * @param line -to see there are any intersection  point with the rectangle and the line.
         * @return Return a (possibly empty) List of intersection points
         * with the specified line.
         */
        public java.util.List<Point> intersectionPoints(Line line) {
            // Create a dynamic array of intersection points
            List<Point> interPoints = new ArrayList<Point>();
            Point inter1 = line.closestIntersectionToStartOfLine(this);
            // if intersection point is exist add to array
            if (inter1 != null) {
                interPoints.add(inter1);
                }
            // Inverse Proportion - closestIntersectionToStartOfLine - checks to start point.
            Point inter2 = new Line(line.getEnd(), line.getStart()).closestIntersectionToStartOfLine(this);
            if (inter2 != null) {
                interPoints.add(inter2);
                }
            return interPoints;
           }
        /**
        * getter for width.
        * @return width of this rectangle
        */
        public double getWidth() {
            return this.width;
            }
        /**
         * getter for height.
         * @return height of this rectangle
         */
        public double getHeight() {
            return this.height;
            }
        /**
         * getter for upper left  point.
         * @return upper left  point  of this rectangle
         */
        public Point getUpperLeft() {
            return this.upperLeft;
            }
        /**
         * getter for upper right point.
         * @return upper right  point  of this rectangle
         */
        public Point getUpperRight() {
            return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        }
        /**
         * getter for lower left  point.
         * @return lower left  point  of this rectangle
         */
        public Point getLowerLeft() {
            return new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        }
        /**
         * getter for lower right point.
         * @return lower right  point  of this rectangle
         */
        public Point getLowerRight() {
            return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        }
}
