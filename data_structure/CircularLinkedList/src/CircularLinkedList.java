import java.util.Iterator;

class Node<T> {
	public T item;
	public Node<T> next;
	public Node(T item, Node<T> next) {
		this.item = item;
		this.next = next;
	}
}

class CircularIterator<T> implements Iterator<T>{	//원형연결구조 반복자 클래스
	private Node<T> tail;
	private Node<T> scanPos;
	
	public CircularIterator (Node<T> tail) {
		this.tail=tail;
		scanPos=tail.next;
	}
	
	
	public boolean hasNext(){	//다시 처음 노드에 도달했을 시 true
		return scanPos==tail.next;
	}
	
	public T next(){	//다음 노드 탐색
		T item = scanPos.item;
		scanPos = scanPos.next;
		return item;
	}
}

class CircularLinkedList<T> {
	public Node<T> tail;
	private int size;
	
	public boolean isEmpty() {
		return size == 0;
	}
	public void insert(T item) {
		if (isEmpty()) {
			tail = new Node<T>(item, null);
			tail.next = tail;
		}
		else {
			Node<T> tmp = new Node<T>(item, tail.next);
			tail.next = tmp;
			tail = tmp;
		}
		size++;
	}
	public String toString() {
		if (isEmpty())
			return "";
		Node<T> tmp = tail.next;
		StringBuilder sb = new StringBuilder(tmp.item + " ");
		while (tmp != tail) {
			tmp = tmp.next;
			sb.append(tmp.item + " ");
		}
		return sb.toString();
	}
	public int size() {
		return size;
	}
	
	public CircularIterator<T> iterator() {				// CircularIterator를 만드는 메소드
		return new CircularIterator<T>(tail);
	}
	// 구현 해야 할 메소드 
	public boolean remove(T item){
		Node<T> prev =null;
		Node<T> curr =tail.next;			//리스트의 처음
		
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		
		if(curr.item.equals(item)){			//리스트의 제일처음에서 찾을 시
			tail.next =curr.next;
			--size;
			return true;
		}

		while(curr != tail){							
			if(curr.item.equals(item))
				break;
			else{
				prev = curr;			
				curr = curr.next;
			}
		}
		
		if(curr==tail){					//리스트 끝 도달
			if(curr.item.equals(item)){
				tail = prev;
				tail.next = curr.next;
				--size;
				return true;
			}
			
			else 
				return false;
		}
		
		prev.next=curr.next;
		--size;
		return true;
	}
	
	public boolean search(T item){
		Node<T> tmp = tail.next;	//리스트의 제일 처음
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		
		while(tmp!=tail){	//탐색
			if(tmp.item.equals(item))
				return true;
			else
				tmp=tmp.next;
		}
		
		if(tmp==tail){					//리스트 끝 도달
			if(tmp.item.equals(item))
				return true;
		}
		
		return false;
	}
}