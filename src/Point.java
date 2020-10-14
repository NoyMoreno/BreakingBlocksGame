package geometry;
/**
 * Point class - description of point.
 * @author noy shriki
 */
public class Point {
    private double x;
    private double y;
    /**
     * Constructor.
     * @param  x position
     * @param  y position
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
     /**
     * The function turns the distance between 2 points.
     * @param other -a point.
     * @return lien - the distance between 2 points.
     */
    // distance - return the distance of this point to the other point.
    public double distance(Point other) {
        double len = Math.sqrt((Math.pow(other.getX() - this.x, 2) + Math.pow(other.getY() - this.y, 2)));
        return len;
    }
    /**
     * The function checks if the points are equal .
     * @param other -a point.
     * @return true - if equal, or false-otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (other.x == this.x && other.y == this.y);
     }
    /**
     * The function returns the x value.
     * @param none
     * @return  x value.
     */
    public double getX() {
        return this.x;
    }
    /**
     * The function returns the y value.
     * @param none
     * @return  y value.
     */
    public double getY() {
        return this.y;
    }
}
