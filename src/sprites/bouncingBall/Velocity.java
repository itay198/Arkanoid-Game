package sprites.bouncingBall;

import shapes.Point;

/**
 * @author Itay Heilbron
 * @version 1.6 (current version number of
program)
 * @since 2010-03-31 (the version of the
package this class was first added to) */
public class Velocity {
    private double dx;
    private double dy;
   // constructor
    /**
     *construct a Velocity.
     *
     * @param dx of velocity
     * @param dy of velocity
     *
     **/
     public Velocity(double dx, double dy) {
       this.dx = dx;
       this.dy = dy;

   }
    /**
     *replace angle and speed to a velocity with dx and dy.
     *
     * @param angle of velocity
     * @param speed of velocity
     *
     * @return a new velociy with dx and dy. */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
      double dx = speed * Math.sin(Math.toRadians(angle));
      double dy = -(speed * Math.cos(Math.toRadians(angle)));
      return new Velocity(dx, dy);
   }
    /**
     *get for dx.
     *
     * @return the dx  of the velociy. */
    public double getDx() {

        return this.dx;
    }
    /**
     *get for dy.
     *
     * @return the dx  of the velociy. */
    public double getDy() {

        return this.dy;
    }
    /**
     *set for dy.
     *
     * @param ndx the old point.
     **/
    public void setDx(double ndx) {
        this.dx = ndx;
    }
    /**
     *set for dy.
     *
     * @param ndy the old point.
     **/
    public void setDy(double ndy) {
        this.dy = ndy;
    }
    /**
     *Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the old point.
     * @return a new point with position (x+dx, y+dy). */
   public Point applyToPoint(Point p) {
       Point np = new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
       return np;
   }
    /**
     *set for dy.
     *
     * @return  speed.
     **/
   public double getSpeed() {
       return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
   }
    /**
     *set for dy.
     *
     * @return  angle.
     **/
   public double getAngle() {
       return Math.asin(this.getDy() / getSpeed());
   }
}
