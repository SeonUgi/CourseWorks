class ListNode {
	public int num;	// 라인 번호
	public ListNode next;
	public ListNode(int num) {
		this.num = num;
		next = null;
	}
}

public class SimpleLinkedList {
	private ListNode head;
	private ListNode tail;
	public SimpleLinkedList() {
		head = null;
		tail = null;
	}
	
	public void insert(int lineNum){
		ListNode newNode = new ListNode(lineNum);
		ListNode curr = head;
		
		if(curr == null){
			head = newNode;
			tail = head;
		}
		else{
			while(curr.next != null){
				curr= curr.next;
				tail = curr;
			}
			if(tail.num == lineNum)
				return;
			
			tail.next = newNode;
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		ListNode curr = head;
		
		sb.append("["+curr.num);
		while(curr.next != null){
			curr = curr.next;
			sb.append(", "+curr.num);
		}
		sb.append("]");
		return sb.toString();
	}
}
