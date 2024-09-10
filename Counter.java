
/**
 * A counter that will roll over to the min
 * value when attempting to increase it beyond the maximum value and to the max 
 * value when attempting to decrease it below the min value
 * 
 * @author Charles Hoot 
 * @version 4.0
 */
public class Counter
{
    // PUT PRIVATE DATA FIELDS HERE
    private int minn;
    private int maxx;
    private int value;
    private boolean rolledOver;

    /**
     * The default constructor for objects of class Counter.  Minimum is 0 and the maximum
     * is the largest possible integer.
     */
    public Counter()
    {
        this.minn = 0;
        this.maxx = (Integer.MAX_VALUE);
        this.value = 0;
        this.rolledOver = false;
    }
    
    
    /**
     * The alternate constructor for objects of class Counter.  The minimum and maximum values are given as parameters.
     * The counter starts at the minimum value.
     * @param min The minimum value that the counter can have
     * @param max The maximum value that the counter can have
     * */
    public Counter(int min, int max)
    {
        if (min > max)
            throw new CounterInitializationException("Min cannot be > than max");
        if (min == max)
            throw new CounterInitializationException("Min cannot = max");

        this.minn = min;
        this.maxx = max;
        this.value = min;
        this.rolledOver = false;
    }
    
    /**
     * Determine if two counters are in the same state
     *
     * @param  otherObject   the object to test against for equality
     * @return     true if the objects are in the same state
     */
    @Override
    public boolean equals(Object otherObject)
    {
        boolean result = false;
        //define reference of type counter, 
        Counter c = (Counter) otherObject;
        if (otherObject instanceof Counter) {
            if ((this.value == c.value) && (this.minn == c.minn) && (this.maxx == c.maxx) && (this.rolledOver == c.rolledOver)) {
                result = true;
            }
        }
        return result;
    }
    
    

    /**
     * Increases the counter by one
     */
    public void increase()
    {
        this.value++;
        if (this.value > this.maxx) {
            this.rolledOver = true;
            this.value = minn;
        } else {
            this.rolledOver = false;
        }
        // System.out.println("check increase");

        // ADD CODE TO INCREASE THE VALUE OF THE COUNTER AND HANDLE ROLLOVER
    }
 
 
     /**
     * Decreases the counter by one
     */
    public void decrease()
    {
        this.value--;
        if (this.value < this.minn) {
            this.rolledOver = true;
            this.value = maxx;
        } else {
            this.rolledOver = false;
        }


        // ADD CODE TO DECREASE THE VALUE OF THE COUNTER AND HANDLE ROLLOVER
    }
    
    /**
     * Get the value of the counter
     *
     * @return     the current value of the counter
     */
    public int value()
    {
        // CHANGE THE RETURN TO GIVE THE CURRENT VALUE OF THE COUNTER
        return this.value;
		
    }
    
    
    /**
     * Accessor that allows the client to determine if the counter
     *             rolled over on the last count
     *
     * @return     true if the counter rolled over
     */
    public boolean rolledOver()
    {
        // CHANGE THE RETURN TO THE ROLLOVER STATUS OF THE COUNTER
        return this.rolledOver;
    }
    
    /**
     * Override the toString method to provide a more informative
     * description of the counter
     *
     * @return     a descriptive string about the object
     */
    public String toString()
    {
        // CHANGE THE RETURN TO A DESCRIPTION OF THE COUNTER
        // MUST FOLLOW THE FOLLOWING FORMAT:
        //Counter: value=1 min=1 max=9 rolled over=false

        return ("Counter: value=" + this.value + " min=" + this.minn + " max="+ this.maxx + " rolled over=" + this.rolledOver());		
    }
 
}
