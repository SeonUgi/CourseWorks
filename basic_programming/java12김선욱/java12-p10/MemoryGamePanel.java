import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import javax.swing.*;

public class MemoryGamePanel extends JPanel {
    static final int ROWS = 4;          // 행
    static final int COLUMNS = 4;       // 열
    // (행 x 열)은 반드시 짝수이어야 함

    static final int IMAGES = ROWS * COLUMNS;       // 이미지 파일 수

    // 버튼에 그림을 그리는 법.
    // JButton의 수퍼클래스인 AbstractButton은 setIcon(Icon) 메소드를 가짐.
    // ImageIcon은 Icon 인터페이스를 구현함 (Icon은 인터페이스 이름).
    // 버튼에 그림을 그려야 하므로 ImageIcon이 필요함.
    // ImageIcon은 이미지 파일로부터 만들어짐.

    ImageIcon[] icon = new ImageIcon[IMAGES];       // 이미지 아이콘들 (이미지 아이콘들은 일련번호를 가짐)
    int[][] grid = new int[ROWS][COLUMNS];          // 각 버튼에 할당된 이미지 아이콘 번호
    // 같은 번호가 두 개의 버튼에 할당됨 (그림이 쌍으로 있어야 하므로)

    List<Integer> iconNumbers = new ArrayList<Integer>();   // 이미지아이콘 번호들을 뒤섞기 위해 이곳에 저장.
    // 게임을 시작할 때 아이콘들을 무작위로 배치하기 위해.

    private JButton[][] buttons = new JButton[ROWS][COLUMNS];   // 버튼들.

    boolean secondOpen = false;                 // 이번에 클릭한 것이 두번째 클릭인지 여부
    // 두개의 클릭을 한 쌍으로 다룸 (첫 클릭, 두번째 클릭)

    JButton firstOpenButton = null;             // 첫 클릭 버튼이 어떤 것이었는지 기억해 둠
    JButton secondOpenButton = null;            // 두번째 클릭이 어떤 것이었는지 기억해 둠
    // 한 쌍의 클릭이 서로 일치하지 않으면 해당 버튼들을 다시
    // 뒤집어 놓아야 하므로 이들을 기억하고 있어야 함

    int firstOpenNumber = 0;            // 첫 클릭 버튼의 이미지 아이콘 번호

    int tries = 0;                      // 지금까지 몇번 시도했는가?
    int pairs = ROWS * COLUMNS / 2;     // 그림이 서로 일치하는 쌍이 몇개인가?
    // 4x4= 16개 버튼이 있다면 8쌍의 서로 일치하는 버튼이 있음

    int pairsToFind = pairs;            // 아직 맞히지 못하고 남아 있는 버튼 쌍이 몇개인가?

    boolean previousCorrect = true;     // 최근에 클릭한 버튼 쌍이 일치했는가?
    // 일치했다면 뒤집힌 상태로 그냥 두면 되고, 그렇지 않으면 다시 엎어야 함

    ActionListener listener;            // 버튼 클릭리스너, 클릭할 때마다 어느 버튼이 클릭됐는지 알아내고
    // 적절한 작업을 수행함

    // 구성자
    public MemoryGamePanel() {
        super(new GridLayout(ROWS, COLUMNS));   // 기본설정인 FlowLayout 대신 GridLayout이 작동하도록 함.

        getImages();        // 이미지 파일을 읽어 미미지 아이콘으로 저장해 둠

        // ImageIcon의 너비와 높이.
        int width = icon[0].getIconWidth();
        int height = icon[0].getIconHeight();

        // 리스트 iconNumbers에 이미지아이콘 번호를 두 개씩 넣는다.
        // 0, 0, 1, 1, 2, 2, ....
        // 버튼 수만큼
        for (int i=0; i<pairs; i++) {
            iconNumbers.add(i);     // auto-boxing 됨.
            iconNumbers.add(i);     // auto-boxing 됨
        }

        // 리스트 iconNumbers에 있는 이미지아이콘 번호들을 뒤섞은 (permutation) 후 각 버튼에 할당.
        shuffle();

        listener = new ClickListener();     // 클릭리스너 객체 구성

        // 버튼들을 구성하여 패널에 넣음 (격자배치)
        for(int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(width, height));    // 버튼의 크기 지정
                add(buttons[i][j]);     // 버튼을 패널에 넣음
                buttons[i][j].addActionListener(listener);      // 버튼에 클릭리스너 등록
                // 한 개의 리스너가 모든 버튼에 등록됨.
                // 버튼 여러 개, 리스너 한 개.
            }
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
        java.net.URL imgURL = MemoryGamePanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        }
        else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    // iconNumbers의 숫자들을 뒤섞은 후 grid에 차례로 넣어줌
    // grid 각 방의 숫자는 그 방에 할당된 이미지아이콘 번호를 나타냄
    private void shuffle() {
        java.util.Collections.shuffle(iconNumbers);     // Collections 클래스의 static 메소드 shuffle.
        // List의 원소들을 뒤섞어 줌.
        int k = 0;
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                grid[i][j] = iconNumbers.get(k++);       // auto-unboxing.
    }

    // Inner Class
    class ClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ++tries;        // 시도 회수 증가.

            // 어느 위치의 버튼이 클릭되었는지 찾음
            int bi = 0, bj = 0;         // 클릭된 버튼의 행, 열 번호.
            for(int i = 0; i < ROWS; i++)
                for (int j = 0; j < COLUMNS; j++) {
                    if (buttons[i][j] == event.getSource()) {   // 이벤트 소스가 i, j 위치의 버튼이라면
                        bi = i;
                        bj = j;
                    }
                }

            // 클릭된 버튼에서 리스너를 제거하고 그림을 보이게 함.
            // 한 번 클릭된 버튼을 곧이어 또 클릭하면 아무런 반응이 없도록.
            buttons[bi][bj].removeActionListener(listener);
            buttons[bi][bj].setIcon(icon[grid[bi][bj]]);
            // grid[bi][bj] <-- (bi,bj) 위치의 버튼에 할당된 ImageIcon 번호.

            // 모든 클릭을 쌍으로 관찰한다.
            // 첫 클릭, 두 번째 클릭, 첫 클릭, 두 번째 클릭, ...

            if(secondOpen) {        // 이 클릭이 두번째 클릭이라면
                secondOpenButton = buttons[bi][bj];     // 어느 버튼이 클릭됐는지 기억해 둠.
                if (firstOpenNumber == grid[bi][bj]) {  // 첫그림과 두번째 그림이 일치하면 성공
                    --pairsToFind;
                    if(pairsToFind == 0) {
                        JOptionPane.showMessageDialog(null, tries + "회 만에 맞추셨습니다.");
                        System.exit(0);
                    }
                    previousCorrect = true;     // 최근 쌍에서 성공!
                }
                else {
                    previousCorrect = false;    // 최근 쌍에서 실패!
                    //buttons[bi][bj].setIcon(null);  // 뒤집어 놓음 (그림 제거).
                }
                secondOpen = false;             // 두번째 클릭 끝남, 다음 번 클릭은 secondOpen 아님.
            }
            else{           // 이 클릭이 첫번째 클릭인 경우
                if(!previousCorrect){           // 최근 쌍에서 성공하지 못했다면 그 쌍을 원상복구.
                    firstOpenButton.setIcon(null);  // 뒤집어 놓음 (그림 제거).
                    secondOpenButton.setIcon(null);
                    firstOpenButton.addActionListener(listener);
                    secondOpenButton.addActionListener(listener);    // 리스너를 등록하여 클릭에 반응하게 함.
                    previousCorrect = true;
                }
                firstOpenNumber = grid[bi][bj];     // 첫 클릭에 의해 나타난 ImageIcon 번호를 기억.
                firstOpenButton = buttons[bi][bj];  // 첫 클릭 버튼을 기억.
                secondOpen = true;                 // 두번째 클릭으로 넘어감.
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("문제 10번");
        MemoryGamePanel panel = new MemoryGamePanel();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }
}