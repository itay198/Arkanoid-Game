package manage;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.DialogManager;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import sprites.observe.Counter;
import table.HighScoresTable;
import table.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter lives;
    private Counter score;
    private HighScoresTable table;

    /**
     * Instantiates a new Game flow.
     *
     * @param gui the gui
     * @param ar  the ar
     * @param ks  the ks
     */
    public GameFlow(GUI gui, AnimationRunner ar, KeyboardSensor ks) {
        this.gui = gui;
        keyboardSensor = ks;
        animationRunner = ar;
        lives = new Counter(4);
        score = new Counter(0);
        File f = new File("src/manage/highscores");
        if (f.exists()) {
            table = HighScoresTable.loadFromFile(f);
        } else {
            this.table = new HighScoresTable(3);
            try {
                table.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Run levels.txt.
     *
     * @param levels the levels.txt
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(this.gui, levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score, this.lives);

            level.initialize();

            while ((level.getBlockCounter().getValue() > 0) && (level.getLives().getValue() > 0)) {
                level.playOneTurn();
            }

            if (level.getLives().getValue() <= 0) {
                animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", new LooserScreen(keyboardSensor, score.getValue())));
                DrawSurface d = gui.getDrawSurface();
                d.drawText(505, 50 , Integer.toString(score.getValue()), 20);
                break;
            }

        }

        animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                "space", new WinnerScreen(keyboardSensor, score.getValue())));
       if (table.getRank(score.getValue()) <= table.size()) {
           DialogManager dialog = gui.getDialogManager();
           String name = dialog.showQuestionDialog("Name", "What is your name?", "");
           System.out.println(name);
           table.add(new ScoreInfo(name, score.getValue()));
           File f = new File("src\\manage\\highscores");

           try {
               table.save(f);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       KeyPressStoppableAnimation animation =
               new KeyPressStoppableAnimation(this.keyboardSensor, "space", new HighScoresAnimation(table));
        Sleeper s = new Sleeper();
        s.sleepFor(100);
        animationRunner.run(animation);

       // gui.close();

    }
}
