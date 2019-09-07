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
		setTitle("객체를 파일에 저장하는 예");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// "파일"메뉴와 "열기"메뉴아이템 구성
		// 메뉴아이템을 메뉴에 넣음
		JMenu menu = new JMenu("파일");
		JMenuItem menuItem = new JMenuItem("열기");
		menu.add(menuItem);

		// ActionListener 선언
		class OpenMenuItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event){
				File f = new File("rectangles.dat");
				if (!f.exists())	// 데이터 파일이 존재하지 않으면 아무 일도 하지 않음
					return;
				else {	// 데이터 파일로부터 사각형 리스트를 읽어 이를 패널에 넘겨줌
					ObjectInputStream in = null;
					try {

						in = new ObjectInputStream(new FileInputStream(f));
						ArrayList<Rectangle2D.Double> rectangles =
								(ArrayList<Rectangle2D.Double>)in.readObject();
						component.setRectangles(rectangles);	// 사각형 리스트를 패널에 넘겨줌
						repaint();	// 방금 읽은 사각형들이 화면에 나타나게 함

					} catch(IOException e){
						System.out.println("파일을 읽을 수가 없습니다.");
						e.printStackTrace();
					} catch(ClassNotFoundException e){
						System.out.println("무엇인지 알 수 없는 객체가 파일에 들어 잇습니다.");
						e.printStackTrace();
					}
				}
			}
		}

		// "열기"메뉴아이템에 ActionListener를 등록함.
		menuItem.addActionListener(new OpenMenuItemListener());

		// "저장"메뉴아이템 구성
		// 메뉴아이템을 메뉴에 넣음
		JMenuItem menuItem1 = new JMenuItem("저장");
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
					System.out.println("파일을 읽을 수가 없습니다.");
					e.printStackTrace();
				}
			}
		}

		//"저장"메뉴아이템에 ActionListener를 등록함.
		menuItem1.addActionListener(new SaveMenuItemListener());

		// 메뉴바를 만들고 메뉴를 메뉴바에 넣은 후
		// 이 프레임에 메뉴바를 설정함.
		JMenuBar menubar = new JMenuBar();
		menubar.add(menu);
		setJMenuBar(menubar);

		// RectanglesComponent를 구성하여 이 프레임에 넣음.
		component = new RectanglesComponent();
		add(component, BorderLayout.CENTER);

		pack();
	}

	private RectanglesComponent component;	// 그림이 그려지는 패널

	public static void main(String[] args)
	{
		RectanglesFrame2 frame = new RectanglesFrame2();
		frame.setVisible(true);
	}
}