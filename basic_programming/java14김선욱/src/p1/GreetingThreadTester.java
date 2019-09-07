package p1;
import java.util.Date;

/**
    This program tests the greeting thread by running two
    threads in parallel.
 */
public class GreetingThreadTester
{
	public static void main(String[] args)
	{
		Thread t1 = new GreetingRunnable("Hello, World!");
		Thread t2 = new GreetingRunnable("Goodbye, World!");
		t1.start();
		t2.start();
	}
}