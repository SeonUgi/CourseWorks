import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonClick
{
    public static void main(String[] args) {
        JFrame frame = new JFrame("문제 2번");
        JButton button = new JButton("클릭하세요");
        final JLabel label = new JLabel("클릭 수: 0");
        JPanel panel = new JPanel();
        
        class ClickListener implements ActionListener {
            int count = 0;  // 필드로 선언
            public void actionPerformed(ActionEvent event){
                label.setText("클릭 수: " + ++count);
            }
        }
        
        ActionListener listener = new ClickListener();
        button.addActionListener(listener);
        frame.add(panel);
        panel.add(button);
        panel.add(label);
        frame.pack();
        frame.setVisible(true);
    }
}
