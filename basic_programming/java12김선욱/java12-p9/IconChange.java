import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IconChange extends JPanel {
    static final int IMAGES = 16;       // 이미지 파일 수
    
    // 버튼에 그림을 그리는 법,
    // JButton의 수퍼클래스인 AbstractButton은 setIcon(Icon) 메소드를 가짐.
    // ImageIcon은 Icon 인터페이스를 구현함 (Icon은 인터페이스 이름).
    // 버튼에 그림을 그려야 하므로 ImageIcon이 필요함.
    // ImageIcon은 이미지 파일로부터 만들어짐.
    
    ImageIcon[] icon = new ImageIcon[IMAGES];       // 이미지 아이콘들 (이미지 아이콘들은 일련번호를 가짐)
    
    private JButton button = new JButton();         // 버튼.
    
    boolean secondClick = false;                    // 이번에 클릭한 것이 두번째 클릭인지 여부
                                                    // 두개의 클릭을 한 쌍으로 다룸 (첫 클릭, 두번째 클릭)
                                                    
    ActionListener listener;                        // 버튼 클릭리스너, 클릭할 때마다 어느 버튼이 클릭됐는지 알아내고
                                                    // 적절한 작업을 수행함
                                                    
    // 구성자
    public IconChange() {
        getImages();    // 이미지 파일을 읽어 이미지 아이콘으로 저장해 둠
        
        // ImageIcon의 너비와 높이.
        int width = icon[0].getIconWidth();
        int height = icon[0].getIconHeight();
        
        button.setPreferredSize(new Dimension(width, height)); // 버튼의 크기 지정
        button.setIcon(icon[0]);
        add(button);                                            // 버튼을 패널에 넣음
        
        listener = new ClickListener();     // 클릭리스너 객체 구성
        button.addActionListener(listener); // 버튼에 클릭리스너 등록
    }
    
    private void getImages() {
        // 16개의 이미지가 있는 경우 32개의 버튼까지 가능
        icon[0] = createImageIcon("/images/dog-icon.png", "cute dog image");
        icon[1] = createImageIcon("/images/cat-icon.png", "pretty cat image");
        icon[2] = createImageIcon("/images/angry-bird-icon.png", "angry bird image");
        icon[3] = createImageIcon("/images/bird-icon.png", "cute bird image");
        icon[4] = createImageIcon("/images/clown-fish-icon.png", "clown fish image");
        icon[5] = createImageIcon("/images/cow-icon.png", "cow cow image");
        icon[6] = createImageIcon("/images/eagles-icon.png", "scary eagle image");
        icon[7] = createImageIcon("/images/elephant-icon.png", "huge elephant image");
        icon[8] = createImageIcon("/images/fish-icon.png", "cute fish image");
        icon[9] = createImageIcon("/images/jelly-fish-icon.png", "jelly fish image");
        icon[10] = createImageIcon("/images/lion-icon.png", "cute lion image");
        icon[11] = createImageIcon("/images/owl-icon.png", "pretty owl image");
        icon[12] = createImageIcon("/images/red-fish-icon.png", "red fish image");
        icon[13] = createImageIcon("/images/snake-icon.png", "long snake image");
        icon[14] = createImageIcon("/images/tiger-icon.png", "big tiger image");
        icon[15] = createImageIcon("/images/bee-icon.png", "cute bee image");
    }
    
    /** ImageIcon을 반환,
     * 단, path가 유효하지 않은 경우에는 null을 반환.
     * @param path 그림 파일 경로명 (가령, "C:\images\smile.jpg").
     * @param description 그림에 대한 설명.
     * @return 그림파일로부터 만들어진 ImageIcon (path가 유효한 경우),
     *          null (path가 유효하지 않은 경우).
     */
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = IconChange.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        }
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    // Inner class
    class ClickListener implements ActionListener {
        private int iconIndex = 0;  // 인스턴스 필드.
        public void actionPerformed(ActionEvent event) {
            // 모든 클릭을 쌍으로 관찰한다.
            // 첫 클릭, 두 번째 클릭, 첫 클릭, 두 번째 클릭, ...
            
            if(secondClick) {           // 이 클릭이 두번째 클릭이라면
                ++iconIndex;
                if(iconIndex>=IMAGES)
                    iconIndex = 0;
                button.setIcon(icon[iconIndex]);         //imageIcon을 바꾼다.
                secondClick = false;    //두번째 클릭 끝남, 다음 번 클릭은 secondClick이 아님.
            }
            else {                      // 이 클릭이 첫번째 클릭인 경우
                secondClick = true;     // 아무 일도 하지 않고 두번째 클릭으로 넘어감.
            }
        }
    }
    
    public static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add content to the window.
        frame.add(new IconChange());
        
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        createAndShowGUI();
    }
}