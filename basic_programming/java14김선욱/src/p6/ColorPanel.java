package p6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPanel extends JPanel {

	private static final ActionListener Interrupter = null;
	private Color color;		// �ν��Ͻ� �ʵ�

	/**
	 * ������
	 */
	public ColorPanel() {
		setPreferredSize(new Dimension(300, 300));
		color = new Color(0, 0, 0);		// ������.
		setBackground(color);			// �г��� �������� ����.
	}

	/**
	 * ȭ���� ����������.
	 */
	public void reset(){
		color = new Color(0, 0, 0);
		setBackground(color);
	}

	/**
	 * ȭ���� ������ �ٲ��ش�.
	 */
	public void changeColor() {
		int red = color.getRed();
		color = new Color((red + 30) % 255, 0, 0);
		setBackground(color);
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		ColorPanel panel = new ColorPanel();
		frame.add(panel);
		JButton stopButton = new JButton("��Ʈ�� �����忡 ���ͷ�Ʈ �ɱ�");
		frame.add(stopButton, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		// ��Ʈ�� ������ : �г��� ������ �ֱ������� �ٲ��ش�.
		final ColorChangeThread colorChanger = new ColorChangeThread(panel);
		colorChanger.start();

		// ��ư�� ��ϵǴ� �׼� ������
		// ��ư�� Ŭ���Ǹ� ��Ʈ�� �����忡 ���ͷ�Ʈ�� �ɾ��ش�.
		class Interrupter implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorChanger.interrupt();
			}
		}
		Interrupter interrupter = new Interrupter();
		stopButton.addActionListener(interrupter);
	}

}
