package readers;
import levels.LevelInformation;
import sprites.BackGround;
import sprites.Sprite;
import sprites.bouncingBall.Velocity;
import sprites.collidables.Block;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import  java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.io.File;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {
    private Map<String, Image> img = new TreeMap<>();
    private Map<String, Color> color = new TreeMap<>();
    private int index;

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> list = new ArrayList<LevelInformation>();
        String word = "";
        try {
            index = 0;
            Scanner input = new Scanner(reader);

            while (input.hasNextLine()) {
                LevelInformation level = reading(input);
                if (level != null) {
                    list.add(level);
                    index++;
                }
            }
        } catch (Exception e) {
            int t = 5;
            t = t / 5;
        }
        return list;
    }

    /**
     * Reading level information.
     *
     * @param input the input
     * @return the level information
     */
    public LevelInformation reading(Scanner input) {
        String word = "", signs = "";
        List<String> lines = new ArrayList<>();
        List<String[]> lines1 = new ArrayList<>();
        List<String[]> blocksAndSpacers = new ArrayList<>();
        try {
            while (input.hasNextLine() && !(word.equals("END_LEVEL"))) {
                word = input.nextLine();
                int j = 0;
                if (word.equals("START_BLOCKS")) {
                    while (input.hasNextLine() && !word.equals("END_BLOCKS")) {
                        word = input.nextLine();
                        int z = 0;
                        if (!word.isEmpty()) {
                            blocksAndSpacers.add(j, new String[word.length()]);
                            for (String str:word.split("")) {
                                blocksAndSpacers.get(j)[z] = str;
                                z++;
                            }
                        }
                        j++;
                    }
                    j = 0;
                }
                if (!word.equals("START_LEVEL") && !word.startsWith("#") && !word.isEmpty()) {
                    lines.add(word);
                    j++;
                }
            }
            int i = 0;
            for (String s:lines) {
                lines1.add(i, lines.get(i).split(":"));
                i++;
            }
            String levelName = lines1.get(0)[1];
            List<Velocity> velcoties = new ArrayList<>();
            for (int k = 0; k < lines1.get(1)[1].split(" ").length; k++) {
                double speed =  Double.parseDouble((lines1.get(1)[1].split(" ")[k].split(",")[0]));
                double angle =  Double.parseDouble((lines1.get(1)[1].split(" ")[k].split(",")[1]));
                velcoties.add(Velocity.fromAngleAndSpeed(speed, angle));
            }
            if (lines1.get(2)[1].startsWith("image")) {
                String image = lines1.get(2)[1].split("(image\\()")[1].split("\\)")[0];
                img.put(levelName, ImageIO.read(new File(image)));
            }
            if (lines1.get(2)[1].startsWith("color")) {
                color.put(levelName, new ColorsParser().colorFromString(lines1.get(2)[1]));
            }
            int paddleSpeed =  Integer.parseInt(lines1.get(3)[1]);

            int paddleWidth =  Integer.parseInt(lines1.get(4)[1]);

            java.io.Reader f = new java.io.FileReader(lines1.get(5)[1]);
            BlocksDefinitionReader l = new BlocksDefinitionReader();
            final BlocksFromSymbolsFactory factory = l.fromReader(f);

            final int blocksStartX =  Integer.parseInt(lines1.get(6)[1]);

            final int blocksStartY =  Integer.parseInt(lines1.get(7)[1]);

            int rowHeight =  Integer.parseInt(lines1.get(8)[1]);

            int numBlocks =  Integer.parseInt(lines1.get(9)[1]);

            LevelInformation level = new LevelInformation() {
                @Override
                public int numberOfBalls() {
                    return velcoties.size();
                }

                @Override
                public List<Velocity> initialBallVelocities() {
                    return velcoties;
                }

                @Override
                public int paddleSpeed() {
                    return paddleSpeed;
                }

                @Override
                public int paddleWidth() {
                    return paddleWidth;
                }

                @Override
                public String levelName() {
                    return levelName;
                }

                @Override
                public Sprite getBackground() {
                    try {
                        if (img.containsKey(levelName)) {
                            return new BackGround(img.get(levelName));
                        } else {
                            if (color.containsKey(levelName)) {
                                return new BackGround(color.get(levelName));
                            }
                        }
                    } catch (Exception e) {
                        int t = 5;
                        t = t / 5;
                    }
                    return null;
                }

                @Override
                public List<Block> blocks() {
                    int startX = blocksStartX, startY = blocksStartY, i = 0;
                    List<Block> list = new LinkedList<>();
                    while (blocksAndSpacers.size() - 1 >= i) {
                        startX = blocksStartX;
                        for (String s:blocksAndSpacers.get(i)) {
                            if (factory.isBlockSymbol(s)) {
                                list.add(factory.getBlock(s, startX, startY));
                                startX += factory.getBlock(s, startX, startY).getWidth();
                            }
                            if (factory.isSpaceSymbol(s)) {
                                startX = startX + factory.getSpaceWidth(s);
                            }
                        }
                        startY += rowHeight;
                        i++;
                    }
                    return list;
                }

                @Override
                public int numberOfBlocksToRemove() {
                    return numBlocks;
                }
            };
            return level;
        } catch (Exception e) {
            int t = 5;
            t = t / 5;
        }
        return null;
    }
}
