package manage;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * The type Pause screen.
 */
public class LooserScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k     the k
     * @param score the score
     */
    public LooserScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(170, d.getHeight() / 2, "Game Over. Your score is " + score  , 32);
    }
    @Override
    public boolean shouldStop() { return this.stop; }


}
