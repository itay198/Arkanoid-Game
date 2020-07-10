package readers;

import java.awt.Color;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Color from string java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
// parse color definition and return the specified color.
    public java.awt.Color colorFromString(String s) {
        if (s.startsWith("color")) {

            if (s.startsWith("color(RGB(")) {
                String[] rgb =
                        s.split("color")[1].split("\\(")[2]
                                .split("\\)")[0].split("\\)")[0].split(",");
                return new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            }
            String er = (s.split("color")[1].split("\\(")[1].split("\\)")[0]);
            java.lang.reflect.Field field = null;
            try {
                field = Color.class.getField(er);
                Color color = (Color) field.get(null);
                return color;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
