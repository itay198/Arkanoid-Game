package manage;

import sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;


/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
            this.gameScreen.drawAllOn(d);
            d.setColor(Color.white);
            if (numOfSeconds > 0) {
                d.drawText(375, 275, Integer.toString((int) numOfSeconds), 50);
            } else {
                d.drawText(375, 275, "GO!", 50);
            }
            numOfSeconds--;
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(1000);
            if (numOfSeconds <= -2) {
                running = false;
            }

    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

}
