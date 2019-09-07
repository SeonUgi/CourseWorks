import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonClick extends JFrame
{
    
    public ButtonClick() {
        
        super("문제 7번"); // 슈퍼클래스 구성자 호출
        JButton button1 = new JButton("A");
        JButton button2 = new JButton("B");
        final JLabel label1 = new JLabel("버튼 클릭 수: 0");
        final JLabel label2 = new JLabel("버튼 클릭 수: 0");
        JPanel panel = new JPanel();
        
        
        ClickListener listener = new ClickListener(label1, label2, this, button1, button2);
        
        button1.addActionListener(listener);
        button2.addActionListener(listener);
       
        add(panel);
        panel.add(button1);
        panel.add(label1);
        panel.add(button2);
        panel.add(label2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }
    public static void main(String[] args) {
        new ButtonClick();
    }
}

 class ClickListener implements ActionListener {
     int countA = 0;  // 필드로 선언, A버튼 카운트
     int countB = 0;  // 필드로 선언, B버튼 카운트
     JLabel label1;   // 필드 선언
     JLabel label2;   // 필드 선언
     JButton buttonA;
     JButton buttonB;
     JFrame frame;   // 필드 선언
     ClickListener(JLabel label1, JLabel label2, JFrame frame, JButton button1, JButton button2) {
                this.label1 = label1;       // A버튼 라벨
                this.label2 = label2;       // B버튼 라벨
                buttonA = button1;
                buttonB = button2;
               // this.name = name;
                this.frame = frame;
     }
            
     public void actionPerformed(ActionEvent event){
                if (event.getSource() == buttonA) {
                    label1.setText("버튼 클릭 수: " + ++countA);
                    frame.pack();
                }
                
                else if(event.getSource() == buttonB) {
                    label2.setText("버튼 클릭 수: " + ++countB);
                    frame.pack();
                }
       
            }
        
     }
