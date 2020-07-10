package sprites;

import shapes.Line;
import shapes.Point;

import java.util.ArrayList;
/**
 * @author Itay Heilbron
 * @version 1.6 (current version number of
program)
 * @since 2010-03-31 (the version of the
package this class was first added to) */
public class GameEnvironment {
    private ArrayList<Collidable> collidables = new ArrayList<Collidable>();;

    /**
     *get for collidables list.
     *
     * @return  collidables list.
     *
     **/
    public ArrayList<Collidable> getList() {
        return this.collidables;
    }



    //
    /**
     *add the given collidable to the environment.
     *
     * @param c src.src.src.sprites.Collidable.
     **/
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }


    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory trj.
     * @return  a collision info.
     *
     **/
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo min;
        if (this.collidables.size() == 0 || this.collidables == null) {
            return null;
        }
        Point minp = null , p = null;
        Collidable minc = null;
        for (Collidable c : this.collidables) {
            p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                minp = p;
                minc = c;
            }
        }
        if (minp == null) {
            return null;
        }
        for (Collidable c : this.collidables) {
            p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null && trajectory.start().distance(p) < trajectory.start().distance(minp)) {
                minp = p;
                minc = c;
            }
        }
        CollisionInfo collisionInfo = new CollisionInfo(minp, minc);
        return collisionInfo;
    }

}