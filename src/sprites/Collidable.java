package sprites;

import biuoop.DrawSurface;
import manage.GameLevel;
import shapes.Point;
import shapes.Rectangle;
import sprites.bouncingBall.Ball;
import sprites.bouncingBall.Velocity;

/**
 * The interface Collidable.
 *
 * @author Itay Heilbron
 * @version 1.6 (current version number ofprogram)
 * @since 2010 -03-31 (the version of thepackage this class was first added to)
 */
public interface Collidable {
    /**
     * get for colliton shape .
     *
     * @return block rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * set new  velocity according to the hit.
     *
     * @param hitter          the hitter
     * @param collisionPoint  src.src.src.shapes.Point of  collision.
     * @param currentVelocity the velocity of the ball.
     * @return new velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * draw the block.
     *
     * @param surface the color for the block.
     */
    void drawOn(DrawSurface surface);

    /**
     * set block's color.
     *
     * @param color the color for the block.
     */
    void setColor(java.awt.Color color);

    /**
     * add the block to the game.
     *
     * @param g the game.
     */
    void addToGame(GameLevel g);
}
