package p7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RectangleFrame2 extends JFrame {
	// 구성자
	RectangleFrame2() {
		setTitle("메뉴 연습");
		
		final RectangleComponent2 component = new RectangleComponent2();
		add(component, BorderLayout.CENTER);
		
		JMenu menu = new JMenu("파일");		// menu 파일  탭
				
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
		
		JMenu menu1 = new JMenu("보기");			// menu1 보기 탭
		JMenuItem menu1Item = new JMenuItem("확대");
		class LargerMenu1ItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				component.larger();
			}
		}
		menu1Item.addActionListener (new LargerMenu1ItemListener());
		menu1.add(menu1Item);

		menu1Item = new JMenuItem("축소");
		class SmallerMenu1ItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				component.smaller();
			}
		}
		menu1Item.addActionListener(new SmallerMenu1ItemListener());
		menu1.add(menu1Item);
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(menu);
		menubar.add(menu1);
		
		
		setJMenuBar(menubar);		// 프레임에 메뉴바를 설정함.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args) {
		RectangleFrame2 frame = new RectangleFrame2();
		frame.setVisible(true);;
	}
}