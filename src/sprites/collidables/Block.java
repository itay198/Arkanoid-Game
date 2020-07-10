package sprites.collidables;

import biuoop.DrawSurface;
import manage.GameLevel;
import sprites.bouncingBall.Ball;
import sprites.bouncingBall.Velocity;
import shapes.Rectangle;
import shapes.Point;
import sprites.Collidable;
import sprites.observe.HitListener;
import sprites.observe.HitNotifier;
import sprites.Sprite;

import java.awt.Color;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


/**
 * The type Block.
 *
 * @author Itay Heilbron
 * @version 1.6 (current version number of program)
 * @since 2010 -03-31 (the version of thepackage this class was first added to)
 */
public class Block implements Collidable, Sprite, HitNotifier {
       private Rectangle block;
       private java.awt.Color color;
       private int hits;
       private List<HitListener> hitListeners = new ArrayList<HitListener>();
       private Image image;
       private Map<Integer, Object> imageLives;
       private java.awt.Color stroke;

    /**
     * construct  a src.src.src.sprites.collidables.Block.
     *
     * @param block rectangle.
     */
    public Block(Rectangle block) {
          this.block = block;
          this.hits = 0;
      }
    /**
     *get for colliton shape .
     *
     * @return  block rectangle.
     *
     **/
      public Rectangle getCollisionRectangle() {
          return this.block;
      }
      @Override
      /**
       *set new  velocity according to the hit.
       *
       * @param collisionPoint src.src.src.shapes.Point of  collision.
       * @param currentVelocity  the velocity of the ball.
       **/
        public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
            if (this.hits > 0) {
                this.hits = this.hits - 1;
            }
            if ((Math.abs(getCollisionRectangle().getUp().start().getY() - collisionPoint.getY()) < 0.03
                    || Math.abs(getCollisionRectangle().getDown().start().getY() - collisionPoint.getY()) < 0.03)
                    && (Math.abs(getCollisionRectangle().getRight().start().getX() - collisionPoint.getX()) < 0.03
                    || Math.abs(getCollisionRectangle().getLeft().start().getX() - collisionPoint.getX()) < 0.03)) {
                currentVelocity.setDx(-currentVelocity.getDx());
                currentVelocity.setDy(-currentVelocity.getDy());
                this.notifyHit(hitter);
                return currentVelocity;
            }
            if (Math.abs(getCollisionRectangle().getUp().start().getY() - collisionPoint.getY()) < 0.03
                    || Math.abs(getCollisionRectangle().getDown().start().getY() - collisionPoint.getY()) < 0.03) {
              currentVelocity.setDx(currentVelocity.getDx());
              currentVelocity.setDy(-currentVelocity.getDy());
          }
          if (Math.abs(getCollisionRectangle().getRight().start().getX() - collisionPoint.getX()) < 0.03
                  || Math.abs(getCollisionRectangle().getLeft().start().getX() - collisionPoint.getX()) < 0.03) {
              currentVelocity.setDx(-currentVelocity.getDx());
              currentVelocity.setDy(currentVelocity.getDy());
          }
          this.notifyHit(hitter);
        return currentVelocity;
      }
    /**
     *set block's color.
     *
     * @param color1 the color for the block.
     **/
       public void setColor(java.awt.Color color1) {
           this.color = color1;
       }

    /**
     * Sets image.
     *
     * @param image1 the image
     */
    public void setImage(Image image1) {
           this.image = image1;
       }

    /**
     * Sets image lives.
     *
     * @param imageLives1 the image lives 1
     */
    public void setImageLives(Map<Integer, Object> imageLives1) {
        this.imageLives = imageLives1;
    }

    /**
     * Sets stroke.
     *
     * @param stroke1 the stroke 1
     */
    public void setStroke(java.awt.Color stroke1) {
        this.stroke = stroke1;
    }

    /**
     *draw the block.
     *
     * @param surface the surface.
     **/
       public void drawOn(DrawSurface surface) {
           String hitss;
           if (this.hits > 0) {
               hitss = String.valueOf(getHits());
           } else {
               hitss = "X";
           }
           if (this.color != null) {
               surface.setColor(this.color);
               surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
                       , (int) this.getCollisionRectangle().getUpperLeft().getY()
                       , (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
               surface.setColor(java.awt.Color.BLACK);
               surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
                       , (int) this.getCollisionRectangle().getUpperLeft().getY()
                       , (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
               surface.setColor(java.awt.Color.WHITE);
           }
           if (this.image != null) {
               surface.drawImage((int) this.getCollisionRectangle().getUpperLeft().getX()
                       , (int) this.getCollisionRectangle().getUpperLeft().getY(), image);
           }
           int flag = 0;
           if (this.imageLives != null) {
               for (Map.Entry itr:this.imageLives.entrySet()) {
                   if (Integer.parseInt(itr.getKey().toString()) == this.hits) {
                       if (this.imageLives.get(this.hits).toString().startsWith("java.awt")
                               || this.imageLives.get(this.hits).toString().startsWith("color")) {
                           surface.setColor((Color) this.imageLives.get(this.hits));
                           surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
                                   , (int) this.getCollisionRectangle().getUpperLeft().getY()
                                   , (int) this.getCollisionRectangle().getWidth(),
                                   (int) this.getCollisionRectangle().getHeight());
                           flag = 1;
                       } else {
                           surface.drawImage((int) this.getCollisionRectangle().getUpperLeft().getX()
                                   , (int) this.getCollisionRectangle().getUpperLeft().getY(),
                                   (Image) this.imageLives.get(this.hits));
                           flag = 1;
                       }
                   }
               }
               if (flag == 0) {
                   if (this.imageLives.get(-1).toString().startsWith("java.awt")
                           || this.imageLives.get(-1).toString().startsWith("color")) {
                       surface.setColor((Color) this.imageLives.get(-1));
                       surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
                               , (int) this.getCollisionRectangle().getUpperLeft().getY()
                               , (int) this.getCollisionRectangle().getWidth(),
                               (int) this.getCollisionRectangle().getHeight());
                   } else {
                       surface.drawImage((int) this.getCollisionRectangle().getUpperLeft().getX()
                               , (int) this.getCollisionRectangle()
                                       .getUpperLeft().getY(), (Image) this.imageLives.get(-1));
                   }
               }
           }
           if (stroke != null) {
               surface.setColor(stroke);
               surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
                       , (int) this.getCollisionRectangle().getUpperLeft().getY()
                       , (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());

           }


       }

    /**
     * set the block's hits.
     *
     * @param initialHits set the initial hits for the block..
     */
    public void setHits(int initialHits) {
          this.hits = initialHits;
        }

    /**
     * set the block's hits.
     *
     * @return current hits of the block.
     */
    public int getHits() {
        return this.hits;
    }
    /**
     *
     *
     *
     **/
       @Override
       public void timePassed() {

       }
    /**
     *add the block to the game.
     *
     * @param  g the game.
     **/
       public void addToGame(GameLevel g) {
           g.addSprite(this);
           g.addCollidable(this);
       }

    /**
     * Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
           gameLevel.removeCollidable(this);
           gameLevel.removeSprite(this);
    }
    /**
     * notify to game.
     *
     * @param hitter the game
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
           this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
           this.hitListeners.remove(hl);
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.block.getWidth();
    }
}
