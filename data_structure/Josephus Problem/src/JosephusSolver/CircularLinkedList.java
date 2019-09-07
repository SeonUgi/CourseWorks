package JosephusSolver;

import java.util.Iterator;

class Node<T> {
	public T item;
	public Node<T> next;
	public Node(T item, Node<T> next) {
		this.item = item;
		this.next = next;
	}
}
class CircularLinkedList<T> implements Iterable<T> {
	private Node<T> tail;
	private int size;
	
	class CircularLinkedListIterator implements Iterator<T>{
		Node<T> curt;
		Node<T> prev = null;
		boolean ch = true;	//hasNext에서 처음 한번은 그냥 넘어가기위해
		
		public CircularLinkedListIterator(Node<T> tail){
			curt=tail.next; //curt가 처음으로 돌아오면 한바퀴 돔
		}
		
		public boolean hasNext() {	
			if(ch){
				ch = false;
				return true;
			}
				
			return curt!=tail.next;
		}

		public T next() {
			T item = curt.item;
			prev(curt);	//현제 노드 prev에 저장
			curt = curt.next;
			return item;
		}
		
		public void prev(Node<T> curt){
			prev = curt;
		}
		
		public void remove(){	//현제 curt노드를 지운다
			prev.next = curt.next;
			size--;
		}
	}  

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
	
	public int size(){
		return size;
	}
	@Override
	public Iterator<T> iterator(){
		return new CircularLinkedListIterator(tail);
	}
}