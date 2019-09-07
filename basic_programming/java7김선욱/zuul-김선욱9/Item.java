
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String name;
    private String description;
    private String weight;
    
    /**
     * 구성자
     */
    public Item(String itemName, String itemDescription, String itemWeight) {
        name = itemName;
        description = itemDescription;
        weight = itemWeight;
    }
    
    /**
     * 아이템 이름을 반환한다.
     */
    public String getName() {
        return name;
    }
    
    /**
     * 아이템에 대한 설명을 반환한다.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 아이템에 대한 무게를 반환한다.
     */
    public String getWeight() {
        return weight;
    }
    
    /**
     * 아이템에 대해 설명하는 문자열을 출력한다.
     */  
    public String getLongDescription() {
        String returnString = name + " (" + "무게 " + weight + ", " + description + ")";
        return returnString;
    }
}
