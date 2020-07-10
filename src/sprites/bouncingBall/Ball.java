package sprites.bouncingBall;

import biuoop.DrawSurface;
import shapes.Line;
import shapes.Point;
import sprites.CollisionInfo;
import manage.GameLevel;
import sprites.GameEnvironment;
import sprites.Sprite;

/**
 * The type Ball.
 *
 * @author Itay Heilbron.
 * @version 1.6 (current version number of program)
 * @since 2010 -03-31 (the version of the package this class was first added to)
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private int bx1; //bounds of the frame the ball moves in.
    private int by1;
    private int bx2;
    private int by2;
    private GameEnvironment gE;
   // constructor

    /**
     * construct a ball.
     *
     * @param x     x of circle
     * @param y     y of circle
     * @param r     r of circle
     * @param color color of circle
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
       this.center = new Point(x, y);
       this.r = r;
       this.color = color;
       this.velocity = new Velocity(0, 0);
   }

    /**
     * construct a ball.
     *
     * @param center point
     * @param r      r of circle
     * @param color  color of circle
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Sets e.
     *
     * @param ngE src.src.src.manage.GameLevel Environment
     */
    public void setgE(GameEnvironment ngE) {
        this.gE = ngE;
    }

    /**
     * Gets e.
     *
     * @return this src.src.src.sprites.GameEnvironment.
     */
    public GameEnvironment getgE() {
        return this.gE;
    }
    // accessors

    /**
     * Gets x.
     *
     * @return the x of the center.
     */
    public int getX() {

       return (int) this.center.getX();
   }
    // accessors

    /**
     * Gets y.
     *
     * @return the y of the center.
     */
    public int getY() {

       return (int) this.center.getY();
   }
    // accessors

    /**
     * Gets size.
     *
     * @return the r of the center.
     */
    public int getSize() {

       return this.r;
   }
    // accessors

    /**
     * Gets color.
     *
     * @return the color of the center.
     */
    public java.awt.Color getColor() {

       return this.color;
   }
    /**
     *draw the ball on the given DrawSurface.
     *
     * @param surface  a DrawSurfacer.
     **/
@Override
   public void drawOn(DrawSurface surface) {
       surface.setColor(this.getColor());
       surface.fillCircle(this.getX(), this.getY(), this.getSize());
   }

    /**
     * set a velocity.
     *
     * @param v a velocity.
     */

    public void setVelocity(Velocity v) {
        this.velocity = v;
}

    /**
     * set a velocity.
     *
     * @param ndx of velocity
     * @param ndy of velocity
     */
    public void setVelocity(double ndx, double ndy) {
        this.velocity.setDx(ndx);
        this.velocity.setDy(ndy);
    }

    /**
     * get a velocity.
     *
     * @return velocity velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
}

    /**
     * make the ball move.
     */
    public void moveOneStep() {
    if (this.getgE() == null) {
        return;
    }
    if (this.getgE().getClosestCollision(this.trajectory()) != null) {
    Line l = this.trajectory();
    Point p = this.getgE().getClosestCollision(this.trajectory()).collisionPoint();
    CollisionInfo collisionInfo = this.gE.getClosestCollision(l);

        this.setVelocity(this.getgE().getClosestCollision(l).collisionObject().hit(this, p, this.velocity));
    } else {
        this.center = this.getVelocity().applyToPoint(this.center);
    }
}

    /**
     * get for trajectory.
     *
     * @return trajectory. line
     */
    public Line trajectory() {
     return new Line(this.center, this.velocity.applyToPoint(this.center));
 }


    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     *add the block to the game.
     *
     * @param  g the game.
     **/
    public void addToGame(GameLevel g) {
     g.addSprite(this);
    }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
