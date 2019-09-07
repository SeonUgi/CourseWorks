package p3;

public class NumberPrinter implements Runnable{
	private static final int REPETITIONS = 100;
	private static final int DELAY = 1000;

	private int number;
	public NumberPrinter(int n)	{ number = n; }

	public void run()	{
		try{
			for (int i = 1; i <= REPETITIONS; i++){
				Thread t = Thread.currentThread();
				String name = t.getName();
				System.out.println(name + ": " + ++number);
				Thread.sleep(DELAY);         
			}
		}
		catch (InterruptedException exception) {}
	}
}

