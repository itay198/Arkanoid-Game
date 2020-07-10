package menu;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import manage.GameFlow;
import manage.Animation;
import manage.AnimationRunner;

import java.util.List;

/**
 * The type Start game.
 */
public class StartGame implements Task<Void> {
    private AnimationRunner runner;
    private Animation gameLevel;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private List<LevelInformation> list;

    /**
     * Instantiates a new Start game.
     *
     * @param runner    the runner
     * @param gameLevel the game level
     * @param gui       the gui
     * @param kb        the kb
     * @param l         the l
     */
    public StartGame(AnimationRunner runner, Animation gameLevel, GUI gui,
                     KeyboardSensor kb, List<LevelInformation> l) {
        this.runner = runner;
        this.gameLevel = gameLevel;
        this.gui = gui;
        this.keyboardSensor = kb;
        this.list = l;

    }
    @Override
    public Void run() {
        GameFlow gameFlow = new GameFlow(gui, runner, keyboardSensor);
        gameFlow.runLevels(list);
        return null;
    }
}
