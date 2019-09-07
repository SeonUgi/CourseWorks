import java.util.ArrayList;
import java.util.Iterator;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        
        createRooms();
        
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room hall, lectureRoom, computerRoom, office, dongBang, cellar;
        Item book, portion;

        // create the rooms
        hall = new Room("가운데 있는 큰 현관 홀");
        lectureRoom = new Room("강의실");
        dongBang = new Room("동아리 방");
        computerRoom = new Room("컴퓨터 실습실");
        office = new Room("과사무실");
        cellar = new Room("지하창고");
        book = new Item("book", "마법에 대한 설명이 적혀 있는 오래된 책", 10);
        portion = new Item("portion", "먹으면 체력이 5만큼 증가하는 건강음료", 10);
        

        // initialise room exits
        hall.setExit("east", lectureRoom);
        hall.setExit("south", computerRoom);
        hall.setExit("west", dongBang);
        lectureRoom.setExit("west", hall);
        dongBang.setExit("east", hall);
        computerRoom.setExit("north", hall);
        computerRoom.setExit("east", office);
        computerRoom.setExit("down", cellar);
        office.setExit("west", computerRoom);
        cellar.setExit("up", computerRoom);
        computerRoom.addItem(book);
        dongBang.addItem(portion);
        dongBang.addItem(book);


        player = new Player(hall, 25);  // 현관 홀에서 게임을 시작한다, 가질 수 있는 아이템의 무게는 25.
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();  // 파싱 후 Command 객체를 반환함에 유의!
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();

        printLocationInfo(player.getCurrentRoom());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("명령을 못 알아 먹겠습니다...");
            return false;
        }

        // Command 객체는 commandWord와 secondWord를 필드로 갖는다.
        // 모든 Command에는 commandWord가 들어 있다(필수).
        // secondWord는 있을 수도 있고 없을 수도 있다(옵션). 없는 경우 null.
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("items")) {
            items();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("입력할 수 있는 명령어는 아래와 같습니다.");
        System.out.println(parser.getCommandList());
    }

    /**
     * 테스트를 위한 임시 메소드. 아직 제대로 구현되지 않았음.
     */
    private void eat() {
        System.out.println("배불리 먹었습니다.");
    }

    /**
     * 최근에 있던 방으로 돌아간다.
     */
    private void back(Command command) {
        // back 명령어는 second word를 갖지 말아야 한다.
        if(command.hasSecondWord()) {
            System.out.println("한 단계 뒤로만 갈 수 있습니다.");
            System.out.println("back 명령어에는 인자를 줄 수 없습니다.");
        }
        else {
            player.back();
            printLocationInfo(player.getCurrentRoom());
        }
    }

    /**
     * 현재 방의 상세 정보를 출력한다.
     */
    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // Command에 second word가 없는 경우 
            System.out.println("어디로 갈까요?");
            return;
        }

        String direction = command.getSecondWord();

        
       
        Room nextRoom = null;
        nextRoom = player.getCurrentRoom().getExit(direction);
        player.moveTo(nextRoom);
        printLocationInfo(player.getCurrentRoom());
        
    }

    /**
     * 현재 위치에 관한 정보를 출력한다.
     */
    private void printLocationInfo(Room room) {
        System.out.println(room.getLongDescription());
        System.out.println();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        // quit 명령어는 second word를 갖지 말아야 한다.
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * 아이템을 집어 든다.
     */
    private void take(Command command) {
        if(!command.hasSecondWord()) {
            // Command에 second word가 없는 경우
            System.out.println("어떤 아이템을 집을까요?");
            return;
        }
        
        String itemName = command.getSecondWord();
        
        Item item = player.takeItem(itemName);   // 선수가 아이템을 집도록 함.
        if (item == null)                       // 실패한 경우
            System.out.println("아이템을 집을 수 없습니다.");
        else {                                  // 성공한 경우
            ArrayList<Item> items = player.getItems();   // 선수가 가진 아이템 컬렉션을 얻는다.
            printItems(items);                          // 컬렉션에 있는 모든 아이템들을 출력
        }
    }
    
    /**
     * 지정된 ArrayList에 있는 모든 아이템들의 상세 내역을 출력한다.
     */
    private void printItems(ArrayList<Item> items) {
        int sum = 0;
        for (Item item : items) {
            System.out.println(item.getLongDescription());
            sum += item.getWeight();
        }
        System.out.println("<현재 가지고 있는 아이템들의 총 무게: " + sum 
                              + ", 들 수 있는 최대 무게: " + player.getMaxWeight() + ">");
    }
    
    /**
     * 아이템을 내려 놓는다.
     */
    private void drop(Command command) {
        if(!command.hasSecondWord()) {
            // Command에 second word가 없는 경우
            System.out.println("어떤 아이템을 내려 놓을까요?");
            return;
        }
        
        String itemName = command.getSecondWord();
        
        Item item = player.dropItem(itemName);   // 선수가 아이템을 집도록 함.
        if (item == null)                       // 실패한 경우
            System.out.println("아이템을 내려 놓을 수 없습니다.");
        else {                                  // 성공한 경우
            ArrayList<Item> items = player.getItems();   // 선수가 가진 아이템 컬렉션을 얻는다.
            printItems(items);                          // 컬렉션에 있는 모든 아이템들을 출력
        }
    }
    
    /**
     * 가지고 있는 아이템들의 상세 내역을 출력한다.
     */
    private void items() {
        printItems(player.getItems());
    }
}
