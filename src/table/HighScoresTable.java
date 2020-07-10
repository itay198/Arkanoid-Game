package table;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Formatter;
import java.util.Scanner;
import java.io.File;
import java.io.Writer;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

/**
 * The type High scores table.
 */
public class HighScoresTable {
    private File tab;
    private int size;
    private Map<String, Integer> table;
    private Formatter write;


    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.table = new TreeMap<>();
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        if (table.size() < this.size) {
            table.put(score.getName(), score.getScore());
            return;
        } else {
            if (getRank(score.getScore())
                    <= this.size && score.getScore() > getHighScores().get(getHighScores().size() - 1).getScore()) {
                for (Map.Entry<String, Integer> m:table.entrySet()) {
                    if (getRank(m.getValue()) > size) {
                        table.remove(m.getKey());
                        break;
                    }
                }
                table.put(score.getName(), score.getScore());
            }
        }
        if (table.size() < this.size) {
            while (table.size() < this.size) {
                table.remove(getHighScores().get(getHighScores().size()).getName());
            }
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        List<ScoreInfo> highScore = new LinkedList<>();
        for (Map.Entry m:table.entrySet()) {
            ScoreInfo scoreInfo = new ScoreInfo(m.getKey().toString(), (int) m.getValue());
            highScore.add(scoreInfo);
        }
        ScoreInfo sc;
        for (int i = highScore.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (highScore.get(j).getScore() < highScore.get(j + 1).getScore()) {
                    sc = highScore.get(j + 1);
                    highScore.set(j + 1, highScore.get(j));
                    highScore.set(j, sc);
                }
            }
        }
        return highScore;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int i = 1;
        for (ScoreInfo sc:getHighScores()) {
            if (score > sc.getScore()) {
                return i;
            }
            i++;
        }
        return getHighScores().size() + 1;
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        table.clear();
        getHighScores().clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the levelIo exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        table.clear();
        String word = "", name = "";
        int score = 0;
        int flag = 0;
        try {
            Scanner input = new Scanner(filename);
            while (input.hasNextLine()) {
                if (flag == 0) {
                    word = input.next();
                    flag = 1;
                } else {
                    score = input.nextInt();
                    flag = 0;
                }
                table.put(word, score);
            }
        } catch (Exception e) {
            int t = 5;
            t = t / 5;
        }
    }


    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the levelIo exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {

        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename), "utf-8"));
            for (Map.Entry<String, Integer> m:table.entrySet()) {
                writer.write(m.getKey());
                writer.write(" ");
                writer.write(m.getValue().toString());
                writer.write("\n");
            }
        } catch (IOException ex) {
            int t = 5;
            t = t / 5;
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                int t = 5;
                t = t / 5;
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
       try {
           HighScoresTable highScoresTable = new HighScoresTable(3);
           highScoresTable.load(filename);
           if (filename.canRead()) {
               return highScoresTable;
           }
       } catch (Exception e) {
           return new HighScoresTable(0);
       }
        return new HighScoresTable(0);
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public Map<String, Integer> getTable() {
        return table;
    }
}
