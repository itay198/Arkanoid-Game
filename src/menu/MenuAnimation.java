package menu;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.LinkedList;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private boolean stop;
    private String title;
    private Map<String, T> selections;
    private List<String> messages;
    private KeyboardSensor keyboardSensor;
    private Map<String, Menu> submenus;

    /**
     * Instantiates a new Menu animation.
     *
     * @param title          the title
     * @param keyboardSensor the keyboard sensor
     */
    public MenuAnimation(String title, KeyboardSensor keyboardSensor) {
        this.stop = false;
        this.title = title;
        selections = new TreeMap<>();
        messages = new LinkedList<>();
        this.keyboardSensor = keyboardSensor;
        submenus = new TreeMap<>();
    }
    @Override
    public void addSelection(String key, String message, T returnVal) {
        selections.put(key, returnVal);
        messages.add(message);
    }

    @Override
    public T getStatus() {
        for (String itr:selections.keySet()) {
            if (keyboardSensor.isPressed(itr)) {
                return selections.get(itr);
            }
        }
        return null;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        submenus.put(key, subMenu);
        messages.add(message);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        for (int i = 0; i < selections.size(); i++) {
            d.drawText(100, 100 + i * 100, messages.get(i), 30);
        }
        for (String itr:selections.keySet()) {
            if (keyboardSensor.isPressed(itr)) {
                stop = true;
            }
        }

    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    /**
     * Sets stop.
     *
     * @param stop1 the stop 1
     */
    public void setStop(boolean stop1) {
        this.stop = stop1;
    }
}
