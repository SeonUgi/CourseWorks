package p6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JPanel;

public class CirclePanel extends JPanel{
	
	// paintComponent �޼ҵ�� �ʿ��� ������ �ý��ۿ� ���� �ڵ����� ȣ���.
	// �ʿ��� ��
	// - ���α׷��� ó�� ����� ��.
	// - �������� ���������� �ּ�ȭ�ߴٰ� �ٽ� â���� ������ų ��.
	// - �������� ũ�⸦ ������ �� ��.
	
	// �ý����� paintComponent�� ȣ���� �� Graphics ��ü�� ���ڷ� �Ѱ���.
	// �� Graphics ��ü�� �� ������Ʈ�� �׸��� �׸��� ���� �ʿ��� ������ ���� ����.
	// ������Ʈ�� �׸��� �׸��� ������ �� Graphics ��ü�� �̿���.
	
	public void paintComponent(Graphics g) // �� �޼ҵ��� ������ �Ķ���� Ÿ���� Graphics��.
	{
		// �׷��� ������ �ý����� �Ѱ��ִ� ��ü�� Ÿ���� Graphics2D��.
		// Graphics2D�� Graphics�� ����Ŭ�����μ� �� ���� ����� ���� ����.
		// Graphics2D Ÿ�� ���������� ����ϸ� �� ���� ����� ����� �� ����.
		// ���������� ������ Ÿ�Կ� ���� ȣ�� ������ �޼ҵ尡 �����ȴ�!
		
		//Recover Graphics2D
		Graphics2D g2 = (Graphics2D)g;
		
		super.paintComponent(g2);	// JPanel�� Ȯ���� ���� �� ������ �� ������� ��.
		
		// Construct and draw two circles
		
		Ellipse2D.Double circle1 = new Ellipse2D.Double(30, 30, 100, 100);
		g2.setColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
		g2.fill(circle1);
		
		Ellipse2D.Double circle2 = new Ellipse2D.Double(160, 30, 100, 100);
		g2.setColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
		g2.draw(circle2);
		
	}
	private Random r = new Random();	// <---- CircleComponent�� �ν��Ͻ� �ʵ� 

}
