package menu;
import biuoop.GUI;
import  manage.AnimationRunner;

/**
 * The type Quit.
 */
public class Quit implements Task<Void> {
    private AnimationRunner runner;
    private GUI gui;

    /**
     * Instantiates a new Quit.
     *
     * @param runner the runner
     * @param gui    the gui
     */
    public Quit(AnimationRunner runner, GUI gui) {
        this.runner = runner;
        this.gui = gui;
    }
    @Override
    public Void run() {
        gui.close();
        return null;
    }
}