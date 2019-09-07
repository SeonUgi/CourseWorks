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
		boolean ch = true;	//hasNext���� ó�� �ѹ��� �׳� �Ѿ������
		
		public CircularLinkedListIterator(Node<T> tail){
			curt=tail.next; //curt�� ó������ ���ƿ��� �ѹ��� ��
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
			prev(curt);	//���� ��� prev�� ����
			curt = curt.next;
			return item;
		}
		
		public void prev(Node<T> curt){
			prev = curt;
		}
		
		public void remove(){	//���� curt��带 �����
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
		Node<T> curr =tail.next;			//����Ʈ�� ó��
		
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		
		if(curr.item.equals(item)){			//����Ʈ�� ����ó������ ã�� ��
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
		
		if(curr==tail){					//����Ʈ �� ����
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
		Node<T> tmp = tail.next;	//����Ʈ�� ���� ó��
		if(isEmpty())
			throw new java.util.NoSuchElementException();
		
		while(tmp!=tail){	//Ž��
			if(tmp.item.equals(item))
				return true;
			else
				tmp=tmp.next;
		}
		
		if(tmp==tail){					//����Ʈ �� ����
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