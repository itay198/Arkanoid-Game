package shapes;

import java.util.ArrayList;
/**
 * @author Itay Heilbron
 * @version 1.6 (current version number of
program)
 * @since 2010-03-31 (the version of the
package this class was first added to) */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line up;
    private Line down;
    private Line right;
    private Line left;
    // Create a new rectangle with location and width/height.
    /**
     *construct  a src.src.src.shapes.Rectangle.
     *
     * @param upperLeft upper Left point of rectangle.
     * @param width the width of rectangle.
     * @param height the height of rectangle.
     **/
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.up = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        this.left = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX(), this.getUpperLeft().getY() + getHeight());
        this.right = new Line(this.getUpperLeft().getX() + getWidth(), this.getUpperLeft().getY(),
                this.getUpperLeft().getX() + getWidth(), this.getUpperLeft().getY() + getHeight());
        this.down = new Line(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight(),
                this.getUpperLeft().getX() + getWidth(), this.getUpperLeft().getY() + getHeight());
    }
    /**
     *get for up line of rectangle .
     *
     * @return  up line of rectangle .
     *
     **/
    public Line getUp() {
        return this.up;
    }
    /**
     *get for left line of rectangle .
     *
     * @return  left line of rectangle .
     *
     **/
    public Line getLeft() {
        return this.left;
    }
    /**
     *get for right line of rectangle .
     *
     * @return  right line of rectangle .
     *
     **/
    public Line getRight() {
        return this.right;
    }
    /**
     *get for down line of rectangle .
     *
     * @return  down line of rectangle .
     *
     **/
    public Line getDown() {
        return this.down;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line line that we check intersction with.
     * @return  list of intersection points.
     *
     **/
    public java.util.ArrayList<Point> intersectionPoints(Line line) {
        ArrayList<Point> list = new ArrayList<Point>();
        if (line.isIntersecting(getUp())) {
            list.add(line.intersectionWith(getUp()));
        }
        if (line.isIntersecting(getDown())) {
            list.add(line.intersectionWith(getDown()));
        }
        if (line.isIntersecting(getLeft())) {
            list.add(line.intersectionWith(getLeft()));
        }
        if (line.isIntersecting(getRight())) {
            list.add(line.intersectionWith(getRight()));
        }
        return list;
    }
    /**
     *Return the width  of the rectangle .
     *
     * @return  width  of rectangle .
     *
     **/
    public double getWidth() {
        return this.width;
    }
    /**
     *Return the height of the rectangle .
     *
     * @return  height of rectangle .
     *
     **/
    public double getHeight() {
        return this.height;
    }

    // Returns the  of the rectangle.
    /**
     *Return the upper-left point of the rectangle .
     *
     * @return  upper-left point of the rectangle .
     *
     **/
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}