
/**
 * Write a description of class Box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Box implements Measurable
{
    private double length;
    private double width;
    private double height;
    
    public Box() {
        ;
    }
    
    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    
    public double getMeasure() {
        double measure = length * width * height;       // box의 부피
        return measure;
    }
    
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public String toString() {
        String string = "Box[length="+length+", width="+width+", height="+height+"]";
        return string;
    }
}
