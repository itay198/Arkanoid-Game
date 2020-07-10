package manage;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation decorator;
    private boolean stop;
    private KeyboardSensor keyboard;
    private String key;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        decorator = animation;
        stop = false;
        keyboard = sensor;
        this.key = key;
        if (keyboard.isPressed(key)) {
            this.isAlreadyPressed = true;
        } else {

            this.isAlreadyPressed = false;
        }
        //this.isAlreadyPressed = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        switch (key) {
            case "space":
                decorator.doOneFrame(d);
                if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                 if (isAlreadyPressed) {
                     isAlreadyPressed = false;
                 } else {
                     this.isAlreadyPressed = true;
                     stop = true;
                     this.shouldStop();
                 }

                }
                break;
                default:
                    decorator.doOneFrame(d);
                    if (this.keyboard.isPressed(key) && !isAlreadyPressed) {
                        this.isAlreadyPressed = true;
                        stop = true;
                        this.shouldStop();
                    }
                    break;

        }


    }


@Override
    public boolean shouldStop() { return this.stop; }

}
