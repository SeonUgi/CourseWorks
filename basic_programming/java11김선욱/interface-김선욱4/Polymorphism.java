import java.util.Random;

/**
 * Write a description of class Polymorphism here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Polymorphism
{
   Random random = new Random();
    public void test() {
        Measurable[] measure = {new Box(Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0), Math.floor(random.nextDouble()*100.0)), new BankAccount(Math.floor(random.nextDouble()*100.0))};
        System.out.println("MeasurableBox의 getMeasure : "+ measure[0].getMeasure());
        System.out.println("BankAccount의 getMeasure : " + measure[1].getMeasure());
    }
}
