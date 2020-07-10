package sprites;

import biuoop.DrawSurface;
import manage.GameLevel;
import levels.LevelInformation;
import shapes.Point;
import shapes.Rectangle;
import sprites.collidables.Block;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Back ground.
 */
public class BackGround extends Block {
    private java.awt.Color color;
    private LevelInformation level;
    private Image image;

    /**
     * Instantiates a new Back ground.
     *
     * @param level the level
     */
    public BackGround(LevelInformation level) {
        super(new Rectangle(new Point(10, 10), 800, 600));
        this.level = level;
        if (level.levelName().equals("Direct Hit")) {
            color = Color.BLACK;
        }
        if (level.levelName().equals("Sunny Day")) {
            color = Color.MAGENTA;
        }
        if (level.levelName().equals("Green")) {
            color = Color.green;
        }
        if (level.levelName().equals("Clouds")) {
            color = Color.CYAN;
        }
    }

    /**
     * Instantiates a new Back ground.
     *
     * @param image the image
     */
    public BackGround(Image image) {
        super(new Rectangle(new Point(10, 10), 800, 600));
        this.image = image;

    }

    /**
     * Instantiates a new Back ground.
     *
     * @param color the color
     */
    public BackGround(Color color) {
        super(new Rectangle(new Point(10, 10), 800, 600));
        this.color = color;

    }

    @Override
    public void drawOn(DrawSurface surface) {
        if (this.image != null) {
            surface.drawImage(0, 0, image);
        }
        if (this.color != null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX()
                    , (int) this.getCollisionRectangle().getUpperLeft().getY()
                    , (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());

        }
    }
    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }

    /**
     * Level 1.
     *
     * @param surface the surface
     */
    public void level1(DrawSurface surface) {
        surface.setColor(Color.blue);
        surface.drawCircle(400, 200, 150);
        surface.drawCircle(400, 200, 100);
        surface.drawCircle(400, 200, 50);
        surface.drawLine(400, 0, 400, 400);
        surface.drawLine(200, 200, 600, 200);
    }

    /**
     * Level 2.
     *
     * @param surface the surface
     */
    public void level2(DrawSurface surface) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 12; j++) {
                surface.setColor(Color.CYAN);
                surface.fillCircle(1 + 50 * i, 1 + 50 * j, 10);
            }
        }
        surface.setColor(Color.yellow);
        for (int i = 0; i < 80; i++) {
            surface.drawLine(150, 150, 10 + 10 * i, 230);
        }
        surface.setColor(Color.decode("#FFDB37"));
        surface.fillCircle(150, 150, 70);
        surface.setColor(Color.decode("#FCFF6A"));
        surface.fillCircle(150, 150, 60);
        surface.setColor(Color.decode("#FFDB37"));
        surface.fillCircle(150, 150, 50);

    }

    /**
     * Level 3.
     *
     * @param surface the surface
     */
    public void level3(DrawSurface surface) {
            surface.setColor(Color.black);
            surface.fillRectangle(90, 400, 115, 200);
            surface.setColor(Color.white);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 20; j++) {
                    surface.fillRectangle(100 + i * 10, 600 - j * 10, 7, 7);
                }
            }
            surface.setColor(Color.black);
            surface.fillRectangle(135, 150, 20, 250);
            surface.setColor(Color.gray);
            surface.fillRectangle(140, 155, 10, 245);
            surface.setColor(Color.red);
            surface.fillCircle(145, 130, 20);
            surface.setColor(Color.pink);
            surface.fillCircle(145, 130, 10);

    }

    /**
     * Level 4.
     *
     * @param surface the surface
     */
    public void level4(DrawSurface surface) {
            for (int i = 0; i < 10; i++) {
                surface.drawLine(70 + (10 * i), 400, 55 + (10 * i), 600);
            }

        surface.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            surface.drawLine(600 + (10 * i), 500, 580 + (10 * i), 600);
        }
        for (int i = 0; i < 10; i++) {
            surface.drawLine(60 + (10 * i), 400, 80 + (10 * i), 600);
        }

        surface.setColor(Color.decode("#BDCCC5"));
        surface.fillCircle(70, 380, 30);
        surface.fillCircle(90, 410, 30);
        surface.setColor(Color.decode("#B9BBB8"));
        surface.fillCircle(120, 390, 35);
        surface.setColor(Color.decode("#A8B0C2"));
        surface.fillCircle(140, 420, 22);
        surface.fillCircle(160, 380, 32);


        surface.setColor(Color.decode("#BDCCC5"));
        surface.fillCircle(600, 480, 30);
        surface.fillCircle(620, 510, 30);
        surface.setColor(Color.decode("#B9BBB8"));
        surface.fillCircle(650, 490, 35);
        surface.setColor(Color.decode("#A8B0C2"));
        surface.fillCircle(670, 520, 22);
        surface.fillCircle(690, 480, 32);
        }
}


