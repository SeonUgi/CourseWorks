import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommentTest
{
    private Comment comment;
    /**
     * Default constructor for test class CommentTest
     */
    public CommentTest()
    {
    }

    /**
     * Test fixture를 설정한다.
     *
     * 이 메소드는 각 테스트 메소드가 호출될 때마다 호출되며, 각 테스트메소드에 앞서 실행된다.
     */
    @Before
    public void setUp()
    {
        comment = new Comment("훈남", "아주 좋아요.", 5);
    }

    /**
     *
     * 이 메소드는 각 테스트 메소드가 호출될 때마다 호출되며, 각 테스트메소드 후에 실행된다.
     */
    @After
    public void tearDown()
    {
        comment = null;
    }
    
    /**
     * getAuthor 메소드가 제대로 작동하는지 검사한다.
     */
    @Test
    public void testGetAuthor()
    {
        assertEquals("훈남", comment.getAuthor());
    }

    /**
     * getRating 메소드가 제대로 작동하는지 검사한다.
     */
    @Test
    public void testGetRating()
    {
        assertEquals(5, comment.getRating());
    }

    /**
     * upvote, downvote, getVoteCount가 제대로 작동하는지 검사한다.
     */
    @Test
    public void testVoting() 
    {
        assertEquals(0, comment.getVoteCount());    // 초기에는 0이어야 한다.
        comment.upvote();
        assertEquals(1, comment.getVoteCount());
        comment.upvote();
        assertEquals(2, comment.getVoteCount());
        comment.downvote();
        assertEquals(1, comment.getVoteCount());
        comment.downvote();
        assertEquals(0, comment.getVoteCount());
        comment.downvote();
        assertEquals(-1, comment.getVoteCount());   // 음수도 가능하다.
    }
}



