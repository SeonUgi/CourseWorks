import java.util.ArrayList;
import java.util.Iterator;

/**
 * 온라인상점의 상품을 나타내는 클래스.
 * 
 * NOTE: 아직 완성되지 않은 버젼임. 현재 사용자 코멘트를 다루는 부분만 완성됨.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 0.1 (2011-07-31)
 */
public class SalesItem
{
    private String name;
    private int price;  // in cents
    private ArrayList<Comment> comments;
    
    /**
     * Create a new sales item.
     * @param name 작성자 이름.
     * @param price 센트 단위로 표현한 가격.
     */
    public SalesItem(String name, int price)
    {
        this.name = name;
        this.price = price;
        comments = new ArrayList<Comment>();
    }

    /**
     * Return the name of this item.
     * @return 상품의 이름.
     */

    public String getName()
    {
        return name;
    }
    
    /**
     * Return the price of this item.
     * @return 상품의 가격.
     */
    public int getPrice()
    {
        return price;
    }
    
    /**
     * Return the number of customer comments for this item.
     * @return 이 상품에 달려 있는 Comment의 개수.
     */
    public int getNumberOfComments()
    {
        return comments.size();
    }
    
    /**
     * 이 상품에 Comment를 추가한다. 
     * @param author 작성자 (이 상품에 대한 Comment를 등록한 적이 없어야 한다. 그렇지 않은 경우 Comment 등록이 거부된다.)
     * @param text 평가문
     * @param rating 별점 (1 이상 5 이하이어야 한다. 그렇지 않은 경우 Comment 등록이 거부된다.)
     * @return true (Comment가 성공적으로 추가된 경우), false (Comment가 거부된 경우).
     */
    public boolean addComment(String author, String text, int rating)
    {
        if(ratingInvalid(rating)) {  // reject invalid ratings
            return false;
        }
        
        if(findCommentByAuthor(author) != null) {  // reject mutiple comments by same author
            return false;
        }
        
        comments.add(new Comment(author, text, rating));
        return true;
    }
    
    /**
     * 지정된 인덱스의 Comment를 제거한다. 만약 지정된 인덱스가 유효하지 않다면 아무 일도 하지 않는다.
     */
    public void removeComment(int index)
    {
        if(index >=0 && index < comments.size()) { // if index is valid
            comments.remove(index);
        }
    }
    
    /**
     * 주어진 인덱스의 Comment를 얻는다.
     * @param index 코멘트 번호 (0번부터 시작)
     * @return 주어진 인덱스의 Comment (인덱스가 유효한 경우), null (인덱스가 유효하지 않은 경우)
     */
    public Comment getComment(int index)
    {
        if(index >=0 && index < comments.size()) { // if index is valid
            return comments.get(index);
        }
        return null;
    }
    
    /**
     * 주어진 인덱스의 comment에 upvote한다. 
     * 만약 주어진 인덱스가 유효하지 않다면 아무 일도 하지 않는다.
     * @param index upvote할 코멘트의 인덱스
     */
    public void upvoteComment(int index)
    {
        if(index >=0 && index < comments.size()) { // if index is valid
            comments.get(index).upvote();
        }
    }
    
    /**
     * 주어진 인덱스의 comment에 downvote한다. 
     * 만약 주어진 인덱스가 유효하지 않다면 아무 일도 하지 않는다.
     * @param index downvote할 코멘트의 인덱스
     */
    public void downvoteComment(int index)
    {
        if(index >=0 && index < comments.size()) { // if index is valid
            comments.get(index).downvote();
        }
    }
    
    /**
     * Show all comments on screen. (Currently, for testing purposes: print to the terminal.
     * Modify later for web display.)
     */
    public void showInfo()
    {
        System.out.println("*** " + name + " ***");
        System.out.println("Price: " + priceString(price));
        System.out.println();
        System.out.println("Customer comments:");
        int i = 0;  // 일련번호
        for(Comment comment : comments) {
            System.out.println("(" + i++ + ") -------------------------------------------");
            System.out.println(comment.getFullDetails());
        }
        System.out.println();
        System.out.println("===========================================");
    }
    
    /**
     * 가장 유용한 코멘트를 반환한다.
     * 가장 유용한 코멘트란 voteup(+), votedown(-) 합이 가장 큰 코멘트를 말한다.
     * 만약 최고점수를 갖는 코멘트가 두 개 이상 있을 때는 그 중 임의로 하나를 반환한다.
     * 코멘트가 아직 하나도 없는 경우에는 null을 반환한다.
     * @return vote 득점이 가장 높은 Comment (코멘트가 하나 이상인 경우), null (코멘트가 없는 경우).
     */
    public Comment findMostHelpfulComment()
    {
        Iterator<Comment> it = comments.iterator();
        if (!it.hasNext())
            return null;
        Comment best = it.next();
        while(it.hasNext()) {
            Comment current = it.next();
            if(current.getVoteCount() > best.getVoteCount()) {
                best = current;
            }
        }
        return best;
    }
    
    /**
     * 주어진 rating(별점)이 유효한지 검사한다.
     * 유효한 rating은 [1..5] 범위이다.
     * @param rating 별점
     * @return true (rating이 범위 밖의 숫자인 경우), false (rating이 범위 내 숫자인 경우)
     */
    private boolean ratingInvalid(int rating)
    {
        return rating < 1 || rating > 5;
    }
    
    /**
     * 주어진 이름의 사람이 작성한 Comment를 찾는다.
     * @param author 작성자 이름
     * @return 주어진 이름의 사람이 작성한 Comment (그런 Comment가 이미 있는 경우), null (그런 Comment가 없는 경우).
     */
    private Comment findCommentByAuthor(String author)
    {
        for(Comment comment : comments) {
            if(comment.getAuthor().equals(author)) {
                return comment;
            }
        }
        return null;
    }
    
    /**
     * 주어진 가격을 아래와 같은 형식의 문자열로 반환한다.
     *    $123.45
     * @param price 센트 단위로 표현된 가격
     * @return 가격을 지정된 형식으로 보여주는 문자열
     */
    private String priceString(int price)
    {
        int dollars = price / 100;
        int cents = price - (dollars*100);
        if(cents <= 9) {
            return "$" + dollars + ".0" + cents;  // include zero padding if necessary
        }
        else {
            return "$" + dollars + "." + cents;
        }
    }
}
