package p7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RectangleFrame2 extends JFrame {
	// ������
	RectangleFrame2() {
		setTitle("�޴� ����");
		
		final RectangleComponent2 component = new RectangleComponent2();
		add(component, BorderLayout.CENTER);
		
		JMenu menu = new JMenu("����");		// menu ����  ��
				
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
		
		JMenu menu1 = new JMenu("����");			// menu1 ���� ��
		JMenuItem menu1Item = new JMenuItem("Ȯ��");
		class LargerMenu1ItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				component.larger();
			}
		}
		menu1Item.addActionListener (new LargerMenu1ItemListener());
		menu1.add(menu1Item);

		menu1Item = new JMenuItem("���");
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
		
		
		setJMenuBar(menubar);		// �����ӿ� �޴��ٸ� ������.
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args) {
		RectangleFrame2 frame = new RectangleFrame2();
		frame.setVisible(true);;
	}
}