package p6;

public class ColorChangeThread extends Thread {

	private ColorPanel panel;		// ���� ������ �� �г�.

	private static final int REPETITIONS = 100;		// �ݺ� ȸ��.
	private static final int DELAY = 500;			// ���� ���� �ֱ� (ms ����).

	// ������
	ColorChangeThread(ColorPanel panel){
		this.panel = panel;
	}

	public void run() 
	{
		try
		{
			for(int i = 1; i <= REPETITIONS; i++)
			{
				sleep(DELAY);			// �� �����尡 DELAY ���� ���� ��.
				panel.changeColor();	// �гο��� ������ �ٲٵ��� ������.
			}
		}
		catch (InterruptedException exception)
		{
			panel.reset();
		}
	}

}
