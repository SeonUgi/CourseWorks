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

		final JButton button = new JButton("����");
		frame.add(button, BorderLayout.SOUTH);

		// ��ư ����� �׼� ������.
		// ��ư�� ���� ������ actionPerformed �޼ҵ尡 ����ȴ�.
		// �� ������ �۵��� ������ �ٲ��.

		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {

				if(button.getText().equals("����")){
					panel.pause();
					button.setText("�۵�");
				}
				else if(button.getText().equals("�۵�")){
					panel.move();
					button.setText("����");
				}
			}

		}
		button.addActionListener(new ButtonListener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setVisible(true);

	}

}
