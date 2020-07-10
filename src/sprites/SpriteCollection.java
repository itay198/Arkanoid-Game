package sprites;

import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * The type Sprite collection.
 *
 * @author Itay Heilbron
 * @version 1.6 (current version number ofprogram)
 * @since 2010 -03-31 (the version of thepackage this class was first added to)
 */
public class SpriteCollection {
   private ArrayList<Sprite> spritesc = new ArrayList<Sprite>();

    /**
     * add the block to the game.
     *
     * @param s add sprite to the collection.
     */
    public void addSprite(Sprite s) {
      spritesc.add(s);
   }

    /**
     * call timePassed() on all src.src.src.sprites.
     */
    public void notifyAllTimePassed() {
       ArrayList<Sprite> copySpritesc = new ArrayList<Sprite>(spritesc);
      for (Sprite s : copySpritesc) {
              s.timePassed();
      }
   }

    /**
     * get for src.src.src.sprites list .
     *
     * @return src.src.src.sprites list.
     */
    public ArrayList<Sprite> getSc() {
      return this.spritesc;
   }

    /**
     * call drawOn(d) on all src.src.src.sprites..
     *
     * @param d the surface .
     */
    public void drawAllOn(DrawSurface d) {
      for (Sprite s : spritesc) {
         try {
             s.drawOn(d);
         } catch (Exception e) {
             int t = 5;
             t = t / 5;
         }
      }

   }
}