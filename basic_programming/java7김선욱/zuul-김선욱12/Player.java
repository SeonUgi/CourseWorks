import java.util.ArrayList;
import java.util.Iterator;

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
    private int maxWeight;
    private ArrayList<Item> items;
    
    /**
     * 구성자
     * @param startRoom 이 선수가 처음 게임을 시작할 방
     */
    public Player(Room startRoom, int maxWeight) {
        currentRoom = startRoom;
        recentRoom = startRoom;
        items = new ArrayList<Item>();
        this.maxWeight = maxWeight;
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
        
    /*
     * 가지고 있는 아이템들의 총 무게를 알아낸다.
     */
    private int totalWeight() {
        Iterator<Item> it = items.iterator();
        int sum = 0;
        while(it.hasNext())
            sum += it.next().getWeight();
        return sum;
    }
    
    /*
     * 지정된 아이템이 집어 들 수 있는 무게인지 판별한다.
     */
    private boolean pickable(Item item) {
        //(아이템의 무게 + 이미 가지고 있는 아이템들의 무게 > 최대 허용 무게)이면 false
        if (item.getWeight() + totalWeight() > maxWeight) 
            return false;
        else
            return true;
    }
    
    /**
     * 선수가 가지고 있는 아이템들의 arraylist를 반환한다.
     * 단, 반환되는 arraylist는 선수가 가지고 있는 arraylist의 복사본이다.
     * @return 선수가 가지고 있는 아이템들의 arraylist의 복사본.
     */
    public ArrayList<Item> getItems() {
        return new ArrayList<Item>(items);
    }
    
    /**
     * 아이템을 집는다.
     * @param name 집어 들 아이템의 이름.
     * @return 집어 든 아이템 (성공한 경우), null (실패한 경우).
     */
    public Item takeItem(String name) {
        Item item = currentRoom.removeItem(name);   // 방에서 아이템을 제거한다.
        if (item != null) {                         // 제거에 성공한 경우
            if (pickable(item))                     // 아이템이 집을 수 있는 무게인 경우
                items.add(item);                    // 아이템을 선수의 컬렉션에 넣는다.
            else {                                  // 너무 무거운 경우
                currentRoom.addItem(item);          // 아이템을 방에 도로 집어 넣는다.
                item = null;                        // 실패한 경우로 간주함.
            }
        }
        return item;                                // 아이템(성공한 경우), 혹은 null(실패한 경우)을 반환.
    }
    
    /**
     * 가지고 있는 아이템 중 하나를 내려 놓는다.
     * @param name 내려 놓을 아이템의 이름.
     * @return 내려 놓은 아이템 (성공한 경우), null (실패한 경우).
     */
    public Item dropItem(String name) {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if(item.getName().equals(name)) {         // 갖고 있는 아이템 중 지정된 이름의 아이템을 찾아
                items.remove(item);         // 아이템을 컬렉션에서 지우고
                currentRoom.addItem(item);  // 현재 방에 그 아이템을 넣고
                return item;                // 아이템을 반환.
            }
        }
        return null;                            // 지정된 이름의 아이템을 갖고 있지 않은 경우 null을 반환.
    }
    
    /**
     * 이 Player가 들 수 있는 아이템의 최대 무게.
     */
    public int getMaxWeight() {
        return maxWeight;
    }
}

