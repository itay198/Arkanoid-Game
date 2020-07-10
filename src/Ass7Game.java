
import readers.LevelSpecificationReader;
import levels.LevelInformation;
import manage.KeyPressStoppableAnimation;
import manage.Animation;
import manage.AnimationRunner;
import manage.HighScoresAnimation;
import manage.GameLevel;
import menu.Task;
import menu.StartGame;
import menu.LevelSet;
import menu.Menu;
import menu.MenuAnimation;
import menu.Quit;
import menu.ShowHiScoresTask;
import sprites.observe.Counter;
import  table.HighScoresTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import biuoop.KeyboardSensor;
import biuoop.GUI;

/**
 * The type Ass 7 game.
 */
public class Ass7Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {


        try {
            java.io.Reader f = new java.io.FileReader("resource/definitions/level_definitions.txt");
            LevelSpecificationReader l = new LevelSpecificationReader();
            List<LevelInformation> list = l.fromReader(f);

            f.close();




            GUI gui = new GUI("Arkanoid", 800, 600);

            HighScoresTable highScoresTable = new HighScoresTable(3);


            try {
                highScoresTable.load(new File("src\\manage\\highscores"));
            } catch (IOException e) {
                e.printStackTrace();
            }


            final KeyboardSensor k = gui.getKeyboardSensor();

            boolean stop = false;
            while (true) {
                Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("menu", k);
                AnimationRunner runner = new AnimationRunner(gui);
                Animation scores = new HighScoresAnimation(highScoresTable);
                menu.addSelection("h", "Hi scores - press h", new ShowHiScoresTask(runner,
                        new KeyPressStoppableAnimation(k, "space", scores)));
                LevelInformation levelInformation = list.get(0);
                Animation level = new GameLevel(gui, levelInformation, k, runner, new Counter(0), new Counter(4));
                LevelSet ls = new LevelSet("level-set", k);
                List<LevelInformation> list1 =
                        new LevelSpecificationReader()
                                .fromReader(new FileReader("resource/definitions/level_definitions.txt"));
                StartGame startGame = new StartGame(runner, level, gui, k, list1);
                ls.addSelection("e", "easy", startGame);

                List<LevelInformation> list2 =
                        new LevelSpecificationReader().fromReader(new FileReader("resource/definitions/levels.txt"));
                StartGame startGame2 = new StartGame(runner, level, gui, k, list2);
                ls.addSelection("m", "medium", startGame2);

                menu.addSelection("g", "level set - press g", new Task<Void>() {
                    @Override
                    public Void run() {
                        runner.run(ls);
                        Task<Void> task = (Task<Void>) ls.getStatus();
                        task.run();
                        return null;
                    }
                });
                menu.addSelection("q", "Quit", new Quit(runner, gui));

                runner.run(menu);

                Task<Void> task = menu.getStatus();
                task.run();
                stop = true;

            }


        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



