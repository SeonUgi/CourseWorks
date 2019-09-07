import java.util.Random;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * Write a description of class BoxTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoxTester
{
    Random random = new Random();
    public void test() {
        
        
        Box box1 = new Box(Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0));
        Box box2 = new Box(Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0));
        Box box3 = new Box(Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0));
        
        ArrayList<Box> boxesArray = new ArrayList<Box>();
        Box[] boxes = new Box[3];
        
        boxesArray.add(box1);
        boxesArray.add(box2);
        boxesArray.add(box3);
        
        boxes[0] = box1;
        boxes[1] = box2;
        boxes[2] = box3;
        
        for(Box box : boxes) {
            System.out.println(box.toString());
        }
        
        for (Box box : boxesArray) {
            System.out.println(box.toString());
        }
    }
}
