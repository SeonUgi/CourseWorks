package p2;
import javax.swing.JOptionPane;

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

		JOptionPane ques = new JOptionPane();
		String input = JOptionPane.showInputDialog("몇번째 스레드를 인터럽트??");

		if (input.equals("1")) {

			t1.interrupt();
		}
		else if (input.equals("2")) {

			t2.interrupt();
		}

		input = JOptionPane.showInputDialog("몇번째 스레드를 인터럽트??");
		if (input.equals("1"))
			t1.interrupt();
		else if (input.equals("2"))
			t2.interrupt();
	}
}