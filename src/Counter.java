package basic;
/**
  * counter class -  simple class used for counting things.
  */
public class Counter {
    private int count = 0;
    /**
    * add number to current count.
    * @param number - number to add.
    */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * subtract number from current count.
     * @param number - number to decrease.
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * getter for current count.
     * @return counter value.
     */
    public int getValue() {
        return this.count;
    }
}
