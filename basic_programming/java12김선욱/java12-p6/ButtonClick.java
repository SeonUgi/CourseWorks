import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonClick extends JFrame
{
    
    public ButtonClick() {
        
        super("문제 6번"); // 슈퍼클래스 구성자 호출
        JButton button1 = new JButton("A");
        JButton button2 = new JButton("B");
        final JLabel label1 = new JLabel("A버튼 클릭 수: 0");
        final JLabel label2 = new JLabel("B버튼 클릭 수: 0");
        JPanel panel = new JPanel();
        
        
        button1.addActionListener(new ClickListener(label1, "A", this));
        button2.addActionListener(new ClickListener(label2, "B", this));
        
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
     int count = 0;  // 필드로 선언
     JLabel label;   // 필드 선언
     String name;    // 필드 선언
     JFrame frame;   // 필드 선언
     ClickListener(JLabel label, String name, JFrame frame) {
                this.label = label;
                this.name = name;
                this.frame = frame;
     }
            
     public void actionPerformed(ActionEvent event){
                label.setText(name + "버튼 클릭 수: " + ++count);
                frame.pack();
            }
        
     }
