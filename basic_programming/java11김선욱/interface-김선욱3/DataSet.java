
/**
 * Write a description of class DataSet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DataSet
{
    private double sum;
    private Measurable maximum;
    private Measurable minimum;
    private int count;

    /**
     * Constructs an empty data set.
     */
    public DataSet() {
        sum = 0;
        count = 0;
        maximum = null;
        minimum = null;
    }

    /**
     * Adds a data value to the data set.
     * @param x a data value
     */
    public void add(Measurable x) {
        sum = sum + x.getMeasure();
        if (count == 0 || maximum.getMeasure() < x.getMeasure())
            maximum = x;
        if (count == 0 || minimum.getMeasure() > x.getMeasure())
            minimum = x;
        count ++;
    }

    /**
     * Gets the average of the added data.
     * @return the average or 0 if no data has been added
     */
    public double getAverage() {
        if (count <= 0)
            return 0.0;
        else
            return sum/count;
    }

    /**
     * Gets the largest of the added data.
     * @return the maximum or null if no data has been added
     */
    public Measurable getMaximum() {
        return maximum;
    }

    /**
     * Gets the smallest of the added data.
     * @return the minimum or null if no data has been added
     */
    public Measurable getMinimum() {
        return minimum;
    }
}
