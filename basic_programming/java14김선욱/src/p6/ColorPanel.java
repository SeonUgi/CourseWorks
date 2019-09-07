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
	private Color color;		// 인스턴스 필드

	/**
	 * 구성자
	 */
	public ColorPanel() {
		setPreferredSize(new Dimension(300, 300));
		color = new Color(0, 0, 0);		// 검은색.
		setBackground(color);			// 패널의 배경색으로 설정.
	}

	/**
	 * 화면을 검정색으로.
	 */
	public void reset(){
		color = new Color(0, 0, 0);
		setBackground(color);
	}

	/**
	 * 화면의 색갈을 바꿔준다.
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
		JButton stopButton = new JButton("콘트롤 쓰레드에 인터럽트 걸기");
		frame.add(stopButton, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		// 콘트롤 쓰레드 : 패널의 색깔을 주기적으로 바꿔준다.
		final ColorChangeThread colorChanger = new ColorChangeThread(panel);
		colorChanger.start();

		// 버튼에 등록되는 액션 리스너
		// 버튼이 클릭되면 콘트롤 쓰레드에 인터럽트를 걸어준다.
		class Interrupter implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				colorChanger.interrupt();
			}
		}
		Interrupter interrupter = new Interrupter();
		stopButton.addActionListener(interrupter);
	}

}
