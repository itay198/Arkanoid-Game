package sprites;

import shapes.Point;

/**
 * @author Itay Heilbron
 * @version 1.6 (current version number of
program)
 * @since 2010-03-31 (the version of the
package this class was first added to) */
public class CollisionInfo {
   private Point collisionPoint;
   private Collidable collisionObject;
   /**
    * @param collisionPoint collision src.src.src.shapes.Point.
    * @param  collisionObject  collision Object .
    **/
   public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
      this.collisionPoint = collisionPoint;
      this.collisionObject = collisionObject;
  //    this.numOfCollisionObjects = 0;
   }
   /**
    *the point at which the collision occurs.
    *
    * @return  collision src.src.src.shapes.Point.
    *
    **/
   public Point collisionPoint() {
      return  this.collisionPoint;
   }

   //
   /**
    *the collidable object involved in the collision.
    *
    * @return  collision object.
    *
    **/
   public Collidable collisionObject() {
      return this.collisionObject;
   }
   /**
    *set collisionPoint.
    *
    * @param  collisionPoint1 collision src.src.src.shapes.Point.
    **/
   public void setCollisionPoint(Point collisionPoint1) {
      this.collisionPoint = collisionPoint1;
   }
   /**
    *set collision Object.
    *
    * @param  ncollisionObject collision object.
    **/
   public void setCollisionObject(Collidable ncollisionObject) {
      this.collisionObject = ncollisionObject;
   }
}