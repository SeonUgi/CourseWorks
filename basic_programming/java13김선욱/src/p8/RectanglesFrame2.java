package p8;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RectanglesFrame2 extends JFrame
{

	RectanglesFrame2()
	{
		setTitle("��ü�� ���Ͽ� �����ϴ� ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// "����"�޴��� "����"�޴������� ����
		// �޴��������� �޴��� ����
		JMenu menu = new JMenu("����");
		JMenuItem menuItem = new JMenuItem("����");
		menu.add(menuItem);

		// ActionListener ����
		class OpenMenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				File f = new File("rectangles.dat");
				if (!f.exists())	// ������ ������ �������� ������ �ƹ� �ϵ� ���� ����
					return;
				else {	// ������ ���Ϸκ��� �簢�� ����Ʈ�� �о� �̸� �гο� �Ѱ���
					ObjectInputStream in = null;
					try {

						in = new ObjectInputStream(new FileInputStream(f));
						ArrayList<Rectangle2D.Double> rectangles =
								(ArrayList<Rectangle2D.Double>)in.readObject();
						component.setRectangles(rectangles);	// �簢�� ����Ʈ�� �гο� �Ѱ���
						repaint();	// ��� ���� �簢������ ȭ�鿡 ��Ÿ���� ��

					} catch(IOException e){
						System.out.println("������ ���� ���� �����ϴ�.");
						e.printStackTrace();
					} catch(ClassNotFoundException e){
						System.out.println("�������� �� �� ���� ��ü�� ���Ͽ� ��� �ս��ϴ�.");
						e.printStackTrace();
					}
				}
			}
		}

		// "����"�޴������ۿ� ActionListener�� �����.
		menuItem.addActionListener(new OpenMenuItemListener());

		// "����"�޴������� ����
		// �޴��������� �޴��� ����
		JMenuItem menuItem1 = new JMenuItem("����");
		menu.add(menuItem1);

		class SaveMenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				ObjectOutputStream out = null;
				try
				{
					out = new ObjectOutputStream(new FileOutputStream("rectangles.dat"));
					ArrayList<Rectangle2D.Double> rectangles = new ArrayList<Rectangle2D.Double>();
					rectangles = component.getRectangles();
					out.writeObject(rectangles);
					out.close();
				}
				catch(IOException e)
				{
					System.out.println("������ ���� ���� �����ϴ�.");
					e.printStackTrace();
				}
			}
		}

		//"����"�޴������ۿ� ActionListener�� �����.
		menuItem1.addActionListener(new SaveMenuItemListener());

		// �޴��ٸ� ����� �޴��� �޴��ٿ� ���� ��
		// �� �����ӿ� �޴��ٸ� ������.
		JMenuBar menubar = new JMenuBar();
		menubar.add(menu);
		setJMenuBar(menubar);

		// RectanglesComponent�� �����Ͽ� �� �����ӿ� ����.
		component = new RectanglesComponent();
		add(component, BorderLayout.CENTER);

		pack();
	}

	private RectanglesComponent component;	// �׸��� �׷����� �г�

	public static void main(String[] args)
	{
		RectanglesFrame2 frame = new RectanglesFrame2();
		frame.setVisible(true);
	}
}