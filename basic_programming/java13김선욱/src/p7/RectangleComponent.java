package p7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class RectangleComponent extends JComponent {
	
	private Rectangle2D.Double r;	// �ν��Ͻ� �ʵ�
	
	// ������
	public RectangleComponent() {
		
		// Rectangle2D.Double <-- ���� ������ ��ü�� Ŭ���� �̸���.
		// ����, ���� �� ũ��� ��ġ�� double Ÿ�� ������ ǥ����.
		// (����: Rectangle Ŭ������ ��� ����, ����, ��ġ��  int Ÿ������ ǥ������)
		r = new Rectangle2D.Double(50.0, 50.0, 200.0, 200.0);
		
		// ������Ʈ�� ũ�⸦ ������ ��.
		// �̰��� ��ġ�����ڰ� �ڸ� ��ġ�� �� �����.
		setPreferredSize(new Dimension(300, 300));
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(r);		// �� ������Ʈ�� r�� �׷���.
	}
}
