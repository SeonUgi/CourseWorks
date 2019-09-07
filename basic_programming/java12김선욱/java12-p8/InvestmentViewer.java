import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InvestmentViewer extends JFrame
{
    private static final double INTEREST_RATE = 10;
    private static final double INITIAL_BALANCE = 1000;

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 100;
    final JLabel label;
    JButton button;
    JPanel panel;
    final BankAccount account;
    public InvestmentViewer() {
     
        button = new JButton("Add Interest");
        account = new BankAccount (INITIAL_BALANCE);
        label = new JLabel ("balance=" + account.getBalance());
        
        panel = new JPanel();
        panel.add(button);
        panel.add(label);
        this.add(panel);
        
        class AddInterestListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                double interest = account.getBalance()*INTEREST_RATE/100;
                account.deposit(interest);
                label.setText("balance="+account.getBalance());
            }
        }   // inner 클래스이므로 필드, 객체 사용가능
        ActionListener listener = new AddInterestListener();
        button.addActionListener(listener);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        InvestmentViewer iv = new InvestmentViewer();
    }
}
