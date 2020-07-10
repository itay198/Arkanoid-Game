package sprites.observe;

import manage.GameLevel;
import sprites.bouncingBall.Ball;
import sprites.collidables.Block;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel        the gameLevel
     * @param removedBalls the remved balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.getBallCounter().decrease(1);
        hitter.removeFromGame(this.gameLevel);

    }
}
