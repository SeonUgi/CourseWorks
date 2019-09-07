package p7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Animation {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		final RectanglePanel panel = new RectanglePanel();
		frame.add(panel);

		final JButton button = new JButton("멈춤");
		frame.add(button, BorderLayout.SOUTH);

		// 버튼 등록할 액션 리스너.
		// 버튼을 누를 때마다 actionPerformed 메소드가 실행된다.
		// 그 때마다 작동과 멈춤이 바뀐다.

		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {

				if(button.getText().equals("멈춤")){
					panel.pause();
					button.setText("작동");
				}
				else if(button.getText().equals("작동")){
					panel.move();
					button.setText("멈춤");
				}
			}

		}
		button.addActionListener(new ButtonListener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setVisible(true);

	}

}
