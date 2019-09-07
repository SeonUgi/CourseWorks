package p6;

import javax.swing.JFrame;

public class CircleViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		final int FRAME_WIDTH = 300;
		final int FRAME_HEIGHT = 200;
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Two circles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CirclePanel panel = new CirclePanel();
		frame.add(panel);
		
		// â�� ������ ���ø����̼��� ����ǵ��� ��.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}
