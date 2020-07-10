package sprites.collidables;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import sprites.bouncingBall.Ball;
import sprites.bouncingBall.Velocity;
import shapes.Point;
import shapes.Rectangle;
import sprites.Collidable;
import manage.GameLevel;
import sprites.Sprite;

/**
 * The type Paddle.
 *
 * @author Itay Heilbron
 * @version 1.6 (current version number ofprogram)
 * @since 2010 -03-31 (the version of thepackage this class was first added to)
 */
public class Paddle implements Sprite, Collidable {
   private biuoop.KeyboardSensor keyboard;
   private  java.awt.Color color;
   private Block paddle;
   private Block[] regions = new Block[5];
   private biuoop.GUI gui;
   private int speed;

   /**
    * construct  a src.src.src.sprites.collidables.Paddle.
    *
    * @param gui   a gui.
    * @param width the width
    * @param speedi the speed
    */
   public Paddle(biuoop.GUI gui, int width, int speedi) {
      this.paddle = new Block(new Rectangle(new Point(400 - width / 2, 560), width, 20));
      this.speed = speedi;
      regions();
      this.gui = gui;
   }


   /**
    * move the paddele and it's regions one step left.
    */
   public void moveLeft() {
      if (this.paddle.getCollisionRectangle().getUpperLeft().getX() - 15.0 >= 0) {
         this.paddle = new Block(new Rectangle(new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX()
                 - speed, 560), this.paddle.getCollisionRectangle().getWidth()
                 , this.paddle.getCollisionRectangle().getHeight()));
         regions();
      }
   }

   /**
    * move the paddele and it's regions one step right.
    */
   public void moveRight() {
      if (this.paddle.getCollisionRectangle().getUpperLeft().getX()
              + this.paddle.getCollisionRectangle().getWidth() + 15.0 <= 800) {
         this.paddle = new Block(new Rectangle(new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX()
                 + speed, 560), this.paddle.getCollisionRectangle().getWidth()
                 , this.paddle.getCollisionRectangle().getHeight()));
         regions();
      }
   }

   /**
    * set the paddele's regions.
    */
   public void regions() {
      for (int i = 0; i < 5; i++) {
         this.regions[i] = new Block(
                 new Rectangle(new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX()
                         + i * this.paddle.getCollisionRectangle().getWidth() / 5
                         , this.paddle.getCollisionRectangle().getUpperLeft().getY())
                         , this.paddle.getCollisionRectangle().getWidth() / 5
                         , this.paddle.getCollisionRectangle().getHeight()));
      }

   }
   // src.src.src.sprites.Sprite
   /**
    *
    */
   public void timePassed() {
      this.keyboard = gui.getKeyboardSensor();
      if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
         this.moveLeft();
      }
      if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
         this.moveRight();
      }
   }
   /**
    *draw the paddle.
    *
    * @param d the surface.
    **/
   public void drawOn(DrawSurface d) {
      d.setColor(java.awt.Color.YELLOW);
      d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
              , (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth()
              , (int) this.getCollisionRectangle().getHeight());
      d.setColor(java.awt.Color.black);
      d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
              , (int) this.getCollisionRectangle().getUpperLeft().getY()
              , (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
   }

   @Override
   /**
    *set color to the paddle.
    *
    * @param color of paddle.
    **/
   public void setColor(java.awt.Color color1) {
      this.color = color1;
   }

   // src.src.src.sprites.Collidable
   /**
    *get for paddle shape .
    *
    * @return  block rectangle.
    *
    **/
   public Rectangle getCollisionRectangle() {
      return new Rectangle(this.paddle.getCollisionRectangle().getUpperLeft()
              , this.paddle.getCollisionRectangle().getWidth(), this.paddle.getCollisionRectangle().getHeight());
   }
   /**
    *set new  velocity according to the hit.
    *
    * @param hitter the ball.
    * @param collisionPoint src.src.src.shapes.Point of  collision.
    * @param currentVelocity  the velocity of the ball.
    * @return  new velocity.
    **/
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
      regions();
      double currentVelocitySpeedspeed = currentVelocity.getSpeed();
      if (regions[2].getCollisionRectangle().getUp().isPointInLine(collisionPoint)
              || regions[2].getCollisionRectangle().getDown().isPointInLine(collisionPoint)) {
            currentVelocity.setDx(currentVelocity.getDx());
            currentVelocity.setDy(-currentVelocity.getDy());
         return currentVelocity;
         }
         if (regions[2].getCollisionRectangle().getLeft().isPointInLine(collisionPoint)
                 || regions[2].getCollisionRectangle().getRight().isPointInLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
            currentVelocity.setDy(currentVelocity.getDy());
            return currentVelocity;
         }
      for (int i = 0; i < 2; i++) {
         if (regions[i].getCollisionRectangle().getUp().isPointInLine(collisionPoint)
                 || regions[i].getCollisionRectangle().getDown().isPointInLine(collisionPoint)
                 || regions[i].getCollisionRectangle().getLeft().isPointInLine(collisionPoint)
                 || regions[i].getCollisionRectangle().getRight().isPointInLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed((30 * i + 300), currentVelocitySpeedspeed);
         }
      }
      for (int i = 3; i < 5; i++) {
            if (regions[i].getCollisionRectangle().getUp().isPointInLine(collisionPoint)
                    || regions[i].getCollisionRectangle().getDown().isPointInLine(collisionPoint)
                    || regions[i].getCollisionRectangle().getLeft().isPointInLine(collisionPoint)
                    || regions[i].getCollisionRectangle().getRight().isPointInLine(collisionPoint)) {
               return Velocity.fromAngleAndSpeed((30 * (i - 2)), currentVelocitySpeedspeed);
            }
      }
         return currentVelocity;
      }
   /**
    *add the paddle to the game.
    *
    * @param  g the game.
    **/
   public void addToGame(GameLevel g) {
      g.addSprite(this);
      g.addCollidable(this);
   }
}