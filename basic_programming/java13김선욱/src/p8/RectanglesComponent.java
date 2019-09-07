package p8;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * ���콺 Ŭ���� ���� �簢�� �׸��� �׷����� �г�
 * �簢������ ��� ����Ʈ�� �־� ������
 */
public class RectanglesComponent extends JComponent{
	
	// ������
	public RectanglesComponent() {
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// ���� �簢������ ������ ��� ����Ʈ
		rectangles = new ArrayList<Rectangle2D.Double>();
		
		// MouseListener ����
		class ClickListener implements MouseListener
		{
			// ���콺�� ������ �� ȣ��Ǵ� �޼ҵ�
			public void mousePressed(MouseEvent event) {
				// ���콺�� ������ ��ġ (x, y)
				double x = (double)event.getX();
				double y = (double)event.getY();
				
				boolean removed = false;
				for (int i=0; i<rectangles.size(); i++){
					// ���� �簢���� �� ��� �ϳ��� (x, y)�� �����ϰ� �ִٸ�
					if(rectangles.get(i).contains(x, y)) {
						rectangles.remove(i--);				// �� �簢���� ������.
						removed = true;
					}
				}
				
				// �簢���� �������� ���� ��쿡�� (x,y) ��ġ�� �簢���� �߰���.
				// (x, y)�� �̹� �ִ� �簢���� �����̾��ٸ� �簢���� �߰����� ����.
				if(!removed) {
					Rectangle2D.Double r = new Rectangle2D.Double(x, y, LENGTH, LENGTH);
					rectangles.add(r);
				}
				repaint();
			}
			// ���콺�� ������ ���� ��, ���콺�� Ŭ���� ��,
			// ���콺�� �� ������Ʈ ���� �ø� ��, ���콺�� ġ�� ��
			// �Ʒ� �޼ҵ尡 ���� �����.
			// �Ʒ� �޼ҵ�� �߰�ȣ �ӿ� ��� �����Ƿ� �ƹ� �ϵ� ���� ����.
			public void mouseReleased(MouseEvent event) {}
			public void mouseClicked(MouseEvent event) {}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
		}
		addMouseListener(new ClickListener());	// �� ������Ʈ�� MouseLisener�� �����.
	}
	
	// �簢������ ��� �׸�
	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		for (Rectangle2D.Double r : rectangles) {
			g2.draw(r);
		}
	}
	
	// �簢������ ��� �ִ� array list�� ��ȯ.
	public ArrayList<Rectangle2D.Double> getRectangles() {
		return rectangles;
	}
	
	public void setRectangles(ArrayList<Rectangle2D.Double> list) {
		rectangles = list;
	}
	
	ArrayList<Rectangle2D.Double> rectangles;		// �ν��Ͻ� �ʵ�
	
	public static double LENGTH = 20;	// ���簢�� ���� ����
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
}
