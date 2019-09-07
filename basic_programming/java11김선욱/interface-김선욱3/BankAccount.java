
/**
 * Write a description of class BankAccount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BankAccount implements Measurable
{
    private double balance;
    
    public BankAccount() {
        balance = 0;
    }
    
    public BankAccount(double balance) {
        this.balance = balance;
    }
    
    public double getMeasure() {
        return balance;
    }
    
    public String toString() {
        return "BankAccount["+balance+"]";
    }
}
