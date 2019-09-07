import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonClick
{
    
    public ButtonClick() {
        JFrame frame = new JFrame("문제 3번");
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
        frame.add(panel);
        panel.add(button);
        panel.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new ButtonClick();
    }
}
