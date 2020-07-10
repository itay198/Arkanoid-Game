package manage;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * The type Pause screen.
 */
public class WinnerScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k     the k
     * @param score the score
     */
    public WinnerScreen(KeyboardSensor k, int score) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(190, d.getHeight() / 2, "You Win! Your score is " + score , 32);
        //if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    @Override
    public boolean shouldStop() { return this.stop; }


}
