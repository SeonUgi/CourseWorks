
/**
 * Write a description of class EventPost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EventPost extends Post
{
    // instance variables - replace the example below with your own
    private int eventType;

    /**
     * Constructor for objects of class EventPost
     */
    public EventPost(String author, int eventType)
    {
        super(author);
        this.eventType = eventType;
    }

    public int getEventType() {
       return eventType; 
    }
}
