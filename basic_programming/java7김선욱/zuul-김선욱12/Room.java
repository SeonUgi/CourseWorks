/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

public class Room 
{
    private String description;
    private Map<String, Room> exits;
    private ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
    }

    /**
     * 이 Room의 출구들 중 하나를 정해 준다.
     * @param direction 출구 방향.
     * @param Room 지정된 방향의 출구에 연결된 Room.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return Room의 상세한 정보를 모두 모아 문자열로 반환한다.
     */
    public String getLongDescription()
    {
        String returnString = "지금 있는 곳: " + description + ".\n" + getExitString();
        if (items.size() > 0) {
            returnString += ".\n" + "<아이템>" + "\n";
            returnString += getItemString();
        }
        return returnString;
    }
    
    /**
     * 이 Room에 있는 아이템들에 대한 상세 정보 문자열을 반환한다.
     * @return 상세정보 문자열.
     */
    public String getItemString() {
        String returnString = "";
        for (Item item : items)
        returnString += item.getLongDescription() + "\n";
        return returnString;
    }

    /**
     * 지정된 방향으로 나가려고 할 때 연결되는 Room을 알려준다.
     * @ param direction 나가려고 하는 방향 "north", "east", "south", "west" 
     * @ return 나가려고 하는 방향으로 연결된 Room, 그 방향으로 출구가 없으먼 null.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    /**
     * 방의 출구들을 알려주는 문자열을 반환한다.
     * 문자열 예: "Exits: north west".
     * @ return 출구가 있는 방향들을 알려주는 문자열
     */
    public String getExitString() {
        String returnString = "Exits: ";
        // Map에 있는 key들을 모두 읽어냄.
        Set<String> keys = exits.keySet();
        // Set에 들어 있는 문자열들을 읽어냄.
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) 
            returnString = returnString + it.next() + " ";
        return returnString;
    }
    
    /**
     * 아이템을 만든다.
     * @param item 이 Room에 넣을 아이템.
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
    /**
     * 이 Room에서 아이템을 제거한다.
     * @param name 이 Room에서 제거할 아이템의 이름.
     * @return 제거된 아이템(제거에 성공한 경우)
     *         null (제거에 실패한 경우)
     */
    public Item removeItem(String name) {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.getName().equals(name)) {
                it.remove();
                return item;    // 주어진 이름의 아이템이 있으면 그 아이템을 반환.
            }
        }
        return null;    // 주어진 이름의 아이템이 없는 경우 null을 반환.
    }
}
