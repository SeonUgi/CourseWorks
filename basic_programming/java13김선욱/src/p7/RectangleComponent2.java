package p7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class RectangleComponent2 extends JComponent {
	
	private Rectangle2D.Double r;	// �ν��Ͻ� �ʵ�
	double size;
	double location;
	
	// ������
	public RectangleComponent2() {
		location = 50.0;
		size = 200.0;
		
		// Rectangle2D.Double <-- ���� ������ ��ü�� Ŭ���� �̸���.
		// ����, ���� �� ũ��� ��ġ�� double Ÿ�� ������ ǥ����.
		// (����: Rectangle Ŭ������ ��� ����, ����, ��ġ��  int Ÿ������ ǥ������)
		r = new Rectangle2D.Double(location, location, size, size);
		
		// ������Ʈ�� ũ�⸦ ������ ��.
		// �̰��� ��ġ�����ڰ� �ڸ� ��ġ�� �� �����.
		setPreferredSize(new Dimension(300, 300));
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(r);		// �� ������Ʈ�� r�� �׷���.
	}
	
	public void larger() {
		size = size + 20.0;
		r = new Rectangle2D.Double(location, location, size, size);
		this.repaint();
	}
	
	public void smaller() {
		size -= 20.0;
		if (size<=0)
			size = 0.0;
		else ;
		r = new Rectangle2D.Double(location, location, size, size);
		this.repaint();
	}
	
}



