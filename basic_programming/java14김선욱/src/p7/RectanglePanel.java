package p7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JPanel;

/**
 * �簢���� �׷����� �г�
 * �簢���� ��ġ�� �ٲٴ� �޼ҵ带 ���� �ִ�.
 * 
 */
public class RectanglePanel extends JPanel
{
	private Rectangle r;		// �ν��Ͻ� �ʵ�

	private final int BOX_WIDTH = 20;		// �簢�� ���� (�ȼ� ����)
	private final int BOX_HEIGHT = 20;		// �簢�� ���� (�ȼ� ����)

	private int dx = 1;			// ���� ���� ��ȸ �̵� �Ÿ� (�ȼ� ����)
	private int dy = 1;			// ���� ���� ��ȸ �̵� �Ÿ� (�ȼ� ����)

	private boolean pause = false;		// ���� ���� �����ΰ�?

	// ������
	public RectanglePanel()
	{
		setPreferredSize(new Dimension(200, 400));		// �г� ũ�� ����.

		r = new Rectangle(10, 10, BOX_WIDTH, BOX_HEIGHT);	// �簢�� ����.

		// Ÿ�̸ӿ� ����� �׼� ������.
		// Ÿ�̸Ӱ� Ÿ�Ӿƿ��� ������ actionPerformed �޼ҵ尡 ����ȴ�.
		// Ÿ�Ӿƿ��� ������ �簢���� (dx, dy) ��ŭ�� �����̰� �Ѵ�.
		class RectangleMover implements ActionListener
		{
			// Ÿ�̸� �̺�Ʈ�� �߻��� ������
			public void actionPerformed(ActionEvent event)
			{
				if(pause)		// ���� ���� �����̸� �ƹ� �ϵ� ���� ����.
					return;		// �׳� ����.

				r.translate(dx,  dy);	// �簢�� ��ġ ����(Rectangle�� �޼ҵ带 ȣ��).

				// �г��� ���� �� ���κп� �ٴٶ����� ���� �� �̵� ������ �ٲ�
				if(r.getX()+BOX_WIDTH >= getWidth())
					dx = -dx;

				// �г��� ���� ���κп� �ٴٶ�� ���� �� �̵� ������ �ٲ�
				if(r.getX() <= 0)
					dx =-dx;

				// �г��� �Ʒ��� ���κп� �ٴٶ����� ���� �� �̵� ������ �ٲ�
				if(r.getY()+BOX_HEIGHT >= getHeight())
					dy = -dy;

				// �г��� ���� ���κп� �ٴٶ�� ���� �� �̵� ������ �ٲ�
				if(r.getY() <= 0)
					dy = -dy;

				repaint();	// �г��� ���� �׷����� ��.
			}
		}

		RectangleMover listener = new RectangleMover();
		int interval = 10;

		// interval �ð� ���ݸ��� Ÿ�Ӿƿ��Ǿ� ActionEvent�� �߻���Ű�� Timer ��ü�� ����.
		// ActionEvent�� �߻��� ������ listener�� actionPerformed �޼ҵ尡 ȣ���.
		Timer t = new Timer(interval, listener);
		t.start();	// Ÿ�̸� ��ŸƮ				// Ÿ�̸Ӱ� ������ ������� �����.
	}

	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;

		// ����Ŭ������ JPanel�� paintComponent �޼ҵ带 ȣ����(�г��� ������ ������.)
		super.paintComponent(g2);

		g2.draw(r);		// �簢���� �׸�.
	}

	public void pause() {
		pause = true;
	}

	public void move(){
		pause = false;
	}
}
