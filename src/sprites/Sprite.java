package sprites;

import biuoop.DrawSurface;
import manage.GameLevel;

/**
 * The interface Sprite.
 *
 * @author Itay Heilbron
 * @version 1.6 (current version number ofprogram)
 * @since 2010 -03-31 (the version of thepackage this class was first added to)
 */
public interface Sprite {
   /**
    * draw the sprite.
    *
    * @param surface the color for the block.
    */
   void drawOn(DrawSurface surface);

   /**
    * notify the sprite that time has passed.
    */
   void timePassed();

   /**
    * add the sprite to the game.
    *
    * @param g the game.
    */
   void addToGame(GameLevel g);


}