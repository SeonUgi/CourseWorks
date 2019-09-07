package p6;

public class ColorChangeThread extends Thread {

	private ColorPanel panel;		// 색깔 변경을 할 패널.

	private static final int REPETITIONS = 100;		// 반복 회수.
	private static final int DELAY = 500;			// 색깔 변경 주기 (ms 단위).

	// 구성자
	ColorChangeThread(ColorPanel panel){
		this.panel = panel;
	}

	public void run() 
	{
		try
		{
			for(int i = 1; i <= REPETITIONS; i++)
			{
				sleep(DELAY);			// 이 쓰레드가 DELAY 동안 잠들게 함.
				panel.changeColor();	// 패널에게 색깔을 바꾸도록 지시함.
			}
		}
		catch (InterruptedException exception)
		{
			panel.reset();
		}
	}

}
