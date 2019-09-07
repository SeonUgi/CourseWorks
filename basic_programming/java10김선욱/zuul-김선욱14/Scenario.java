import java.util.Random;
import java.util.ArrayList;

/**
 * Write a description of class Scenario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scenario
{
    // instance variables - replace the example below with your own
    private ArrayList<Room> rooms;   // List of all rooms in the game
    private Room startRoom;
    private Random random;

    /**
     * Constructor for objects of class Scenario
     */
    public Scenario()
    {
        Room hall, lectureRoom, computerRoom, office, dongBang, cellar, transporter;
        
        // create the rooms
        transporter = new TransporterRoom("공간이동하는 방", this);    // this는 이 코드를 실행하고 있는 Scenario 객체를 의미한다.
        hall = new Room("가운데 있는 큰 현관 홀");
        lectureRoom = new Room("강의실");
        dongBang = new Room("동아리 방");
        computerRoom = new Room("컴퓨터 실습실");
        office = new Room("과사무실");
        cellar = new Room("지하창고");

        // initialise room exits
        hall.setExit("east", lectureRoom);
        hall.setExit("south", computerRoom);
        hall.setExit("west", dongBang);
        hall.setExit("north", transporter);
        lectureRoom.setExit("west", hall);
        dongBang.setExit("east", hall);
        computerRoom.setExit("north", hall);
        computerRoom.setExit("east", office);
        computerRoom.setExit("down", cellar);
        office.setExit("west", computerRoom);
        cellar.setExit("up", computerRoom);

        Item book = new Item("book", "마법에 대한 설명이 적혀 있는 오래된 책", 10);
        computerRoom.addItem(book);
        dongBang.addItem(new Item("portion", "먹으면 체력이 5만큼 증가하는 건강음료", 10));
        dongBang.addItem(book);
        
        startRoom = hall;   // 홀에서 시작
        
        rooms = new ArrayList<Room>();
        rooms.add(hall);
        rooms.add(dongBang);
        rooms.add(lectureRoom);
        rooms.add(computerRoom);
        rooms.add(office);
        rooms.add(cellar);
        rooms.add(transporter);
        
        random = new Random();
    }

    /**
     * Return the room where players start the game.
     */
    public Room getStartRoom() {
        return startRoom;
    }
    
    /**
     * Return a random room in this game.
     */
    public Room getRandomRoom() {
        return rooms.get(random.nextInt(rooms.size()));
    }
}
