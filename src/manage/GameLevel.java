package manage;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import biuoop.GUI;
import levels.LevelInformation;
import shapes.Point;
import shapes.Rectangle;
import sprites.Collidable;
import sprites.GameEnvironment;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.bouncingBall.Ball;
import sprites.collidables.Block;
import sprites.collidables.Paddle;
import sprites.observe.Counter;
import sprites.observe.BallRemover;
import sprites.observe.ScoreTrackingListener;
import sprites.observe.BlockRemover;
import sprites.observe.HitNotifier;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The type GameLevel.
 *
 * @author Itay Heilbron
 * @version 1.6 (current version number ofprogram)
 * @since 2010 -03-31 (the version of thepackage this class was first added to)
 */
public class GameLevel implements Animation {
   private SpriteCollection sprites = new SpriteCollection();
   private GameEnvironment environment = new GameEnvironment();
   private GUI gui;
   private Counter blockCounter;
    private Counter ballCounter;
    private Block deathRegion = new Block(new Rectangle(new Point(0, 600), 800, 20));
    private Counter score;
    private Sprite scoreIndicator;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private BallRemover ballRemover;

    /**
     * Instantiates a new Game level.
     *
     * @param gui   the gui
     * @param level the level
     * @param ks    the ks
     * @param ar    the ar
     * @param score the score
     * @param lives the lives
     */
    public GameLevel(GUI gui, LevelInformation level,
                     KeyboardSensor ks, AnimationRunner ar, Counter score, Counter lives) {
        this.gui = gui;
        this.level = level;
        this.keyboard = ks;
        this.runner = ar;
        this.score = score;
        this.lives = lives;
    }

    /**
     * add  a src.src.src.sprites.Collidable.
     *
     * @param c src.src.src.sprites.Collidable.
     */
    public void addCollidable(Collidable c) {
      environment.addCollidable(c);
   }

    /**
     * add  a sprite.
     *
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
      sprites.addSprite(s);
   }

    /**
     * Initialize a new game: create the Blocks and Ball (and src.src.src.sprites.collidables.Paddle) .
     * and add them to the game.
     */
    public void initialize() {
      sprites.addSprite(level.getBackground());
      Random r = new Random();
      this.runner = new AnimationRunner(this.gui);
      //special blokcs

      this.scoreIndicator = new Block(new Rectangle(new Point(0, 0), 800, 20));
      ((Block) this.scoreIndicator).setColor(Color.WHITE);



      this.deathRegion.setColor(Color.GRAY);
      this.deathRegion.addToGame(this);
        //boundries
       Block block1 = new Block(new Rectangle(new Point(0, 20), 800, 20));
       block1.setColor(java.awt.Color.GRAY);
       block1.addToGame(this);
       Block block2 = new Block(new Rectangle(new Point(0, 10), 10, 590));
       block2.setColor(java.awt.Color.GRAY);
       block2.addToGame(this);
       Block block3 = new Block(new Rectangle(new Point(790, 10), 10, 590));
       block3.setColor(java.awt.Color.GRAY);
       block3.addToGame(this);


    for (int i = 0; i < level.numberOfBlocksToRemove(); i++) {
        this.level.blocks().get(i).addToGame(this);
    }
       scoreIndicator.addToGame(this);

    //observers
      ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
      this.blockCounter = new Counter(this.level.numberOfBlocksToRemove());
      BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
      for (Collidable c : this.environment.getList()) {
         if ((c instanceof Block) && (this.environment.getList().indexOf(c) > 3)) {
            ((Block) c).addHitListener(blockRemover);
             ((Block) c).addHitListener(scoreTrackingListener);
         }
      }

      Block livesIndicator = new Block(new Rectangle(new Point(0, 0), 200, 20));
      livesIndicator.setColor(Color.WHITE);

   }

    /**
     * Run.
     */
    public void run() {
       while (this.lives.getValue() > 0) {
           playOneTurn();
       }
       gui.close();
       return;

   }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
      //...
       //balls
      this.createBallsOnTopOfPaddle();
      this.running = true;
      this.runner.run(new CountdownAnimation(3, 3, sprites));
      this.runner.run(this);
      closeAll();
   }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
      this.environment.getList().remove(c);
   }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
      this.sprites.getSc().remove(s);
   }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public Counter getBlockCounter() {
      return blockCounter;
   }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * Gets death region.
     *
     * @return the death region
     */
    public HitNotifier getDeathRegion() {
       return this.deathRegion;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(360, 17, "Score:" + this.score.getValue(), 15);
        d.drawText(100, 17, "Lives:" + this.lives.getValue(), 15);
        d.drawText(600, 17, "Level Name:" + level.levelName(), 15);

        this.sprites.notifyAllTimePassed();
        this.keyboard = gui.getKeyboardSensor();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen(this.keyboard)));
        }

        if ((this.blockCounter.getValue() == 0) || (this.ballCounter.getValue() <= 0)) {
            //100 bonus
            if (this.blockCounter.getValue() == 0) {
                this.score.increase(100);
            }
            //lives
            if (this.ballCounter.getValue() == 0) {
                this.lives.decrease(1);
            }
            this.running = false;
            //shouldStop();

        }
    }

    /**
     * Close all.
     */
    public void closeAll() {
        this.removeCollidable(paddle);
        this.removeSprite(paddle);
        this.deathRegion.removeHitListener(ballRemover);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Create balls on top of paddle.
     */
    public void  createBallsOnTopOfPaddle() {
        List<Ball> balls = new LinkedList<>();
        for (int i = 0; i < level.numberOfBalls(); i++) {
            balls.add(new Ball(395 + (int) Math.pow(-1, i) * 40 * i, 500, 7, Color.yellow));
            balls.get(i).setVelocity(this.level.initialBallVelocities().get(i));
            balls.get(i).setgE(this.environment);
            balls.get(i).addToGame(this);
        }
        this.ballCounter = new Counter(level.numberOfBalls());
        this.ballRemover = new BallRemover(this, this.ballCounter);
        this.deathRegion.addHitListener(this.ballRemover);

        //paddle
        this.paddle = new Paddle(gui, level.paddleWidth(), level.paddleSpeed());
        paddle.setColor(java.awt.Color.YELLOW);
        paddle.addToGame(this);
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Counter getLives() {
        return lives;
    }

}