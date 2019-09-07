

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SalesItemTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SalesItemTest
{
    private SalesItem item0;
    private SalesItem item1;
    private Comment comment;
    
    /**
     * Default constructor for test class SalesItemTest
     */
    public SalesItemTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        item0 = new SalesItem("쫄쫄이 바지", 1000);
        item1 = new SalesItem("브라보콘", 100);
        comment = new Comment("날라리", "폼 나요!", 5);
    }
    
    /**
     * SalesItem의 이름을 제대로 얻는지 시험한다.
     */
    @Test
    public void testGetName() {
        assertEquals("쫄쫄이 바지", item0.getName());
        assertEquals("브라보콘", item1.getName());
    }
    
    /**
     * SalesItem의 가격을 제대로 얻는지 시험한다.
     */
    @Test
    public void testGetPrice() {
        assertEquals(1000, item0.getPrice());
        assertEquals(100, item1.getPrice());
    }
    
    /**
     * SalesItem에 Comment를 제대로 추가하는지 검사한다.
     * SalesItem에 달려 있는 Comment 개수를 제대로 얻는지 검사한다.
     */
    @Test
    public void testComment() {
        // 첫 상품에 세 개의 코멘트를 추가하고 제대로 추가되었는지 검사.
        assertEquals(true, item0.addComment("날라리", "폼 나요!", 5));
        assertEquals(true, item0.addComment("어르신", "흠~ 별로...", 1));
        assertEquals(true, item0.addComment("범생이", "쩐다~~", 4));
        assertEquals(3, item0.getNumberOfComments());
        
        // 코멘트가 달리지 않은 두 번째 상품의 경우 코멘트 개수 읽기 검사
        assertEquals(0, item1.getNumberOfComments());
        // 두 번째 상품에 한 개의 코멘트를 추가하고 제대로 추가되었는지 검사.
        assertEquals(true, item1.addComment("서눅서눅", "마싯다", 5));
        assertEquals(1, item1.getNumberOfComments());
    }
     
    /**
     * SalesItem의 GetComment메소드를 시험한다.
     */
    @Test
    public void testGetComment()
    {
        
        item0.addComment("날라리", "폼 나요!", 5);
        assertEquals("날라리", item0.getComment(0).getAuthor());

    }
    
      /**
     * SalesItem의 Comment들 중에서 가장 표를 많이 받았는지를 시험한다.
     */
    @Test
    public void testFindMostHelpfulComment()
    {
        assertEquals(true, item0.addComment("날라리", "폼 나요!", 5));
        assertEquals(true, item0.addComment("어르신", "흠~ 별로...", 1));
        assertEquals(true, item0.addComment("범생이", "쩐다~~", 4));
        
        item0.upvoteComment(0);
        item0.downvoteComment(0);
        item0.upvoteComment(2);
        assertEquals(1, item0.findMostHelpfulComment().getVoteCount());
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        comment = null;
    }
}
