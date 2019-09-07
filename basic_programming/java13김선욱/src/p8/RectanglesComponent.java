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
 * 마우스 클릭에 의해 사각형 그림이 그려지는 패널
 * 사각형들을 어레이 리스트에 넣어 관리함
 */
public class RectanglesComponent extends JComponent{
	
	// 구성자
	public RectanglesComponent() {
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// 작은 사각형들을 저장할 어레이 리스트
		rectangles = new ArrayList<Rectangle2D.Double>();
		
		// MouseListener 선언
		class ClickListener implements MouseListener
		{
			// 마우스가 눌려질 대 호출되는 메소드
			public void mousePressed(MouseEvent event) {
				// 마우스가 눌려진 위치 (x, y)
				double x = (double)event.getX();
				double y = (double)event.getY();
				
				boolean removed = false;
				for (int i=0; i<rectangles.size(); i++){
					// 기존 사각형들 중 어느 하나라도 (x, y)를 포함하고 있다면
					if(rectangles.get(i).contains(x, y)) {
						rectangles.remove(i--);				// 그 사각형을 제거함.
						removed = true;
					}
				}
				
				// 사각형을 제거하지 않은 경우에만 (x,y) 위치에 사각형을 추가함.
				// (x, y)가 이미 있는 사각형의 안쪽이었다면 사각형을 추가하지 않음.
				if(!removed) {
					Rectangle2D.Double r = new Rectangle2D.Double(x, y, LENGTH, LENGTH);
					rectangles.add(r);
				}
				repaint();
			}
			// 마우스를 눌럿다 놓을 때, 마우스가 클릭될 때,
			// 마우스를 이 컴포넌트 위에 올릴 때, 마우스를 치울 때
			// 아래 메소드가 각각 실행됨.
			// 아래 메소드는 중괄호 속에 비어 있으므로 아무 일도 하지 않음.
			public void mouseReleased(MouseEvent event) {}
			public void mouseClicked(MouseEvent event) {}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
		}
		addMouseListener(new ClickListener());	// 이 컴포넌트에 MouseLisener를 등록함.
	}
	
	// 사각형들을 모두 그림
	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		for (Rectangle2D.Double r : rectangles) {
			g2.draw(r);
		}
	}
	
	// 사각형들이 들어 있는 array list를 반환.
	public ArrayList<Rectangle2D.Double> getRectangles() {
		return rectangles;
	}
	
	public void setRectangles(ArrayList<Rectangle2D.Double> list) {
		rectangles = list;
	}
	
	ArrayList<Rectangle2D.Double> rectangles;		// 인스턴스 필드
	
	public static double LENGTH = 20;	// 정사각형 변의 길이
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
}
