package p7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RectangleFrame extends JFrame {
	// 구성자
	RectangleFrame() {
		setTitle("메뉴 연습");
		
		RectangleComponent component = new RectangleComponent();
		add(component, BorderLayout.CENTER);
		
		JMenu menu = new JMenu("파일");
		
		JMenuItem menuItem = new JMenuItem("열기");
		class OpenMenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				System.out.println("\"열기\"메뉴아이템이 선택되었습니다.");
			}
		}
		menuItem.addActionListener(new OpenMenuItemListener());
		menu.add(menuItem);
		
		menuItem = new JMenuItem("저장");
		class SaveMenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				System.out.println("\"저장\"메뉴아이템이 선택되었습니다.");
			}
		}
		menuItem.addActionListener(new SaveMenuItemListener());
		menu.add(menuItem);
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(menu);
		
		setJMenuBar(menubar);		// 프레임에 메뉴바를 설정함.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args) {
		RectangleFrame frame = new RectangleFrame();
		frame.setVisible(true);;
	}
}
