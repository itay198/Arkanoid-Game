package readers;

import shapes.Point;
import shapes.Rectangle;
import sprites.collidables.Block;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.List;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    private int height;

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        String line = "";
        List<String> lines = new ArrayList<>();
        Map<String, String> defaults = new TreeMap<>();
        List<Map<String, Object>> blocksS = new ArrayList<>();
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        try {
            Scanner input = new Scanner(reader);
            int j = 0;
            //reading
            while (input.hasNextLine()) {
                line = input.nextLine();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
                j++;
            }
            //defaults
            if (lines.get(0).equals("# default values for blocks")) {
                for (String s : lines.get(1).split(" ")) {
                    if (s.contains(":")) {
                        defaults.put(s.split(":")[0], s.split(":")[1]);
                    }
                }
            }
            int i = lines.indexOf("# block definitions") + 1, w = 0;
            //blocks
            while (!lines.get(i).equals("# spacers definitions") && lines.get(i) != "") {
                blocksS.add(new TreeMap<>());
                for (String s : lines.get(i).split(" ")) {
                    if (s.contains(":")) {
                        blocksS.get(w).put(s.split(":")[0], s.split(":")[1]);
                    }

                }
                w++;
                i++;
            }
            blockCreators = blockCreating(defaults, blocksS);
            //spacers
            i = lines.indexOf("# spacers definitions") + 1;
            String str = "";
            int width = 0;
            while (i < lines.size()) {
                for (String s : lines.get(i).split(" ")) {
                    if (s.contains("width")) {
                        width = Integer.parseInt(s.split(":")[1]);
                    }
                    if (s.contains("symbol")) {
                        str = s.split(":")[1];
                    }
                }
                spacerWidths.put(str, width);
                i++;
            }

            return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);

        }  catch (Exception e) {
            int t = 5;
            t = t / 5;
        }
        return null;
    }

    /**
     * Block creating map.
     *
     * @param defaults the defaults
     * @param blocksS  the blocks s
     * @return the map
     */
    public static Map<String, BlockCreator> blockCreating(Map<String, String> defaults,
                                                          List<Map<String, Object>> blocksS) {
        List<Map<String, Object>> blocks = new ArrayList<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        for (int i = 0; i < blocksS.size(); i++) {
            Map<String, Object> current = blocksS.get(i);
            blocks.add(new TreeMap<>());
            blocks.get(i).put("symbol", blocksS.get(i).get("symbol"));
            if (defaults.containsKey("height")) {
                blocks.get(i).put("height", Integer.parseInt(defaults.get("height")));
            } else {
                blocks.get(i).put("height", Integer.parseInt(blocksS.get(i).get("height").toString()));
            }
            if (defaults.containsKey("width")) {
                blocks.get(i).put("width", Integer.parseInt(defaults.get("width").toString()));
            } else {
                blocks.get(i).put("width", Integer.parseInt(blocksS.get(i).get("width").toString()));
            }
            if (defaults.containsKey("hit_points")) {
                blocks.get(i).put("hit_points", Integer.parseInt(defaults.get("hit_points").toString()));
            } else {
                blocks.get(i).put("hit_points", Integer.parseInt(blocksS.get(i).get("hit_points").toString()));
            }
            if (defaults.containsKey("fill")) {
                if (defaults.get("fill").startsWith("color")) {
                    blocks.get(i).put("fill", new ColorsParser().colorFromString(defaults.get("fill")));
                }
                if (defaults.get("fill").startsWith("image")) {
                    String image = defaults.toString().split("(image\\()")[1].split("\\)")[0];
                    Image img;
                    try {
                        img = ImageIO.read(new File(image));
                        blocks.get(i).put("fill", img);
                    } catch (IOException e) {
                        int t = 5;
                        t = t / 5;
                    }
                }
            } else {
                if (blocksS.get(i).containsKey("fill")) {
                    if (blocksS.get(i).get("fill").toString().startsWith("color")) {
                        blocks.get(i).put("fill",
                                new ColorsParser().colorFromString(blocksS.get(i).get("fill").toString()));
                    }
                    if (blocksS.get(i).get("fill").toString().startsWith("image")) {
                        String image = blocksS.get(i).toString().split("(image\\()")[1].split("\\)")[0];
                        Image img;
                        try {
                            img = ImageIO.read(new File(image));
                            blocks.get(i).put("fill", img);
                        } catch (IOException e) {
                            int t = 5;
                            t = t / 5;
                        }
                    }
                }
            }
            for (int r = 0; r < 7; r++) {
                if (defaults.containsKey("fill-" + r)) {
                    if (defaults.get("fill-" + r).startsWith("color")) {
                        blocks.get(i).put("fill-" + r, new ColorsParser().colorFromString(defaults.get("fill-" + r)));
                    }
                    if (defaults.get("fill-" + r).startsWith("image")) {
                        String image = defaults.toString().split("(image\\()")[1].split("\\)")[0];
                        Image img;
                        try {
                            img = ImageIO.read(new File(image));
                            blocks.get(i).put("fill-" + r, img);
                        } catch (IOException e) {
                            int t = 5;
                            t = t / 5;
                        }
                    }
                }
                if (blocksS.get(i).containsKey("fill-" + r)) {
                    if (blocksS.get(i).get("fill-" + r).toString().startsWith("color")) {
                        blocks.get(i).put("fill-" + r, new ColorsParser().colorFromString(blocksS.get(i)
                                .get("fill-" + r).toString()));
                    }
                    if (blocksS.get(i).get("fill-" + r).toString().startsWith("image")) {
                        String image = blocksS.get(i).toString().split("(image\\()")[1].split("\\)")[0];
                        Image img;
                        try {
                            img = ImageIO.read(new File(image));
                            blocks.get(i).put("fill-" + r, img);
                        } catch (IOException e) {
                            int t = 5;
                            t = t / 5;
                        }
                    }
                }
            }
            Map<Integer, Object> lives = new TreeMap<>();
            for (Map.Entry itr : current.entrySet()) {
                if (itr.getKey().toString().startsWith("fill")) {
                    int key;
                    if (itr.getKey().equals("fill")) {
                        key = -1;
                    } else {
                        key = Integer.parseInt(itr.getKey().toString().split("fill-")[1]);
                    }
                    Color color = null;
                    if (itr.getValue().toString().startsWith("color")) {
                        color = new ColorsParser().colorFromString(itr.getValue().toString());
                    }
                    Image img = null;
                    if (itr.getValue().toString().startsWith("image")) {
                        String image = itr.toString().split("(image\\()")[1].split("\\)")[0];
                        try {
                            img = ImageIO.read(new File(image));
                        } catch (IOException e) {
                            int t = 5;
                            t = t / 5;
                        }
                    }
                    if (img != null) {
                        lives.put(key, img);
                    }
                    if (color != null) {
                        lives.put(key, color);
                    }
                }
            }
            if (defaults.containsKey("stroke")) {
                if (defaults.get("stroke").startsWith("color")) {
                    blocks.get(i).put("stroke", new ColorsParser().colorFromString(defaults.get("stroke")));
                }
            }
            if (blocksS.get(i).containsKey("stroke")) {
                if (blocksS.get(i).get("stroke").toString().startsWith("color")) {
                    blocks.get(i).put("stroke",
                            new ColorsParser().colorFromString(blocksS.get(i).get("stroke").toString()));
                }
            }
            final int finalI = i;
            BlockCreator blockCreator = new BlockCreator() {
                @Override
                public Block create(int xpos, int ypos) {
                    Block b = new Block(new Rectangle(new Point(xpos, ypos),
                            Double.parseDouble(blocks.get(finalI).get("width").toString())
                            , Double.parseDouble(blocks.get(finalI).get("height").toString())));
                    b.setHits(Integer.parseInt(blocks.get(finalI).get("hit_points").toString()));
                    b.setImageLives(lives);
                    b.setStroke((Color) blocks.get(finalI).get("stroke"));
                    return b;
                }
            };
            blockCreators.put(blocks.get(finalI).get("symbol").toString(), blockCreator);
        }
        return blockCreators;
    }
}





