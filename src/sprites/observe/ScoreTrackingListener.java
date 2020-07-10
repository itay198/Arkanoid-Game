package sprites.observe;
import sprites.bouncingBall.Ball;
import sprites.collidables.Block;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
@Override
    public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHits() != 0) {
           currentScore.increase(5);
       } else {
           currentScore.increase(10);
       }
    }
}
