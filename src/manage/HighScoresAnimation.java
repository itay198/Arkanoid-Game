package manage;

import biuoop.DrawSurface;
import table.HighScoresTable;
import table.ScoreInfo;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements  Animation {
    private HighScoresTable table;

    /**
     * Instantiates a new High scores animation.
     *
     * @param highScoresTable the high scores table
     */
    public HighScoresAnimation(HighScoresTable highScoresTable) {
        this.table = highScoresTable;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int i = 100;
        for (ScoreInfo sc:table.getHighScores()) {
            d.drawText(170, 100 + i,   sc.getName() + ": " + sc.getScore()  , 32);
            i += 50;
        }

    }

    @Override
    public boolean shouldStop() { return false; }
}
