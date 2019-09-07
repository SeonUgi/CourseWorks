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
 * 사각형이 그려지는 패널
 * 사각형의 위치를 바꾸는 메소드를 갖고 있다.
 * 
 */
public class RectanglePanel extends JPanel
{
	private Rectangle r;		// 인스턴스 필드

	private final int BOX_WIDTH = 20;		// 사각형 가로 (픽셀 단위)
	private final int BOX_HEIGHT = 20;		// 사각형 세로 (픽셀 단위)

	private int dx = 1;			// 가로 방향 일회 이동 거리 (픽셀 단위)
	private int dy = 1;			// 세로 방향 일회 이동 거리 (픽셀 단위)

	private boolean pause = false;		// 현재 멈춤 상태인가?

	// 구성자
	public RectanglePanel()
	{
		setPreferredSize(new Dimension(200, 400));		// 패널 크기 설정.

		r = new Rectangle(10, 10, BOX_WIDTH, BOX_HEIGHT);	// 사각형 구성.

		// 타이머에 등록할 액션 리스너.
		// 타이머가 타임아웃될 때마다 actionPerformed 메소드가 실행된다.
		// 타임아웃될 때마다 사각형이 (dx, dy) 만큼씩 움직이게 한다.
		class RectangleMover implements ActionListener
		{
			// 타이머 이벤트가 발생할 때마다
			public void actionPerformed(ActionEvent event)
			{
				if(pause)		// 현재 멈춤 상태이면 아무 일도 하지 않음.
					return;		// 그냥 리턴.

				r.translate(dx,  dy);	// 사각형 위치 변경(Rectangle의 메소드를 호출).

				// 패널의 오른 쪽 끝부분에 다다랐으면 가로 축 이동 방향을 바꿈
				if(r.getX()+BOX_WIDTH >= getWidth())
					dx = -dx;

				// 패널의 왼쪽 끝부분에 다다랐어도 가로 축 이동 방향을 바꿈
				if(r.getX() <= 0)
					dx =-dx;

				// 패널의 아래쪽 끝부분에 다다랐으면 세로 축 이동 방향을 바꿈
				if(r.getY()+BOX_HEIGHT >= getHeight())
					dy = -dy;

				// 패널의 위쪽 끝부분에 다다랐어도 세로 축 이동 방향을 바꿈
				if(r.getY() <= 0)
					dy = -dy;

				repaint();	// 패널이 새로 그려지게 함.
			}
		}

		RectangleMover listener = new RectangleMover();
		int interval = 10;

		// interval 시간 간격마다 타임아웃되어 ActionEvent를 발생시키는 Timer 객체를 만듬.
		// ActionEvent가 발생할 때마다 listener의 actionPerformed 메소드가 호출됨.
		Timer t = new Timer(interval, listener);
		t.start();	// 타이머 스타트				// 타이머가 별도의 쓰레드로 실행됨.
	}

	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;

		// 수퍼클래스인 JPanel의 paintComponent 메소드를 호출함(패널을 깨끗이 지워줌.)
		super.paintComponent(g2);

		g2.draw(r);		// 사각형을 그림.
	}

	public void pause() {
		pause = true;
	}

	public void move(){
		pause = false;
	}
}
