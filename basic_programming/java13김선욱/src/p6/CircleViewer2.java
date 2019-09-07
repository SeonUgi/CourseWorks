package p6;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CircleViewer2 {

	
	public static void main(String[] args) {
		
		final CirclePanel2 panel = new CirclePanel2();
		final JFrame frame = new JFrame();
		final JButton button = new JButton("Reset");

		final int FRAME_WIDTH = 300;
		final int FRAME_HEIGHT = 200;

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Two circles");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);
		frame.add(button, BorderLayout.SOUTH);

		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				panel.repaint();
				frame.add(panel);
				frame.add(button, BorderLayout.SOUTH);
			}
		};

		button.addActionListener(listener);


		// 창을 닫으면 애플리케이션이 종료되도록 함.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}
}
