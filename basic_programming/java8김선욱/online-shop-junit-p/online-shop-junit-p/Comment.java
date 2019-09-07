
/**
 * 온라인 상점에서 파는 상품에 대한 상품평을 나타내는 클래스.
 * 상품평은 작성자, 평가문, 별점으로 구성된다. 
 * 다른 사용자들은 이 상품평이 유용한지 여부를 버튼 클릭으로 표현할 수 있다.
 * 유용하면 upvote, 그렇지 않으면 downvote이다.
 * upvote - downvote가 이 상품평에 대한 유용성을 나타낸다.
 * upvote보다 downvote가 많으면 유용성이 음수이다.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011-07-31
 */
public class Comment
{
    private String author;
    private String text;
    private int rating;
    private int votes;

    /**
     * 구성자
     * @param author 작성자
     * @param text 평가문
     * @param rating 별점 (1점 - 5점)
     */
    public Comment(String author, String text, int rating)
    {
        this.author = author;
        this.text = text;
        this.rating = rating;
        votes = 0;
    }

    /**
     * 이 상품평이 유용하다고 투표한다. 
     * "이 상품평이 도움이 되었나요?" 질문에 "예"를 클릭하면 이 메소드가 실행된다.
     */
    public void upvote()
    {
        votes++;
    }

    /**
     * 이 상품평이 유용하지 않다고 투표한다. 
     * "이 상품평이 도움이 되었나요?" 질문에 "아니오"를 클릭하면 이 메소드가 실행된다.
     */
    public void downvote()
    {
        votes--;
    }
    
    /**
     * Return the author(작성자) of this comment.
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * Return the rating (별점) of this comment.
     */
    public int getRating()
    {
        return rating;
    }
    
    /**
     * Return the vote count (upvotes 빼기 downvotes).
     */
    public int getVoteCount()
    {
        return votes;
    }
    
    /**
     * Return the full text and details of the comment, including 
     * the comment text, author and rating.
     */
    public String getFullDetails()
    {
        String details = "Rating: " + "*****".substring(0,rating) + "    "
                         + "By: " + author + "\n"
                         + "    " + text + "\n";
        // Note: 'votes' is currently included for testing purposes only. In the final
        // application, this will nt be shown. Instead, the vote count will be used to 
        // select and order the comments on screen.
        details += "(Voted as helpful: " + votes + ")\n";
        return details;
    }
}
