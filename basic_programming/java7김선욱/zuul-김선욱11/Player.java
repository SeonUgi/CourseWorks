
/**
 * 선수가 현재 방에 있는 아이템을 집어들 수 있다.
 * 선수가 여러 아이템을 갖고 다닐 수 있지만 들 수 있는 무게에 제한이 있다.
 * 어떤 아이템은 집어 들 수 없다.
 * 선수는 자기가 갖고 있는 아이템을 현재 방에 내려 놓을 수 있다.
 */
public class Player
{
    private Room currentRoom;   // 지금 있는 방
    private Room recentRoom;    // 최근에 있던 방
    
    /**
     * 구성자
     * @param startRoom 이 선수가 처음 게임을 시작할 방
     */
    public Player(Room startRoom) {
        currentRoom = startRoom;
        recentRoom = startRoom;
    }
    
    /**
     * 이전 방으로 돌아간다.
     */
    public void back() {
        currentRoom = recentRoom;
    }
    
    /**
     * 선수가 현재 있는 방을 반환한다.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * 새 방으로 옮겨간다.
     * @param newRoom 새로 옮겨갈 방.
     */
    public void moveTo(Room newRoom) {
            recentRoom = currentRoom;   // 지금 있는 방을 기억해 둠.
            currentRoom = newRoom;     // 방을 변경
        }
    }

