package sprites.observe;

/**
 * The type Counter.
 */
public class Counter {
    private int currentNum;

    /**
     * Instantiates a new Counter.
     *
     * @param initailNum the initail num
     */
    public Counter(int initailNum) {
        this.currentNum = initailNum;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
   public void increase(int number) {
        this.currentNum += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
   public void decrease(int number) {
        this.currentNum -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.currentNum;
    }
}
