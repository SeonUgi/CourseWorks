package p6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JPanel;

public class CirclePanel extends JPanel{
	
	// paintComponent 메소드는 필요할 때마다 시스템에 의해 자동으로 호출됨.
	// 필요한 때
	// - 프로그램이 처음 실행될 때.
	// - 프레임을 아이콘으로 최소화했다가 다시 창으로 복원시킬 때.
	// - 프레임의 크기를 변경할 때 등.
	
	// 시스템이 paintComponent를 호출할 때 Graphics 객체를 인자로 넘겨줌.
	// 이 Graphics 객체는 이 컴포넌트에 그림을 그리기 위해 필요한 정보를 갖고 있음.
	// 컴포넌트에 그림을 그리고 싶으면 이 Graphics 객체를 이용함.
	
	public void paintComponent(Graphics g) // 이 메소드의 형식적 파라미터 타입은 Graphics임.
	{
		// 그러나 실제로 시스템이 넘겨주는 객체의 타입은 Graphics2D임.
		// Graphics2D는 Graphics의 서브클래스로서 더 많은 기능을 갖고 있음.
		// Graphics2D 타입 참조변수를 사용하면 더 많은 기능을 사용할 수 있음.
		// 참조변수의 형식적 타입에 의해 호출 가능한 메소드가 결정된다!
		
		//Recover Graphics2D
		Graphics2D g2 = (Graphics2D)g;
		
		super.paintComponent(g2);	// JPanel을 확장할 때는 이 문장을 꼭 적어줘야 함.
		
		// Construct and draw two circles
		
		Ellipse2D.Double circle1 = new Ellipse2D.Double(30, 30, 100, 100);
		g2.setColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
		g2.fill(circle1);
		
		Ellipse2D.Double circle2 = new Ellipse2D.Double(160, 30, 100, 100);
		g2.setColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
		g2.draw(circle2);
		
	}
	private Random r = new Random();	// <---- CircleComponent의 인스턴스 필드 

}
