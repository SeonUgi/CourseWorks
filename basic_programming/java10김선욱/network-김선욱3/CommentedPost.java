import java.util.ArrayList;

public class CommentedPost extends Post
{
    private int likes;
    private ArrayList<String> comments;

    /**
     * Constructor for objects of class CommentedPost
     */
    public CommentedPost(String author)
    {
        super(author);
        likes = 0;
        comments = new ArrayList<String>();
    }

  
}
