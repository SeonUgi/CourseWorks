public class BankAccount {
    private int accountNumber;
    private double balance;
    
    private static int nextAccountNumber = 1000;
    
    public BankAccount() {
        this(0.0);
    }
    
    public BankAccount(double initialBalance) {
        if(initialBalance >= 0.0)
            balance = initialBalance;
        else
            balance = 0.0;
        accountNumber = nextAccountNumber++;
    }
    
    public void deposit(double amount) {
        if(amount >= 0.0)
            balance = balance + amount;
    }
    
    public double getBalance() {
        return balance;
    }
}