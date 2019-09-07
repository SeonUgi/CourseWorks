package p7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RectangleFrame extends JFrame {
	// ������
	RectangleFrame() {
		setTitle("�޴� ����");
		
		RectangleComponent component = new RectangleComponent();
		add(component, BorderLayout.CENTER);
		
		JMenu menu = new JMenu("����");
		
		JMenuItem menuItem = new JMenuItem("����");
		class OpenMenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				System.out.println("\"����\"�޴��������� ���õǾ����ϴ�.");
			}
		}
		menuItem.addActionListener(new OpenMenuItemListener());
		menu.add(menuItem);
		
		menuItem = new JMenuItem("����");
		class SaveMenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				System.out.println("\"����\"�޴��������� ���õǾ����ϴ�.");
			}
		}
		menuItem.addActionListener(new SaveMenuItemListener());
		menu.add(menuItem);
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(menu);
		
		setJMenuBar(menubar);		// �����ӿ� �޴��ٸ� ������.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args) {
		RectangleFrame frame = new RectangleFrame();
		frame.setVisible(true);;
	}
}
