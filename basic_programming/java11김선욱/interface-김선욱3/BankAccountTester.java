import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class BankAccountTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BankAccountTester
{
    private DataSet dataset;
   private BankAccount ba;
   private ArrayList<BankAccount> list;
   private Random random;

   public BankAccountTester() {
       random = new Random();
       list = new ArrayList<BankAccount>();
       dataset = new DataSet();
    }
   
   public void test() {
       for(int i = 0; i<100; i++) {
           ba = new BankAccount(Math.floor(random.nextDouble()*100.0));
           //System.out.println(i+" "+box.toString());
           list.add(ba);
           dataset.add(ba);
       }
    
       System.out.println("balance가 가장 큰 계좌 : "+dataset.getMaximum());
       System.out.println("balance가 가장 작은 계좌 : "+dataset.getMinimum());
       System.out.println("balance의 평균 : "+dataset.getAverage());
        
    }
}
