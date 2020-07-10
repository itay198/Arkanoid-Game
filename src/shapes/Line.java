package shapes;
/**
 * @author Itay Heilbron
 * @version 1.6 (current version number of
program)
 * @since 2010-03-31 (the version of the
package this class was first added to) */
public class Line {
    private Point start;
    private Point end;
   // constructors
    /**
     *construct  a src.src.src.shapes.Line.
     *
     * @param start src.src.src.shapes.Point of the line.
     * @param end  src.src.src.shapes.Point of the line.
     **/

   public Line(Point start, Point end) {
               this.start = start;
               this.end = end;
   }
    /**
     *construct  a src.src.src.shapes.Line.
     *
     * @param x1 of the line.
     * @param y1 of the line.
     * @param x2 of the line.
     * @param y2 of the line.
     **/

    public Line(double x1, double y1, double x2, double y2) {
                this.start = new Point(x1, y1);
                this.end = new Point(x2, y2);
    }
    /**
     *get fo length.
     *
     * @return the length of the line. */
   public double length() {

       return this.start().distance(this.end());
   }


    /**
     *get for start point.
     *
     * @return the start src.src.src.shapes.Point of the line. */

   public Point start() {

       return this.start;
   }

    /**
     *get for end point.
     *
     * @return the end src.src.src.shapes.Point of the line. */
   public Point end() {

       return this.end;
   }
    /**
     *get for middle point.
     *
     * @return the middle src.src.src.shapes.Point of the line. */
   public Point middle() {
       double x = (this.start().getX() + this.end().getX()) / 2.0;
       double y = (this.start().getY() + this.end().getY()) / 2.0;
        Point mid = new Point(x, y);
        return mid;
   }
    /**
     *get for slope.
     *
     * @return the slope of the line. */
   public double slope() {
       if (Math.abs(this.start().getX() - this.end().getX()) < 0.01) {
           return 0.0;
       }
       if (Math.abs(this.start().getY() - this.end().getY()) < 0.01) {
           return 0.0;
       }
        return ((this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX()));
   }
    /**
     *get for end intercept.
     *
     * @return the intercept of the line. */
   public double intercept() {
        if (Math.abs(this.start().getY() - this.end().getY()) < 0.01) {
           return this.start().getY();
       }
       double itr = this.start().getY() - this.slope() * this.start().getX();
        return itr;
   }
    /**
     *chekc if the point is not in the line.
     *
     * @param p  a point.
     * @param st  a y or x value of start point.
     * @param en  a y or x value of start point.
     * @return false if the point in this line, true otherwise. */
   public boolean isntPointInLine(double p, double st, double en) {
       if (p < st && p < en) {
          return true;
      }
       if (p > en && p > st) {
           return true;
       }
        return false;
   }
    /**
     *chekc if the point is in the line.
     *
     * @param p  a point.

     * @return true if the point in this line, false otherwise. */
   public boolean isPointInLine(Point p) {
       if (Math.abs(p.distance(this.start) + p.distance(this.end) - this.length()) < 0.1) {
           return true;
       }
       return false;
   }
    /**
     *chekc if the lines intersecting.
     *
     * @param other  other line.
     * @return true if the lines intersect, false otherwise. */

   public boolean isIntersecting(Line other) {
       if (this.intersectionWith(other) == null) {
           return false;
       }
       return true;
   }
    /**
     *calculate intersecting point.
     *
     * @param other  other line.
     * @return the intersection src.src.src.shapes.Point if the lines intersect, and null otherwise.. */
   public Point intersectionWith(Line other) {
       double x, y;
       if ((Math.abs(this.start().getY() - this.end().getY()) < 0.01)
               && (Math.abs(other.start().getX() - other.end().getX()) < 0.01)) {
           Point p = new Point(other.start().getX(), this.start().getY());
           if (this.isPointInLine(p) && other.isPointInLine(p)) {
               return p;
           }
       }
       if ((Math.abs(other.start().getY() - other.end().getY()) < 0.01)
               && (Math.abs(this.start().getX() - this.end().getX()) < 0.01)) {
           Point p = new Point(this.start().getX(), other.start().getY());
           if (this.isPointInLine(p) && other.isPointInLine(p)) {
               return p;
           }
       }
       if (Math.abs(this.start().getY() - this.end().getY()) < 0.01) {
           x = this.start().getY();
           y = other.slope() * x + other.intercept();
           /*src.src.src.shapes.Point p = new src.src.src.shapes.Point(x, y);
           if (this.isPointInLine(p) && other.isPointInLine(p)) {
               return p;
           }*/

       }
       if (Math.abs(other.start().getY() - other.end().getY()) < 0.01) {
           x = other.start().getY();
           y = this.slope() * x + this.intercept();
        /*   src.src.src.shapes.Point p = new src.src.src.shapes.Point(x, y);
           if (this.isPointInLine(p) && other.isPointInLine(p)) {
               return p;
           }*/

       }
       if (Math.abs(this.start().getX() - this.end().getX()) < 0.01) {
           x = this.start().getX();
           y = other.slope() * x + other.intercept();
        /*   src.src.src.shapes.Point p = new src.src.src.shapes.Point(x, y);
           if (this.isPointInLine(p) && other.isPointInLine(p)) {
               return p;
           }*/

       }
       if (Math.abs(other.start().getX() - other.end().getX()) < 0.01) {
           x = other.start().getX();
           y = this.slope() * x + this.intercept();
       } else { if (this.slope() != 0) {
               x = (other.intercept() - this.intercept()) / (this.slope() - other.slope());
               y = this.slope() * x + this.intercept();
       } else { x = (other.intercept() - this.intercept()) / (this.slope() - other.slope());
               y = other.slope() * x + other.intercept();
           }
       }
       Point p = new Point(x, y);
       if (this.isPointInLine(p) && other.isPointInLine(p)) {
           return p;
       }
       return null;
   }
    /**
     *check if the lines are equals.
     *
     * @param other  other line.
     * @return true is the lines are equal, false otherwise. */
   public boolean equals(Line other) {
        if (Math.abs(this.slope() - other.slope()) < 0.01 && this.start().equals(other.start())
                && this.end().equals(other.end())) {
                return true;
        }
       return false;
   }

    /**
     *f this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect  other rectangle.
     * @return closest Intersection To Start Of src.src.src.shapes.Line. */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
       Line l = new Line(this.start(), this.end());
        if (rect.intersectionPoints(l).size() == 0) {
            return null;
        }
       Point p0 = rect.intersectionPoints(l).get(0);
       for (Point p : rect.intersectionPoints(l)) {
           if (p0.distance(this.start()) > p.distance(this.start())) {
               p0 = p;
           }
       }
       return p0;
    }
}