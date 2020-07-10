package shapes;

/**
 * @author Itay Heilbron
 * @version 1.6 (current version number of
program)
 * @since 2010-03-31 (the version of the
package this class was first added to) */
public class Point {
    private double x;
    private double y;
   // constructor
    /**
     * consruct point.
     *
     * @param x  of the point.
     * @param y  of the point.
     **/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
   }
    /**
     *  distance -- return the distance of this point to the other point.
     *
     * @param other points x and y.
     *
     * @return the distance between two points. */
   public double distance(Point other) {
        double dist = (this.getX() - other.getX()) * (this.getX() - other.getX())
                + (this.getY() - other.getY()) * (this.getY() - other.getY());
        double root = Math.sqrt(dist);
        return root;
   }
    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other points x and y.
     * @return true is the points are equal, false otherwise.
     * */
   public boolean  equals(Point other) {
        if (Math.abs(this.getX() - other.getX()) < 0.1 && Math.abs(this.getY() - other.getY()) < 0.1) {
            return true;
            }
        return false;
   }
    /**
     * Return the x value of this point.
     *
     * @return x value */

   public double getX() {

       return this.x;
   }
    /**
     * Return the  y value of this point.
     *
     * @return x value */
   public double getY() {
        return this.y;
   }
}