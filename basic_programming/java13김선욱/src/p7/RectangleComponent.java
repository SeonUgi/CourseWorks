package p7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class RectangleComponent extends JComponent {
	
	private Rectangle2D.Double r;	// 인스턴스 필드
	
	// 구성자
	public RectangleComponent() {
		
		// Rectangle2D.Double <-- 점을 포함한 전체가 클래스 이름임.
		// 가로, 세로 등 크기와 위치를 double 타입 값으로 표현함.
		// (참고: Rectangle 클래스의 경우 가로, 세로, 위치를  int 타입으로 표현했음)
		r = new Rectangle2D.Double(50.0, 50.0, 200.0, 200.0);
		
		// 컴포넌트의 크기를 지정해 줌.
		// 이값은 배치관리자가 자리 배치할 때 사용함.
		setPreferredSize(new Dimension(300, 300));
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(r);		// 이 컴포넌트에 r를 그려줌.
	}
}
