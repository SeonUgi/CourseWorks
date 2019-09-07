package p8;

/**
    This program runs two threads that deposit and withdraw
    money from the same bank account. 
 */
public class BankAccountThreadTester
{
	public static void main(String[] args)
	{
		BankAccount account = new BankAccount();
		final double AMOUNT = 100;
		final int REPETITIONS = 1000;

		DepositRunnable d = new DepositRunnable(
				account, AMOUNT, REPETITIONS);
		WithdrawRunnable w = new WithdrawRunnable(
				account, AMOUNT, REPETITIONS);

		Thread t1 = new Thread(d);
		Thread t2 = new Thread(w);

		t1.start();
		t2.start();
	}
}