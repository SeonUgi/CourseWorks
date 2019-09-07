import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class DataSetTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DataSetTester
{
   private DataSet dataset;
   private Box box;
   private ArrayList<Box> list;
   private Random random;

   public DataSetTester() {
       random = new Random();
       list = new ArrayList<Box>();
       dataset = new DataSet();
    }
   
   public void test() {
       for(int i = 0; i<100; i++) {
           box = new Box(Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0));
           //System.out.println(i+" "+box.toString());
           list.add(box);
           dataset.add(box);
       }
    
       System.out.println("부피가 가장 큰 box : "+dataset.getMaximum());
       System.out.println("box들 부피의 평균 : "+dataset.getAverage()); 
        
    }
}
