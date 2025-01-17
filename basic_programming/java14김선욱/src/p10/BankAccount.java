package p10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
    A bank account has a balance that can be changed by 
    deposits and withdrawals.
 */
public class BankAccount
{
	/**
       Constructs a bank account with a zero balance.
	 */
	public BankAccount()
	{
		balanceLock = new ReentrantLock();

		balance = 0;
	}

	/**
       Deposits money into the bank account.
       @param amount the amount to deposit
	 */ 
	public void deposit(double amount)
	{
		balanceLock.lock();
		try { 
			System.out.print("Depositing " + amount);
			double newBalance = balance + amount;
			System.out.println(", new balance is " + newBalance);
			balance = newBalance;
		}
		finally
		{
			balanceLock.unlock();
		}
	}

	/**
	       Withdraws money from the bank account.
	       @param amount the amount to withdraw
	 */
	public void withdraw(double amount)
	{
		balanceLock.lock();
		try
		{
			while (balance < amount) {
				// 잔금이 증가할 때까지 기다린다.
			}
			System.out.print("Withdrawing " + amount);
			double newBalance = balance - amount;
			System.out.println(", new balance is " + newBalance);
			balance = newBalance;
		}
		finally
		{
			balanceLock.unlock();
		}
	} 
	/**
	       Gets the current balance of the bank account.
	       @return the current balance
	 */
	public double getBalance()
	{
		return balance;
	}

	private double balance;
	private Lock balanceLock;

} 