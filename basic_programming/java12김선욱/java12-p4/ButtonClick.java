import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonClick extends JFrame
{
    
    public ButtonClick() {
        
        super("문제 4번"); // 슈퍼클래스 구성자 호출
        JButton button = new JButton("클릭하세요");
        final JLabel label = new JLabel("클릭 수: 0");
        JPanel panel = new JPanel();
        
        ActionListener listener = new ActionListener() {
            int count = 0;  // 필드로 선언
            public void actionPerformed(ActionEvent event){
                label.setText("클릭 수: " + ++count);
            }
        };
    
        
        
        button.addActionListener(listener);
        add(panel);
        panel.add(button);
        panel.add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
        new ButtonClick();
    }
}
